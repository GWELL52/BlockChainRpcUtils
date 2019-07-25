package com.gwell.rpc.eth.call.method;

import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.model.request.SendTransactionParams;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
public class TokenMethod {
  private Web3j web3j;

  public static TokenMethod build(Web3j web3j) {
    TokenMethod instance = getInstance();
    instance.setWeb3j(web3j);
    return instance;
  }

  private TokenMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static TokenMethod instance = new TokenMethod();
  }

  private static TokenMethod getInstance() {
    return Holder.instance;
  }

  private void checkError(Response result) {
    if (result.hasError()) {
      ResponseException.create(result.getError().getMessage());
    }
  }

  /** 格式化发送合约交易的 data */
  public String getTransactionData(SendTransactionParams params) {
    if (StringUtils.isBlank(params.getContractAddress())) {
      return "0x";
    }
    String methodName = "transfer";
    if (StringUtils.isNotBlank(params.getMethod())) {
      methodName = params.getMethod();
    }

    List<TypeReference<?>> outputParameters = new ArrayList<>();
    TypeReference<Bool> typeReference = new TypeReference<Bool>() {};
    outputParameters.add(typeReference);

    BigDecimal amount = params.getAmount();
    BigDecimal unit = params.getUnit();
    if (null == unit || BigDecimal.ZERO.compareTo(unit) >= 0) {
      unit = getTokenUnit(params.getContractAddress());
      params.setUnit(unit);
    }
    Address toAddress = new Address(params.getToAddress());
    Uint256 tokenValue = new Uint256(amount.multiply(unit).toBigInteger());

    List<Type> inputParameters = new ArrayList<>();
    inputParameters.add(toAddress);
    inputParameters.add(tokenValue);

    Function function = new Function(methodName, inputParameters, outputParameters);
    return FunctionEncoder.encode(function);
  }

  /** 获取合约精度 */
  @SneakyThrows
  public Integer getTokenDecimal(String contractAddress) {
    String methodName = "decimals";

    List<Type> inputParameters = new ArrayList<>();
    List<TypeReference<?>> outputParameters = new ArrayList<>();
    outputParameters.add(new TypeReference<Uint8>() {});

    Function function = new Function(methodName, inputParameters, outputParameters);
    String data = FunctionEncoder.encode(function);

    Transaction transaction =
        Transaction.createEthCallTransaction(contractAddress, contractAddress, data);

    EthCall result = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
    checkError(result);
    List<Type> results =
        FunctionReturnDecoder.decode(result.getValue(), function.getOutputParameters());
    if (results.size() < 1) {
      throw new RuntimeException("合约不存在!");
    }
    Integer decimal = Integer.parseInt(results.get(0).getValue().toString());
    return decimal;
  }

  /** 获取合约Unit(100000......) */
  public BigDecimal getTokenUnit(String contractAddress) {
    return getTokenUnit(getTokenDecimal(contractAddress));
  }

  /** 转换成unit(100000......) */
  public static BigDecimal getTokenUnit(Integer decimal) {
    BigDecimal unit = BigDecimal.TEN.pow(decimal);
    return unit;
  }

  /** 转换成精度 */
  public static Integer getTokenDecimal(BigDecimal unit) {
    BigDecimal num = BigDecimal.ONE.divide(unit);
    return num.scale();
  }
}

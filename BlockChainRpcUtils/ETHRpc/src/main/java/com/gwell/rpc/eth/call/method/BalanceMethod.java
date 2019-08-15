package com.gwell.rpc.eth.call.method;

import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.call.override.Web3j;
import com.gwell.rpc.eth.model.request.BalanceParams;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Setter
public class BalanceMethod {
  private Web3j web3j;
  private TokenMethod tokenMethod;

  public static BalanceMethod build(Web3j web3j) {
    BalanceMethod instance = new BalanceMethod();
    instance.setWeb3j(web3j);
    instance.setTokenMethod(TokenMethod.build(web3j));
    return instance;
  }

  private BalanceMethod() {}

  private void checkError(Response result) {
    if (result.hasError()) {
      ResponseException.create(result.getError().getMessage());
    }
  }

  /** 获取余额 */
  public BigDecimal getBalance(BalanceParams params) {
    if (StringUtils.isBlank(params.getContractAddress())) {
      return getEthBalance(params.getAddress());
    }
    return getTokenBalance(params);
  }

  /** 获取ETH余额 */
  @SneakyThrows
  private BigDecimal getEthBalance(String address) {
    EthGetBalance result = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
    checkError(result);
    BigInteger balance = result.getBalance();
    return Convert.fromWei(new BigDecimal(balance), Convert.Unit.ETHER);
  }

  /** 获取代币余额 */
  @SneakyThrows
  private BigDecimal getTokenBalance(BalanceParams params) {
    String methodName = "balanceOf";

    String address = params.getAddress();
    String contractAddress = params.getContractAddress();
    BigDecimal unit = params.getUnit();
    if (null == params.getUnit() || BigDecimal.ZERO.compareTo(unit) <= 0) {
      unit = tokenMethod.getTokenUnit(contractAddress);
    }

    List<TypeReference<?>> outputParameters = new ArrayList<>();
    outputParameters.add(new TypeReference<Uint256>() {});

    List<Type> inputParameters = new ArrayList<>();
    inputParameters.add(new Address(address));

    Function function = new Function(methodName, inputParameters, outputParameters);
    String data = FunctionEncoder.encode(function);

    Transaction transaction = Transaction.createEthCallTransaction(address, contractAddress, data);

    EthCall result = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
    checkError(result);
    List<Type> results =
        FunctionReturnDecoder.decode(result.getValue(), function.getOutputParameters());
    BigInteger balanceValue = (BigInteger) results.get(0).getValue();

    BigDecimal balance =
        new BigDecimal(balanceValue)
            .divide(unit, TokenMethod.getTokenDecimal(unit), BigDecimal.ROUND_DOWN);
    return balance;
  }
}

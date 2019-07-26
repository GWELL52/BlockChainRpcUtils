package com.gwell.rpc.eth.call.method;

import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.call.override.Web3j;
import com.gwell.rpc.eth.model.request.SendTransactionParams;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Convert;

import java.math.BigInteger;

@Setter
public class BaseMethod {
  private Web3j web3j;
  private TokenMethod tokenMethod;

  public static BaseMethod build(Web3j web3j) {
    BaseMethod instance = getInstance();
    instance.setWeb3j(web3j);
    instance.setTokenMethod(TokenMethod.build(web3j));
    return instance;
  }

  private BaseMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static BaseMethod instance = new BaseMethod();
  }

  private static BaseMethod getInstance() {
    return Holder.instance;
  }

  private void checkError(Response result) {
    if (result.hasError()) {
      ResponseException.create(result.getError().getMessage());
    }
  }

  /** 返回当前最高区块 */
  @SneakyThrows
  public BigInteger getBlockNumber() {
    EthBlockNumber result = web3j.ethBlockNumber().send();
    checkError(result);
    return result.getBlockNumber();
  }

  /** 获取区块信息 */
  @SneakyThrows
  public EthBlock.Block getBlockInfo(BigInteger blockNumber, boolean returnTransactionInfo) {
    EthBlock result =
        web3j
            .ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), returnTransactionInfo)
            .send();
    checkError(result);
    return result.getBlock();
  }

  /** 获取GasLimit */
  @SneakyThrows
  public BigInteger getGasLimit(SendTransactionParams params) {
    org.web3j.protocol.core.methods.request.Transaction transaction;
    if (StringUtils.isBlank(params.getContractAddress())) {
      transaction =
          org.web3j.protocol.core.methods.request.Transaction.createEtherTransaction(
              params.getFromAddress(), null, null, null, params.getToAddress(), BigInteger.ZERO);
    } else {
      String data;
      if (StringUtils.isNotBlank(params.getData())) {
        data = params.getData();
      } else {
        data = tokenMethod.getTransactionData(params);
      }
      transaction =
          org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction(
              params.getFromAddress(), null, null, null, params.getContractAddress(), data);
    }
    EthEstimateGas result = web3j.ethEstimateGas(transaction).send();
    checkError(result);
    return result.getAmountUsed();
  }

  /** 获取gasPrice 单位:wei */
  @SneakyThrows
  public BigInteger getGasPrice() {
    if (web3j.getBlockChain().equals(BlockChainEnum.VNS)) {
      return Convert.Unit.GWEI.getWeiFactor().toBigInteger();
    }

    EthGasPrice result = web3j.ethGasPrice().send();
    checkError(result);
    return result.getGasPrice();
  }

  /** 获取地址的 nonce */
  @SneakyThrows
  public BigInteger getNonce(String address) {
    EthGetTransactionCount result =
        web3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).sendAsync().get();
    checkError(result);
    return result.getTransactionCount();
  }
}

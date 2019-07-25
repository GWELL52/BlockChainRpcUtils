package com.gwell.rpc.eth.call.method;

import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.model.response.ETHTransactionInfo;
import lombok.Setter;
import lombok.SneakyThrows;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.*;

import java.math.BigInteger;
import java.util.Date;

@Setter
public class TransactionInfoMethod {
  private Web3j web3j;
  private BaseMethod baseMethod;

  public static TransactionInfoMethod build(Web3j web3j) {
    TransactionInfoMethod instance = getInstance();
    instance.setWeb3j(web3j);
    instance.setBaseMethod(BaseMethod.build(web3j));
    return instance;
  }

  private TransactionInfoMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static TransactionInfoMethod instance = new TransactionInfoMethod();
  }

  private static TransactionInfoMethod getInstance() {
    return Holder.instance;
  }

  private void checkError(Response result) {
    if (result.hasError()) {
      ResponseException.create(result.getError().getMessage());
    }
  }

  /** 已确认的交易详情 */
  @SneakyThrows
  public TransactionReceipt getTransactionReceipt(String hash) {
    EthGetTransactionReceipt result = web3j.ethGetTransactionReceipt(hash).send();
    checkError(result);
    return result.getTransactionReceipt().orElse(null);
  }

  /** 根据Hash获取交易 */
  @SneakyThrows
  public Transaction getTransactionByHash(String hash) {
    EthTransaction result = web3j.ethGetTransactionByHash(hash).send();
    checkError(result);
    return result.getTransaction().orElse(null);
  }

  /** 获取交易详情 */
  public ETHTransactionInfo getTransactionAllInfo(String hash, boolean getTransferTime) {
    ETHTransactionInfo result =
        new ETHTransactionInfo(getTransactionByHash(hash), getTransactionReceipt(hash));
    if (!result.isPending() && getTransferTime) {
      EthBlock.Block block = baseMethod.getBlockInfo(result.getBlockNumber(), false);
      // 完成交易时间
      Date transferTime =
          new Date(block.getTimestamp().multiply(BigInteger.valueOf(1000)).longValue());
      result.setTransferTime(transferTime);
    }
    return result;
  }
}

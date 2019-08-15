package com.gwell.rpc.eth.call.method;

import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.call.override.Web3j;
import com.gwell.rpc.eth.model.response.ETHTransactionInfo;
import lombok.Setter;
import lombok.SneakyThrows;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.*;

import java.math.BigInteger;
import java.util.Date;

@Setter
public class TransactionInfoMethod {
  private Web3j web3j;
  private BaseMethod baseMethod;

  public static TransactionInfoMethod build(Web3j web3j) {
    TransactionInfoMethod instance = new TransactionInfoMethod();
    instance.setWeb3j(web3j);
    instance.setBaseMethod(BaseMethod.build(web3j));
    return instance;
  }

  private TransactionInfoMethod() {}

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
  @SneakyThrows
  public ETHTransactionInfo getTransactionAllInfo(String hash, boolean getTransferTime) {
    EthTransaction ethTransaction = web3j.ethGetTransactionByHash(hash).send();
    Transaction transaction = null;
    if (!ethTransaction.hasError()) {
      transaction = ethTransaction.getTransaction().orElse(null);
    }

    EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(hash).send();
    TransactionReceipt transactionReceipt = null;
    if (!ethGetTransactionReceipt.hasError()) {
      transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().orElse(null);
    }

    ETHTransactionInfo result = new ETHTransactionInfo(transaction, transactionReceipt);
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

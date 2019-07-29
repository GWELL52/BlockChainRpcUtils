package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.SendTransaction;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

public class TransferMethod extends SuperMethod {

  public static TransferMethod build(Connection connection, BlockChainEnum blockChain) {
    TransferMethod instance = getInstance();
    instance.init(connection, blockChain);
    return instance;
  }

  private TransferMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static TransferMethod instance = new TransferMethod();
  }

  private static TransferMethod getInstance() {
    return Holder.instance;
  }

  /**
   * 转账给某个地址
   *
   * @param toAddress 接收地址
   * @param amount 数量
   */
  public SendTransaction sendToAddress(String toAddress, BigDecimal amount) {
    return Request.rpc(
            connection, "sendtoaddress", Arrays.asList(toAddress, amount), SendTransaction.class)
        .send();
  }

  /** 发送已经签名的交易数据 */
  public SendTransaction sendRawTransaction(String rawData) {
    return Request.rpc(
            connection,
            "sendrawtransaction",
            Collections.singletonList(rawData),
            SendTransaction.class)
        .send();
  }
}

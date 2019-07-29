package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.GetAddressAllHash;
import com.gwell.rpc.btc.model.response.GetRawTransactionInfo;
import com.gwell.rpc.btc.model.response.GetTransactionInfo;
import com.gwell.rpc.btc.model.response.ListTransactions;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;

public class TransactionInfoMethod extends SuperMethod {

  public static TransactionInfoMethod build(Connection connection, BlockChainEnum blockChain) {
    TransactionInfoMethod instance = getInstance();
    instance.init(connection, blockChain);
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

  /**
   * 获取交易信息集合
   *
   * @param account 账号 null=*
   * @param limit 限制结果大小 default=1000
   * @param index 起始下标 default=0
   */
  public ListTransactions listTransactions(String account, Long limit, Long index) {
    if (StringUtils.isBlank(account)) {
      account = "*";
    }
    if (limit == null || limit > 1000) {
      limit = 1000L;
    }
    if (index == null || index < 0) {
      index = 0L;
    }
    return Request.rpc(
            connection,
            "listtransactions",
            Arrays.asList(account, limit, index),
            ListTransactions.class)
        .send();
  }

  /** 根据哈希获取交易信息 */
  public GetTransactionInfo getTransactionInfo(String hash) {
    return Request.rpc(
            connection, "gettransaction", Collections.singletonList(hash), GetTransactionInfo.class)
        .send();
  }

  /**
   * 根据哈希获取签名数据
   *
   * @param hash 交易哈希
   */
  public GetRawTransactionInfo getRawTransactionInfo(String hash) {
    return Request.rpc(
            connection, "getrawtransaction", Arrays.asList(hash, 1), GetRawTransactionInfo.class)
        .send();
  }

  /** 根据地址获取所有的交易hash */
  public GetAddressAllHash getAddressAllHash(String address) {
    return Request.rpc(
            connection,
            "getaddresstxids",
            Collections.singletonList(address),
            GetAddressAllHash.class)
        .send();
  }
}

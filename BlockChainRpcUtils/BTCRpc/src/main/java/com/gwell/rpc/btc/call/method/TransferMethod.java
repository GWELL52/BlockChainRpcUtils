package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.*;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

public class TransferMethod {

  private Connection connection;

  private TransferMethod(Connection connection) {
    this.connection = connection;
  }

  public static TransferMethod build(Connection connection) {
    return new TransferMethod(connection);
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
            connection,
            "gettransaction",
            Collections.singletonList(hash),
            GetTransactionInfo.class)
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

  /** 根据地址获取未消费的交易输出 unspent tx outputs(UTXOs) */
  public GetAddressUTXOs getAddressUnspentOutputs(String address) {
    return Request.rpc(
            connection,
            "getaddressutxos",
            Collections.singletonList(address),
            GetAddressUTXOs.class)
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

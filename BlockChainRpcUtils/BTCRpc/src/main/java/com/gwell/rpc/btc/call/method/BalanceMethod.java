package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.GetAddressBalance;
import com.gwell.rpc.btc.model.response.GetAddressUTXOs;
import com.gwell.rpc.btc.model.response.GetBalance;
import com.gwell.rpc.btc.model.response.GetReceivedInfo;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import lombok.NonNull;

import java.util.Collections;

public class BalanceMethod extends SuperMethod {

  public static BalanceMethod build(Connection connection, BlockChainEnum blockChain) {
    BalanceMethod instance = new BalanceMethod();
    instance.init(connection, blockChain);
    return instance;
  }

  private BalanceMethod() {}

  /**
   * 根据账号获取余额
   *
   * @param account 账号
   */
  public GetBalance getBalance(String account) {
    return Request.rpc(
            connection,
            "getbalance",
            account == null ? null : Collections.singletonList(account),
            GetBalance.class)
        .send();
  }

  /**
   * 根据地址获取余额
   *
   * @param address 地址
   */
  public GetAddressBalance getAddressBalance(@NonNull String address) {
    GetAddressBalance result =
        Request.rpc(
                connection,
                "getaddressbalance",
                Collections.singletonList(address),
                GetAddressBalance.class)
            .send();
    result.setChain(blockChain);
    return result;
  }

  /**
   * 根据账号获取总接收数值
   *
   * @param account 账号
   */
  public GetReceivedInfo getReceivedByAccount(String account) {
    return Request.rpc(
            connection,
            "getreceivedbyaccount",
            Collections.singletonList(account),
            GetReceivedInfo.class)
        .send();
  }

  /**
   * 根据地址获取总接收数值
   *
   * @param address 地址(钱包管理的地址)
   */
  public GetReceivedInfo getReceivedByAddress(String address) {
    return Request.rpc(
            connection,
            "getreceivedbyaddress",
            Collections.singletonList(address),
            GetReceivedInfo.class)
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
}

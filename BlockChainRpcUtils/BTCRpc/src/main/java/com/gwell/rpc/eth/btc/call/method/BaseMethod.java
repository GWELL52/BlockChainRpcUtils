package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.btc.model.response.*;
import com.gwell.rpc.eth.common.model.Connection;
import com.gwell.rpc.eth.common.model.Request;
import lombok.NonNull;

import java.util.Collections;

public class BaseMethod {
  private Connection connection;

  private BaseMethod(Connection connection) {
    this.connection = connection;
  }

  public static BaseMethod build(Connection connection) {
    return new BaseMethod(connection);
  }

  /** 获取服务器版本余额信息 */
  public BtcGetInfo getInfo() {
    return Request.rpc(connection, "getinfo", null, BtcGetInfo.class).send();
  }

  /**
   * 创建地址
   *
   * @param account 备注账号名
   */
  public BtcGetNewAddress getNewAddress(String account) {
    return Request.rpc(
            connection, "getnewaddress", Collections.singletonList(account), BtcGetNewAddress.class)
        .send();
  }

  /**
   * 根据账号获取余额
   *
   * @param account 账号
   */
  public BtcGetBalance getBalance(String account) {
    return Request.rpc(
            connection,
            "getbalance",
            account == null ? null : Collections.singletonList(account),
            BtcGetBalance.class)
        .send();
  }

  /**
   * 根据地址获取余额
   *
   * @param address 地址
   */
  public BtcGetAddressBalance getAddressBalance(@NonNull String address) {
    return Request.rpc(
            connection,
            "getaddressbalance",
            Collections.singletonList(address),
            BtcGetAddressBalance.class)
        .send();
  }

  /**
   * 获取同账号下的地址列表
   *
   * @param account 账号
   * @return List 地址集合
   */
  public BtcGetAddresses getAddressesByAccount(@NonNull String account) {
    return Request.rpc(
            connection,
            "getaddressesbyaccount",
            Collections.singletonList(account),
            BtcGetAddresses.class)
        .send();
  }
}

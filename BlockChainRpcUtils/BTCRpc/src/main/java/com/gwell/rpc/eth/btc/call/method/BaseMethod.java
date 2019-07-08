package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.btc.model.response.BtcGeNewAddress;
import com.gwell.rpc.eth.btc.model.response.BtcGetAddressBalance;
import com.gwell.rpc.eth.btc.model.response.BtcGetBalance;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
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

  public BtcGetInfo getInfo() {
    return Request.rpc(connection, "getinfo", null, BtcGetInfo.class).send();
  }

  public BtcGeNewAddress newAddress(String account) {
    return Request.rpc(
            connection, "getnewaddress", Collections.singletonList(account), BtcGeNewAddress.class)
        .send();
  }

  public BtcGetBalance getBalance(String account) {
    return Request.rpc(
            connection,
            "getbalance",
            account == null ? null : Collections.singletonList(account),
            BtcGetBalance.class)
        .send();
  }

  public BtcGetAddressBalance getAddressBalance(@NonNull String address) {
    return Request.rpc(
            connection,
            "getaddressbalance",
            Collections.singletonList(address),
            BtcGetAddressBalance.class)
        .send();
  }
}

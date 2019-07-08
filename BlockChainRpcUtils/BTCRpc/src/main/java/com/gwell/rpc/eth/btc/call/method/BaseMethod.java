package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.btc.model.response.BtcGeNewAddress;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
import com.gwell.rpc.eth.common.model.Connection;
import com.gwell.rpc.eth.common.model.Request;

import java.util.Arrays;

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
    return Request.rpc(connection, "getnewaddress", Arrays.asList(account), BtcGeNewAddress.class)
        .send();
  }
}

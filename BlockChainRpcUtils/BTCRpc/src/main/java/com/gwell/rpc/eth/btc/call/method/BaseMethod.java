package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.btc.model.response.BtcInfo;
import com.gwell.rpc.eth.common.model.Connection;
import com.gwell.rpc.eth.common.model.Request;

public class BaseMethod {
  private Connection connection;

  private BaseMethod(Connection connection) {
    this.connection = connection;
  }

  public static BaseMethod build(Connection connection) {
    return new BaseMethod(connection);
  }

  public BtcInfo getInfo() {
    return Request.rpc(connection, "getinfo", null, BtcInfo.class).send();
  }
}

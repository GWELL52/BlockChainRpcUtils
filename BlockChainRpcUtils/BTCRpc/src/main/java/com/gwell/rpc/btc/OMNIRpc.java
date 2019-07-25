package com.gwell.rpc.btc;

import com.gwell.rpc.btc.call.Call;
import com.gwell.rpc.common.model.Connection;

public class OMNIRpc extends Call {

  public static OMNIRpc build(String url) {
    return OMNIRpc.build(Connection.builder(url).build());
  }

  public static OMNIRpc build(String ip, Integer port, String username, String password) {
    return OMNIRpc.build(Connection.builder(ip, port, username, password).build());
  }

  public static OMNIRpc build(Connection connection) {
    return new OMNIRpc(connection);
  }

  private OMNIRpc(Connection connection) {
    super(connection);
  }
}

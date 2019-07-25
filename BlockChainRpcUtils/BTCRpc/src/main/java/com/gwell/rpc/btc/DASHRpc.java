package com.gwell.rpc.btc;

import com.gwell.rpc.btc.call.Call;
import com.gwell.rpc.common.model.Connection;

public class DASHRpc extends Call {

  public static DASHRpc build(String url) {
    return DASHRpc.build(Connection.builder(url).build());
  }

  public static DASHRpc build(String ip, Integer port, String username, String password) {
    return DASHRpc.build(Connection.builder(ip, port, username, password).build());
  }

  public static DASHRpc build(Connection connection) {
    return new DASHRpc(connection);
  }

  private DASHRpc(Connection connection) {
    super(connection);
  }
}

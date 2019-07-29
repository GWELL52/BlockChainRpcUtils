package com.gwell.rpc.btc;

import com.gwell.rpc.btc.call.Call;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

public class BCHRpc extends Call {

  public static BCHRpc build(String url) {
    return BCHRpc.build(Connection.builder(url).build());
  }

  public static BCHRpc build(String ip, Integer port, String username, String password) {
    return BCHRpc.build(Connection.builder(ip, port, username, password).build());
  }

  public static BCHRpc build(Connection connection) {
    return new BCHRpc(connection);
  }

  private BCHRpc(Connection connection) {
    super(connection, BlockChainEnum.BCH);
  }
}

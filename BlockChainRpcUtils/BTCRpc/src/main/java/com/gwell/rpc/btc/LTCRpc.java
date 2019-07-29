package com.gwell.rpc.btc;

import com.gwell.rpc.btc.call.Call;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

public class LTCRpc extends Call {

  public static LTCRpc build(String url) {
    return LTCRpc.build(Connection.builder(url).build());
  }

  public static LTCRpc build(String ip, Integer port, String username, String password) {
    return LTCRpc.build(Connection.builder(ip, port, username, password).build());
  }

  public static LTCRpc build(Connection connection) {
    return new LTCRpc(connection);
  }

  private LTCRpc(Connection connection) {
    super(connection, BlockChainEnum.LTC);
  }
}

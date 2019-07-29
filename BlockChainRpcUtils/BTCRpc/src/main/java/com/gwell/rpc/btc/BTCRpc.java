package com.gwell.rpc.btc;

import com.gwell.rpc.btc.call.Call;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

public class BTCRpc extends Call {

  public static BTCRpc build(String url) {
    return BTCRpc.build(Connection.builder(url).build());
  }

  public static BTCRpc build(String ip, Integer port, String username, String password) {
    return BTCRpc.build(Connection.builder(ip, port, username, password).build());
  }

  public static BTCRpc build(Connection connection) {
    return new BTCRpc(connection);
  }

  private BTCRpc(Connection connection) {
    super(connection, BlockChainEnum.BTC);
  }
}

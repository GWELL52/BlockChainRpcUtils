package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

public class SuperMethod {
  public Connection connection;
  public BlockChainEnum blockChain;

  public void init(Connection connection, BlockChainEnum blockChain) {
    this.connection = connection;
    this.blockChain = blockChain;
  }
}

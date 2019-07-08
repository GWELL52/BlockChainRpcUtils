package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.common.model.Connection;

public class TransferMethod {

  private Connection connection;

  private TransferMethod(Connection connection) {
    this.connection = connection;
  }

  public static TransferMethod build(Connection connection) {
    return new TransferMethod(connection);
  }
}

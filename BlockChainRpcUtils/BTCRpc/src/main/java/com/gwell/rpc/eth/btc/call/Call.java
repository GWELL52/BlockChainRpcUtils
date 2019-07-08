package com.gwell.rpc.eth.btc.call;

import com.gwell.rpc.eth.btc.call.method.BaseMethod;
import com.gwell.rpc.eth.btc.call.method.TransferMethod;
import com.gwell.rpc.eth.btc.model.response.BtcInfo;
import com.gwell.rpc.eth.common.model.Connection;

public class Call {
  private BaseMethod baseMethod;
  private TransferMethod transferMethod;

  public Call(Connection connection) {
    this.baseMethod = BaseMethod.build(connection);
    this.transferMethod = TransferMethod.build(connection);
  }

  public BtcInfo getInfo() {
    return baseMethod.getInfo();
  }
}

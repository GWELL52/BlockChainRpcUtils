package com.gwell.rpc.eth.btc.call;

import com.gwell.rpc.eth.btc.call.method.BaseMethod;
import com.gwell.rpc.eth.btc.call.method.TransferMethod;
import com.gwell.rpc.eth.btc.model.response.BtcGeNewAddress;
import com.gwell.rpc.eth.btc.model.response.BtcGetAddressBalance;
import com.gwell.rpc.eth.btc.model.response.BtcGetBalance;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
import com.gwell.rpc.eth.common.model.Connection;

public class Call {
  private BaseMethod baseMethod;
  private TransferMethod transferMethod;

  public Call(Connection connection) {
    this.baseMethod = BaseMethod.build(connection);
    this.transferMethod = TransferMethod.build(connection);
  }

  public BtcGetInfo getInfo() {
    return baseMethod.getInfo();
  }

  public BtcGeNewAddress newAddress(String account) {
    return baseMethod.newAddress(account);
  }

  public BtcGetBalance getBalance(String account) {
    return baseMethod.getBalance(account);
  }

  public BtcGetAddressBalance getAddressBalance(String address) {
    return baseMethod.getAddressBalance(address);
  }
}

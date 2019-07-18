package com.gwell.rpc.eth.btc.call;

import com.gwell.rpc.eth.btc.call.method.BaseMethod;
import com.gwell.rpc.eth.btc.call.method.TransferMethod;
import com.gwell.rpc.eth.btc.model.response.*;
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

  public BtcGetNewAddress getNewAddress(String account) {
    return baseMethod.getNewAddress(account);
  }

  public BtcGetBalance getBalance(String account) {
    return baseMethod.getBalance(account);
  }

  public BtcGetAddressBalance getAddressBalance(String address) {
    return baseMethod.getAddressBalance(address);
  }

  public BtcGetAddresses getAddressesByAccount(String account) {
    return baseMethod.getAddressesByAccount(account);
  }

  public BtcListTransactions listTransactions(String account, Long limit, Long index) {
    return transferMethod.listTransactions(account, limit, index);
  }
}

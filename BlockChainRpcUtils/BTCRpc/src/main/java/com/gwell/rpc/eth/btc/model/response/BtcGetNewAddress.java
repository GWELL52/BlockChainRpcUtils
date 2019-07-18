package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

public class BtcGetNewAddress extends Response<String> {
  public BtcGetNewAddress() {}

  public String getAddress() {
    return this.getRawResult();
  }
}

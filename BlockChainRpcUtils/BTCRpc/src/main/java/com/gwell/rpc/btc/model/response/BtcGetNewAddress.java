package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class BtcGetNewAddress extends Response<String> {
  public BtcGetNewAddress() {}

  public String getAddress() {
    return this.getRawResult();
  }
}

package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

public class BtcGeNewAddress extends Response<String> {
  public BtcGeNewAddress() {}

  public String getAddress() {
    return this.getRawResult();
  }
}

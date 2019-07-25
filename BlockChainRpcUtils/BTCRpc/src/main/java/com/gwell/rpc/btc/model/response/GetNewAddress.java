package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class GetNewAddress extends Response<String> {
  public GetNewAddress() {}

  public String getAddress() {
    return this.getRawResult();
  }
}

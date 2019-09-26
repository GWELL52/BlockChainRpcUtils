package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class DumpPrivateKey extends Response<String> {
  public DumpPrivateKey() {}

  public String getPrivateKey() {
    return this.getRawResult();
  }
}

package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class SendTransaction extends Response<String> {
  public SendTransaction() {}

  public String getHash() {
    return this.getRawResult();
  }
}

package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class BtcSendTransaction extends Response<String> {
  public BtcSendTransaction() {}

  public String getHash() {
    return this.getRawResult();
  }
}

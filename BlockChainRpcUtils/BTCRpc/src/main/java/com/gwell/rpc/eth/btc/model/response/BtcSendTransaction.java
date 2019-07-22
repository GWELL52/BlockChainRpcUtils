package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

public class BtcSendTransaction extends Response<String> {
  public BtcSendTransaction() {}

  public String getHash() {
    return this.getRawResult();
  }
}

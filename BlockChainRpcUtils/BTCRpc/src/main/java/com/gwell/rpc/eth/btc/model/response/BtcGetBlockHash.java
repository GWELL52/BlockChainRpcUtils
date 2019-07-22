package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

public class BtcGetBlockHash extends Response<String> {
  public BtcGetBlockHash() {}

  public String getBlockHash() {
    return this.getRawResult();
  }
}

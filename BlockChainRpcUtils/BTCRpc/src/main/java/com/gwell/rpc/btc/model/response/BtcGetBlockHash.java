package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class BtcGetBlockHash extends Response<String> {
  public BtcGetBlockHash() {}

  public String getBlockHash() {
    return this.getRawResult();
  }
}

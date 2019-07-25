package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

public class GetBlockHash extends Response<String> {
  public GetBlockHash() {}

  public String getBlockHash() {
    return this.getRawResult();
  }
}

package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class GetReceivedInfo extends Response<BigDecimal> {
  public GetReceivedInfo() {}

  public BigDecimal getReceived() {
    return new BigDecimal(this.getRawResult());
  }
}

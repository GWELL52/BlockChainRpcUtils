package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class BtcGetReceivedInfo extends Response<BigDecimal> {
  public BtcGetReceivedInfo() {}

  public BigDecimal getReceived() {
    return new BigDecimal(this.getRawResult());
  }
}

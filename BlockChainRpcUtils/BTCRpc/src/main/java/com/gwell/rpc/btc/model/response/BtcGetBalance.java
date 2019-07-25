package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class BtcGetBalance extends Response<BigDecimal> {
  public BtcGetBalance() {}

  public BigDecimal getBalance() {
    return new BigDecimal(this.getRawResult());
  }
}

package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

import java.math.BigDecimal;

public class BtcGetBalance extends Response<BigDecimal> {
  public BtcGetBalance() {}

  public BigDecimal getBalance() {
    return new BigDecimal(this.getRawResult());
  }
}

package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class GetBalance extends Response<BigDecimal> {
  public GetBalance() {}

  public BigDecimal getBalance() {
    return new BigDecimal(this.getRawResult());
  }
}

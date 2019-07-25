package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigInteger;

public class BtcGetBlockCount extends Response<BigInteger> {
  public BtcGetBlockCount() {}

  public BigInteger getBlockCount() {
    return new BigInteger(this.getRawResult());
  }
}

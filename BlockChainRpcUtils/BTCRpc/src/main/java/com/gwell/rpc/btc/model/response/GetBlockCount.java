package com.gwell.rpc.btc.model.response;

import com.gwell.rpc.common.model.Response;

import java.math.BigInteger;

public class GetBlockCount extends Response<BigInteger> {
  public GetBlockCount() {}

  public BigInteger getBlockCount() {
    return new BigInteger(this.getRawResult());
  }
}

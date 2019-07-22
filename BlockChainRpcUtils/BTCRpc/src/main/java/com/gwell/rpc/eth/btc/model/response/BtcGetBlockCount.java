package com.gwell.rpc.eth.btc.model.response;

import com.gwell.rpc.eth.common.model.Response;

import java.math.BigInteger;

public class BtcGetBlockCount extends Response<BigInteger> {
  public BtcGetBlockCount() {}

  public BigInteger getBlockCount() {
    return new BigInteger(this.getRawResult());
  }
}

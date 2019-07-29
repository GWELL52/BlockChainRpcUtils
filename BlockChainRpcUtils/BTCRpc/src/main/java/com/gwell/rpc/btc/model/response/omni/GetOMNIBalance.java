package com.gwell.rpc.btc.model.response.omni;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.omni.OMNIBalanceInfo;
import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class GetOMNIBalance extends Response<OMNIBalanceInfo> {
  public GetOMNIBalance() {}

  public OMNIBalanceInfo getResult() {
    return JSONObject.parseObject(this.getRawResult(), OMNIBalanceInfo.class);
  }

  public BigDecimal getBalance() {
    return this.getResult().getBalance();
  }
}

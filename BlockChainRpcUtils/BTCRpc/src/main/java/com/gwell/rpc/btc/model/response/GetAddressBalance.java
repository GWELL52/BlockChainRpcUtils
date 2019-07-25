package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.BalanceInfo;
import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class GetAddressBalance extends Response<BalanceInfo> {
  public GetAddressBalance() {}

  public BalanceInfo getResult() {
    return JSONObject.parseObject(this.getRawResult(), BalanceInfo.class);
  }

  public BigDecimal getBalance() {
    return this.getResult().getBalance();
  }
}

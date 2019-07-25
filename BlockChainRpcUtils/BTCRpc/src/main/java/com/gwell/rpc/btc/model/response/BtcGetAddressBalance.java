package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.BtcBalanceInfo;
import com.gwell.rpc.common.model.Response;

import java.math.BigDecimal;

public class BtcGetAddressBalance extends Response<BtcBalanceInfo> {
  public BtcGetAddressBalance() {}

  public BtcBalanceInfo getResult() {
    return JSONObject.parseObject(this.getRawResult(), BtcBalanceInfo.class);
  }

  public BigDecimal getBalance() {
    return this.getResult().getBalance();
  }
}

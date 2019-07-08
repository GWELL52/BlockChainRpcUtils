package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.BalanceInfo;
import com.gwell.rpc.eth.common.model.Response;

import java.math.BigDecimal;

public class BtcGetAddressBalance extends Response<BalanceInfo> {
  public BtcGetAddressBalance() {}

  public BalanceInfo getResult() {
    return JSONObject.parseObject(this.getRawResult(), BalanceInfo.class);
  }

  public BigDecimal getBalance() {
    return this.getResult().getBalance();
  }
}

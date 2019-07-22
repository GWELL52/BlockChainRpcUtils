package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.BtcTransactionInfo;
import com.gwell.rpc.eth.common.model.Response;

import java.util.List;

public class BtcGetTransactionInfo extends Response<BtcTransactionInfo> {
  public BtcGetTransactionInfo() {}

  public BtcTransactionInfo getTransactionInfo() {
    return JSONObject.parseObject(this.getRawResult(), BtcTransactionInfo.class);
  }

  public List<BtcTransactionInfo.Details> getDetailList() {
    return getTransactionInfo().getDetails();
  }
}

package com.gwell.rpc.btc.model.response.omni;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.omni.OMNITransactionInfo;
import com.gwell.rpc.common.model.Response;

public class GetOMNITransactionInfo extends Response<OMNITransactionInfo> {
  public GetOMNITransactionInfo() {}

  public OMNITransactionInfo getTransactionInfo() {
    return JSONObject.parseObject(this.getRawResult(), OMNITransactionInfo.class);
  }
}

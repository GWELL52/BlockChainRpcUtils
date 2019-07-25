package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.RawTransactionInfo;
import com.gwell.rpc.common.model.Response;

public class GetRawTransactionInfo extends Response<RawTransactionInfo> {
  public GetRawTransactionInfo() {}

  public RawTransactionInfo getRawInfo() {
    return JSONObject.parseObject(this.getRawResult(), RawTransactionInfo.class);
  }
}

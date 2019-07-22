package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.BtcRawTransactionInfo;
import com.gwell.rpc.eth.common.model.Response;

public class BtcGetRawTransactionInfo extends Response<BtcRawTransactionInfo> {
  public BtcGetRawTransactionInfo() {}

  public BtcRawTransactionInfo getRawInfo() {
    return JSONObject.parseObject(this.getRawResult(), BtcRawTransactionInfo.class);
  }
}

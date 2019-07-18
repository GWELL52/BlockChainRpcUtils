package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.BtcInfo;
import com.gwell.rpc.eth.common.model.Response;

public class BtcGetInfo extends Response<BtcInfo> {
  public BtcGetInfo() {}

  public BtcInfo getInfo() {
    return JSONObject.parseObject(this.getRawResult(), BtcInfo.class);
  }
}

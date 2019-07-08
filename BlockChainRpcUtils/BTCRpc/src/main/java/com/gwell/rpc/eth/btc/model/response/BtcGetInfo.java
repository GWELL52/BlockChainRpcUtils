package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.Info;
import com.gwell.rpc.eth.common.model.Response;

public class BtcGetInfo extends Response<Info> {
  public BtcGetInfo() {}

  public Info getInfo() {
    return JSONObject.parseObject(this.getRawResult(), Info.class);
  }
}

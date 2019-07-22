package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.result.BtcBlockInfo;
import com.gwell.rpc.eth.common.model.Response;

public class BtcGetBlockInfo extends Response<BtcBlockInfo> {
  public BtcGetBlockInfo() {}

  public BtcBlockInfo getInfo() {
    return JSONObject.parseObject(this.getRawResult(), BtcBlockInfo.class);
  }
}

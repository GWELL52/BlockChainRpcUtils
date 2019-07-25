package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.WalletInfo;
import com.gwell.rpc.common.model.Response;

public class GetInfo extends Response<WalletInfo> {
  public GetInfo() {}

  public WalletInfo getInfo() {
    return JSONObject.parseObject(this.getRawResult(), WalletInfo.class);
  }
}

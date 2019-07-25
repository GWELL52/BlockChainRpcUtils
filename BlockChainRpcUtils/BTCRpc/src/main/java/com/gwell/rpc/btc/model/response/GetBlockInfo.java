package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.BlockInfo;
import com.gwell.rpc.common.model.Response;

public class GetBlockInfo extends Response<BlockInfo> {
  public GetBlockInfo() {}

  public BlockInfo getInfo() {
    return JSONObject.parseObject(this.getRawResult(), BlockInfo.class);
  }
}

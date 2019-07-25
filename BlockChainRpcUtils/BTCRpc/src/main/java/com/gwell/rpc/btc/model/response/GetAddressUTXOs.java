package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.btc.model.response.result.UTXOInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class GetAddressUTXOs extends Response<List<UTXOInfo>> {
  public GetAddressUTXOs() {}

  public List<UTXOInfo> getUTXOList() {
    return JSONArray.parseArray(this.getRawResult(), UTXOInfo.class);
  }
}

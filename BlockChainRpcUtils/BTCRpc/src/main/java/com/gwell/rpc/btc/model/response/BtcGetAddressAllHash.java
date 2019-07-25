package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class BtcGetAddressAllHash extends Response<List<String>> {
  public BtcGetAddressAllHash() {}

  public List<String> getHashList() {
    return JSONArray.parseArray(this.getRawResult(), String.class);
  }
}

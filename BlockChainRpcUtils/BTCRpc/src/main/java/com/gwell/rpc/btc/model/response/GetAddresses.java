package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class GetAddresses extends Response<List<String>> {
  public GetAddresses() {}

  public List<String> getAddressList() {
    return JSONArray.parseArray(this.getRawResult(), String.class);
  }
}

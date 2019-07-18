package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.eth.common.model.Response;

import java.util.List;

public class BtcGetAddresses extends Response<List<String>> {
  public BtcGetAddresses() {}

  public List<String> getAddressList() {
    return JSONArray.parseArray(this.getRawResult(), String.class);
  }
}

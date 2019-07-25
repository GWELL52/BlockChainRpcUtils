package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.common.model.Response;

import java.util.Set;

public class ListAccounts extends Response<JSONObject> {
  public ListAccounts() {}

  public JSONObject getAccountMap() {
    return JSONObject.parseObject(this.getRawResult());
  }

  public Set<String> getAccountList() {
    return getAccountMap().keySet();
  }
}

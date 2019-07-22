package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.common.model.Response;

import java.util.Set;

public class BtcListAccounts extends Response<JSONObject> {
  public BtcListAccounts() {}

  public JSONObject getAccountMap() {
    return JSONObject.parseObject(this.getRawResult());
  }

  public Set<String> getAccountList() {
    return getAccountMap().keySet();
  }
}

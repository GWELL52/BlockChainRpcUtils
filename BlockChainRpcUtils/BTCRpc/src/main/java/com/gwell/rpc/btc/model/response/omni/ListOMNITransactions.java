package com.gwell.rpc.btc.model.response.omni;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.btc.model.response.result.omni.OMNITransactionInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class ListOMNITransactions extends Response<List<OMNITransactionInfo>> {
  public ListOMNITransactions() {}

  public List<OMNITransactionInfo> getTransactionList() {
    return JSONArray.parseArray(this.getRawResult(), OMNITransactionInfo.class);
  }
}

package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.btc.model.response.result.TransactionInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class ListTransactions extends Response<List<TransactionInfo>> {
  public ListTransactions() {}

  public List<TransactionInfo> getTransactionList() {
    return JSONArray.parseArray(this.getRawResult(), TransactionInfo.class);
  }
}

package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.btc.model.response.result.BtcTransactionInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class BtcListTransactions extends Response<List<BtcTransactionInfo>> {
  public BtcListTransactions() {}

  public List<BtcTransactionInfo> getTransactionList() {
    return JSONArray.parseArray(this.getRawResult(), BtcTransactionInfo.class);
  }
}

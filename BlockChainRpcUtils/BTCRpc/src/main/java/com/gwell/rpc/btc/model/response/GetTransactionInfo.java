package com.gwell.rpc.btc.model.response;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.btc.model.response.result.TransactionInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class GetTransactionInfo extends Response<TransactionInfo> {
  public GetTransactionInfo() {}

  public TransactionInfo getTransactionInfo() {
    return JSONObject.parseObject(this.getRawResult(), TransactionInfo.class);
  }

  public List<TransactionInfo.Details> getDetailList() {
    return getTransactionInfo().getDetails();
  }
}

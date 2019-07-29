package com.gwell.rpc.btc.model.response.omni;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.btc.model.response.result.omni.OMNIWalletAddressBalanceInfo;
import com.gwell.rpc.common.model.Response;

import java.util.List;

public class GetOMNIWalletAddressBalance extends Response<List<OMNIWalletAddressBalanceInfo>> {
  public GetOMNIWalletAddressBalance() {}

  public List<OMNIWalletAddressBalanceInfo> getResult() {
    return JSONArray.parseArray(this.getRawResult(), OMNIWalletAddressBalanceInfo.class);
  }
}

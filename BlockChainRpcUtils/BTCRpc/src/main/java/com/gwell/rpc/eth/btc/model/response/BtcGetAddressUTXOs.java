package com.gwell.rpc.eth.btc.model.response;

import com.alibaba.fastjson.JSONArray;
import com.gwell.rpc.eth.btc.model.response.result.BtcUTXOInfo;
import com.gwell.rpc.eth.common.model.Response;

import java.util.List;

public class BtcGetAddressUTXOs extends Response<List<BtcUTXOInfo>> {
  public BtcGetAddressUTXOs() {}

  public List<BtcUTXOInfo> getUTXOList() {
    return JSONArray.parseArray(this.getRawResult(), BtcUTXOInfo.class);
  }
}

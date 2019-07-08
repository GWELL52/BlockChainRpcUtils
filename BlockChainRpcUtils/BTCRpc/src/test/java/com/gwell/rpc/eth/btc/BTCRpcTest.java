package com.gwell.rpc.eth.btc;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.BtcInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BTCRpcTest {
  private BTCRpc rpc = BTCRpc.build("192.168.1.232", 20001, "bitcorehot", "uCV2Masdf9");

  @Test
  public void getInfo() {
    BtcInfo result = rpc.getInfo();
    System.out.println(JSONObject.toJSON(result.getInfo()));
  }
}

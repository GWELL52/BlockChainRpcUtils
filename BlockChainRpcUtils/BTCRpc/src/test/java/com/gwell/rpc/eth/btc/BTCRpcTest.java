package com.gwell.rpc.eth.btc;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BTCRpcTest {
  private BTCRpc rpc = BTCRpc.build("", 0, "", "");

  @Test
  public void getInfo() {
    BtcGetInfo result = rpc.getInfo();
    System.out.println(JSONObject.toJSON(result.getInfo()));
  }

  @Test
  public void newAddress1() {
    System.out.println(rpc.newAddress("test").getAddress());
  }
}

package com.gwell.rpc.eth.btc;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BTCRpcTest {
  private BTCRpc rpc = BTCRpc.build("192.168.1.232", 20001, "bitcorehot", "uCV2Masdf9");

  @Test
  public void getInfo() {
    BtcGetInfo result = rpc.getInfo();
    System.out.println(JSONObject.toJSON(result.getInfo()));
  }

  @Test
  public void newAddress() {
    System.out.println(rpc.newAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    System.out.println(rpc.getBalance(null).getBalance());
    System.out.println(rpc.getBalance("test").getBalance());
  }

  @Test
  public void getAddressBalance() {
    System.out.println(rpc.getAddressBalance("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getBalance());
  }
}

package com.gwell.rpc.eth.btc;

import com.alibaba.fastjson.JSON;
import com.gwell.rpc.eth.btc.model.response.BtcGetInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BTCRpcTest {
  private BTCRpc rpc = BTCRpc.build("192.168.1.232", 20001, "bitcorehot", "uCV2Masdf9");

  private void println(Object object) {
    System.out.println(JSON.toJSONString(object.toString()));
  }

  @Test
  public void getInfo() {
    BtcGetInfo result = rpc.getInfo();
    println(result.getInfo());
  }

  @Test
  public void getNewAddress() {
    println(rpc.getNewAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    println(rpc.getBalance(null).getBalance());
    println(rpc.getBalance("test").getBalance());
  }

  @Test
  public void getAddressBalance() {
    println(rpc.getAddressBalance("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getBalance());
  }

  @Test
  public void getAddressesByAccount() {
    println(rpc.getAddressesByAccount("test").getAddressList());
  }

  @Test
  public void listTransactions() {
    println(rpc.listTransactions(null, 1000L, 0L).getTransactionList());
  }
}

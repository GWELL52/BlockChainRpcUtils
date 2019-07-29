package com.gwell.rpc.btc;

import com.alibaba.fastjson.JSON;
import com.gwell.rpc.btc.model.response.GetInfo;
import com.gwell.rpc.btc.model.response.ListAccounts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(JUnit4.class)
public class DASHRpcTest extends AbstractTest {
  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getInfo() {
    GetInfo result = dashRpc.getInfo();
    println(result.getInfo());
  }

  @Test
  public void getNewAddress() {
    println(dashRpc.getNewAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    println(dashRpc.getBalance("test").getBalance());
    println(dashRpc.getBalance(null).getBalance());
  }

  @Test
  public void getAddressBalance() {
    println(dashRpc.getAddressBalance("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getBalance());
  }

  @Test
  public void getAddressesByAccount() {
    println(dashRpc.getAddressesByAccount("test").getAddressList());
  }

  @Test
  public void listTransactions() {
    println(dashRpc.listTransactions("test", 1000L, 0L).getTransactionList());
  }

  @Test
  public void sendToAddress() {
    println(
        dashRpc
            .sendToAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV", new BigDecimal("0.001"))
            .getHash());
  }

  @Test
  public void getBlockCount() {
    println(dashRpc.getBlockCount().getBlockCount());
  }

  @Test
  public void getBlockHash() {
    println(dashRpc.getBlockHash(new BigInteger("1000")).getBlockHash());
  }

  @Test
  public void getBlockInfo() {
    println(
        dashRpc
            .getBlockInfo("00000000373403049c5fff2cd653590e8cbe6f7ac639db270e7d1a7503d698df")
            .getInfo());
  }

  @Test
  public void getTransactionInfo() {
    println(
        dashRpc
            .getTransactionInfo("bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getDetailList());
  }

  @Test
  public void getRawTransactionInfo() {
    println(
        dashRpc
            .getRawTransactionInfo(
                "bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getRawInfo());
  }

  @Test
  public void listAccounts() {
    ListAccounts result = dashRpc.listAccounts();
    println(result.getAccountMap());
    println(result.getAccountList());
  }

  @Test
  public void getReceivedByAccount() {
    println(dashRpc.getReceivedByAccount("test").getReceived());
  }

  @Test
  public void getReceivedByAddress() {
    println(
        "不属于钱包管理的: "
            + dashRpc.getReceivedByAddress("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getReceived());
    println(
        "属于钱包管理的: "
            + dashRpc.getReceivedByAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV").getReceived());
  }

  @Test
  public void sendRawTransaction() {
    println(dashRpc.sendRawTransaction("rawData").getHash());
  }

  @Test
  public void getAddressUnspentOutputs() {
    println(dashRpc.getAddressUnspentOutputs("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getUTXOList());
  }

  @Test
  public void getAddressAllHash() {
    println(dashRpc.getAddressAllHash("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getHashList());
  }
}

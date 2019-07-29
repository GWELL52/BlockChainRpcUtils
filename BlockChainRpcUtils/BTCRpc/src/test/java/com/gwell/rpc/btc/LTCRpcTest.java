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
public class LTCRpcTest extends AbstractTest {
  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getInfo() {
    GetInfo result = ltcRpc.getInfo();
    println(result.getInfo());
  }

  @Test
  public void getNewAddress() {
    println(ltcRpc.getNewAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    println(ltcRpc.getBalance("test").getBalance());
    println(ltcRpc.getBalance(null).getBalance());
  }

  @Test
  public void getAddressBalance() {
    println(ltcRpc.getAddressBalance("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getBalance());
  }

  @Test
  public void getAddressesByAccount() {
    println(ltcRpc.getAddressesByAccount("test").getAddressList());
  }

  @Test
  public void listTransactions() {
    println(ltcRpc.listTransactions("test", 1000L, 0L).getTransactionList());
  }

  @Test
  public void sendToAddress() {
    println(
        ltcRpc
            .sendToAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV", new BigDecimal("0.001"))
            .getHash());
  }

  @Test
  public void getBlockCount() {
    println(ltcRpc.getBlockCount().getBlockCount());
  }

  @Test
  public void getBlockHash() {
    println(ltcRpc.getBlockHash(new BigInteger("1000")).getBlockHash());
  }

  @Test
  public void getBlockInfo() {
    println(
        ltcRpc
            .getBlockInfo("00000000373403049c5fff2cd653590e8cbe6f7ac639db270e7d1a7503d698df")
            .getInfo());
  }

  @Test
  public void getTransactionInfo() {
    println(
        ltcRpc
            .getTransactionInfo("bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getDetailList());
  }

  @Test
  public void getRawTransactionInfo() {
    println(
        ltcRpc
            .getRawTransactionInfo(
                "bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getRawInfo());
  }

  @Test
  public void listAccounts() {
    ListAccounts result = ltcRpc.listAccounts();
    println(result.getAccountMap());
    println(result.getAccountList());
  }

  @Test
  public void getReceivedByAccount() {
    println(ltcRpc.getReceivedByAccount("test").getReceived());
  }

  @Test
  public void getReceivedByAddress() {
    println(
        "不属于钱包管理的: "
            + ltcRpc.getReceivedByAddress("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getReceived());
    println(
        "属于钱包管理的: "
            + ltcRpc.getReceivedByAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV").getReceived());
  }

  @Test
  public void sendRawTransaction() {
    println(ltcRpc.sendRawTransaction("rawData").getHash());
  }

  @Test
  public void getAddressUnspentOutputs() {
    println(ltcRpc.getAddressUnspentOutputs("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getUTXOList());
  }

  @Test
  public void getAddressAllHash() {
    println(ltcRpc.getAddressAllHash("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getHashList());
  }
}

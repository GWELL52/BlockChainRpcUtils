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
public class OMNIRpcTest extends AbstractTest {
  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getInfo() {
    GetInfo result = omniRpc.getInfo();
    println(result.getInfo());
  }

  @Test
  public void getNewAddress() {
    println(omniRpc.getNewAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    println(omniRpc.getBalance("test").getBalance());
    println(omniRpc.getBalance(null).getBalance());
  }

  @Test
  public void getAddressBalance() {
    println(omniRpc.getAddressBalance("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getBalance());
  }

  @Test
  public void getAddressesByAccount() {
    println(omniRpc.getAddressesByAccount("test").getAddressList());
  }

  @Test
  public void listTransactions() {
    println(omniRpc.listTransactions("test", 1000L, 0L).getTransactionList());
  }

  @Test
  public void sendToAddress() {
    println(
        omniRpc
            .sendToAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV", new BigDecimal("0.001"))
            .getHash());
  }

  @Test
  public void getBlockCount() {
    println(omniRpc.getBlockCount().getBlockCount());
  }

  @Test
  public void getBlockHash() {
    println(omniRpc.getBlockHash(new BigInteger("1000")).getBlockHash());
  }

  @Test
  public void getBlockInfo() {
    println(
        omniRpc
            .getBlockInfo("00000000373403049c5fff2cd653590e8cbe6f7ac639db270e7d1a7503d698df")
            .getInfo());
  }

  @Test
  public void getTransactionInfo() {
    println(
        omniRpc
            .getTransactionInfo("bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getDetailList());
  }

  @Test
  public void getRawTransactionInfo() {
    println(
        omniRpc
            .getRawTransactionInfo(
                "bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getRawInfo());
  }

  @Test
  public void listAccounts() {
    ListAccounts result = omniRpc.listAccounts();
    println(result.getAccountMap());
    println(result.getAccountList());
  }

  @Test
  public void getReceivedByAccount() {
    println(omniRpc.getReceivedByAccount("test").getReceived());
  }

  @Test
  public void getReceivedByAddress() {
    println(
        "不属于钱包管理的: "
            + omniRpc.getReceivedByAddress("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getReceived());
    println(
        "属于钱包管理的: "
            + omniRpc.getReceivedByAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV").getReceived());
  }

  @Test
  public void sendRawTransaction() {
    println(omniRpc.sendRawTransaction("rawData").getHash());
  }

  @Test
  public void getAddressUnspentOutputs() {
    println(omniRpc.getAddressUnspentOutputs("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getUTXOList());
  }

  @Test
  public void getAddressAllHash() {
    println(omniRpc.getAddressAllHash("mkN8FtQZmJp5CRLNY9tb6Ce2F1xCoUquST").getHashList());
  }

  @Test
  public void getOMNIBalance() {
    println(omniRpc.getOMNIBalance("mkN8FtQZmJp5CRLNY9tb6Ce2F1xCoUquST", 1).getBalance());
    println(omniRpc.getOMNIBalance("mnLpd1DgziFw7wap2vjypZDCkQZGAZPMZL", 1).getBalance());
  }

  @Test
  public void getOMNIWalletAddressBalance() {
    println(omniRpc.getOMNIWalletAddressBalance().getResult());
  }

  @Test
  public void listOMNITransactions() {
    println(
        omniRpc
            .listOMNITransactions("mkN8FtQZmJp5CRLNY9tb6Ce2F1xCoUquST", null, null, null, null)
            .getTransactionList());
  }

  @Test
  public void getOMNITransactionInfo() {
    println(
        omniRpc
            .getOMNITransactionInfo(
                "890d6d702a02543325154e8b1dea4d558f2c24089d35f66ad5dbffe3c461d6bd")
            .getTransactionInfo());
  }

  @Test
  public void sendOMNITo() {
    println(
        omniRpc
            .sendOMNITo(
                "mkN8FtQZmJp5CRLNY9tb6Ce2F1xCoUquST",
                "mnLpd1DgziFw7wap2vjypZDCkQZGAZPMZL",
                1,
                BigDecimal.ONE,
                null)
            .getHash());
  }
}

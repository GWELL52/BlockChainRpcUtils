package com.gwell.rpc.btc;

import com.alibaba.fastjson.JSON;
import com.gwell.rpc.btc.model.request.SendTransactionParams;
import com.gwell.rpc.btc.model.response.GetInfo;
import com.gwell.rpc.btc.model.response.ListAccounts;
import com.gwell.rpc.btc.model.response.result.UTXOInfo;
import com.gwell.rpc.common.model.BlockChainWallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RunWith(JUnit4.class)
public class BTCRpcTest extends AbstractTest {
  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getInfo() {
    GetInfo result = btcRpc.getInfo();
    println(result.getInfo());
  }

  @Test
  public void getNewAddress() {
    println(btcRpc.getNewAddress("test").getAddress());
  }

  @Test
  public void getBalance() {
    println(btcRpc.getBalance("test").getBalance());
    println(btcRpc.getBalance(null).getBalance());
  }

  @Test
  public void getAddressBalance() {
    println(btcRpc.getAddressBalance("mwHrmiQTgB88SypvSSFFt6gCzdo6NJ2Eh8").getBalance());
  }

  @Test
  public void getAddressesByAccount() {
    println(btcRpc.getAddressesByAccount("test").getAddressList());
  }

  @Test
  public void listTransactions() {
    println(btcRpc.listTransactions("test", 1000L, 0L).getTransactionList());
  }

  @Test
  public void sendToAddress() {
    println(
        btcRpc
            .sendToAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV", new BigDecimal("0.001"))
            .getHash());
  }

  @Test
  public void getBlockCount() {
    println(btcRpc.getBlockCount().getBlockCount());
  }

  @Test
  public void getBlockHash() {
    println(btcRpc.getBlockHash(new BigInteger("1000")).getBlockHash());
  }

  @Test
  public void getBlockInfo() {
    println(
        btcRpc
            .getBlockInfo("00000000373403049c5fff2cd653590e8cbe6f7ac639db270e7d1a7503d698df")
            .getInfo());
  }

  @Test
  public void getTransactionInfo() {
    println(
        btcRpc
            .getTransactionInfo("bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getDetailList());
  }

  @Test
  public void getRawTransactionInfo() {
    println(
        btcRpc
            .getRawTransactionInfo(
                "bc889691337643ed1eec6aadd6b85efa28af71f9fe162dac1394ebb4c1d808f3")
            .getRawInfo());
  }

  @Test
  public void listAccounts() {
    ListAccounts result = btcRpc.listAccounts();
    println(result.getAccountMap());
    println(result.getAccountList());
  }

  @Test
  public void getReceivedByAccount() {
    println(btcRpc.getReceivedByAccount("test").getReceived());
  }

  @Test
  public void getReceivedByAddress() {
    println(
        "不属于钱包管理的: "
            + btcRpc.getReceivedByAddress("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getReceived());
    println(
        "属于钱包管理的: "
            + btcRpc.getReceivedByAddress("n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV").getReceived());
  }

  @Test
  public void sendRawTransaction() {
    println(btcRpc.sendRawTransaction("rawData").getHash());
  }

  @Test
  public void getAddressUnspentOutputs() {
    println(btcRpc.getAddressUnspentOutputs("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getUTXOList());
  }

  @Test
  public void getAddressAllHash() {
    println(btcRpc.getAddressAllHash("mwdXtp8ow61jcGuEXYVy5aZqksxvtrnNsL").getHashList());
  }

  @Test
  public void sendSignTransaction() {
    String address = "n1tysbphwWvQYXf3ap2uN4ZQE5U9gVz5VV";
    String privateKey = btcRpc.dumpPrivateKey(address).getPrivateKey();
    BlockChainWallet wallet = new BlockChainWallet();
    wallet.setAddress(address);
    wallet.setPrivateKey(privateKey);

    List<UTXOInfo> utxoList = btcRpc.getAddressUnspentOutputs(address).getUTXOList();
    println(utxoList);
    SendTransactionParams params = new SendTransactionParams();
    params.setFromAccount(wallet);
    params.setToAddress("2MyU89kfuCKtaNsrAB1R477rWrZVbaikwxc");
    params.setTestNet(true);
    params.setUTXOInfoList(utxoList);
    params.setFee(new BigDecimal("0.0001"));
    params.setAmount(new BigDecimal("0.0009"));
    String signData = btcRpc.signTransaction(params);
    System.out.println("signData = " + signData);

    //    println(btcRpc.sendRawTransaction(signData));
  }
}

package com.gwell.rpc.eth;

import com.alibaba.fastjson.JSON;
import com.gwell.rpc.eth.call.method.TokenMethod;
import com.gwell.rpc.eth.model.request.BalanceParams;
import com.gwell.rpc.eth.model.request.SendTransactionParams;
import com.gwell.rpc.eth.model.response.EthAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(JUnit4.class)
public class ETHRpcTest extends AbstractTest {

  private SendTransactionParams sendEth(EthAccount from, String to, BigDecimal amount) {
    return SendTransactionParams.createEthTransaction(from, to, amount);
  }

  private SendTransactionParams sendAllEth(EthAccount from, String to) {
    return SendTransactionParams.createAllEthTransaction(from, to);
  }

  private SendTransactionParams sendToken(
      EthAccount from, String to, BigDecimal amount, String contract) {
    return SendTransactionParams.createTokenTransaction(from, to, amount, contract);
  }

  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getBlockNumber() {
    println(ethRpc.getBlockNumber());
  }

  @Test
  public void getBlockInfo() {
    println(ethRpc.getBlockInfo(new BigInteger("4788666"), false));
    println(ethRpc.getBlockInfo(new BigInteger("4788666"), true));
  }

  @Test
  public void getNonce() {
    println(ethRpc.getNonce("0xc00dcf5646eb41f8c3737c67d18801e599dc4473"));
  }

  @Test
  public void getGasPrice() {
    println(ethRpc.getGasPrice());
  }

  @Test
  public void getGasLimit() {
    println("ALL ETH ==> " + ethRpc.getGasLimit(sendAllEth(from, to.getAddress())));
    println("ETH ==> " + ethRpc.getGasLimit(sendEth(from, to.getAddress(), BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + ethRpc.getGasLimit(
                sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress)));
  }

  @Test
  public void getTransactionByHash() {
    println(
        "ETH ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionByHash(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7")));
    println(
        "TOKEN ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionByHash(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce")));
  }

  @Test
  public void getTransactionReceipt() {
    println(
        "ETH ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionReceipt(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7")));
    println(
        "TOKEN ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionReceipt(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce")));
  }

  @Test
  public void getTransactionAllInfo() {
    println(
        "ETH - [getTransferTime=false] ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionAllInfo(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7", false)));
    println(
        "ETH - [getTransferTime=true] ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionAllInfo(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7", true)));
    println(
        "TOKEN - [getTransferTime=false] ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionAllInfo(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce", false)));
    println(
        "TOKEN - [getTransferTime=true] ==> "
            + JSON.toJSONString(
                ethRpc.getTransactionAllInfo(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce", true)));
  }

  @Test
  public void newAccount() {
    println(ethRpc.newAccount("123456789"));
  }

  @Test
  public void importPrivateKey() {
    println(ethRpc.importPrivateKey(from));
  }

  @Test
  public void getBalance() {
    println("ETH ==> " + ethRpc.getBalance(BalanceParams.createEthBalance(from.getAddress())));
    println(
        "TOKEN ==> "
            + ethRpc.getBalance(
                BalanceParams.createTokenBalance(from.getAddress(), ethContractAddress)));
  }

  @Test
  public void getTransactionData() {
    println("ALL ETH ==> " + ethRpc.getTransactionData(sendAllEth(from, to.getAddress())));
    println("ETH ==> " + ethRpc.getTransactionData(sendEth(from, to.getAddress(), BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + ethRpc.getTransactionData(
                sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress)));
  }

  @Test
  public void getTokenName() {
    println(ethRpc.getTokenName(ethContractAddress));
  }

  @Test
  public void getTokenSymbol() {
    println(ethRpc.getTokenSymbol(ethContractAddress));
  }

  @Test
  public void getTokenTotalSupply() {
    println(ethRpc.getTokenTotalSupply(ethContractAddress, null));
  }

  @Test
  public void getTokenDecimal() {
    println(ethRpc.getTokenDecimal(ethContractAddress));
  }

  @Test
  public void getTokenUnit() {
    BigDecimal unit = ethRpc.getTokenUnit(ethContractAddress);
    println("unit ==> " + unit);
    println("decimal ==> " + TokenMethod.getTokenDecimal(unit));
  }

  @Test
  public void getTransactionFee() {
    println("ALL ETH ==> " + ethRpc.getTransactionFee(sendAllEth(from, to.getAddress())));
    println("ETH ==> " + ethRpc.getTransactionFee(sendEth(from, to.getAddress(), BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + ethRpc.getTransactionFee(
                sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress)));
  }

  @Test
  public void signTransaction() {
    println("ALL ETH ==> " + ethRpc.signTransaction(sendAllEth(from, to.getAddress())));
    println("ETH ==> " + ethRpc.signTransaction(sendEth(from, to.getAddress(), BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + ethRpc.signTransaction(
                sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress)));
  }

  @Test
  public void sendAllEth() {
    println(ethRpc.sendAllEth(sendAllEth(to, from.getAddress())));
  }

  @Test
  public void sendTransaction() {
    println(ethRpc.sendTransaction(sendEth(from, to.getAddress(), BigDecimal.ONE)));
    println(ethRpc.sendTransaction(sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress)));
  }

  @Test
  public void sendRawTransaction() {
    String signData =
        ethRpc.signTransaction(
            sendToken(from, to.getAddress(), BigDecimal.ONE, ethContractAddress));
    println(ethRpc.sendRawTransaction(signData));
  }
}

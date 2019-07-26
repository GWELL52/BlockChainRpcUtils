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
public class ETCRpcTest extends AbstractTest {

  private SendTransactionParams sendEtc(EthAccount from, EthAccount to, BigDecimal amount) {
    return SendTransactionParams.createEthTransaction(
        from.getAddress(), from.getPassword(), to.getAddress(), amount);
  }

  private SendTransactionParams sendAllEtc(EthAccount from, EthAccount to) {
    return SendTransactionParams.createAllEthTransaction(
        from.getAddress(), from.getPassword(), to.getAddress());
  }

  private SendTransactionParams sendToken(
      EthAccount from, EthAccount to, BigDecimal amount, String contract) {
    return SendTransactionParams.createTokenTransaction(
        from.getAddress(), from.getPassword(), to.getAddress(), amount, contract);
  }

  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getBlockNumber() {
    println(etcRpc.getBlockNumber());
  }

  @Test
  public void getBlockInfo() {
    println(etcRpc.getBlockInfo(new BigInteger("4788666"), false));
    println(etcRpc.getBlockInfo(new BigInteger("4788666"), true));
  }

  @Test
  public void getNonce() {
    println(etcRpc.getNonce("0xc00dcf5646eb41f8c3737c67d18801e599dc4473"));
  }

  @Test
  public void getGasPrice() {
    println(etcRpc.getGasPrice());
  }

  @Test
  public void getGasLimit() {
    println("ALL ETC ==> " + etcRpc.getGasLimit(sendAllEtc(from, to)));
    println("ETC ==> " + etcRpc.getGasLimit(sendEtc(from, to, BigDecimal.ONE)));
    println(
        "TOKEN ==> " + etcRpc.getGasLimit(sendToken(from, to, BigDecimal.ONE, contractAddress)));
  }

  @Test
  public void getTransactionByHash() {
    println(
        "ETC ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionByHash(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7")));
    println(
        "TOKEN ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionByHash(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce")));
  }

  @Test
  public void getTransactionReceipt() {
    println(
        "ETC ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionReceipt(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7")));
    println(
        "TOKEN ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionReceipt(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce")));
  }

  @Test
  public void getTransactionAllInfo() {
    println(
        "ETC - [getTransferTime=false] ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionAllInfo(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7", false)));
    println(
        "ETC - [getTransferTime=true] ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionAllInfo(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7", true)));
    println(
        "TOKEN - [getTransferTime=false] ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionAllInfo(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce", false)));
    println(
        "TOKEN - [getTransferTime=true] ==> "
            + JSON.toJSONString(
                etcRpc.getTransactionAllInfo(
                    "0x6ea883b225c8c331d37418c8bf6b948e4449c59c782415a37d3e0190c4b6d1ce", true)));
  }

  @Test
  public void newAccount() {
    println(etcRpc.newAccount("123456789"));
  }

  @Test
  public void importPrivateKey() {
    println(etcRpc.importPrivateKey(from));
  }

  @Test
  public void getBalance() {
    println("ETC ==> " + etcRpc.getBalance(BalanceParams.createEthBalance(from.getAddress())));
    println(
        "TOKEN ==> "
            + etcRpc.getBalance(
                BalanceParams.createTokenBalance(from.getAddress(), contractAddress)));
  }

  @Test
  public void getTransactionData() {
    println("ALL ETC ==> " + etcRpc.getTransactionData(sendAllEtc(from, to)));
    println("ETC ==> " + etcRpc.getTransactionData(sendEtc(from, to, BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + etcRpc.getTransactionData(sendToken(from, to, BigDecimal.ONE, contractAddress)));
  }

  @Test
  public void getTokenName() {
    println(etcRpc.getTokenName(contractAddress));
  }

  @Test
  public void getTokenSymbol() {
    println(etcRpc.getTokenSymbol(contractAddress));
  }

  @Test
  public void getTokenTotalSupply() {
    println(etcRpc.getTokenTotalSupply(contractAddress, null));
  }

  @Test
  public void getTokenDecimal() {
    println(etcRpc.getTokenDecimal(contractAddress));
  }

  @Test
  public void getTokenUnit() {
    BigDecimal unit = etcRpc.getTokenUnit(contractAddress);
    println("unit ==> " + unit);
    println("decimal ==> " + TokenMethod.getTokenDecimal(unit));
  }

  @Test
  public void getTransactionFee() {
    println("ALL ETC ==> " + etcRpc.getTransactionFee(sendAllEtc(from, to)));
    println("ETC ==> " + etcRpc.getTransactionFee(sendEtc(from, to, BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + etcRpc.getTransactionFee(sendToken(from, to, BigDecimal.ONE, contractAddress)));
  }

  @Test
  public void signTransaction() {
    println("ALL ETC ==> " + etcRpc.signTransaction(sendAllEtc(from, to)));
    println("ETC ==> " + etcRpc.signTransaction(sendEtc(from, to, BigDecimal.ONE)));
    println(
        "TOKEN ==> "
            + etcRpc.signTransaction(sendToken(from, to, BigDecimal.ONE, contractAddress)));
  }

  @Test
  public void sendAllEtc() {
    println(etcRpc.sendAllEth(sendAllEtc(from, to)));
  }

  @Test
  public void sendTransaction() {
    println(etcRpc.sendTransaction(sendEtc(from, to, BigDecimal.ONE)));
    println(etcRpc.sendTransaction(sendToken(from, to, BigDecimal.ONE, contractAddress)));
  }

  @Test
  public void sendRawTransaction() {
    String signData = etcRpc.signTransaction(sendToken(from, to, BigDecimal.ONE, contractAddress));
    println(etcRpc.sendRawTransaction(signData));
  }
}

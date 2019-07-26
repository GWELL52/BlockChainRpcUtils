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
public class VNSRpcTest extends AbstractTest {

  private SendTransactionParams sendVns(EthAccount from, EthAccount to, BigDecimal amount) {
    return SendTransactionParams.createEthTransaction(
        from.getAddress(), from.getPassword(), to.getAddress(), amount);
  }

  private SendTransactionParams sendAllVns(EthAccount from, EthAccount to) {
    return SendTransactionParams.createAllEthTransaction(
        from.getAddress(), from.getPassword(), to.getAddress());
  }

  private void println(Object object) {
    System.out.println(JSON.toJSONString(object));
  }

  @Test
  public void getBlockNumber() {
    println(vnsRpc.getBlockNumber());
  }

  @Test
  public void getBlockInfo() {
    println(vnsRpc.getBlockInfo(new BigInteger("2547097"), false));
    println(vnsRpc.getBlockInfo(new BigInteger("2547097"), true));
  }

  @Test
  public void getNonce() {
    println(vnsRpc.getNonce("0xc00dcf5646eb41f8c3737c67d18801e599dc4473"));
  }

  @Test
  public void getGasPrice() {
    println(vnsRpc.getGasPrice());
  }

  @Test
  public void getGasLimit() {
    println("ALL VNS ==> " + vnsRpc.getGasLimit(sendAllVns(from, to)));
    println("VNS ==> " + vnsRpc.getGasLimit(sendVns(from, to, BigDecimal.ONE)));
  }

  @Test
  public void getTransactionByHash() {
    println(
        "VNS ==> "
            + JSON.toJSONString(
                vnsRpc.getTransactionByHash(
                    "0x2e57b37e0348920718ada744c586ba1b60cac4b3801709cbb1ae2fae85dbafb7")));
  }

  @Test
  public void getTransactionReceipt() {
    println(
        "VNS ==> "
            + JSON.toJSONString(
                vnsRpc.getTransactionReceipt(
                    "0x222cc33942b180f7222b261c1b69ee4b304442096ee645d5ab5d24e09cc939ad")));
  }

  @Test
  public void getTransactionAllInfo() {
    println(
        "ETC - [getTransferTime=false] ==> "
            + JSON.toJSONString(
                vnsRpc.getTransactionAllInfo(
                    "0x4cef541f4605378cb6362ac26df3162314eb7769a696d7d5fe728d91a8940313", false)));
    println(
        "ETC - [getTransferTime=true] ==> "
            + JSON.toJSONString(
                vnsRpc.getTransactionAllInfo(
                    "0x4d9c1e3807a9fec498b691285dcee5d305d870f2b6c8dadb6e7c08bce532bc38", true)));
  }

  @Test
  public void newAccount() {
    println(vnsRpc.newAccount("123456789"));
  }

  @Test
  public void importPrivateKey() {
    println(vnsRpc.importPrivateKey(from));
  }

  @Test
  public void getBalance() {
    println("VNS ==> " + vnsRpc.getBalance(BalanceParams.createEthBalance(from.getAddress())));
  }

  @Test
  public void getTransactionData() {
    println("ALL VNS ==> " + vnsRpc.getTransactionData(sendAllVns(from, to)));
    println("VNS ==> " + vnsRpc.getTransactionData(sendVns(from, to, BigDecimal.ONE)));
  }

  @Test
  public void getTokenName() {
    println(vnsRpc.getTokenName(vnsContractAddress));
  }

  @Test
  public void getTokenSymbol() {
    println(vnsRpc.getTokenSymbol(vnsContractAddress));
  }

  @Test
  public void getTokenTotalSupply() {
    println(vnsRpc.getTokenTotalSupply(vnsContractAddress, null));
  }

  @Test
  public void getTokenDecimal() {
    println(vnsRpc.getTokenDecimal(vnsContractAddress));
  }

  @Test
  public void getTokenUnit() {
    BigDecimal unit = vnsRpc.getTokenUnit(vnsContractAddress);
    println("unit ==> " + unit);
    println("decimal ==> " + TokenMethod.getTokenDecimal(unit));
  }

  @Test
  public void getTransactionFee() {
    println("ALL VNS ==> " + vnsRpc.getTransactionFee(sendAllVns(from, to)));
    println("VNS ==> " + vnsRpc.getTransactionFee(sendVns(from, to, BigDecimal.ONE)));
  }

  @Test
  public void signTransaction() {
    println("ALL VNS ==> " + vnsRpc.signTransaction(sendAllVns(from, to)));
    println("VNS ==> " + vnsRpc.signTransaction(sendVns(from, to, BigDecimal.ONE)));
  }

  @Test
  public void sendAllVns() {
    println(vnsRpc.sendAllEth(sendAllVns(from, to)));
  }

  @Test
  public void sendTransaction() {
    println(vnsRpc.sendTransaction(sendVns(from, to, BigDecimal.ONE)));
  }

  @Test
  public void sendRawTransaction() {
    String signData = vnsRpc.signTransaction(sendVns(from, to, BigDecimal.ONE));
    println(vnsRpc.sendRawTransaction(signData));
  }
}

package com.gwell.rpc.eth.model.request;

import com.gwell.rpc.eth.model.response.EthAccount;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;

import java.math.BigDecimal;
import java.math.BigInteger;

/** 发送交易参数类 */
@Data
public class SendTransactionParams {
  private String fromAddress;
  private String password;
  private String toAddress;
  private BigDecimal amount;

  private BigInteger nonce;
  private BigInteger gasLimit;
  private BigInteger gasPrice;

  private String contractAddress;
  private BigDecimal unit;
  private String method;

  private String data;

  public Credentials getCredentials() {
    EthAccount ethAccount = new EthAccount(fromAddress, password);
    return ethAccount.getCredentials();
  }

  public static SendTransactionParams createAllEthTransaction(
      String fromAddress, String password, String toAddress) {
    return new SendTransactionParams(
        fromAddress, password, toAddress, BigDecimal.ZERO, null, "", null, null, null);
  }

  public static SendTransactionParams createEthTransaction(
      String fromAddress, String password, String toAddress, BigDecimal amount) {
    return new SendTransactionParams(
        fromAddress, password, toAddress, amount, null, "", null, null, null);
  }

  public static SendTransactionParams createEthTransaction(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    return new SendTransactionParams(
        fromAddress, password, toAddress, amount, null, "", gasLimit, gasPrice, nonce);
  }

  public static SendTransactionParams createTokenTransaction(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress) {
    return new SendTransactionParams(
        fromAddress,
        password,
        toAddress,
        amount,
        contractAddress,
        null,
        null,
        null,
        null,
        null,
        null);
  }

  public static SendTransactionParams createTokenTransaction(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String unit) {
    return new SendTransactionParams(
        fromAddress,
        password,
        toAddress,
        amount,
        contractAddress,
        unit,
        null,
        null,
        null,
        null,
        null);
  }

  public static SendTransactionParams createTokenTransaction(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String method,
      String unit) {
    return new SendTransactionParams(
        fromAddress,
        password,
        toAddress,
        amount,
        contractAddress,
        unit,
        method,
        null,
        null,
        null,
        null);
  }

  public static SendTransactionParams createTokenTransaction(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String method,
      String unit,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    return new SendTransactionParams(
        fromAddress,
        password,
        toAddress,
        amount,
        contractAddress,
        unit,
        method,
        null,
        gasLimit,
        gasPrice,
        nonce);
  }

  public SendTransactionParams() {}

  public SendTransactionParams(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String data,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    this.fromAddress = fromAddress;
    this.password = password;
    this.toAddress = toAddress;
    this.amount = amount;
    this.contractAddress = contractAddress;
    this.data = data;
    this.gasLimit = gasLimit;
    this.gasPrice = gasPrice;
    this.nonce = nonce;
  }

  public SendTransactionParams(
      String fromAddress,
      String password,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String unit,
      String method,
      String data,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    this.fromAddress = fromAddress;
    this.password = password;
    this.toAddress = toAddress;
    this.amount = amount;
    this.contractAddress = contractAddress;
    if (StringUtils.isNotBlank(unit)) {
      this.unit = new BigDecimal(unit);
    } else {
      this.unit = null;
    }
    if (StringUtils.isNotBlank(method)) {
      this.method = method;
    } else {
      this.method = null;
    }
    this.data = data;
    this.gasLimit = gasLimit;
    this.gasPrice = gasPrice;
    this.nonce = nonce;
  }
}

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
  private EthAccount fromAccount;
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
    return fromAccount.getCredentials();
  }

  public String getFromAddress() {
    return fromAccount.getAddress();
  }

  public static SendTransactionParams createAllEthTransaction(
      EthAccount fromAccount, String toAddress) {
    return new SendTransactionParams(
        fromAccount, toAddress, BigDecimal.ZERO, null, "", null, null, null);
  }

  public static SendTransactionParams createEthTransaction(
      EthAccount fromAccount, String toAddress, BigDecimal amount) {
    return new SendTransactionParams(fromAccount, toAddress, amount, null, "", null, null, null);
  }

  public static SendTransactionParams createEthTransaction(
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    return new SendTransactionParams(
        fromAccount, toAddress, amount, null, "", gasLimit, gasPrice, nonce);
  }

  public static SendTransactionParams createTokenTransaction(
      EthAccount fromAccount, String toAddress, BigDecimal amount, String contractAddress) {
    return new SendTransactionParams(
        fromAccount, toAddress, amount, contractAddress, null, null, null, null, null, null);
  }

  public static SendTransactionParams createTokenTransaction(
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String unit) {
    return new SendTransactionParams(
        fromAccount, toAddress, amount, contractAddress, unit, null, null, null, null, null);
  }

  public static SendTransactionParams createTokenTransaction(
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String method,
      String unit) {
    return new SendTransactionParams(
        fromAccount, toAddress, amount, contractAddress, unit, method, null, null, null, null);
  }

  public static SendTransactionParams createTokenTransaction(
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String method,
      String unit,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    return new SendTransactionParams(
        fromAccount,
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
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String data,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    this.fromAccount = fromAccount;
    this.toAddress = toAddress;
    this.amount = amount;
    this.contractAddress = contractAddress;
    this.data = data;
    this.gasLimit = gasLimit;
    this.gasPrice = gasPrice;
    this.nonce = nonce;
  }

  public SendTransactionParams(
      EthAccount fromAccount,
      String toAddress,
      BigDecimal amount,
      String contractAddress,
      String unit,
      String method,
      String data,
      BigInteger gasLimit,
      BigInteger gasPrice,
      BigInteger nonce) {
    this.fromAccount = fromAccount;
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

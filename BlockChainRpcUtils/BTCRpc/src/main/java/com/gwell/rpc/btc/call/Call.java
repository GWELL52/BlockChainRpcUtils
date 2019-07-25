package com.gwell.rpc.btc.call;

import com.gwell.rpc.btc.call.method.BaseMethod;
import com.gwell.rpc.btc.call.method.TransferMethod;
import com.gwell.rpc.btc.model.response.*;
import com.gwell.rpc.common.model.Connection;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Call {
  private BaseMethod baseMethod;
  private TransferMethod transferMethod;

  public Call(Connection connection) {
    this.baseMethod = BaseMethod.build(connection);
    this.transferMethod = TransferMethod.build(connection);
  }

  public GetInfo getInfo() {
    return baseMethod.getInfo();
  }

  public GetNewAddress getNewAddress(String account) {
    return baseMethod.getNewAddress(account);
  }

  public GetBalance getBalance(String account) {
    return baseMethod.getBalance(account);
  }

  public GetAddressBalance getAddressBalance(String address) {
    return baseMethod.getAddressBalance(address);
  }

  public GetAddresses getAddressesByAccount(String account) {
    return baseMethod.getAddressesByAccount(account);
  }

  public ListTransactions listTransactions(String account, Long limit, Long index) {
    return transferMethod.listTransactions(account, limit, index);
  }

  public SendTransaction sendToAddress(String toAddress, BigDecimal amount) {
    return transferMethod.sendToAddress(toAddress, amount);
  }

  public GetBlockCount getBlockCount() {
    return baseMethod.getBlockCount();
  }

  public GetBlockHash getBlockHash(BigInteger blockCount) {
    return baseMethod.getBlockHash(blockCount);
  }

  public GetBlockInfo getBlockInfo(String blockHash) {
    return baseMethod.getBlockInfo(blockHash);
  }

  public GetTransactionInfo getTransactionInfo(String hash) {
    return transferMethod.getTransactionInfo(hash);
  }

  public GetRawTransactionInfo getRawTransactionInfo(String hash) {
    return transferMethod.getRawTransactionInfo(hash);
  }

  public ListAccounts listAccounts() {
    return baseMethod.listAccounts();
  }

  public GetReceivedInfo getReceivedByAccount(String account) {
    return baseMethod.getReceivedByAccount(account);
  }

  public GetReceivedInfo getReceivedByAddress(String address) {
    return baseMethod.getReceivedByAddress(address);
  }

  public SendTransaction sendRawTransaction(String rawData) {
    return transferMethod.sendRawTransaction(rawData);
  }

  public GetAddressUTXOs getAddressUnspentOutputs(String address) {
    return transferMethod.getAddressUnspentOutputs(address);
  }

  public GetAddressAllHash getAddressAllHash(String address) {
    return transferMethod.getAddressAllHash(address);
  }
}

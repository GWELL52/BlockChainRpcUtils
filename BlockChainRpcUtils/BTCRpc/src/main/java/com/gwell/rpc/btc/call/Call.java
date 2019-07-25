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

  public BtcGetInfo getInfo() {
    return baseMethod.getInfo();
  }

  public BtcGetNewAddress getNewAddress(String account) {
    return baseMethod.getNewAddress(account);
  }

  public BtcGetBalance getBalance(String account) {
    return baseMethod.getBalance(account);
  }

  public BtcGetAddressBalance getAddressBalance(String address) {
    return baseMethod.getAddressBalance(address);
  }

  public BtcGetAddresses getAddressesByAccount(String account) {
    return baseMethod.getAddressesByAccount(account);
  }

  public BtcListTransactions listTransactions(String account, Long limit, Long index) {
    return transferMethod.listTransactions(account, limit, index);
  }

  public BtcSendTransaction sendToAddress(String toAddress, BigDecimal amount) {
    return transferMethod.sendToAddress(toAddress, amount);
  }

  public BtcGetBlockCount getBlockCount() {
    return baseMethod.getBlockCount();
  }

  public BtcGetBlockHash getBlockHash(BigInteger blockCount) {
    return baseMethod.getBlockHash(blockCount);
  }

  public BtcGetBlockInfo getBlockInfo(String blockHash) {
    return baseMethod.getBlockInfo(blockHash);
  }

  public BtcGetTransactionInfo getTransactionInfo(String hash) {
    return transferMethod.getTransactionInfo(hash);
  }

  public BtcGetRawTransactionInfo getRawTransactionInfo(String hash) {
    return transferMethod.getRawTransactionInfo(hash);
  }

  public BtcListAccounts listAccounts() {
    return baseMethod.listAccounts();
  }

  public BtcGetReceivedInfo getReceivedByAccount(String account) {
    return baseMethod.getReceivedByAccount(account);
  }

  public BtcGetReceivedInfo getReceivedByAddress(String address) {
    return baseMethod.getReceivedByAddress(address);
  }

  public BtcSendTransaction sendRawTransaction(String rawData) {
    return transferMethod.sendRawTransaction(rawData);
  }

  public BtcGetAddressUTXOs getAddressUnspentOutputs(String address) {
    return transferMethod.getAddressUnspentOutputs(address);
  }

  public BtcGetAddressAllHash getAddressAllHash(String address) {
    return transferMethod.getAddressAllHash(address);
  }
}

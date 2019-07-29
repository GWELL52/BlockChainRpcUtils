package com.gwell.rpc.btc.call;

import com.gwell.rpc.btc.call.method.*;
import com.gwell.rpc.btc.model.response.*;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Call {
  private BaseMethod baseMethod;
  private AccountMethod accountMethod;
  private BalanceMethod balanceMethod;
  private TransferMethod transferMethod;
  private TransactionInfoMethod transactionInfoMethod;

  public Call(Connection connection, BlockChainEnum blockChain) {
    this.baseMethod = BaseMethod.build(connection, blockChain);
    this.accountMethod = AccountMethod.build(connection, blockChain);
    this.balanceMethod = BalanceMethod.build(connection, blockChain);
    this.transferMethod = TransferMethod.build(connection, blockChain);
    this.transactionInfoMethod = TransactionInfoMethod.build(connection, blockChain);
  }

  public GetInfo getInfo() {
    return baseMethod.getInfo();
  }

  public GetNewAddress getNewAddress(String account) {
    return accountMethod.getNewAddress(account);
  }

  public GetBalance getBalance(String account) {
    return balanceMethod.getBalance(account);
  }

  public GetAddressBalance getAddressBalance(String address) {
    return balanceMethod.getAddressBalance(address);
  }

  public GetAddresses getAddressesByAccount(String account) {
    return accountMethod.getAddressesByAccount(account);
  }

  public ListTransactions listTransactions(String account, Long limit, Long index) {
    return transactionInfoMethod.listTransactions(account, limit, index);
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
    return transactionInfoMethod.getTransactionInfo(hash);
  }

  public GetRawTransactionInfo getRawTransactionInfo(String hash) {
    return transactionInfoMethod.getRawTransactionInfo(hash);
  }

  public ListAccounts listAccounts() {
    return accountMethod.listAccounts();
  }

  public GetReceivedInfo getReceivedByAccount(String account) {
    return balanceMethod.getReceivedByAccount(account);
  }

  public GetReceivedInfo getReceivedByAddress(String address) {
    return balanceMethod.getReceivedByAddress(address);
  }

  public SendTransaction sendRawTransaction(String rawData) {
    return transferMethod.sendRawTransaction(rawData);
  }

  public GetAddressUTXOs getAddressUnspentOutputs(String address) {
    return balanceMethod.getAddressUnspentOutputs(address);
  }

  public GetAddressAllHash getAddressAllHash(String address) {
    return transactionInfoMethod.getAddressAllHash(address);
  }
}

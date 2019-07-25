package com.gwell.rpc.eth.call;

import com.gwell.rpc.eth.call.method.*;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.eth.model.request.BalanceParams;
import com.gwell.rpc.eth.model.request.SendTransactionParams;
import com.gwell.rpc.eth.model.response.ETHFee;
import com.gwell.rpc.eth.model.response.ETHTransactionInfo;
import com.gwell.rpc.eth.model.response.EthAccount;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Call {
  private BaseMethod baseMethod;
  private AccountMethod accountMethod;
  private BalanceMethod balanceMethod;
  private TokenMethod tokenMethod;
  private TransferMethod transferMethod;

  public Call(Connection connection, byte chainId, String keystorePath) {
    Web3j web3j =
        Web3j.build(new HttpService(connection.getUrlBuilder().toString(), connection.getClient()));
    this.baseMethod = BaseMethod.build(web3j);
    this.accountMethod = AccountMethod.build(keystorePath);
    this.balanceMethod = BalanceMethod.build(web3j);
    this.tokenMethod = TokenMethod.build(web3j);
    this.transferMethod = TransferMethod.build(web3j, chainId);
  }

  /* 基础方法 */
  /** 返回当前最高区块 */
  public BigInteger getBlockNumber() {
    return baseMethod.getBlockNumber();
  }

  /** 获取区块信息 */
  public EthBlock.Block getBlockInfo(BigInteger blockNumber, boolean returnTransactionInfo) {
    return baseMethod.getBlockInfo(blockNumber, returnTransactionInfo);
  }

  /** 获取地址的 nonce */
  public BigInteger getNonce(String address) {
    return baseMethod.getNonce(address);
  }

  /** 获取gasPrice 单位:wei */
  public BigInteger getGasPrice() {
    return baseMethod.getGasPrice();
  }

  /** 获取GasLimit */
  public BigInteger getGasLimit(SendTransactionParams params) {
    return baseMethod.getGasLimit(params);
  }

  /* 交易信息相关方法 */
  /** 根据Hash获取交易 */
  public Transaction getTransactionByHash(String hash) {
    return baseMethod.getTransactionByHash(hash);
  }

  /** 已确认的交易详情 */
  public TransactionReceipt getTransactionReceipt(String hash) {
    return baseMethod.getTransactionReceipt(hash);
  }

  /** 获取交易详情 */
  public ETHTransactionInfo getTransactionAllInfo(String hash, boolean getTransferTime) {
    return baseMethod.getTransactionAllInfo(hash, getTransferTime);
  }

  /* 账号相关方法 */
  /** 创建地址 - keystore */
  public EthAccount newAccount(String password) {
    return accountMethod.newAccount(password);
  }

  /** 导入私钥 */
  public EthAccount importPrivateKey(EthAccount ethAccount) {
    return accountMethod.importPrivateKey(ethAccount);
  }

  /* 余额相关方法 */
  /** 获取余额 */
  public BigDecimal getBalance(BalanceParams params) {
    return balanceMethod.getBalance(params);
  }

  /* 合约相关方法 */
  /** 格式化发送合约交易的 data */
  public String getTransactionData(SendTransactionParams params) {
    return tokenMethod.getTransactionData(params);
  }

  /** 获取合约精度 */
  public Integer getTokenDecimal(String contractAddress) {
    return tokenMethod.getTokenDecimal(contractAddress);
  }

  /** 获取合约Unit(100000......) */
  public BigDecimal getTokenUnit(String contractAddress) {
    return tokenMethod.getTokenUnit(contractAddress);
  }

  /* 交易相关方法 */
  /** 获取交易预计手续费 */
  public ETHFee getTransactionFee(SendTransactionParams params) {
    return transferMethod.getTransactionFee(params);
  }

  /** 签名交易 */
  public String signTransaction(SendTransactionParams params) {
    return transferMethod.signTransaction(params);
  }

  /** 发送全部ETH交易 */
  public String sendAllEth(SendTransactionParams params) {
    return transferMethod.sendAllEth(params);
  }

  /** 发送交易 - 需要存在keystore文件 */
  public String sendTransaction(SendTransactionParams params) {
    return transferMethod.sendTransaction(params);
  }

  /** 发送已签名的交易 */
  public String sendRawTransaction(String signedData) {
    return transferMethod.sendRawTransaction(signedData);
  }
}

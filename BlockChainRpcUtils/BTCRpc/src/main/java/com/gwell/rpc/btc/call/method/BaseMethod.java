package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.*;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.Collections;

public class BaseMethod {
  private Connection connection;

  private BaseMethod(Connection connection) {
    this.connection = connection;
  }

  public static BaseMethod build(Connection connection) {
    return new BaseMethod(connection);
  }

  /** 获取服务器版本余额信息 */
  public GetInfo getInfo() {
    return Request.rpc(connection, "getinfo", null, GetInfo.class).send();
  }

  /**
   * 创建地址
   *
   * @param account 备注账号名
   */
  public GetNewAddress getNewAddress(String account) {
    return Request.rpc(
            connection, "getnewaddress", Collections.singletonList(account), GetNewAddress.class)
        .send();
  }

  /**
   * 根据账号获取余额
   *
   * @param account 账号
   */
  public GetBalance getBalance(String account) {
    return Request.rpc(
            connection,
            "getbalance",
            account == null ? null : Collections.singletonList(account),
            GetBalance.class)
        .send();
  }

  /**
   * 根据地址获取余额
   *
   * @param address 地址
   */
  public GetAddressBalance getAddressBalance(@NonNull String address) {
    return Request.rpc(
            connection,
            "getaddressbalance",
            Collections.singletonList(address),
            GetAddressBalance.class)
        .send();
  }

  /**
   * 获取同账号下的地址列表
   *
   * @param account 账号
   * @return List 地址集合
   */
  public GetAddresses getAddressesByAccount(@NonNull String account) {
    return Request.rpc(
            connection,
            "getaddressesbyaccount",
            Collections.singletonList(account),
            GetAddresses.class)
        .send();
  }

  /** 获取服务器最新高度 */
  public GetBlockCount getBlockCount() {
    return Request.rpc(connection, "getblockcount", null, GetBlockCount.class).send();
  }

  /**
   * 根据高度或者区块哈希
   *
   * @param blockCount 区块高度
   */
  public GetBlockHash getBlockHash(BigInteger blockCount) {
    return Request.rpc(
            connection,
            "getblockhash",
            Collections.singletonList(blockCount),
            GetBlockHash.class)
        .send();
  }

  /**
   * 根据区块哈希获取区块信息
   *
   * @param blockHash 区块哈希
   */
  public GetBlockInfo getBlockInfo(String blockHash) {
    return Request.rpc(
            connection, "getblock", Collections.singletonList(blockHash), GetBlockInfo.class)
        .send();
  }

  /** 获取钱包中所有账号 */
  public ListAccounts listAccounts() {
    return Request.rpc(connection, "listaccounts", null, ListAccounts.class).send();
  }

  /**
   * 根据账号获取总接收数值
   *
   * @param account 账号
   */
  public GetReceivedInfo getReceivedByAccount(String account) {
    return Request.rpc(
            connection,
            "getreceivedbyaccount",
            Collections.singletonList(account),
            GetReceivedInfo.class)
        .send();
  }

  /**
   * 根据地址获取总接收数值
   *
   * @param address 地址(钱包管理的地址)
   */
  public GetReceivedInfo getReceivedByAddress(String address) {
    return Request.rpc(
            connection,
            "getreceivedbyaddress",
            Collections.singletonList(address),
            GetReceivedInfo.class)
        .send();
  }
}

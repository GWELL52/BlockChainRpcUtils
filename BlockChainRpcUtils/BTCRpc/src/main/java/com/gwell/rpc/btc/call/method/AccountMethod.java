package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.GetAddresses;
import com.gwell.rpc.btc.model.response.GetNewAddress;
import com.gwell.rpc.btc.model.response.ListAccounts;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import lombok.NonNull;

import java.util.Collections;

public class AccountMethod extends SuperMethod {

  public static AccountMethod build(Connection connection, BlockChainEnum blockChain) {
    AccountMethod instance = new AccountMethod();
    instance.init(connection, blockChain);
    return instance;
  }

  private AccountMethod() {}

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
   * 获取账号下的地址列表
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

  /** 获取钱包中所有账号 */
  public ListAccounts listAccounts() {
    return Request.rpc(connection, "listaccounts", null, ListAccounts.class).send();
  }
}

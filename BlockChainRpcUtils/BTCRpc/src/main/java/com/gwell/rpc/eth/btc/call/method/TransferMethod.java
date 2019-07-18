package com.gwell.rpc.eth.btc.call.method;

import com.gwell.rpc.eth.btc.model.response.BtcListTransactions;
import com.gwell.rpc.eth.common.model.Connection;
import com.gwell.rpc.eth.common.model.Request;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class TransferMethod {

  private Connection connection;

  private TransferMethod(Connection connection) {
    this.connection = connection;
  }

  public static TransferMethod build(Connection connection) {
    return new TransferMethod(connection);
  }

  /**
   * 获取交易信息集合
   *
   * @param account 账号 null=*
   * @param limit 限制结果大小 default=1000
   * @param index 起始下标 default=0
   */
  public BtcListTransactions listTransactions(String account, Long limit, Long index) {
    if (StringUtils.isBlank(account)) {
      account = "*";
    }
    if (limit == null || limit > 1000) {
      limit = 1000L;
    }
    if (index == null || index < 0) {
      index = 0L;
    }
    return Request.rpc(
            connection,
            "listtransactions",
            Arrays.asList(account, limit, index),
            BtcListTransactions.class)
        .send();
  }
}

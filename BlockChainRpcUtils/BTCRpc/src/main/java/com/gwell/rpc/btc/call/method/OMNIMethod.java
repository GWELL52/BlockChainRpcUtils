package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.SendTransaction;
import com.gwell.rpc.btc.model.response.omni.GetOMNIBalance;
import com.gwell.rpc.btc.model.response.omni.GetOMNITransactionInfo;
import com.gwell.rpc.btc.model.response.omni.GetOMNIWalletAddressBalance;
import com.gwell.rpc.btc.model.response.omni.ListOMNITransactions;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class OMNIMethod extends SuperMethod {

  public static OMNIMethod build(Connection connection, BlockChainEnum blockChain) {
    OMNIMethod instance = new OMNIMethod();
    instance.init(connection, blockChain);
    return instance;
  }

  private OMNIMethod() {}

  /**
   * 获取propertyId币种余额
   *
   * @param address 地址
   * @param propertyId 币种propertyId USDT=31
   */
  public GetOMNIBalance getOMNIBalance(String address, Integer propertyId) {
    return Request.rpc(
            connection, "omni_getbalance", Arrays.asList(address, propertyId), GetOMNIBalance.class)
        .send();
  }

  /** 获取钱包中所有代币的余额,以地址为key */
  public GetOMNIWalletAddressBalance getOMNIWalletAddressBalance() {
    return Request.rpc(
            connection, "omni_getwalletaddressbalances", null, GetOMNIWalletAddressBalance.class)
        .send();
  }

  /**
   * 获取交易信息集合
   *
   * @param address 地址 null=*
   * @param limit 限制结果大小 default=1000
   * @param index 起始下标 default=0
   * @param startBlock 开始区块 default=0
   * @param endBlock 结束区块 default=999999999
   */
  public ListOMNITransactions listOMNITransactions(
      String address, Long limit, Long index, BigInteger startBlock, BigInteger endBlock) {
    if (StringUtils.isBlank(address)) {
      address = "*";
    }
    if (limit == null || limit > 1000) {
      limit = 1000L;
    }
    if (index == null || index < 0) {
      index = 0L;
    }
    if (startBlock == null || startBlock.compareTo(BigInteger.ZERO) < 0) {
      startBlock = BigInteger.ZERO;
    }
    if (endBlock == null || endBlock.compareTo(BigInteger.ZERO) < 0) {
      endBlock = new BigInteger("999999999");
    }
    return Request.rpc(
            connection,
            "omni_listtransactions",
            Arrays.asList(address, limit, index, startBlock, endBlock),
            ListOMNITransactions.class)
        .send();
  }

  /** 根据哈希获取交易信息 */
  public GetOMNITransactionInfo getOMNITransactionInfo(String hash) {
    return Request.rpc(
            connection,
            "omni_gettransaction",
            Collections.singletonList(hash),
            GetOMNITransactionInfo.class)
        .send();
  }

  /**
   * 转账给某个地址
   *
   * @param fromAddress 发送地址
   * @param toAddress 接收地址
   * @param propertyId 币种propertyId USDT=31
   * @param amount 数量
   * @param feeAddress 手续费支出地址
   */
  public SendTransaction sendOMNITo(
      String fromAddress,
      String toAddress,
      Integer propertyId,
      BigDecimal amount,
      String feeAddress) {
    if (StringUtils.isBlank(feeAddress)) {
      feeAddress = fromAddress;
    }
    return Request.rpc(
            connection,
            "omni_funded_send",
            Arrays.asList(
                fromAddress,
                toAddress,
                propertyId,
                amount.stripTrailingZeros().toPlainString(),
                feeAddress),
            SendTransaction.class)
        .send();
  }
}

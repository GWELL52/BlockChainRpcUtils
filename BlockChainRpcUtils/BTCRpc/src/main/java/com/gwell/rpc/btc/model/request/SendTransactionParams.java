package com.gwell.rpc.btc.model.request;

import com.gwell.rpc.btc.model.response.result.UTXOInfo;
import com.gwell.rpc.common.model.BlockChainWallet;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/** 发送交易参数类 */
@Data
public class SendTransactionParams {
  private BlockChainWallet fromAccount;
  private String toAddress;
  private BigDecimal amount;

  private BigDecimal fee;

  private List<UTXOInfo> UTXOInfoList;

  private boolean testNet;

  public long getRawAmount(BigDecimal unit) {
    return amount.multiply(unit).longValueExact();
  }

  public long getRawFee(BigDecimal unit) {
    return fee.multiply(unit).longValueExact();
  }
}

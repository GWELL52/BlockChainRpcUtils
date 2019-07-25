package com.gwell.rpc.btc.model.response.result;

import com.gwell.rpc.common.enums.BlockChainEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceInfo {
  private BigDecimal balance;
  private BigDecimal received;

  public BigDecimal getBalance() {
    return balance.divide(BlockChainEnum.BTC.getDecimal());
  }
}

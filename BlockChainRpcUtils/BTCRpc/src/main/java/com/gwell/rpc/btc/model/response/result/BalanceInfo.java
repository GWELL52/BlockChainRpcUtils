package com.gwell.rpc.btc.model.response.result;

import com.gwell.rpc.common.enums.BlockChainEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceInfo {
  private BigDecimal rawBalance;
  private BigDecimal received;

  public BigDecimal getBalance(BlockChainEnum chainEnum) {
    return rawBalance.divide(chainEnum.getUnit(), chainEnum.getDecimal(), BigDecimal.ROUND_DOWN);
  }
}

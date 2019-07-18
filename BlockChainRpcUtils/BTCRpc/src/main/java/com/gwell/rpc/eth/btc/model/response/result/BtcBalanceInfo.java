package com.gwell.rpc.eth.btc.model.response.result;

import com.gwell.rpc.eth.common.enums.BlockChainEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BtcBalanceInfo {
  private BigDecimal balance;
  private BigDecimal received;

  public BigDecimal getBalance() {
    return balance.divide(BlockChainEnum.BTC.getDecimal());
  }
}

package com.gwell.rpc.common.enums;

import java.math.BigDecimal;

public enum BlockChainEnum {
  BTC(BigDecimal.TEN.pow(8)),
  ETH(BigDecimal.TEN.pow(18)),
  BCH(BigDecimal.TEN.pow(8)),
  DASH(BigDecimal.TEN.pow(8)),
  LTC(BigDecimal.TEN.pow(8)),
  OMNI(BigDecimal.TEN.pow(8)),
  ETC(BigDecimal.TEN.pow(18)),
  VNS(BigDecimal.TEN.pow(18)),
  ;

  private BigDecimal decimal;

  BlockChainEnum(BigDecimal decimal) {
    this.decimal = decimal;
  }

  public String lowerCaseName() {
    return name().toLowerCase();
  }

  public BigDecimal getDecimal() {
    return decimal;
  }
}

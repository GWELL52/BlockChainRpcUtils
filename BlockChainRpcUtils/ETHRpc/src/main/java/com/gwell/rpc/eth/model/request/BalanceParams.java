package com.gwell.rpc.eth.model.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/** 获取余额参数类 */
@Data
public class BalanceParams {
  private String address;
  private String contractAddress;
  private BigDecimal unit;

  public static BalanceParams createEthBalance(String address) {
    return new BalanceParams(address, null, null);
  }

  /** unit 为空 则会调用 getTokenUnit() 获取合约精度 */
  public static BalanceParams createTokenBalance(String address, String contractAddress) {
    return new BalanceParams(address, contractAddress, null);
  }

  public static BalanceParams createTokenBalance(
      String address, String contractAddress, String unit) {
    return new BalanceParams(address, contractAddress, unit);
  }

  public BalanceParams(String address, String contractAddress, String unit) {
    this.address = address;
    this.contractAddress = contractAddress;
    if (StringUtils.isNotBlank(unit)) {
      this.unit = new BigDecimal(unit);
    } else {
      this.unit = null;
    }
  }
}

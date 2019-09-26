package com.gwell.rpc.btc.model.response.result.omni;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OMNIBalanceInfo {
  @JSONField(alternateNames = "propertyid")
  private Integer propertyId;

  private String name;
  private BigDecimal balance;
  private BigDecimal reserved;
  private BigDecimal frozen;
}

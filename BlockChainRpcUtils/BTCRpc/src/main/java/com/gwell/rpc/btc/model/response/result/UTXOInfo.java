package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.gwell.rpc.common.enums.BlockChainEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class UTXOInfo {
  @JSONField(alternateNames = "address")
  private String address;

  @JSONField(alternateNames = "txid")
  private String hash;

  @JSONField(alternateNames = "outputIndex")
  private Integer outputIndex;

  @JSONField(alternateNames = "script")
  private String script;

  @JSONField(alternateNames = "satoshis")
  private Long rawValue;

  @JSONField(alternateNames = "height")
  private BigInteger height;

  public BigDecimal getValue(BlockChainEnum chainEnum) {
    return BigDecimal.valueOf(rawValue)
        .divide(chainEnum.getUnit(), chainEnum.getDecimal(), BigDecimal.ROUND_DOWN);
  }
}

package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;

@Data
public class BtcUTXOInfo {
  @JSONField(name = "address")
  private String address;

  @JSONField(name = "txid")
  private String hash;

  @JSONField(name = "outputIndex")
  private Integer outputIndex;

  @JSONField(name = "script")
  private String script;

  @JSONField(name = "satoshis")
  private Long satoshis;

  @JSONField(name = "height")
  private BigInteger height;
}

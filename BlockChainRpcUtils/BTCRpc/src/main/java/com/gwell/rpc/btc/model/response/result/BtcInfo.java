package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class BtcInfo {
  @JSONField(name = "version", alternateNames = "版本")
  private Long version;

  @JSONField(name = "protocolversion")
  private Long protocolVersion;

  @JSONField(name = "walletversion")
  private Long walletVersion;

  @JSONField(name = "balance")
  private BigDecimal balance;

  @JSONField(name = "blocks")
  private BigInteger blocks;

  @JSONField(name = "timeoffset")
  private Long timeOffset;

  @JSONField(name = "connections")
  private Long connections;

  @JSONField(name = "proxy")
  private String proxy;

  @JSONField(name = "difficulty")
  private Double difficulty;

  @JSONField(name = "testnet")
  private Boolean testNet;

  @JSONField(name = "keypoololdest")
  private Long keyPoolOldest;

  @JSONField(name = "keypoolsize")
  private Long keyPoolSize;

  @JSONField(name = "paytxfee")
  private BigDecimal payTxFee;

  @JSONField(name = "relayfee")
  private BigDecimal relayFee;

  @JSONField(name = "errors")
  private String errors;
}

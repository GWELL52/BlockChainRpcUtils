package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class WalletInfo {
  @JSONField(alternateNames = "version")
  private Long version;

  @JSONField(alternateNames = "protocolversion")
  private Long protocolVersion;

  @JSONField(alternateNames = "walletversion")
  private Long walletVersion;

  @JSONField(alternateNames = "balance")
  private BigDecimal balance;

  @JSONField(alternateNames = "blocks")
  private BigInteger blocks;

  @JSONField(alternateNames = "timeoffset")
  private Long timeOffset;

  @JSONField(alternateNames = "connections")
  private Long connections;

  @JSONField(alternateNames = "proxy")
  private String proxy;

  @JSONField(alternateNames = "difficulty")
  private Double difficulty;

  @JSONField(alternateNames = "testnet")
  private Boolean testNet;

  @JSONField(alternateNames = "keypoololdest")
  private Long keyPoolOldest;

  @JSONField(alternateNames = "keypoolsize")
  private Long keyPoolSize;

  @JSONField(alternateNames = "paytxfee")
  private BigDecimal payTxFee;

  @JSONField(alternateNames = "relayfee")
  private BigDecimal relayFee;

  @JSONField(alternateNames = "errors")
  private String errors;
}

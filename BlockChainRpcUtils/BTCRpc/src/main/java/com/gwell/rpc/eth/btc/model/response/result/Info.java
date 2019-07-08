package com.gwell.rpc.eth.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class Info {
  @JSONField(name = "version", alternateNames = "版本")
  private long version;

  @JSONField(name = "protocolversion")
  private long protocolVersion;

  @JSONField(name = "walletversion")
  private long walletVersion;

  @JSONField(name = "balance")
  private BigDecimal balance;

  @JSONField(name = "blocks")
  private BigInteger blocks;

  @JSONField(name = "timeoffset")
  private long timeOffset;

  @JSONField(name = "connections")
  private long connections;

  @JSONField(name = "proxy")
  private String proxy;

  @JSONField(name = "difficulty")
  private double difficulty;

  @JSONField(name = "testnet")
  private boolean testNet;

  @JSONField(name = "keypoololdest")
  private long keyPoolOldest;

  @JSONField(name = "keypoolsize")
  private long keyPoolSize;

  @JSONField(name = "paytxfee")
  private BigDecimal payTxFee;

  @JSONField(name = "relayfee")
  private BigDecimal relayFee;

  @JSONField(name = "errors")
  private String errors;
}

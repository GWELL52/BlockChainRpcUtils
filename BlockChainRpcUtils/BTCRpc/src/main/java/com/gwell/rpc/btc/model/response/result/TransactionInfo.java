package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class TransactionInfo {
  @JSONField(alternateNames = "account")
  private String account;

  @JSONField(alternateNames = "address")
  private String address;

  @JSONField(alternateNames = "category")
  private String category;

  @JSONField(alternateNames = "amount")
  private BigDecimal amount;

  @JSONField(alternateNames = "label")
  private String label;

  @JSONField(alternateNames = "vout")
  private Integer vout;

  @JSONField(alternateNames = "confirmations")
  private Long confirmations;

  @JSONField(alternateNames = "blockhash")
  private String blockHash;

  @JSONField(alternateNames = "blockindex")
  private BigInteger blockNumber;

  @JSONField(alternateNames = "blocktime", format = "unixtime")
  private Date blockTime;

  @JSONField(alternateNames = "txid")
  private String txHash;

  @JSONField(alternateNames = "walletconflicts")
  private List<String> walletConflicts;

  @JSONField(alternateNames = "time", format = "unixtime")
  private Date time;

  @JSONField(alternateNames = "timereceived", format = "unixtime")
  private Date timeReceived;

  @JSONField(alternateNames = "bip125-replaceable")
  private String bip125Replaceable;

  @JSONField(alternateNames = "details")
  private List<Details> details;

  @JSONField(alternateNames = "hex")
  private String hex;

  @Data
  public class Details {
    @JSONField(alternateNames = "account")
    private String account;

    @JSONField(alternateNames = "address")
    private String address;

    @JSONField(alternateNames = "category")
    private String category;

    @JSONField(alternateNames = "amount")
    private BigDecimal amount;

    @JSONField(alternateNames = "label")
    private String label;

    @JSONField(alternateNames = "vout")
    private Integer vout;
  }
}

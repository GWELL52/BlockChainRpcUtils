package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class TransactionInfo {
  @JSONField(name = "account")
  private String account;

  @JSONField(name = "address")
  private String address;

  @JSONField(name = "category")
  private String category;

  @JSONField(name = "amount")
  private BigDecimal amount;

  @JSONField(name = "label")
  private String label;

  @JSONField(name = "vout")
  private Integer vout;

  @JSONField(name = "confirmations")
  private Long confirmations;

  @JSONField(name = "blockhash")
  private String blockHash;

  @JSONField(name = "blockindex")
  private BigInteger blockNumber;

  @JSONField(name = "blocktime", format = "unixtime")
  private Date blockTime;

  @JSONField(name = "txid")
  private String txHash;

  @JSONField(name = "walletconflicts")
  private List<String> walletConflicts;

  @JSONField(name = "time", format = "unixtime")
  private Date time;

  @JSONField(name = "timereceived", format = "unixtime")
  private Date timeReceived;

  @JSONField(name = "bip125-replaceable")
  private String bip125Replaceable;

  @JSONField(name = "details")
  private List<Details> details;

  @JSONField(name = "hex")
  private String hex;

  @Data
  public class Details {
    @JSONField(name = "account")
    private String account;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "category")
    private String category;

    @JSONField(name = "amount")
    private BigDecimal amount;

    @JSONField(name = "label")
    private String label;

    @JSONField(name = "vout")
    private Integer vout;
  }
}

package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class BlockInfo {
  @JSONField(alternateNames = "hash")
  private String hash;

  @JSONField(alternateNames = "confirmations")
  private Long confirmations;

  @JSONField(alternateNames = "size")
  private Long size;

  @JSONField(alternateNames = "height")
  private BigInteger height;

  @JSONField(alternateNames = "version")
  private Long version;

  @JSONField(alternateNames = "merkleroot")
  private String merkleRoot;

  @JSONField(alternateNames = "tx")
  private List<String> transactionHashList;

  @JSONField(alternateNames = "time", format = "unixtime")
  private Date time;

  @JSONField(alternateNames = "mediantime", format = "unixtime")
  private Date medianTime;

  @JSONField(alternateNames = "nonce")
  private BigInteger nonce;

  @JSONField(alternateNames = "bits")
  private String bits;

  @JSONField(alternateNames = "difficulty")
  private Integer difficulty;

  @JSONField(alternateNames = "chainwork")
  private String chainWork;

  @JSONField(alternateNames = "previousblockhash")
  private String previousBlockHash;

  @JSONField(alternateNames = "nextblockhash")
  private String nextBlockHash;
}

package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class BtcBlockInfo {
  @JSONField(name = "hash")
  private String hash;

  @JSONField(name = "confirmations")
  private Long confirmations;

  @JSONField(name = "size")
  private Long size;

  @JSONField(name = "height")
  private BigInteger height;

  @JSONField(name = "version")
  private Long version;

  @JSONField(name = "merkleroot")
  private String merkleRoot;

  @JSONField(name = "tx")
  private List<String> transactionHashList;

  @JSONField(name = "time", format = "unixtime")
  private Date time;

  @JSONField(name = "mediantime", format = "unixtime")
  private Date medianTime;

  @JSONField(name = "nonce")
  private BigInteger nonce;

  @JSONField(name = "bits")
  private String bits;

  @JSONField(name = "difficulty")
  private Integer difficulty;

  @JSONField(name = "chainwork")
  private String chainWork;

  @JSONField(name = "previousblockhash")
  private String previousBlockHash;

  @JSONField(name = "nextblockhash")
  private String nextBlockHash;
}

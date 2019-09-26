package com.gwell.rpc.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class RawTransactionInfo {
  @JSONField(alternateNames = "hex")
  private String hex;

  @JSONField(alternateNames = "txid")
  private String hash;

  @JSONField(alternateNames = "size")
  private Long size;

  @JSONField(alternateNames = "version")
  private Long version;

  @JSONField(alternateNames = "locktime")
  private Long lockTime;

  @JSONField(alternateNames = "vin")
  private List<Vin> vin;

  @JSONField(alternateNames = "vout")
  private List<Vout> vout;

  @JSONField(alternateNames = "blockhash")
  private String blockHash;

  @JSONField(alternateNames = "height")
  private BigInteger height;

  @JSONField(alternateNames = "confirmations")
  private Long confirmations;

  @JSONField(alternateNames = "time", format = "unixtime")
  private Date time;

  @JSONField(alternateNames = "blocktime", format = "unixtime")
  private Date blockTime;

  @Data
  public class Vin {
    @JSONField(alternateNames = "txid")
    private String hash;

    @JSONField(alternateNames = "vout")
    private Integer vout;

    @JSONField(alternateNames = "scriptSig")
    private ScriptSig scriptSig;

    @JSONField(alternateNames = "value")
    private BigDecimal value;

    @JSONField(alternateNames = "valueSat")
    private BigInteger valueSat;

    @JSONField(alternateNames = "sequence")
    private Long sequence;

    @Data
    public class ScriptSig {
      @JSONField(alternateNames = "asm")
      private String asm;

      @JSONField(alternateNames = "hex")
      private String hex;
    }
  }

  @Data
  public class Vout {
    @JSONField(alternateNames = "value")
    private BigDecimal value;

    @JSONField(alternateNames = "valueSat")
    private BigInteger valueSat;

    @JSONField(alternateNames = "n")
    private Integer n;

    @JSONField(alternateNames = "scriptPubKey")
    private ScriptPubKey scriptPubKey;

    @JSONField(alternateNames = "spentTxId")
    private String spentTxId;

    @JSONField(alternateNames = "spentIndex")
    private Integer spentIndex;

    @JSONField(alternateNames = "spentHeight")
    private Long spentHeight;

    @Data
    public class ScriptPubKey {
      @JSONField(alternateNames = "asm")
      private String asm;

      @JSONField(alternateNames = "hex")
      private String hex;

      @JSONField(alternateNames = "reqSigs")
      private Integer reqSigs;

      @JSONField(alternateNames = "type")
      private String type;

      @JSONField(alternateNames = "addresses")
      private List<String> addresses;
    }
  }
}

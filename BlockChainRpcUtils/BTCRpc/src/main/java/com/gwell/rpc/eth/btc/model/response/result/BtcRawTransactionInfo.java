package com.gwell.rpc.eth.btc.model.response.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class BtcRawTransactionInfo {
  @JSONField(name = "hex")
  private String hex;

  @JSONField(name = "txid")
  private String hash;

  @JSONField(name = "size")
  private Long size;

  @JSONField(name = "version")
  private Long version;

  @JSONField(name = "locktime")
  private Long lockTime;

  @JSONField(name = "vin")
  private List<Vin> vin;

  @JSONField(name = "vout")
  private List<Vout> vout;

  @JSONField(name = "blockhash")
  private String blockHash;

  @JSONField(name = "height")
  private BigInteger height;

  @JSONField(name = "confirmations")
  private Long confirmations;

  @JSONField(name = "time", format = "unixtime")
  private Date time;

  @JSONField(name = "blocktime", format = "unixtime")
  private Date blockTime;

  @Data
  public class Vin {
    @JSONField(name = "txid")
    private String hash;

    @JSONField(name = "vout")
    private Integer vout;

    @JSONField(name = "scriptSig")
    private ScriptSig scriptSig;

    @JSONField(name = "value")
    private BigDecimal value;

    @JSONField(name = "valueSat")
    private BigInteger valueSat;

    @JSONField(name = "sequence")
    private Long sequence;

    @Data
    public class ScriptSig {
      @JSONField(name = "asm")
      private String asm;

      @JSONField(name = "hex")
      private String hex;
    }
  }

  @Data
  public class Vout {
    @JSONField(name = "value")
    private BigDecimal value;

    @JSONField(name = "valueSat")
    private BigInteger valueSat;

    @JSONField(name = "n")
    private Integer n;

    @JSONField(name = "scriptPubKey")
    private ScriptPubKey scriptPubKey;

    @JSONField(name = "spentTxId")
    private String spentTxId;

    @JSONField(name = "spentIndex")
    private Integer spentIndex;

    @JSONField(name = "spentHeight")
    private Long spentHeight;

    @Data
    public class ScriptPubKey {
      @JSONField(name = "asm")
      private String asm;

      @JSONField(name = "hex")
      private String hex;

      @JSONField(name = "reqSigs")
      private Integer reqSigs;

      @JSONField(name = "type")
      private String type;

      @JSONField(name = "addresses")
      private List<String> addresses;
    }
  }
}

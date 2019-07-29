package com.gwell.rpc.btc.model.response.result.omni;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class OMNITransactionInfo {
  @JSONField(name = "txid")
  private String hash;

  @JSONField(name = "fee")
  private String fee;

  @JSONField(name = "sendingaddress")
  private String formAddress;

  @JSONField(name = "referenceaddress")
  private String toAddress;

  /** 交易是否涉及钱包中的地址 */
  @JSONField(name = "ismine")
  private boolean isMine;

  @JSONField(name = "version")
  private Long version;

  @JSONField(name = "type_int")
  private Long typeInt;

  @JSONField(name = "type")
  private String type;

  @JSONField(name = "propertyid")
  private Integer propertyId;

  /** Token是否可以整除 */
  @JSONField(name = "divisible")
  private boolean divisible;

  @JSONField(name = "amount")
  private BigDecimal amount;

  @JSONField(name = "valid")
  private boolean valid;

  @JSONField(name = "blockhash")
  private String blockHash;

  @JSONField(name = "blocktime", format = "unixtime")
  private Date blockTime;

  /** 在当前块的哪个位置 */
  @JSONField(name = "positioninblock")
  private Long positionInBlock;

  @JSONField(name = "block")
  private BigInteger blockNumber;

  @JSONField(name = "confirmations")
  private Long confirmations;
}

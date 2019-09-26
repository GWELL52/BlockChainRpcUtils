package com.gwell.rpc.btc.model.response.result.omni;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class OMNITransactionInfo {
  @JSONField(alternateNames = "txid")
  private String hash;

  @JSONField(alternateNames = "fee")
  private String fee;

  @JSONField(alternateNames = "sendingaddress")
  private String formAddress;

  @JSONField(alternateNames = "referenceaddress")
  private String toAddress;

  /** 交易是否涉及钱包中的地址 */
  @JSONField(alternateNames = "ismine")
  private boolean isMine;

  @JSONField(alternateNames = "version")
  private Long version;

  @JSONField(alternateNames = "type_int")
  private Long typeInt;

  @JSONField(alternateNames = "type")
  private String type;

  @JSONField(alternateNames = "propertyid")
  private Integer propertyId;

  /** Token是否可以整除 */
  @JSONField(alternateNames = "divisible")
  private boolean divisible;

  @JSONField(alternateNames = "amount")
  private BigDecimal amount;

  @JSONField(alternateNames = "valid")
  private boolean valid;

  @JSONField(alternateNames = "blockhash")
  private String blockHash;

  @JSONField(alternateNames = "blocktime", format = "unixtime")
  private Date blockTime;

  /** 在当前块的哪个位置 */
  @JSONField(alternateNames = "positioninblock")
  private Long positionInBlock;

  @JSONField(alternateNames = "block")
  private BigInteger blockNumber;

  @JSONField(alternateNames = "confirmations")
  private Long confirmations;
}

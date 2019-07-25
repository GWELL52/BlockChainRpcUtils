package com.gwell.rpc.eth.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/** 以太坊手续费 返回类 */
@Data
public class ETHFee {

  private BigInteger gasLimit;

  private BigInteger gasPrice;

  private BigDecimal transactionFee;

  public ETHFee(BigInteger gasLimit, BigInteger gasPrice, BigDecimal transactionFee) {
    this.gasLimit = gasLimit;
    this.gasPrice = gasPrice;
    this.transactionFee = transactionFee;
  }
}

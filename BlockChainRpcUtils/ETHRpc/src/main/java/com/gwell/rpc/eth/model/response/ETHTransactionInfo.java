package com.gwell.rpc.eth.model.response;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/** 以太坊交易信息返回类 */
@Data
public class ETHTransactionInfo {

  private boolean pending;

  private boolean success;

  private boolean token;

  /** 该事务所在的块的hash */
  private String blockHash;

  /** 该事物所在块的下标 字符串 */
  private BigInteger blockNumber;

  /** 该事务的完成时间 */
  private Date transferTime;

  /** 块中交易指标位置的整数 字符串 */
  private BigInteger transactionIndex;

  /** 交易Hash */
  private String hash;

  /** 发送人在此之前的交易次数 字符串 */
  private BigInteger nonce;

  /** 发送人地址 */
  private String from;

  /** 接收人地址 */
  private String to;

  /** 值 */
  private BigDecimal value;

  /** GasPrice */
  private BigInteger gasPrice;

  /** GasLimit */
  private BigInteger gas;

  /** UseGas */
  private BigInteger useGas;

  /** 发起交易时附带的数据 */
  private String input;

  /** 分解input数据-在logs里 */
  private InputData inputData;

  /** EventLog 记录合约的操作 */
  private List<Log> logs;

  /** 获取已使用的手续费 */
  public BigDecimal getFee() {
    if (null != getGasPrice() && null != getUseGas()) {
      BigInteger weiFee = getGasPrice().multiply(getUseGas());
      return Convert.fromWei(weiFee.toString(), Convert.Unit.ETHER);
    }
    return BigDecimal.ZERO;
  }

  /** 获取开始设置的手续费 */
  public BigDecimal getOriginalFee() {
    if (null != getGasPrice() && null != getGas()) {
      BigInteger weiFee = getGasPrice().multiply(getGas());
      return Convert.fromWei(weiFee.toString(), Convert.Unit.ETHER);
    }
    return BigDecimal.ZERO;
  }

  public ETHTransactionInfo(Transaction transaction, TransactionReceipt transactionReceipt) {
    pending = false;
    success = false;
    if (null != transaction) {
      token = false;
      if (StringUtils.isNotBlank(transaction.getInput())
          && transaction.getInput().length() == 138) {
        String method = transaction.getInput().substring(0, 10);
        if ("0xa9059cbb".equals(method)) {
          token = true;
        }
      }
      if (StringUtils.isNotBlank(transaction.getBlockHash().replaceFirst("^(0x)?0*", ""))) {
        transactionIndex = transaction.getTransactionIndex();
        blockHash = transaction.getBlockHash();
        blockNumber = transaction.getBlockNumber();
        hash = transaction.getHash();
        nonce = transaction.getNonce();
        from = transaction.getFrom();
        to = transaction.getTo();
        value = Convert.fromWei(transaction.getValue().toString(), Convert.Unit.ETHER);
        gas = transaction.getGas();
        gasPrice = transaction.getGasPrice();
        input = transaction.getInput();
        if (null != transactionReceipt) {
          success = transactionReceipt.isStatusOK();
          useGas = transactionReceipt.getGasUsed();
          logs = transactionReceipt.getLogs();
          if (isToken()) {
            success =
                transactionReceipt.getLogs() != null && transactionReceipt.getLogs().size() > 0;
            InputData data = new InputData();
            String input = transaction.getInput();
            String toStr = input.substring(10, 74);
            String valueStr = input.substring(74);
            try {
              Method refMethod =
                  TypeDecoder.class.getDeclaredMethod(
                      "decode", String.class, int.class, Class.class);
              refMethod.setAccessible(true);

              Address address = (Address) refMethod.invoke(null, toStr, 0, Address.class);
              Uint256 unitValue = (Uint256) refMethod.invoke(null, valueStr, 0, Uint256.class);

              String toAddress = address.toString();
              BigInteger weiValue = unitValue.getValue();

              data.setContractAddress(getTo());
              data.setToAddress(toAddress);
              data.setWeiValue(weiValue);
            } catch (Exception e) {
              e.printStackTrace();
            }
            inputData = data;
          }
        } else {
          pending = true;
        }
      } else {
        pending = true;
        success = false;
      }
    }
  }
}

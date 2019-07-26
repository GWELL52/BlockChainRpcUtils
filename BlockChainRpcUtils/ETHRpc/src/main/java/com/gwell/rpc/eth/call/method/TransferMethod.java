package com.gwell.rpc.eth.call.method;

import com.alibaba.fastjson.JSON;
import com.gwell.rpc.common.exception.ResponseException;
import com.gwell.rpc.eth.model.request.SendTransactionParams;
import com.gwell.rpc.eth.model.response.ETHFee;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.ChainIdLong;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

@Setter
public class TransferMethod {
  private Web3j web3j;
  private long chainId;
  private TokenMethod tokenMethod;
  private BaseMethod baseMethod;

  public static TransferMethod build(Web3j web3j, long chainId) {
    TransferMethod instance = getInstance();
    instance.setWeb3j(web3j);
    instance.setChainId(chainId);
    instance.setTokenMethod(TokenMethod.build(web3j));
    instance.setBaseMethod(BaseMethod.build(web3j));
    return instance;
  }

  private TransferMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static TransferMethod instance = new TransferMethod();
  }

  private static TransferMethod getInstance() {
    return Holder.instance;
  }

  private void checkError(Response result) {
    if (result.hasError()) {
      ResponseException.create(result.getError().getMessage());
    }
  }

  /**
   * 获取交易预计手续费
   *
   * @param params 发送交易的参数
   */
  @SneakyThrows
  public ETHFee getTransactionFee(SendTransactionParams params) {
    BigInteger gasPrice = params.getGasPrice();
    if (gasPrice == null) {
      gasPrice = baseMethod.getGasPrice();
    }
    BigInteger gasLimit = params.getGasLimit();
    if (gasLimit == null) {
      gasLimit = baseMethod.getGasLimit(params);
    }
    BigDecimal fee = Convert.fromWei(gasPrice.multiply(gasLimit).toString(), Convert.Unit.ETHER);
    return new ETHFee(gasLimit, gasPrice, fee);
  }

  /** 发送全部ETH交易 */
  @SneakyThrows
  public String sendAllEth(SendTransactionParams params) {
    // 获取预计手续费信息
    ETHFee feeResult = getTransactionFee(params);

    // 获取余额
    EthGetBalance getBalanceResult =
        web3j.ethGetBalance(params.getFromAddress(), DefaultBlockParameterName.LATEST).send();
    checkError(getBalanceResult);
    BigInteger weiBalance = getBalanceResult.getBalance();
    BigDecimal balance = Convert.fromWei(new BigDecimal(weiBalance), Convert.Unit.ETHER);

    // 实际转账的数量
    BigDecimal amount = balance.subtract(feeResult.getTransactionFee());
    if (amount.compareTo(BigDecimal.ZERO) < 1) {
      String content =
          String.format(
              "地址 %s 余额不足手续费消耗! balance=%g, feeResult=%s",
              params.getFromAddress(), balance, JSON.toJSON(feeResult));
      throw new RuntimeException(content);
    }

    params.setAmount(amount);
    params.setGasPrice(feeResult.getGasPrice());
    params.setGasLimit(feeResult.getGasLimit());
    return sendTransaction(params);
  }

  /** 发送交易 */
  public String sendTransaction(SendTransactionParams params) {
    String signedData = signTransaction(params);
    return sendRawTransaction(signedData);
  }

  /** 发送已签名的交易 */
  @SneakyThrows
  public String sendRawTransaction(String signedData) {
    EthSendTransaction result = web3j.ethSendRawTransaction(signedData).send();
    checkError(result);
    return result.getTransactionHash();
  }

  /** 签名交易 */
  public String signTransaction(SendTransactionParams params) {
    Credentials credentials = params.getCredentials();

    if (null == params.getNonce()) {
      BigInteger nonce = baseMethod.getNonce(params.getFromAddress());
      params.setNonce(nonce);
    }
    if (null == params.getGasPrice()) {
      BigInteger gasPrice = baseMethod.getGasPrice();
      params.setGasPrice(gasPrice);
    }
    if (null == params.getGasLimit()) {
      BigInteger gasLimit = baseMethod.getGasLimit(params);
      params.setGasLimit(gasLimit);
    }

    BigInteger nonce = params.getNonce();
    BigInteger gasPrice = params.getGasPrice();
    BigInteger gasLimit = params.getGasLimit();
    BigInteger value = Convert.toWei(params.getAmount(), Convert.Unit.ETHER).toBigInteger();
    String toAddress = params.getToAddress();
    String data = "0x";
    // 如果是Token转账的参数
    if (StringUtils.isNotBlank(params.getContractAddress())) {
      value = BigInteger.ZERO;
      toAddress = params.getContractAddress();
      data = tokenMethod.getTransactionData(params);
      params.setData(data);
    }

    RawTransaction rawTransaction =
        RawTransaction.createTransaction(nonce, gasPrice, gasLimit, toAddress, value, data);

    byte[] signedMessage;
    if (chainId > ChainIdLong.MAINNET) {
      // 测试网络
      signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
    } else {
      signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
    }
    return Numeric.toHexString(signedMessage);
  }
}

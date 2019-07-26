package com.gwell.rpc.eth.call.override;

import com.gwell.rpc.common.enums.BlockChainEnum;
import lombok.Getter;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

public class JsonRpc2_0Web3j extends org.web3j.protocol.core.JsonRpc2_0Web3j implements Web3j {
  @Getter private BlockChainEnum blockChain;

  public JsonRpc2_0Web3j(Web3jService web3jService, BlockChainEnum blockChain) {
    super(web3jService);
    this.blockChain = blockChain;
  }

  public JsonRpc2_0Web3j(
      Web3jService web3jService,
      long pollingInterval,
      ScheduledExecutorService scheduledExecutorService,
      BlockChainEnum blockChain) {
    super(web3jService, pollingInterval, scheduledExecutorService);
    this.blockChain = blockChain;
  }

  private String getMethod(String method) {
    if (blockChain.equals(BlockChainEnum.ETH) || blockChain.equals(BlockChainEnum.ETC)) {
      return BlockChainEnum.ETH.lowerCaseName().concat(method);
    }
    return blockChain.lowerCaseName().concat(method);
  }

  @Override
  public Request<?, EthBlockNumber> ethBlockNumber() {
    return new Request<>(
        getMethod("_blockNumber"),
        Collections.<String>emptyList(),
        web3jService,
        EthBlockNumber.class);
  }

  @Override
  public Request<?, EthBlock> ethGetBlockByNumber(
      DefaultBlockParameter defaultBlockParameter, boolean returnFullTransactionObjects) {
    return new Request<>(
        getMethod("_getBlockByNumber"),
        Arrays.asList(defaultBlockParameter.getValue(), returnFullTransactionObjects),
        web3jService,
        EthBlock.class);
  }

  @Override
  public Request<?, EthEstimateGas> ethEstimateGas(Transaction transaction) {
    return new Request<>(
        getMethod("_estimateGas"), Arrays.asList(transaction), web3jService, EthEstimateGas.class);
  }

  @Override
  public Request<?, EthGasPrice> ethGasPrice() {
    return new Request<>(
        getMethod("_gasPrice"), Collections.<String>emptyList(), web3jService, EthGasPrice.class);
  }

  @Override
  public Request<?, EthGetTransactionCount> ethGetTransactionCount(
      String address, DefaultBlockParameter defaultBlockParameter) {
    return new Request<>(
        getMethod("_getTransactionCount"),
        Arrays.asList(address, defaultBlockParameter.getValue()),
        web3jService,
        EthGetTransactionCount.class);
  }

  @Override
  public Request<?, EthGetBalance> ethGetBalance(
      String address, DefaultBlockParameter defaultBlockParameter) {
    return new Request<>(
        getMethod("_getBalance"),
        Arrays.asList(address, defaultBlockParameter.getValue()),
        web3jService,
        EthGetBalance.class);
  }

  @Override
  public Request<?, EthCall> ethCall(
      Transaction transaction, DefaultBlockParameter defaultBlockParameter) {
    return new Request<>(
        getMethod("_call"),
        Arrays.asList(transaction, defaultBlockParameter),
        web3jService,
        EthCall.class);
  }

  @Override
  public Request<?, EthGetTransactionReceipt> ethGetTransactionReceipt(String transactionHash) {
    return new Request<>(
        getMethod("_getTransactionReceipt"),
        Arrays.asList(transactionHash),
        web3jService,
        EthGetTransactionReceipt.class);
  }

  @Override
  public Request<?, EthTransaction> ethGetTransactionByHash(String transactionHash) {
    return new Request<>(
        getMethod("_getTransactionByHash"),
        Arrays.asList(transactionHash),
        web3jService,
        EthTransaction.class);
  }

  @Override
  public Request<?, EthSendTransaction> ethSendRawTransaction(String signedTransactionData) {
    return new Request<>(
        getMethod("_sendRawTransaction"),
        Arrays.asList(signedTransactionData),
        web3jService,
        EthSendTransaction.class);
  }
}

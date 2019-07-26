package com.gwell.rpc.eth.call.override;

import com.gwell.rpc.common.enums.BlockChainEnum;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.rx.Web3jRx;

import java.util.concurrent.ScheduledExecutorService;

/** JSON-RPC Request object building factory. */
public interface Web3j extends Ethereum, Web3jRx {

  /**
   * Construct a new Web3j instance.
   *
   * @param web3jService web3j service instance - i.e. HTTP or IPC
   * @param blockChain Block Chain Type
   * @return new Web3j instance
   */
  static Web3j build(Web3jService web3jService, BlockChainEnum blockChain) {
    return new JsonRpc2_0Web3j(web3jService, blockChain);
  }

  /**
   * Construct a new Web3j instance.
   *
   * @param web3jService web3j service instance - i.e. HTTP or IPC
   * @param pollingInterval polling interval for responses from network nodes
   * @param scheduledExecutorService executor service to use for scheduled tasks. <strong>You are
   *     responsible for terminating this thread pool</strong>
   * @param blockChain Block Chain Type
   * @return new Web3j instance
   */
  static Web3j build(
      Web3jService web3jService,
      long pollingInterval,
      ScheduledExecutorService scheduledExecutorService,
      BlockChainEnum blockChain) {
    return new JsonRpc2_0Web3j(web3jService, pollingInterval, scheduledExecutorService, blockChain);
  }

  /** Shutdowns a Web3j instance and closes opened resources. */
  void shutdown();

  BlockChainEnum getBlockChain();
}

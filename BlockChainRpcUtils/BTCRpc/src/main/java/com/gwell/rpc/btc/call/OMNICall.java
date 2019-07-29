package com.gwell.rpc.btc.call;

import com.gwell.rpc.btc.call.method.OMNIMethod;
import com.gwell.rpc.btc.model.response.SendTransaction;
import com.gwell.rpc.btc.model.response.omni.GetOMNIBalance;
import com.gwell.rpc.btc.model.response.omni.GetOMNITransactionInfo;
import com.gwell.rpc.btc.model.response.omni.GetOMNIWalletAddressBalance;
import com.gwell.rpc.btc.model.response.omni.ListOMNITransactions;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OMNICall extends Call {
  private OMNIMethod omniMethod;

  public OMNICall(Connection connection, BlockChainEnum blockChain) {
    super(connection, blockChain);
    this.omniMethod = OMNIMethod.build(connection, blockChain);
  }

  public GetOMNIBalance getOMNIBalance(String address, Integer propertyId) {
    return omniMethod.getOMNIBalance(address, propertyId);
  }

  public GetOMNIWalletAddressBalance getOMNIWalletAddressBalance() {
    return omniMethod.getOMNIWalletAddressBalance();
  }

  public ListOMNITransactions listOMNITransactions(
      String address, Long limit, Long index, BigInteger startBlock, BigInteger endBlock) {
    return omniMethod.listOMNITransactions(address, limit, index, startBlock, endBlock);
  }

  public GetOMNITransactionInfo getOMNITransactionInfo(String hash) {
    return omniMethod.getOMNITransactionInfo(hash);
  }

  public SendTransaction sendOMNITo(
      String fromAddress,
      String toAddress,
      Integer propertyId,
      BigDecimal amount,
      String feeAddress) {
    return omniMethod.sendOMNITo(fromAddress, toAddress, propertyId, amount, feeAddress);
  }
}

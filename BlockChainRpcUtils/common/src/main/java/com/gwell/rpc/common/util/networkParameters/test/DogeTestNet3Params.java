package com.gwell.rpc.common.util.networkParameters.test;

import org.bitcoinj.params.AbstractBitcoinNetParams;

/**
 * 参数参考下面网址 https://github.com/iancoleman/bip39/blob/master/src/js/bitcoinjs-extensions.js
 *
 * @implNote dumpedPrivateKeyHeader = wif
 * @implNote addressHeader = pubKeyHash
 * @implNote p2shHeader = scriptHash
 * @implNote bip32HeaderPriv = bip32.private
 */
public class DogeTestNet3Params extends AbstractBitcoinNetParams {
  private static DogeTestNet3Params instance;

  public DogeTestNet3Params() {
    super();
    dumpedPrivateKeyHeader = 241;
    addressHeader = 113;
    p2shHeader = 196;
    bip32HeaderPriv = 0x04358394;
  }

  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized DogeTestNet3Params get() {
    if (instance == null) {
      instance = new DogeTestNet3Params();
    }
    return instance;
  }

  @Override
  public String getPaymentProtocolId() {
    return PAYMENT_PROTOCOL_ID_TESTNET;
  }
}

package com.gwell.rpc.common.util.networkParameters;

import org.bitcoinj.params.AbstractBitcoinNetParams;

/**
 * 参数参考下面网址 https://github.com/iancoleman/bip39/blob/master/src/js/bitcoinjs-extensions.js
 *
 * @implNote dumpedPrivateKeyHeader = wif
 * @implNote addressHeader = pubKeyHash
 * @implNote p2shHeader = scriptHash
 * @implNote bip32HeaderPriv = bip32.private
 */
public class DogeMainNetParams extends AbstractBitcoinNetParams {
  private static DogeMainNetParams instance;

  public DogeMainNetParams() {
    super();
    dumpedPrivateKeyHeader = 158;
    addressHeader = 30;
    p2shHeader = 22;
    bip32HeaderPriv = 0x2FAC398;
  }

  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized DogeMainNetParams get() {
    if (instance == null) {
      instance = new DogeMainNetParams();
    }
    return instance;
  }

  @Override
  public String getPaymentProtocolId() {
    return PAYMENT_PROTOCOL_ID_MAINNET;
  }
}

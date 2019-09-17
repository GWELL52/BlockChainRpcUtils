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
public class DashMainNetParams extends AbstractBitcoinNetParams {
  private static DashMainNetParams instance;

  public DashMainNetParams() {
    super();
    dumpedPrivateKeyHeader = 204;
    addressHeader = 76;
    p2shHeader = 16;
    bip32HeaderPriv = 0x2FE52CC;
  }

  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized DashMainNetParams get() {
    if (instance == null) {
      instance = new DashMainNetParams();
    }
    return instance;
  }

  @Override
  public String getPaymentProtocolId() {
    return PAYMENT_PROTOCOL_ID_MAINNET;
  }
}

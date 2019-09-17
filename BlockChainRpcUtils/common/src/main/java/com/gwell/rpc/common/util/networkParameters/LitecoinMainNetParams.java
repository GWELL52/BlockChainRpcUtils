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
public class LitecoinMainNetParams extends AbstractBitcoinNetParams {
  private static LitecoinMainNetParams instance;

  public LitecoinMainNetParams() {
    super();
    dumpedPrivateKeyHeader = 176;
    addressHeader = 48;
    p2shHeader = 5;
    bip32HeaderPriv = 0x0488ADE4;
  }

  @SuppressWarnings("PMD.AvoidSynchronizedAtMethodLevel")
  public static synchronized LitecoinMainNetParams get() {
    if (instance == null) {
      instance = new LitecoinMainNetParams();
    }
    return instance;
  }

  @Override
  public String getPaymentProtocolId() {
    return PAYMENT_PROTOCOL_ID_MAINNET;
  }
}

package com.gwell.rpc.common.enums;

import com.gwell.rpc.common.util.networkParameters.DashMainNetParams;
import com.gwell.rpc.common.util.networkParameters.LitecoinMainNetParams;
import com.gwell.rpc.common.util.networkParameters.test.DashTestNet3Params;
import com.gwell.rpc.common.util.networkParameters.test.LitecoinTestNet3Params;
import lombok.Getter;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Getter
public enum BlockChainEnum {
  BTC(BigDecimal.TEN.pow(8), 0, MainNetParams.get(), 1, TestNet3Params.get()),
  ETH(BigDecimal.TEN.pow(18), 60, null, 60, null),
  BCH(BigDecimal.TEN.pow(8), 145, MainNetParams.get(), 145, TestNet3Params.get()),
  DASH(BigDecimal.TEN.pow(8), 5, DashMainNetParams.get(), 1, DashTestNet3Params.get()),
  LTC(BigDecimal.TEN.pow(8), 2, LitecoinMainNetParams.get(), 1, LitecoinTestNet3Params.get()),
  OMNI(BigDecimal.TEN.pow(8), 200, MainNetParams.get(), 200, TestNet3Params.get()),
  ETC(BigDecimal.TEN.pow(18), 61, null, 61, null),
  VNS(BigDecimal.TEN.pow(18), 316, null, 316, null),
  ;

  private BigDecimal decimal;
  private int coinType;
  private NetworkParameters mainNetParam;
  private int testNetCoinType;
  private NetworkParameters testNetParam;

  BlockChainEnum(
      BigDecimal decimal,
      int coinType,
      NetworkParameters mainNetParam,
      int testNetCoinType,
      NetworkParameters testNetParam) {
    this.decimal = decimal;
    this.coinType = coinType;
    this.mainNetParam = mainNetParam;
    this.testNetCoinType = testNetCoinType;
    this.testNetParam = testNetParam;
  }

  public String lowerCaseName() {
    return name().toLowerCase();
  }

  public String getBip44Path(boolean testNet) {
    int coinType = testNet ? this.testNetCoinType : this.coinType;
    return "M/44H/" + coinType + "H/0H/0";
  }

  public NetworkParameters getNetworkParam(boolean testNet) {
    return testNet ? this.testNetParam : this.mainNetParam;
  }

  public boolean isBitCoin() {
    return Stream.of(BTC, BCH, DASH, LTC, OMNI).anyMatch(other -> other.equals(this));
  }

  public boolean isEther() {
    return Stream.of(ETH, ETC, VNS).anyMatch(other -> other.equals(this));
  }
}

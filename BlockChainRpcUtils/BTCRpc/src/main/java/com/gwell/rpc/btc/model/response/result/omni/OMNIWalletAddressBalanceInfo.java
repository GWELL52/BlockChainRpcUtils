package com.gwell.rpc.btc.model.response.result.omni;

import lombok.Data;

import java.util.List;

@Data
public class OMNIWalletAddressBalanceInfo {
  private String address;
  private List<OMNIBalanceInfo> balances;
}

package com.gwell.rpc.btc;

public class AbstractTest {
  public BTCRpc btcRpc = BTCRpc.build("", 0, "", "");
  public BCHRpc bchRpc = BCHRpc.build("", 0, "", "");
  public DASHRpc dashRpc = DASHRpc.build("", 0, "", "");
  public LTCRpc ltcRpc = LTCRpc.build("", 0, "", "");
  public OMNIRpc omniRpc = OMNIRpc.build("", 0, "", "");
}

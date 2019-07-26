package com.gwell.rpc.eth;

import com.gwell.rpc.common.constant.Constant;
import com.gwell.rpc.eth.model.response.EthAccount;
import org.web3j.tx.ChainIdLong;

public class AbstractTest {
  public ETHRpc ethRpc =
      ETHRpc.build("infura-url", ChainIdLong.RINKEBY, Constant.DEFAULT_KEYSTORE_PATH);
  public String ethContractAddress = "ContractAddress";

  public ETHRpc etcRpc =
      ETHRpc.build("ip", 0, ChainIdLong.ETHEREUM_CLASSIC_TESTNET, Constant.DEFAULT_KEYSTORE_PATH);
  public String etcContractAddress = "ContractAddress";

  public VNSRpc vnsRpc = VNSRpc.build("ip", 0, Constant.DEFAULT_KEYSTORE_PATH);
  public String vnsContractAddress = "ContractAddress";

  public EthAccount from = new EthAccount("address", "privateKey", "password");
  public EthAccount to = new EthAccount("address", "privateKey", "password");
}

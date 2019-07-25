package com.gwell.rpc.eth;

import com.gwell.rpc.common.constant.Constant;
import com.gwell.rpc.eth.model.response.EthAccount;
import org.web3j.tx.ChainId;

public class AbstractTest {
  public ETHRpc rpc = ETHRpc.build("infura-url", ChainId.RINKEBY, Constant.DEFAULT_KEYSTORE_PATH);

  public EthAccount from = new EthAccount("address", "privateKey", "password");
  public EthAccount to = new EthAccount("address", "privateKey", "password");
  public String contractAddress = "contractAddress";
}

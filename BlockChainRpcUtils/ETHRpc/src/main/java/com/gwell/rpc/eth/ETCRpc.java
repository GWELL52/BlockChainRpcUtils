package com.gwell.rpc.eth;

import com.gwell.rpc.common.constant.Constant;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.eth.call.Call;
import org.web3j.tx.ChainIdLong;

public class ETCRpc extends Call {

  public static String keystorePath = Constant.DEFAULT_KEYSTORE_PATH;

  public static ETCRpc build(String ip, Integer port) {
    return ETCRpc.build(Connection.builder(ip, port).build());
  }

  public static ETCRpc build(String ip, Integer port, long chainId, String keystorePath) {
    return ETCRpc.build(Connection.builder(ip, port).build(), chainId, keystorePath);
  }

  public static ETCRpc build(Connection connection) {
    return new ETCRpc(connection, ChainIdLong.ETHEREUM_CLASSIC_MAINNET, keystorePath);
  }

  public static ETCRpc build(Connection connection, long chainId, String keystorePath) {
    return new ETCRpc(connection, chainId, keystorePath);
  }

  private ETCRpc(Connection connection, long chainId, String keystorePath) {
    super(connection, chainId, keystorePath, BlockChainEnum.ETC);
    ETCRpc.keystorePath = keystorePath;
  }
}

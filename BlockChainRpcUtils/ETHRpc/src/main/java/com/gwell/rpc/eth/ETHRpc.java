package com.gwell.rpc.eth;

import com.gwell.rpc.common.constant.Constant;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.eth.call.Call;
import org.web3j.tx.ChainIdLong;

public class ETHRpc extends Call {

  public static String keystorePath = Constant.DEFAULT_KEYSTORE_PATH;

  public static ETHRpc build(String url) {
    return ETHRpc.build(Connection.builder(url).build());
  }

  public static ETHRpc build(String url, long chainId, String keystorePath) {
    return ETHRpc.build(Connection.builder(url).build(), chainId, keystorePath);
  }

  public static ETHRpc build(String ip, Integer port) {
    return ETHRpc.build(Connection.builder(ip, port).build());
  }

  public static ETHRpc build(String ip, Integer port, long chainId, String keystorePath) {
    return ETHRpc.build(Connection.builder(ip, port).build(), chainId, keystorePath);
  }

  public static ETHRpc build(Connection connection) {
    return new ETHRpc(connection, ChainIdLong.MAINNET, keystorePath);
  }

  public static ETHRpc build(Connection connection, long chainId, String keystorePath) {
    return new ETHRpc(connection, chainId, keystorePath);
  }

  private ETHRpc(Connection connection, long chainId, String keystorePath) {
    super(connection, chainId, keystorePath);
    ETHRpc.keystorePath = keystorePath;
  }
}

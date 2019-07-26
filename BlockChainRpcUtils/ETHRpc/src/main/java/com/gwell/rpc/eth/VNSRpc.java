package com.gwell.rpc.eth;

import com.gwell.rpc.common.constant.Constant;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.eth.call.Call;
import org.web3j.tx.ChainIdLong;

public class VNSRpc extends Call {

  public static String keystorePath = Constant.DEFAULT_KEYSTORE_PATH;

  public static VNSRpc build(String ip, Integer port) {
    return VNSRpc.build(Connection.builder(ip, port).build());
  }

  public static VNSRpc build(String ip, Integer port, String keystorePath) {
    return VNSRpc.build(Connection.builder(ip, port).build(), keystorePath);
  }

  public static VNSRpc build(Connection connection) {
    return new VNSRpc(connection, keystorePath);
  }

  public static VNSRpc build(Connection connection, String keystorePath) {
    return new VNSRpc(connection, keystorePath);
  }

  private VNSRpc(Connection connection, String keystorePath) {
    super(connection, ChainIdLong.MAINNET, keystorePath, BlockChainEnum.VNS);
    VNSRpc.keystorePath = keystorePath;
  }
}

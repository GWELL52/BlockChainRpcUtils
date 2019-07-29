package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.response.GetBlockCount;
import com.gwell.rpc.btc.model.response.GetBlockHash;
import com.gwell.rpc.btc.model.response.GetBlockInfo;
import com.gwell.rpc.btc.model.response.GetInfo;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;

import java.math.BigInteger;
import java.util.Collections;

public class BaseMethod extends SuperMethod {

  public static BaseMethod build(Connection connection, BlockChainEnum blockChain) {
    BaseMethod instance = getInstance();
    instance.init(connection, blockChain);
    return instance;
  }

  private BaseMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static BaseMethod instance = new BaseMethod();
  }

  private static BaseMethod getInstance() {
    return Holder.instance;
  }

  /** 获取服务器版本余额信息 */
  public GetInfo getInfo() {
    return Request.rpc(connection, "getinfo", null, GetInfo.class).send();
  }

  /** 获取服务器最新高度 */
  public GetBlockCount getBlockCount() {
    return Request.rpc(connection, "getblockcount", null, GetBlockCount.class).send();
  }

  /**
   * 根据高度获取区块哈希
   *
   * @param blockCount 区块高度
   */
  public GetBlockHash getBlockHash(BigInteger blockCount) {
    return Request.rpc(
            connection, "getblockhash", Collections.singletonList(blockCount), GetBlockHash.class)
        .send();
  }

  /**
   * 根据区块哈希获取区块信息
   *
   * @param blockHash 区块哈希
   */
  public GetBlockInfo getBlockInfo(String blockHash) {
    return Request.rpc(
            connection, "getblock", Collections.singletonList(blockHash), GetBlockInfo.class)
        .send();
  }
}

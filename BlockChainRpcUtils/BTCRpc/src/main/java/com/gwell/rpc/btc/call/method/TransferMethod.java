package com.gwell.rpc.btc.call.method;

import com.gwell.rpc.btc.model.request.SendTransactionParams;
import com.gwell.rpc.btc.model.response.SendTransaction;
import com.gwell.rpc.btc.model.response.result.UTXOInfo;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.BlockChainWallet;
import com.gwell.rpc.common.model.Connection;
import com.gwell.rpc.common.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.*;
import org.bitcoinj.script.Script;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TransferMethod extends SuperMethod {

  public static TransferMethod build(Connection connection, BlockChainEnum blockChain) {
    TransferMethod instance = new TransferMethod();
    instance.init(connection, blockChain);
    return instance;
  }

  private TransferMethod() {}

  /**
   * 转账给某个地址
   *
   * @param toAddress 接收地址
   * @param amount 数量
   */
  public SendTransaction sendToAddress(String toAddress, BigDecimal amount) {
    return Request.rpc(
            connection, "sendtoaddress", Arrays.asList(toAddress, amount), SendTransaction.class)
        .send();
  }

  /** 发送已经签名的交易数据 */
  public SendTransaction sendRawTransaction(String rawData) {
    return Request.rpc(
            connection,
            "sendrawtransaction",
            Collections.singletonList(rawData),
            SendTransaction.class)
        .send();
  }

  /** 签名交易 */
  public String signTransaction(SendTransactionParams params) {
    // 私钥
    ECKey key =
        BlockChainWallet.getECKey(
            params.getFromAccount().getPrivateKey(),
            blockChain.getNetworkParam(params.isTestNet()));
    // 发送地址
    Address formAddress =
        Address.fromBase58(
            blockChain.getNetworkParam(params.isTestNet()), params.getFromAccount().getAddress());
    // 接收地址
    Address toAddress =
        Address.fromBase58(blockChain.getNetworkParam(params.isTestNet()), params.getToAddress());
    // 构建交易
    Transaction tx = new Transaction(blockChain.getNetworkParam(params.isTestNet()));
    // 添加输出 Output
    tx.addOutput(Coin.valueOf(params.getRawAmount(blockChain.getUnit())), toAddress);
    // 如果需要找零 消费列表总金额 - 已经转账的金额 - 手续费
    long value = params.getUTXOInfoList().stream().mapToLong(UTXOInfo::getRawValue).sum();
    long leave =
        value - params.getRawAmount(blockChain.getUnit()) - params.getRawFee(blockChain.getUnit());
    if (leave > 0) {
      tx.addOutput(Coin.valueOf(leave), formAddress);
    }
    if (leave < 0) {
      //      throw new RuntimeException("余额不足");
    }
    List<UTXO> UTXOList =
        params
            .getUTXOInfoList()
            .stream()
            .map(
                UTXOInfo -> {
                  UTXO UTXO =
                      new UTXO(
                          Sha256Hash.wrap(UTXOInfo.getHash()),
                          UTXOInfo.getOutputIndex(),
                          Coin.valueOf(UTXOInfo.getRawValue()),
                          UTXOInfo.getHeight().intValue(),
                          false,
                          new Script(Utils.HEX.decode(UTXOInfo.getScript())),
                          UTXOInfo.getAddress());
                  return UTXO;
                })
            .collect(Collectors.toList());
    UTXOList.forEach(
        UTXO -> {
          TransactionOutPoint outPoint =
              new TransactionOutPoint(
                  blockChain.getNetworkParam(params.isTestNet()), UTXO.getIndex(), UTXO.getHash());
          // 添加输入 Input
          tx.addSignedInput(outPoint, UTXO.getScript(), key, Transaction.SigHash.ALL, true);
        });
    Context context = new Context(blockChain.getNetworkParam(params.isTestNet()));
    tx.getConfidence().setSource(TransactionConfidence.Source.NETWORK);
    tx.setPurpose(Transaction.Purpose.USER_PAYMENT);

    log.info("=== [BTC] sign success,hash is :{} ===", tx.getHashAsString());
    return Hex.toHexString(tx.bitcoinSerialize());
  }
}

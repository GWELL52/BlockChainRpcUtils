package com.gwell.rpc.common.util.bip44;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.BlockChainWallet;
import com.gwell.rpc.common.util.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.io.File;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
public class Bip44WalletUtil {

  /** 生成钱包 */
  @SneakyThrows
  public static BlockChainWallet generateBip44Wallet(
      BlockChainEnum chainEnum, String keystorePath, boolean testNet) {
    // 定义生成几位的助记词
    // 算单词个数基数为3,3*4=12个单词, 算byte基数为4,4*4=16byte,
    byte[] initialEntropy = new byte[16];
    SecureRandomUtils.secureRandom().nextBytes(initialEntropy);
    // 1.生成助记词
    String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
    log.info("Mnemonic = {}", mnemonic);
    //    String mnemonic = "";

    // 生成随机密码(掩码)
    String passphrase = RandomStringUtils.randomPrint(16);
    log.info("passphrase = {}", passphrase);
    // 2.生成种子
    byte[] seed = MnemonicUtils.generateSeed(mnemonic, passphrase);
    // 3. 生成根私钥 root private key 树顶点的master key ；bip32
    DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
    log.info("Root Key = {}", rootPrivateKey.serializePrivB58(MainNetParams.get()));
    // 4. 由根私钥生成 第一个HD 钱包
    DeterministicHierarchy dh = new DeterministicHierarchy(rootPrivateKey);
    // 5. 定义父路径 H(H=')则是加强 imtoken中的eth钱包进过测试发现使用的是此方式生成 bip44
    List<ChildNumber> path = HDUtils.parsePath(chainEnum.getBip44Path(testNet));
    DeterministicKey extended = dh.get(path, true, true);
    log.info("Path = {}", extended.getPathAsString());
    log.info("Extended Private Key = {}", extended.serializePrivB58(MainNetParams.get()));
    log.info("Extended Public Key = {}", extended.serializePubB58(MainNetParams.get()));
    log.info("ChainCode = {}", new String(extended.getChainCode()));

    // 6. 由父路径,派生出第一个子私钥 "new ChildNumber(0)" 表示第一个 （m/44'/60'/0'/0/0）
    DeterministicKey child = HDKeyDerivation.deriveChildKey(extended, 0);

    BlockChainWallet wallet = null;
    if (chainEnum.isBitCoin()) {
      wallet = createBitCoinWallet(child, chainEnum.getNetworkParam(testNet));
    } else if (chainEnum.isEther()) {
      wallet = createEtherWallet(child, keystorePath);
    } else {
      throw new RuntimeException("暂不支持该币种");
    }
    wallet.setBlockChainEnum(chainEnum);
    wallet.setChildIndex(0);
    log.info(JSON.toJSONString(wallet));
    return wallet;
  }

  public static int byteArrayToInt(byte[] bytes) {
    int value = 0;
    // 由高位到低位
    for (int i = 0; i < 4; i++) {
      int shift = (4 - 1 - i) * 8;
      value += (bytes[i] & 0x000000FF) << shift; // 往高位游
    }
    return value;
  }

  public static void GeneratorEtherAddress(String extendedPublicKey, int childIndex) {
    DeterministicKey extended =
        DeterministicKey.deserializeB58(extendedPublicKey, MainNetParams.get());
    DeterministicKey child = HDKeyDerivation.deriveChildKey(extended, childIndex);
    String address =
        Keys.toChecksumAddress(Numeric.toHexStringNoPrefix(Keys.getAddress(child.getPubKeyHash())));
    log.info("address = {}", address);
  }

  private static BlockChainWallet createEtherWallet(
      DeterministicKey deterministicKey, String keystorePath) {
    ECKeyPair ecKeyPair = ECKeyPair.create(deterministicKey.getPrivKeyBytes());
    createKeystoreFile(keystorePath, ecKeyPair);
    return BlockChainWallet.builder()
        .address(Keys.toChecksumAddress(Keys.getAddress(ecKeyPair)))
        .privateKey(Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey()))
        .privateKey(Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey()))
        .build();
  }

  private static BlockChainWallet createBitCoinWallet(
      DeterministicKey deterministicKey, NetworkParameters networkParameters) {
    ECKey ecKey = ECKey.fromPrivate(deterministicKey.getPrivKeyBytes());
    return BlockChainWallet.builder()
        .address(ecKey.toAddress(networkParameters).toString())
        .privateKey(ecKey.getPublicKeyAsHex())
        .privateKey(ecKey.getPrivateKeyAsWiF(networkParameters))
        .build();
  }

  /** 创建Keystore文件 */
  @SneakyThrows
  private static void createKeystoreFile(String keystorePath, ECKeyPair ecKeyPair) {
    File dir = getKeystoreDir(keystorePath);
    WalletFile walletFile = Wallet.createLight("", ecKeyPair);
    String address =
        new StringBuffer(walletFile.getAddress()).insert(0, "0x").toString().toLowerCase();
    // 生成 keystore 文件名
    DateTimeFormatter format = DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'");
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    String fileName = now.format(format) + address + ".json";
    // 生成文件
    File destination = new File(dir, fileName);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(destination, walletFile);
  }

  /** 获取keystore文件夹, 不存在则创建 */
  @SneakyThrows
  private static File getKeystoreDir(String keystorePath) {
    File dir = FileUtil.getFile(keystorePath);
    if (!dir.exists()) {
      // 文件夹不存在, 创建
      boolean result = dir.mkdir();
      if (!result) {
        throw new RuntimeException("创建Keystore文件夹失败!");
      }
    }
    return dir;
  }

  public static void main(String[] args) {
    //    generateBip44Wallet(BlockChainEnum.ETH, "/keystore", false);
    // 0x55ea0d1Ab3e435e75B604C0539e1188206A87b38
    GeneratorEtherAddress(
        "xpub6EQey6ULcu3UbgKRLEjHNNnbnBhgfPrYozP3CgU59AUHUQ29sdLs2ZKLXYxFvoEupVZtv5MXmZPXWh891ozpVpgqiZEYiZXipW4keX3BvQL",
        0);
  }
}

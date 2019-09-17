package com.gwell.rpc.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwell.rpc.common.enums.BlockChainEnum;
import com.gwell.rpc.common.model.BlockChainWallet;
import lombok.SneakyThrows;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.WalletFiles;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;

import java.io.File;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Bip44WalletUtil {

  /** 生成钱包 */
  @SneakyThrows
  public static BlockChainWallet generateBip44Wallet(
      BlockChainEnum chainEnum, String password, String keystorePath, boolean testNet) {
    File dir = getKeystoreDir(keystorePath);
    byte[] initialEntropy = new byte[16];
    SecureRandomUtils.secureRandom().nextBytes(initialEntropy);
    String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
    // 1.生成助记词
    //    String mnemonic = "ocean race coil clock slow attract drift engage arm flash spoil
    // kidney";
    //    System.out.println("mnemonic = " + mnemonic);
    // 2.生成种子
    byte[] seed = MnemonicUtils.generateSeed(mnemonic, null);
    //    System.out.println("seed = " + Numeric.toHexStringNoPrefix(seed));
    // 3. 生成根私钥 root private key 树顶点的master key ；bip32
    DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
    //    System.out.println("rootKey = " + rootPrivateKey.serializePrivB58(MainNetParams.get()));
    //    ExtendedPrivateKey rootKey = ExtendedPrivateKey.fromSeed(seed, Bitcoin.MAIN_NET);
    // 4. 由根私钥生成 第一个HD 钱包
    DeterministicHierarchy dh = new DeterministicHierarchy(rootPrivateKey);
    // 5. 定义父路径 H则是加强 imtoken中的eth钱包进过测试发现使用的是此方式生成 bip44
    List<ChildNumber> parentPath = HDUtils.parsePath(chainEnum.getBip44Path(testNet));
    // 6. 由父路径,派生出第一个子私钥 "new ChildNumber(0)" 表示第一个 （m/44'/60'/0'/0/0）
    DeterministicKey child = dh.deriveChild(parentPath, true, true, new ChildNumber(0));
    byte[] privateKeyByte = child.getPrivKeyBytes();

    BlockChainWallet wallet = null;
    if (chainEnum.isBitCoin()) {
      wallet = createBitCoinWallet(privateKeyByte, chainEnum.getNetworkParam(testNet));
    } else if (chainEnum.isEther()) {
      wallet = createEtherWallet(privateKeyByte);
    } else {
      throw new RuntimeException("暂不支持该币种");
    }
    wallet.setMnemonic(mnemonic);
    wallet.setPassword(password);
    return wallet;
  }

  private static BlockChainWallet createEtherWallet(byte[] privateKeyByte) {
    ECKeyPair ecKeyPair = ECKeyPair.create(privateKeyByte);
    BlockChainWallet wallet = new BlockChainWallet();
    wallet.setAddress(Keys.toChecksumAddress(Keys.getAddress(ecKeyPair)));
    wallet.setPublicKey(Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey()));
    wallet.setPrivateKey(Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey()));
    return wallet;
  }

  private static BlockChainWallet createBitCoinWallet(
      byte[] privateKeyByte, NetworkParameters networkParameters) {
    ECKey ecKey = ECKey.fromPrivate(privateKeyByte);
    BlockChainWallet wallet = new BlockChainWallet();
    wallet.setAddress(ecKey.toAddress(networkParameters).toString());
    wallet.setPublicKey(ecKey.getPublicKeyAsHex());
    wallet.setPrivateKey(ecKey.getPrivateKeyAsWiF(networkParameters));
    return wallet;
  }

  /** 创建Keystore文件 */
  @SneakyThrows
  private static void createKeystoreFile(File dir, WalletFile walletFile) {
   wall
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
    generateBip44Wallet(BlockChainEnum.LTC, "123456", "/keystore", false);
  }
}

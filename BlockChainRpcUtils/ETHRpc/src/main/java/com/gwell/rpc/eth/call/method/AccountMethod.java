package com.gwell.rpc.eth.call.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwell.rpc.common.util.FileUtil;
import com.gwell.rpc.eth.model.response.EthAccount;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import java.io.File;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
public class AccountMethod {
  private String keystorePath;

  public static AccountMethod build(String keystorePath) {
    AccountMethod instance = getInstance();
    instance.setKeystorePath(keystorePath);
    return instance;
  }

  private AccountMethod() {}

  /** 在静态内部类中持有singleton的实例，可以被直接初始化 */
  private static class Holder {
    private static AccountMethod instance = new AccountMethod();
  }

  private static AccountMethod getInstance() {
    return Holder.instance;
  }

  /**
   * 所有信息(私钥, 地址) - keystore
   *
   * @return {"privateKey" : "", "address" : ""}
   */
  @SneakyThrows
  public EthAccount newAccount(String password) {
    File dir = getKeystoreDir();
    // 生成 publicKey 和 privateKey
    ECKeyPair ecKeyPair = Keys.createEcKeyPair();
    // 生成钱包
    WalletFile walletFile = Wallet.createLight(password, ecKeyPair);
    createKeystoreFile(dir, walletFile);

    String address =
        new StringBuffer(walletFile.getAddress()).insert(0, "0x").toString().toLowerCase();
    return new EthAccount(address, ecKeyPair, password);
  }

  /** 导入私钥 */
  @SneakyThrows
  public EthAccount importPrivateKey(EthAccount ethAccount) {
    File dir = getKeystoreDir();

    String address = ethAccount.getAddress();
    String password = ethAccount.getPassword();
    String privateKey = ethAccount.getPrivateKey();
    ECKeyPair ecKeyPair = EthAccount.getECKeyPair(privateKey);

    // 生成钱包
    WalletFile walletFile;
    try {
      walletFile = Wallet.createStandard(password, ecKeyPair);
    } catch (Exception e) {
      throw new RuntimeException("私钥错误, 生成钱包失败! privateKey=" + privateKey);
    }
    String generateAddress = new StringBuffer(walletFile.getAddress()).insert(0, "0x").toString();
    if (!address.equalsIgnoreCase(generateAddress)) {
      throw new RuntimeException("地址与私钥不匹配! 私钥中的地址为:" + generateAddress);
    }
    File[] files =
        dir.listFiles(
            (file, name) -> {
              String fileName = name.toLowerCase();
              String addressStr = address.toLowerCase();
              return fileName.contains(addressStr);
            });
    if (files == null || files.length < 1) {
      createKeystoreFile(dir, walletFile);
    }
    log.info("keystore文件已存在!");

    return ethAccount;
  }

  /** 获取keystore文件夹, 不存在则创建 */
  @SneakyThrows
  private File getKeystoreDir() {
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

  /** 创建Keystore文件 */
  @SneakyThrows
  private void createKeystoreFile(File dir, WalletFile walletFile) {
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
}

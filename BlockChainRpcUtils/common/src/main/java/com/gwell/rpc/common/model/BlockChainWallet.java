package com.gwell.rpc.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.SneakyThrows;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;

import java.math.BigInteger;

@Data
public class BlockChainWallet {
  private String address;
  private String privateKey;
  private String publicKey;
  private String password;
  private String mnemonic;

  @JSONField(serialize = false)
  private Credentials credentials;

  public static ECKeyPair getECKeyPair(String privateKey) {
    String privateKeyStr = privateKey.replaceFirst("0x", "");
    BigInteger key;
    try {
      key = new BigInteger(privateKeyStr, 16);
    } catch (Exception e) {
      throw new RuntimeException("私钥格式错误! privateKey=" + privateKey);
    }
    ECKeyPair ecKeyPair = ECKeyPair.create(key);
    return ecKeyPair;
  }

  public static Credentials getCredentials(String privateKey) {
    return Credentials.create(privateKey.replaceFirst("0x", ""));
  }

  public BlockChainWallet() {}

  public BlockChainWallet(String address, String password) {
    this.address = address;
    this.password = password;
  }

  public BlockChainWallet(String privateKey) {
    this.credentials = Credentials.create(privateKey.replaceFirst("0x", ""));
    this.address = credentials.getAddress();
    this.privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
    this.publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
    this.password = "" + System.currentTimeMillis();
  }

  public BlockChainWallet(String address, ECKeyPair ecKeyPair, String password) {
    this.credentials = Credentials.create(ecKeyPair);
    this.address = address;
    this.privateKey = ecKeyPair.getPrivateKey().toString(16);
    this.publicKey = ecKeyPair.getPublicKey().toString(16);
    this.password = password;
  }

  public BlockChainWallet(Credentials credentials, String password) {
    this.credentials = credentials;
    this.address = credentials.getAddress();
    this.privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
    this.publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
    this.password = password;
  }

  public void setCredentials(Credentials credentials) {
    this.credentials = credentials;
    this.privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
    this.publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
  }

  /** 获取凭据 */
  @SneakyThrows
  public Credentials getCredentials() {
    if (credentials != null) {
      return credentials;
    }
    // 从私钥中获取凭据
    Credentials credentials;
    //    if (StringUtils.isNotBlank(privateKey)) {
    credentials = Credentials.create(privateKey.replaceFirst("0x", ""));
    //    } else {
    // 从keystore文件中获取凭据
    //      File dir = FileUtil.getFile(ETHRpc.keystorePath);
    //      if (!dir.exists()) {
    //        throw new RuntimeException("keystore文件夹不存在!");
    //      }
    //      File[] files =
    //          dir.listFiles(
    //              (file, name) -> {
    //                String fileName = name.toLowerCase();
    //                String addressStr = address.replaceFirst("0x", "").toLowerCase();
    //                return fileName.contains(addressStr);
    //              });
    //      if (null == files || files.length < 1) {
    //        throw new RuntimeException("keystore不存在!");
    //      }
    //      credentials = WalletUtils.loadCredentials(password, files[0]);
    //    }

    setCredentials(credentials);

    return credentials;
  }
}

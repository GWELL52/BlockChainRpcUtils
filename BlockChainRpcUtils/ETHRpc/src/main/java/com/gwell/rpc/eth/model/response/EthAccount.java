package com.gwell.rpc.eth.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.gwell.rpc.common.util.FileUtil;
import com.gwell.rpc.eth.ETHRpc;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.math.BigInteger;

@Data
public class EthAccount {
  private String address;
  private String privateKey;
  private String publicKey;
  private String password;

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

  public EthAccount() {}

  public EthAccount(String address, String password) {
    this.address = address;
    this.password = password;
  }

  public EthAccount(String address, String privateKey, String password) {
    this.credentials = Credentials.create(privateKey.replaceFirst("0x", ""));
    this.address = address;
    this.privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
    this.publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
    this.password = password;
  }

  public EthAccount(String address, ECKeyPair ecKeyPair, String password) {
    this.credentials = Credentials.create(ecKeyPair);
    this.address = address;
    this.privateKey = ecKeyPair.getPrivateKey().toString(16);
    this.publicKey = ecKeyPair.getPublicKey().toString(16);
    this.password = password;
  }

  public EthAccount(Credentials credentials, String password) {
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
    // 从私钥中获取凭据
    Credentials credentials;
    if (StringUtils.isNotBlank(privateKey)) {
      credentials = Credentials.create(privateKey.replaceFirst("0x", ""));
    } else {
      // 从keystore文件中获取凭据
      File dir = FileUtil.getFile(ETHRpc.keystorePath);
      if (!dir.exists()) {
        throw new RuntimeException("keystore文件夹不存在!");
      }
      File[] files =
          dir.listFiles(
              (file, name) -> {
                String fileName = name.toLowerCase();
                String addressStr = address.replaceFirst("0x", "").toLowerCase();
                return fileName.contains(addressStr);
              });
      if (null == files || files.length < 1) {
        throw new RuntimeException("keystore不存在!");
      }
      credentials = WalletUtils.loadCredentials(password, files[0]);
    }

    setCredentials(credentials);

    return credentials;
  }
}

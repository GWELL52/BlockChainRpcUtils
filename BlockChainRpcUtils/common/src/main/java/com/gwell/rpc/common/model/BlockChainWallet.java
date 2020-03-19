package com.gwell.rpc.common.model;

import com.gwell.rpc.common.enums.BlockChainEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockChainWallet {
  /** 地址 */
  private String address;
  /** 私钥 */
  private String privateKey;
  /** 公钥 */
  private String publicKey;
  /** 当前子地址索引 */
  private int childIndex;
  /** 所属链 */
  private BlockChainEnum blockChainEnum;
}

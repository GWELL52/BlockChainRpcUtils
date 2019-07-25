package com.gwell.rpc.eth.model.response;

import lombok.Data;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

@Data
public class InputData {
  private String contractAddress;

  private String toAddress;

  private BigInteger weiValue;

  public InputData() {}

  public InputData(Log log) {
    if (null != log) {
      contractAddress = log.getAddress();
      List<String> topics = log.getTopics();
      if (topics.size() == 3) {
        toAddress = log.getTopics().get(2);
        weiValue = Numeric.decodeQuantity(log.getData());
      }
    }
  }
}

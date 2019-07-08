package com.gwell.rpc.eth.common.enums;

public enum BlockChainEnum {
	BTC,
	ETH,
	;

	public String lowerCaseName() {
		return name().toLowerCase();
	}
}

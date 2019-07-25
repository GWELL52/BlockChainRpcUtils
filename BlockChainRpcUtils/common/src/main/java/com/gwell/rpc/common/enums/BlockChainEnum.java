package com.gwell.rpc.common.enums;

import java.math.BigDecimal;

public enum BlockChainEnum {
	BTC(BigDecimal.TEN.pow(8)),
	ETH(BigDecimal.TEN.pow(18)),
	;

	private BigDecimal decimal;

	BlockChainEnum(BigDecimal decimal) {
		this.decimal = decimal;
	}

	public String lowerCaseName() {
		return name().toLowerCase();
	}

	public BigDecimal getDecimal() {
		return decimal;
	}
}

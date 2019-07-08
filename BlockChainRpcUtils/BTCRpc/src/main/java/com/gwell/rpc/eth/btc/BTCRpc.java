package com.gwell.rpc.eth.btc;

import com.gwell.rpc.eth.btc.call.Call;
import com.gwell.rpc.eth.common.model.Connection;

public class BTCRpc extends Call {

	public static BTCRpc build(String url) {
		return new BTCRpc(url);
	}

	public static BTCRpc build(String ip, Integer port, String username, String password) {
		return new BTCRpc(ip,port,username,password);
	}

	public static BTCRpc build(Connection connection) {
		return new BTCRpc(connection);
	}

	private BTCRpc(String url) {
		super(Connection.builder(url).build());
	}

	private BTCRpc(String ip, Integer port, String username, String password) {
		super(Connection.builder(ip, port, username, password).build());
	}

	private BTCRpc(Connection connection) {
		super(connection);
	}

}

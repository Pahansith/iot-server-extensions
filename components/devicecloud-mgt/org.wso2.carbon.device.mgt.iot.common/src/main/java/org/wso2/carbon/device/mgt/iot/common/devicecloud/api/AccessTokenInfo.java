package org.wso2.carbon.device.mgt.iot.common.devicecloud.api;

public class AccessTokenInfo {

	private String token_type;
	private int expres_in;
	private String refresh_token;
	private String access_token;

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public int getExpres_in() {
		return expres_in;
	}

	public void setExpres_in(int expres_in) {
		this.expres_in = expres_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


}

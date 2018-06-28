package com.zplay.android.sdk.online;

import com.zplay.android.sdk.online.bean.ZplayBaseLoginBean;

public class ZplayLoginBean implements ZplayBaseLoginBean {

	
	private String zplayToken;
	private String loginUid;
	
	
	

	
	public ZplayLoginBean(String zplayToken, String loginUid) {
		super();
		this.zplayToken = zplayToken;
		this.loginUid = loginUid;
	}
	
	
	public String getLoginUid() {
		return loginUid;
	}
	public void setLoginUid(String loginUid) {
		this.loginUid = loginUid;
	}
	public String getZplayToken() {
		return zplayToken;
	}
	public void setZplayToken(String accessToken) {
		this.zplayToken = accessToken;
	}

}

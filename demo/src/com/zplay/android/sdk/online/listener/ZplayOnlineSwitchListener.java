package com.zplay.android.sdk.online.listener;

import com.zplay.android.sdk.online.bean.ZplayBaseLoginBean;

public interface ZplayOnlineSwitchListener {
	
	public void onSwitchSuccess(ZplayBaseLoginBean loginUser, String errorMsg);
	
	public void onSwitchFail(String errorMsg);
	
}

package com.zplay.android.sdk.online.listener;

import com.zplay.android.sdk.online.bean.ResultCode;

public interface ZplayOnlinePayResultListener {

	public void onPaySuccess(String orderId, String zplayOrderId, String amount);
	
	public void onPayFailed(String orderId, String zplayOrderId, String amount, ResultCode error, String errorMsg);
	
}

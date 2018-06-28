package com.zplay.android.sdk.online.listener;

import com.zplay.android.sdk.online.bean.ResultCode;
import com.zplay.android.sdk.online.bean.ZplayBaseLoginBean;

public interface ZplayOnlineLoginListener {

	public void loginResult(boolean success, ZplayBaseLoginBean loginUser, ResultCode error, String errorMsg);
}

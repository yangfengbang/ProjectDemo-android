package com.zplay.android.sdk.online.listener;

import com.zplay.android.sdk.online.bean.ResultCode;

/**
 * 游戏退出监听
 * @author Administrator
 *
 */
public interface ZplayOnlineExitListener {
	
	public void continuePlay(String data);
	
	public void exitPlay(String data);
}

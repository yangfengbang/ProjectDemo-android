package com.zplay.android.sdk.online.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zplay.android.sdk.online.bean.ZplayBaseInitParams;
import com.zplay.android.sdk.online.bean.ZplayBaseOrder;
import com.zplay.android.sdk.online.bean.ZplayBaseUserInfo;

public interface ZplayBaseOnlineAdapter {
	/**
	 * 初始化SDK
	 * 
	 * @param activity
	 * @param logListener
	 * @param payListener
	 * @param params
	 */
	public void initAdapter(Activity activity,
			ZplayOnlineLoginListener logListener,
			ZplayOnlinePayResultListener payListener,
			ZplayBaseInitParams params, ZplayOnlineExitListener exitListener,
			ZplayOnlineLogoutListener logoutListener,
			ZplayOnlineSwitchListener switchListener);

	/**
	 * 登陆接口
	 * 
	 * @param activity
	 */
	public void login(Activity activity);

	/**
	 * 注销接口
	 * 
	 * @param activity
	 */
	public void logout(Activity activity);

	/**
	 * 支付接口
	 * 
	 * @param activity
	 * @param order
	 */
	public void doPay(Activity activity, ZplayBaseOrder order);

	/**
	 * activity 结果返回
	 * 
	 * @param context
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public void onActivityResult(Context context, int requestCode,
			int resultCode, Intent data);

	/**
	 * 用户中心
	 * 
	 * @param activity
	 */
	public void userCenter(Activity activity);

	/**
	 * 游戏按下返回键时调用
	 * 
	 * @param activity
	 */
	public void onQuit(Activity activity);

	/**
	 * 游戏获取焦点的时候调用
	 * 
	 * @param activity
	 */
	public void onZplayResume(Activity activity);

	/**
	 * 游戏失去焦点的时候调用
	 * 
	 * @param activity
	 */
	public void onZplayPause(Activity activity);

	/**
	 * 销毁SDK的时候调用
	 * 
	 * @param activity
	 */
	public void releaseAdapter(Activity activity);

	/**
	 * 切换账号时调用
	 * 
	 * @param activity
	 */
	public void switchUser(Activity activity);

	/**
	 * 登陆成功到游戏后调用 上传用户信息到SDK
	 * 
	 * @param userInfo
	 */
	public void sdkSubmitExtendData(ZplayBaseUserInfo userInfo);

	/***
	 * 用户反馈接口
	 * 
	 * @param activity
	 * @param title
	 * @param content
	 */
	public void onZplayUserFeedback(Activity activity, String title,
			String content);

	public void visitorLogin(Activity activity);
}

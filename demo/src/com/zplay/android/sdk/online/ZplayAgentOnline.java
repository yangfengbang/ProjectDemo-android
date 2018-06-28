package com.zplay.android.sdk.online;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zplay.android.sdk.online.adapter.huawei.HuaWeiOrder;
import com.zplay.android.sdk.online.adapter.huawei.ZplayUserInfo;
import com.zplay.android.sdk.online.bean.Providers;
import com.zplay.android.sdk.online.core.ZplayOnlineHelper;
import com.zplay.android.sdk.online.listener.ZplayOnlineExitListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLoginListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLogoutListener;
import com.zplay.android.sdk.online.listener.ZplayOnlinePayResultListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineSwitchListener;
import com.zplay.android.sdk.online.utils.ConfigValueHandler;
import com.zplay.android.sdk.online.utils.IdentifierGetter;

public class ZplayAgentOnline {

	private static ZplayOnlineHelper helper = null;
	private static String Zplay_SDK_KEY;

	/**
	 * SDK初始化接口
	 * 
	 * @param provider
	 *            初始化SDK类型 列如: 初始化uc Providers.UC
	 * @param activity
	 *            当前activity
	 * @param logListener
	 *            登陆监听回调
	 * @param payListener
	 *            支付监听回调
	 * @param exitListener
	 *            推出监听回调
	 * @param logoutListener
	 *            注销监听回调
	 * @param switchListener
	 *            切换账号监听回调
	 */
	public static void initSDK(final Providers provider,
			final Activity activity,
			final ZplayOnlineLoginListener logListener,
			final ZplayOnlinePayResultListener payListener,
			final ZplayOnlineExitListener exitListener,
			final ZplayOnlineLogoutListener logoutListener,
			final ZplayOnlineSwitchListener switchListener) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				helper = ZplayOnlineHelper.getHelper(provider);

				if (helper != null) {

					// 配置游戏信息（gid、appKey由搜狗游戏平台统一分配）

					helper.initAdapter(activity, logListener, payListener,
							null, exitListener, logoutListener, switchListener);
				}
			}
		});

	}

	/**
	 * 登陆接口
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void login(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.login(activity);
				}
			}
		});
	}

	/**
	 * 游客登陆接口
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void visitorLogin(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.visitorLogin(activity);
				}
			}
		});
	}

	/**
	 * 注销接口
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void logout(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.logout(activity);
				}
			}
		});
	}

	/**
	 * 支付接口
	 * 
	 * @param activity
	 * @param orderId
	 *            订单号
	 * @param currency
	 *            游戏货币名字
	 * @param rate
	 *            人民币兑换比例
	 * @param productName
	 *            购买商品名字
	 * @param amount
	 *            充值金额,单位分
	 * @param unitPrice
	 *            商品单价,单位分
	 * @param productNumber
	 *            购买数量
	 * @param roleId
	 *            角色ID
	 * @param roleName
	 *            用户的游戏角色名字
	 * @param grade
	 *            用户的游戏角色等级
	 * @param body
	 *            商品描述
	 * @param productId
	 *            游戏道具Id
	 * @param productSubject
	 *            订单标题,格式为： ”购买 N 枚金币
	 * @param product_unit
	 *            游戏道具的单位，默认值： ””
	 * @param blance
	 *            游戏中元宝数量 例子:100元宝
	 * @param vip
	 *            vip等级 "vip2"
	 * @param party
	 *            工会
	 * @param serverName
	 *            服务器信息
	 * @param gameAbbreviation
	 *            游戏简称
	 * 
	 */
	public static void doPay(final Activity activity, final String orderId,
			final String currency, final String rate, final String productName,
			final String amount, String unitPrice, final String productNumber,
			final String roleId, final String roleName, final String grade,
			final String body, String productId, String productSubject,
			String product_unit, String blance, String vip, String party,
			String serverName, final String gameAbbreviation) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					// orderNumber 交易流水号，由订单推送接口返回
					// accessKey 由订单推送接口返回
					// notifyUrl 回调地址
					helper.doPay(activity,
							new HuaWeiOrder(body, roleId, roleName, grade,
									Float.valueOf(amount) / 100, orderId,
									productNumber, productName, rate, currency));
				}

			}
		});
	}

	/**
	 * activity 结果返回
	 * 
	 * @param context
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public static void onActivityResult(Context context, int requestCode,
			int resultCode, Intent data) {
		if (helper != null) {
			helper.onActivityResult(context, requestCode, resultCode, data);
		}
	}

	/**
	 * 用户中心
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void userCenter(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.userCenter(activity);
				}
			}
		});
	}

	/**
	 * 游戏按下返回键时调用
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void onQuit(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.onQuit(activity);
				}

			}
		});
	}

	/**
	 * 游戏获取焦点的时候调用
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void onZplayResume(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.onZplayResume(activity);
				}
			}
		});
	}

	/**
	 * 游戏失去焦点的时候调用
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void onZplayPause(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.onZplayPause(activity);
				}
			}
		});
	}

	/**
	 * 销毁SDK的时候调用
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void onZplayDestroy(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.releaseAdapter(activity);
				}
			}
		});
	}

	/**
	 * 切换账号
	 * 
	 * @param activity
	 *            当前activity
	 */
	public static void onZplaySwitchUser(final Activity activity) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.onZplaySwitchUser(activity);
				}
			}
		});
	}

	/**
	 * 上传用户信息到SDK
	 * 
	 * @param roleId
	 *            玩家角色ID
	 * @param roleName
	 *            玩家角色名
	 * @param roleLevel
	 *            玩家角色等级
	 * @param zoneId
	 *            游戏区服ID
	 * @param zoneName
	 *            游戏区服名称
	 * @param sociatyName
	 *            玩家的公会名称
	 */
	public static void onZplaySdkSubmitExtendData(String roleId,
			String roleName, String roleLevel, String zoneId, String zoneName,
			String sociatyName) {
		if (helper != null) {
			ZplayUserInfo userInfo = new ZplayUserInfo(roleId, roleName,
					roleLevel, zoneId, zoneName, sociatyName);
			helper.onZplaySdkSubmitExtendData(userInfo);
		}
	}

	/**
	 * 用户反馈接口
	 * 
	 * @param activity
	 *            当前activity
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 */
	public static void onZplayUserFeedback(final Activity activity,
			final String title, final String content) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (helper != null) {
					helper.onZplayUserFeedback(activity, title, content);
				}
			}
		});

	}

	/**
	 * 显示闪屏logo
	 */
	public static void onZplayShowLogo(final Activity activity) {
	}

	public static String onGetZplayChannelID(final Activity activity) {
		return ConfigValueHandler.getChannel(activity);
	}

	public static void setZplaySdkKey(String Zplay_SDK_KEY) {
		ZplayAgentOnline.Zplay_SDK_KEY = Zplay_SDK_KEY;
	}

	public static String getZplaySdkKey() {
		return Zplay_SDK_KEY;
	}
}

package com.zplay.android.sdk.online.adapter.huawei;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.game.GameLoginSignUtil;
import com.huawei.android.hms.agent.game.handler.ICheckLoginSignHandler;
import com.huawei.android.hms.agent.game.handler.LoginHandler;
import com.huawei.android.hms.agent.game.handler.SaveInfoHandler;
import com.huawei.android.hms.agent.pay.PaySignUtil;
import com.huawei.android.hms.agent.pay.handler.PayHandler;
import com.huawei.hms.support.api.entity.game.GamePlayerInfo;
import com.huawei.hms.support.api.entity.game.GameStatusCodes;
import com.huawei.hms.support.api.entity.game.GameUserData;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.PayStatusCodes;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.zplay.android.sdk.online.ZplayAgentOnline;
import com.zplay.android.sdk.online.ZplayLoginBean;
import com.zplay.android.sdk.online.bean.ResultCode;
import com.zplay.android.sdk.online.bean.ZplayBaseInitParams;
import com.zplay.android.sdk.online.bean.ZplayBaseLoginBean;
import com.zplay.android.sdk.online.bean.ZplayBaseOrder;
import com.zplay.android.sdk.online.bean.ZplayBaseUserInfo;
import com.zplay.android.sdk.online.constants.APIList;
import com.zplay.android.sdk.online.constants.ConstantsHolder;
import com.zplay.android.sdk.online.listener.ZplayBaseOnlineAdapter;
import com.zplay.android.sdk.online.listener.ZplayOnlineExitListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLoginListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLogoutListener;
import com.zplay.android.sdk.online.listener.ZplayOnlinePayResultListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineSwitchListener;
import com.zplay.android.sdk.online.utils.ConfigValueHandler;
import com.zplay.android.sdk.online.utils.Encrypter;
import com.zplay.android.sdk.online.utils.IdentifierGetter;
import com.zplay.android.sdk.online.utils.JsonResolveUtils;
import com.zplay.android.sdk.online.utils.LogUtils;
import com.zplay.android.sdk.online.utils.PackageInfoGetter;
import com.zplay.android.sdk.online.utils.ParamsMapBuilder;
import com.zplay.android.sdk.online.utils.PhoneInfoGetter;
import com.zplay.android.sdk.online.utils.RSAUtil;
import com.zplay.android.sdk.online.utils.SPValueHandler;
import com.zplay.android.sdk.online.utils.Task;
import com.zplay.android.sdk.online.utils.TaskHandler;

public class HuaWeiAdapter implements ZplayBaseOnlineAdapter {
	private static final String TAG = "HuaweiAdapter";

	private ZplayOnlineLoginListener logListener;
	private ZplayOnlinePayResultListener payListener;
	private ZplayOnlineExitListener exitListener;
	private ZplayOnlineSwitchListener switchListener;
	public static ZplayOnlineLogoutListener logoutListener;
	//
	private String uid = "999999999";
	private String zplayToken = null;
	public static String token = null;
	private Activity activity = null;
	private static HuaWeiOrder huaWeiOrder;
	private String orderId;
	private boolean isChannelLogin = false;

	@Override
	public void initAdapter(Activity activity,
			ZplayOnlineLoginListener logListener,
			ZplayOnlinePayResultListener payListener,
			ZplayBaseInitParams params, ZplayOnlineExitListener exitListener,
			ZplayOnlineLogoutListener logoutListener,
			ZplayOnlineSwitchListener switchListener) {
		this.activity = activity;
		this.logListener = logListener;
		this.payListener = payListener;
		this.switchListener = switchListener;
		this.logoutListener = logoutListener;
		this.exitListener = exitListener;
		LogUtils.isShowLog = ConfigValueHandler.getLogSwitch(activity);
		//
		initSDK(activity);
		// 获取 deviceId,并保存本地
		String deviceId = Encrypter.doMD5EncodeWithUppercase(PhoneInfoGetter
				.getIMEI(activity) + PhoneInfoGetter.getAndroidID(activity));
		SPValueHandler.setDeviceID(activity, deviceId);
	}

	public void initSDK(final Activity activity) {
	}

	@Override
	public void login(final Activity activity) {
		loginHuaWei(activity);
	}

	// 登录 华为
	private void loginHuaWei(final Activity activity) {
		// forceLogin:0适用于单机游戏的登录场景;1适用于网游的登录场景
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				HMSAgent.Game.login(new LoginHandler() {
					@Override
					public void onResult(int retCode,
							final GameUserData userData) {
						LogUtils.i(TAG, "login retCode:" + retCode);
						if (retCode == GameStatusCodes.GAME_STATE_SUCCESS) {
							// 登录成功
							final String huaweiUid = userData.getPlayerId();
							if (userData.getIsAuth() == 1) {
								LogUtils.i(TAG, "IsAuth() == 1,验证 登录结果签名");
								// 当isAuth为1的时候，应用需要校验返回的参数鉴权签名。
								final String appId = ConfigValueHandler
										.getAppID(activity);
								final String cpId = ConfigValueHandler
										.getPayID(activity);
								final String gamePrivateKey = ConfigValueHandler
										.getGamePrivateKey(activity);
								final String gamePublicKey = ConfigValueHandler
										.getGamePublicKey(activity);
								activity.runOnUiThread(new Runnable() {
									@Override
									public void run() {
										GameLoginSignUtil.checkLoginSign(appId,
												cpId, gamePrivateKey,
												gamePublicKey, userData,
												new ICheckLoginSignHandler() {
													@Override
													public void onCheckResult(
															String code,
															final String resultDesc,
															final boolean isCheckSuccess) {
														LogUtils.i(
																TAG,
																"checkLoginSign:"
																		+ resultDesc);
														activity.runOnUiThread(new Runnable() {
															@Override
															public void run() {
																if (isCheckSuccess) {
																	// zlpay登录
																	requestLogin(
																			activity,
																			huaweiUid);
																} else {
																	// 鉴权失败
																	logListener
																			.loginResult(
																					false,
																					null,
																					ResultCode.LOGIN_FAIL,
																					"登录鉴权失败");
																}
															}
														});
													}
												});
									}
								});
							}
						} else if (retCode == GameStatusCodes.GAME_STATE_USER_CANCEL) {
							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									// 用户取消
									logListener.loginResult(false, null,
											ResultCode.LOGIN_CANCEL, "用户取消");
								}
							});
						} else {
							activity.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									logListener.loginResult(false, null,
											ResultCode.LOGIN_FAIL, "登录失败");
								}
							});
						}
					}

					@Override
					public void onChange() {
						// 当游戏收到该回调结果的时候，表示用户在游戏浮标中进行了帐号切换，
						// 游戏收到通知之后需要返回游戏首页重新调用login接口。
						loginHuaWei(activity);
					}
				}, 0);
			}
		});
	}

	// 登录 zlpay
	@SuppressWarnings("unchecked")
	private void requestLogin(final Activity activity, final String huaweiUid) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!TextUtils.isEmpty(huaweiUid)) {
					(new TaskHandler(activity, true, IdentifierGetter
							.getStringIdentifier(activity, "zplay_login"),
							new Task() {
								public void doTask(String data, String msg) {
									LogUtils.d(TAG, "requestLogin data:" + data);
									if (data == null) {
										Toast.makeText(
												activity,
												IdentifierGetter
														.getStringIdentifier(
																activity,
																"zplay_request_data_null"),
												0).show();
									} else {
										JSONObject jsonObject = JsonResolveUtils
												.buildJSON(data);
										int msgCode = JsonResolveUtils
												.getIntFromJson(jsonObject,
														"errno", 3);
										if (msgCode == 0) {
											LogUtils.d(TAG, "登陆成功");
											JSONObject jsonData = JsonResolveUtils
													.getJsonObjectFromJson(
															jsonObject, "data");
											HuaWeiAdapter.this.uid = JsonResolveUtils
													.getStringFromJson(
															jsonData, "uid",
															(String) null);
											HuaWeiAdapter.this.zplayToken = JsonResolveUtils
													.getStringFromJson(
															jsonData,
															"zplay_token",
															(String) null);
											LogUtils.d(TAG, "zplayToken:"
													+ zplayToken + ",uid:"
													+ uid);
											isChannelLogin = true;
											SPValueHandler.setLoginUserID(
													activity, uid);
											SPValueHandler.setLoginType(
													activity, "user");
											logListener.loginResult(true,
													new ZplayLoginBean(
															zplayToken, uid),
													ResultCode.LOGIN_SUCCESS,
													"登陆成功");
										} else {
											LogUtils.d(TAG, "登录失败");
											logListener.loginResult(false,
													null,
													ResultCode.LOGIN_FAIL,
													"登录失败");
										}
									}
								}
							})).execute(new Map[] { ParamsMapBuilder
							.buildParams(
									APIList.BECOME_BIND_USER,
									new String[] { "zplay_key", "platform",
											"code", "uid", "android_id",
											"game_channel_id", "game_version",
											"user_imei", "sign", "zplay_uid",
											ConstantsHolder.UINFO_DEVICE_ID },
									new String[] {
											ZplayAgentOnline.getZplaySdkKey(),
											"huawei",
											"",
											huaweiUid,
											PhoneInfoGetter
													.getAndroidID(activity),
											ConfigValueHandler
													.getChannel(activity),
											PhoneInfoGetter
													.getAppVersionName(activity),
											PhoneInfoGetter.getIMEI(activity),
											Encrypter.doMD5EncodeWithUppercase(ZplayAgentOnline
													.getZplaySdkKey()
													+ "huawei"
													+ huaweiUid
													+ ConstantsHolder.ZPLAY_SING_KEY),
											SPValueHandler
													.getVisitorLoginUserID(activity),
											SPValueHandler
													.getDeviceID(activity) }) });
				} else {
					logListener.loginResult(false, null, ResultCode.LOGIN_FAIL,
							"获取 huaweiUid失败");
				}
			}
		});
	}

	@Override
	public void logout(Activity activity) {
		logoutListener.onLogoutSuccess();
	}

	@Override
	public void doPay(Activity activity, ZplayBaseOrder order) {
		if (!isChannelLogin && !ConfigValueHandler.getLoginONOFF(activity)) {
			login(activity);
			return;
		}
		HuaWeiOrder huaweiOrder = (HuaWeiOrder) order;
		// TODO 请求服务器, 生成订单id
		if (huaweiOrder.getOrderId() != null) {
			requestOrderId(activity, huaweiOrder);
		}
	}

	public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@SuppressWarnings("unchecked")
	private void requestOrderId(final Activity activity,
			final HuaWeiOrder huaWeiOrder) {
		new TaskHandler(activity, true, IdentifierGetter.getStringIdentifier(
				activity, "zplay_request_order"), new Task() {
			public void doTask(String data, String msg) {
				if (data == null) {
					payListener.onPayFailed(huaWeiOrder.getOrderId(), "",
							String.valueOf((huaWeiOrder.getAmount() * 100)),
							ResultCode.PAY_FAIL, "get sign fail");
					Toast.makeText(
							activity,
							IdentifierGetter.getStringIdentifier(activity,
									"zplay_request_data_null"), 0).show();
				} else {
					JSONObject jsonObject = JsonResolveUtils.buildJSON(data);
					int msgCode = JsonResolveUtils.getIntFromJson(jsonObject,
							"errno", 3);
					if (msgCode == 0) {
						JSONObject jsonData = JsonResolveUtils
								.getJsonObjectFromJson(jsonObject, "data");
						orderId = JsonResolveUtils.getStringFromJson(jsonData,
								"orderId", null);
						LogUtils.i(TAG, orderId);
						if (!TextUtils.isEmpty(orderId)) {
							String AppID = ConfigValueHandler
									.getAppID(activity);
							String PayID = ConfigValueHandler
									.getPayID(activity);
							// 创建 支付数据
							PayReq payReq = createPayReq(huaWeiOrder, AppID,
									PayID, orderId);
							HMSAgent.Pay.pay(payReq, new PayHandler() {

								@Override
								public void onResult(int retCode,
										PayResultInfo payInfo) {
									if (retCode == PayStatusCodes.PAY_STATE_SUCCESS
											&& payInfo != null) {
										boolean checkRst = PaySignUtil
												.checkSign(
														payInfo,
														ConfigValueHandler
																.getPayPublicKey(activity));
										if (checkRst) {
											// 支付成功并且验签成功，发放商品
											// pay_result_success
											LogUtils.i(TAG, "支付成功");
											payListener.onPaySuccess(
													huaWeiOrder.getOrderId(),
													orderId,
													String.valueOf((huaWeiOrder
															.getAmount() * 100)));
										} else {
											// 签名失败，需要查询订单状态：对于没有服务器的单机应用，调用查询订单接口查询；其他应用到开发者服务器查询订单状态。
											// pay_result_check_sign_failed
											LogUtils.i(TAG, "支付 签名失败");
											payListener.onPayFailed(
													huaWeiOrder.getOrderId(),
													orderId,
													String.valueOf((huaWeiOrder
															.getAmount() * 100)),
													ResultCode.PAY_FAIL,
													"pay_result_check_sign_failed");
										}
									} else if (retCode == PayStatusCodes.PAY_STATE_CANCEL) {
										payListener.onPayFailed(huaWeiOrder
												.getOrderId(), orderId, String
												.valueOf((huaWeiOrder
														.getAmount() * 100)),
												ResultCode.PAY_CANCEL, payInfo
														.getErrMsg());
									} else {
										payListener.onPayFailed(huaWeiOrder
												.getOrderId(), orderId, String
												.valueOf((huaWeiOrder
														.getAmount() * 100)),
												ResultCode.PAY_FAIL, payInfo
														.getErrMsg());
									}
								}

							});
						} else {
							LogUtils.d(TAG, "zplaySDK服务器获取订单号为null");
							payListener.onPayFailed(
									huaWeiOrder.getOrderId(),
									"",
									String.valueOf((huaWeiOrder.getAmount() * 100)),
									ResultCode.PAY_FAIL, "get orderId fail");
							Toast.makeText(
									activity,
									IdentifierGetter.getStringIdentifier(
											activity, "zplay_data_erro"), 0)
									.show();
						}

					} else {
						LogUtils.d(TAG, "获取订单号失败");
						payListener.onPayFailed(
								huaWeiOrder.getOrderId(),
								"",
								String.valueOf((huaWeiOrder.getAmount() * 100)),
								ResultCode.PAY_FAIL, "get orderId fail");
						Toast.makeText(
								activity,
								IdentifierGetter.getStringIdentifier(activity,
										"zplay_data_erro"), 0).show();
					}
				}
			}
		}).execute(ParamsMapBuilder.buildParams(
				APIList.REQUEST_REQUEST_ORDERID,
				new String[] { ConstantsHolder.SDK_KEY,
						ConstantsHolder.KEY_CHANNEL,
						ConstantsHolder.KEY_ZPLAY_QUANTITY,
						ConstantsHolder.KEY_ZPLAY_TOTALFEE,
						ConstantsHolder.KEY_ZPLAY_FEEWAY,
						ConstantsHolder.KEY_ZPLAY_IMEI,
						ConstantsHolder.KEY_ZPLAY_IDFA,
						ConstantsHolder.KEY_ZPLAY_PLATFORM,
						ConstantsHolder.KEY_ZPLAY_GAMEUSERID,
						ConstantsHolder.KEY_ZPLAY_CPDEFINEINFO,
						ConstantsHolder.KEY_ZPLAY_GAMEORDERID,
						ConstantsHolder.KEY_ZPLAY_PRODUCTNAME,
						ConstantsHolder.KEY_ZPLAY_SING,
						ConstantsHolder.KEY_ZPLAY_GAME_VER,
						ConstantsHolder.KEY_ZPLAY_SITE_NAME },
				new String[] {
						ZplayAgentOnline.getZplaySdkKey(),
						ConfigValueHandler.getChannel(activity),
						huaWeiOrder.getProductNumber(),
						String.valueOf(huaWeiOrder.getAmount()),
						"",
						PhoneInfoGetter.getIMEI(activity),
						"",
						"android",
						SPValueHandler.getLoginUserID(activity),
						huaWeiOrder.getProductDesc(),
						huaWeiOrder.getOrderId(),
						huaWeiOrder.getProductName(),
						Encrypter.doMD5EncodeWithUppercase(ZplayAgentOnline
								.getZplaySdkKey()
								+ huaWeiOrder.getOrderId()
								+ uid + ConstantsHolder.KEY_ZPLAY_SECRET_KEY),
						PackageInfoGetter.getAppVersionName(
								activity.getPackageManager(),
								activity.getPackageName()), "huawei" }));
	}

	private PayReq createPayReq(HuaWeiOrder huaWeiOrder, String appID,
			String cpID, String orderId) {
		PayReq payReq = new PayReq();
		/**
		 * 生成总金额,单位元；
		 */
		String amount = String.format("%.2f", huaWeiOrder.getAmount());
		// 商品名称
		payReq.productName = "" + huaWeiOrder.getProductName();
		// 商品描述
		payReq.productDesc = "" + huaWeiOrder.getProductDesc();
		// 商户ID
		payReq.merchantId = cpID;
		// 应用ID
		payReq.applicationID = appID;
		// 支付金额
		payReq.amount = amount;
		// 支付订单号
		payReq.requestId = orderId;
		// 国家码
		payReq.country = "CN";
		// 币种
		payReq.currency = "CNY";
		// 渠道号
		payReq.sdkChannel = 1;
		// 回调接口版本号
		payReq.urlVer = "2";

		// 商户名称，必填，不参与签名。会显示在支付结果页面
		payReq.merchantName = "掌游天下（北京）信息技术股份有限公司";
		// 分类，必填，不参与签名。该字段会影响风控策略
		// X4：主题,X5：应用商店, X6：游戏,X7：天际通,X8：云空间,X9：电子书,X10：华为学习,X11：音乐,X12 视频,
		// X31 话费充值,X32 机票/酒店,X33 电影票,X34 团购,X35 手机预购,X36 公共缴费,X39 流量充值
		payReq.serviceCatalog = "X6";
		// 商户保留信息，选填不参与签名，支付成功后会华为支付平台会原样 回调CP服务端
		payReq.extReserved = orderId;

		// 对支付请求信息进行签名,建议CP在服务器端储存签名私钥，并在服务器端进行签名操作。
		// 下面调用的工具方法，供实现参考
		payReq.sign = PaySignUtil.calculateSignString(payReq,
				ConfigValueHandler.getPayPrivateKey(activity));
		return payReq;
	}

	@Override
	public void onActivityResult(Context context, int requestCode,
			int resultCode, Intent data) {

	}

	@Override
	public void userCenter(Activity activity) {

	}

	@Override
	public void onQuit(Activity activity) {

	}

	@Override
	public void onZplayResume(Activity activity) {
		// 在界面恢复的时候显示浮标，和onPause配合使用
		HMSAgent.Game.showFloatWindow(activity);
	}

	@Override
	public void onZplayPause(Activity activity) {
		// 在界面暂停的时候，隐藏悬浮按钮，和onResume配合使用
		HMSAgent.Game.hideFloatWindow(activity);
	}

	@Override
	public void releaseAdapter(Activity activity) {
	}

	@Override
	public void switchUser(Activity activity) {

	}

	@Override
	public void sdkSubmitExtendData(ZplayBaseUserInfo userInfo) {
		ZplayUserInfo zUserInfo = (ZplayUserInfo) userInfo;
		GamePlayerInfo gamePlayerInfo = new GamePlayerInfo();
		gamePlayerInfo.rank = zUserInfo.getRoleLevel();// 角色等级
		gamePlayerInfo.role = zUserInfo.getRoleName();// 角色名称
		gamePlayerInfo.area = zUserInfo.getZoneName();// 角色所属区
		gamePlayerInfo.sociaty = zUserInfo.getSociatyName();// 角色所属公会名称
		HMSAgent.Game.savePlayerInfo(gamePlayerInfo, new SaveInfoHandler() {

			@Override
			public void onResult(int retCode) {
				LogUtils.i(TAG, "submitExtendData retCode:" + retCode);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onZplayUserFeedback(final Activity activity, String title,
			String content) {
		OnlineUtils.userFeedback(activity, zplayToken, title, content, TAG);
	}

	/**
	 * 游客登录接口
	 * 
	 * @param activity
	 * @param logout
	 */
	public void visitorLogin(final Activity activity) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				String deviceId = Encrypter
						.doMD5EncodeWithUppercase(PhoneInfoGetter
								.getIMEI(activity)
								+ PhoneInfoGetter.getAndroidID(activity));
				SPValueHandler.setDeviceID(activity, deviceId);
				requestVisitorLogin(activity, deviceId);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void requestVisitorLogin(final Activity activity,
			final String deviceId) {
		if (!TextUtils.isEmpty(deviceId)) {
			new TaskHandler(activity, false,
					IdentifierGetter.getStringIdentifier(activity,
							"zplay_login"), new Task() {
						public void doTask(String data, String msg) {
							if (data == null) {
								Toast.makeText(
										activity,
										IdentifierGetter.getStringIdentifier(
												activity,
												"zplay_request_data_null"), 0)
										.show();
							} else {
								JSONObject jsonObject = JsonResolveUtils
										.buildJSON(data);
								int msgCode = JsonResolveUtils.getIntFromJson(
										jsonObject, "errno", 3);
								if (msgCode == 0) {
									LogUtils.d(TAG, "登陆成功");
									JSONObject jsonData = JsonResolveUtils
											.getJsonObjectFromJson(jsonObject,
													"data");
									uid = JsonResolveUtils.getStringFromJson(
											jsonData, "uid", null);
									zplayToken = JsonResolveUtils
											.getStringFromJson(jsonData,
													"zplay_token", null);
									LogUtils.d(TAG, "zplayToken:" + zplayToken
											+ ",uid:" + uid);
									isChannelLogin = false;

									SPValueHandler
											.setLoginUserID(activity, uid);
									SPValueHandler.setVisitorLoginUserID(
											activity, uid);
									SPValueHandler.setLoginType(activity,
											"visitor");
									logListener
											.loginResult(true,
													new ZplayLoginBean(
															zplayToken, uid),
													ResultCode.LOGIN_SUCCESS,
													"登陆成功");
								} else {
									LogUtils.d(TAG, "登录失败");
									logListener.loginResult(false, null,
											ResultCode.LOGIN_FAIL, "登录失败");
								}
							}

						}
					}).execute(ParamsMapBuilder.buildParams(
					APIList.VISITOR_LOGIN,
					new String[] { ConstantsHolder.SDK_KEY,
							ConstantsHolder.UINFO_DEVICE_ID,
							ConstantsHolder.ZPLAY_KEY_IMEI,
							ConstantsHolder.ZPLAY_ANDROID_ID,
							ConstantsHolder.KEY_ZPLAY_USER_CHANNEL_ID,
							ConstantsHolder.KEY_ZPLAY_USER_GAME_VERSION },
					new String[] { ZplayAgentOnline.getZplaySdkKey(), deviceId,
							PhoneInfoGetter.getIMEI(activity),
							PhoneInfoGetter.getAndroidID(activity),
							ConfigValueHandler.getChannel(activity),
							PhoneInfoGetter.getAppVersionName(activity) }));
		} else {
			logListener.loginResult(false, null,
					com.zplay.android.sdk.online.bean.ResultCode.LOGIN_FAIL,
					"获取token失败");
		}
	}

}

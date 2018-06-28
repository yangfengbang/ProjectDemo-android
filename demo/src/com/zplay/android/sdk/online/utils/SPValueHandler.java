package com.zplay.android.sdk.online.utils;

import android.app.Activity;
import android.content.Context;

/**
 * get value from ini files
 * 
 * no mood, bad atmosphere ,no passion to work,zhong me ban!?
 * 
 * @author glzlaohuai
 * @date 2013-4-15
 */
public class SPValueHandler {
	private final static String SP_FILE_NAME = "com.zplay.android.sdk.user";

	private final static String KEY_LAST_MONEY = "last_pay_money";
	private final static String KEY_LAST_ORDERID = "last_pay_orderid";
	private final static String KEY_LAST_PAY_TYPE = "last_pay_type";
	private final static String KEY_LAST_PAY_UID = "last_pay_uid";
	private final static String KEY_IS_ORDER_REPORT_NEEDED = "is_order_report_needed";

	// 记录计时器记录的时间
	private final static String KEY_STICK_TIMER_RECORD = "stick_timer_record";
	// 记录计时器记录的uid
	private final static String KEY_STICK_UID_RECORD = "stick_uid_record";

	// session启动时间
	private final static String KEY_SESSION_START_TIME = "session_start_time";
	// session结束时间
	private final static String KEY_SESSION_END_TIME = "session_end_time";

	// 记录当前登录的用户
	private final static String KEY_VISITOR_UID = "Visitor_usr";
	// 记录当前登录的用户
	private final static String KEY_ONLINE_UID = "online_usr";
	// 记录当前用户的登录类型
	private final static String KEY_LOGIN_TYPE = "login_type";
	// 记录当前用户的登录类型
	private final static String KEY_DEVICEID = "deviceID";
	// 记录当前登陆用户的token
	private final static String KEY_ONLINE_TOKEN = "online_token";
	
	private final static String KEY_ONLINE_NICKNAME = "online_nickName";
	// 支持的支付方式以及支付方式顺序
	private final static String KEY_PAY_CONFIG = "pay_config";

	// 保存上一次获取验证码操作的时间(获取验证码有四种操作：修改密码、绑定邮箱、绑定手机、手机解绑、邮箱解绑)
	private final static String KEY_LAST_VERIFY_CODE_TIME = "last_verify_code_time";

	// 根据获取验证码的类型来记录的时间
	private final static String KEY_LAST_VERIFY_CODE_TIME2 = "last_verify_code_time2";

	private static final String KEY_GAME_LAST_ORDERID = "last_pay_game_orderid";
	//微信orderId
		private final static String WECHAT_ORDERID = "WECHAT_ORDERID";

	public static void setLastMoney(Context context, String money) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_LAST_MONEY,
				money);
	}

	public static void setLastOrderID(Context context, String orderID) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_LAST_ORDERID,
				orderID);
	}
	
	public static void setLastGameOrderID(Context context, String orderID) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_GAME_LAST_ORDERID,
				orderID);
	}

	public static String getLastMoney(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_LAST_MONEY);
	}

	public static String getLastOrderID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_LAST_ORDERID);
	}
	
	public static String getLastGameOrderID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_GAME_LAST_ORDERID);
	}

	public static void setLastPayType(Context context, int payType) {
		ParamsPutter.putIntParams(context, SP_FILE_NAME, KEY_LAST_PAY_TYPE,
				payType);
	}

	public static void setLastPayUID(Context context, String uid) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_LAST_PAY_UID,
				uid);
	}
	
	/**
	 * 设置当前登录游客id
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setVisitorLoginUserID(Context context, String uid) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_VISITOR_UID +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context), uid);
	} 
	
	/**
	 * 获取当前登录的游客id
	 * 
	 * @param context
	 * @return
	 */
	public static String getVisitorLoginUserID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_VISITOR_UID +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context), "");
	}

	public static String getLastPayUID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_LAST_PAY_UID);
	}

	public static int getLastPayType(Context context) {
		return ParamsGetter.getIntParam(context, SP_FILE_NAME,
				KEY_LAST_PAY_TYPE);
	}

	public static boolean isOrderReportNeeded(Context context) {
		return ParamsGetter.getBooleanParam(context, SP_FILE_NAME,
				KEY_IS_ORDER_REPORT_NEEDED);
	}

	public static void setIsOrderReportNeeded(Context context, boolean needOrNot) {
		ParamsPutter.putBooleanParam(context, SP_FILE_NAME,
				KEY_IS_ORDER_REPORT_NEEDED, needOrNot);
	}

	/**
	 * 设置当前登录用户的id
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setLoginUserID(Context context, String uid) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_ONLINE_UID +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context), uid);
	}
	
	/**
	 * 设置当前登录类型
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setLoginType(Context context, String type) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_LOGIN_TYPE +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context), type);
	}
	
	/**
	 * 设置当前设备id
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setDeviceID(Context context, String type) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_DEVICEID, type);
	}

	/**
	 * 设置当前登录用户的nickName
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setLoginUserNickName(Context context, String nickName) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_ONLINE_NICKNAME,
				nickName);
	}

	/**
	 * 获取当前登录用户的uid
	 * 
	 * @param context
	 * @return
	 */
	public static String getLoginUserID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_ONLINE_UID +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context), "");
	}

	/**
	 * 获取当前用户登录的类型
	 * 
	 * @param context
	 * @return
	 */
	public static String getLoginType(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_LOGIN_TYPE +ConfigValueHandler.getGameID(context) + ConfigValueHandler.getChannel(context));
	}
	
	/**
	 * 获取当前设备ID
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_DEVICEID);
	}
	
	/**
	 * 获取当前登录用户的nickName
	 * 
	 * @param context
	 * @return
	 */
	public static String getLoginUserNickName(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_ONLINE_NICKNAME);
	}

	/**
	 * 用户是否登录
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isUserOnline(Context context) {
		String uid = getLoginUserID(context);
		if (uid.equals("") || uid.equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取计时器记录的时间
	 * 
	 * @param context
	 * @return
	 */
	public static long getStickTimerRecordTime(Context context) {
		return ParamsGetter.getLongParam(context, SP_FILE_NAME,
				KEY_STICK_TIMER_RECORD);
	}

	/**
	 * 记录上次上报“启动时间”的时间
	 * 
	 * @param context
	 * @param time
	 */
	public static void setStickTimerRecordTime(Context context, long time) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME,
				KEY_STICK_TIMER_RECORD, time);
	}

	/**
	 * 记录产生运行时间的用户uid
	 * 
	 * @param context
	 * @param uid
	 */
	public static void setStickTimerRecordUID(Context context, String uid) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME,
				KEY_STICK_UID_RECORD, uid);
	}

	/**
	 * 获取产生运行时间的用户id
	 * <p>
	 * fuck, the logic is so complex
	 * 
	 * @param context
	 * @return
	 */
	public static String getStickTimerRecordUID(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_STICK_UID_RECORD);
	}

	/**
	 * session启动时间
	 * 
	 * @param context
	 */
	public static void setSessionStartTime(Context context) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME,
				KEY_SESSION_START_TIME, System.currentTimeMillis());
	}

	public static void clearSessionStartTime(Context context) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME,
				KEY_SESSION_START_TIME, -1l);
	}

	public static void clearSessionEndTime(Context context) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME, KEY_SESSION_END_TIME,
				-1l);
	}

	public static long getSessionStartTime(Context context) {
		return ParamsGetter.getLongParam(context, SP_FILE_NAME,
				KEY_SESSION_START_TIME);
	}

	public static long getSessionEndTime(Context context) {
		return ParamsGetter.getLongParam(context, SP_FILE_NAME,
				KEY_SESSION_END_TIME);
	}

	/**
	 * session结束时间
	 * 
	 * @param context
	 */
	public static void setSessionEndTime(Context context) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME, KEY_SESSION_END_TIME,
				System.currentTimeMillis());
	}

	/**
	 * 设置支付方式配置
	 * 
	 * @param context
	 * @param orderID
	 */
	public static void setPayConfig(Context context, String payConfig) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_PAY_CONFIG,
				payConfig);
	}

	/**
	 * 获取支付方式配置
	 * 
	 * @param context
	 * @return
	 */
	public static String getpayConfig(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_PAY_CONFIG);
	}

	/**
	 * 获取上一次获取验证码的时间
	 * 
	 * @param context
	 * @param type
	 * @return
	 */
	public static long getLastVerifyCodeTime(Context context, int type) {
		return ParamsGetter.getLongParam(context, SP_FILE_NAME,
				KEY_LAST_VERIFY_CODE_TIME + type);
	}

	/**
	 * 设置上一次获取验证码的时间
	 * 
	 * @param context
	 * @param type
	 * @param time
	 */
	public static void setLastVerifyCodeTime(Context context, int type,
			long time) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME,
				KEY_LAST_VERIFY_CODE_TIME + type, time);
	}

	/**
	 * 设置上一次获取验证码的时间
	 * 
	 * @param context
	 * @param type
	 * @param time
	 */
	public static void setLastVerifyCodeTime2(Context context, int type,
			long time) {
		ParamsPutter.putLongParam(context, SP_FILE_NAME,
				KEY_LAST_VERIFY_CODE_TIME2 + type, time);
	}

	/**
	 * 获取上一次获取验证码的时间
	 * 
	 * @param context
	 * @param type
	 * @return
	 */
	public static long getLastVerifyCodeTime2(Context context, int type) {
		return ParamsGetter.getLongParam(context, SP_FILE_NAME,
				KEY_LAST_VERIFY_CODE_TIME2 + type);
	}

	/**
	 * 设置当前登陆用户token
	 * @param activity
	 * @param token
	 */
	public static void setLoginUserToken(Context context, String token) {
		ParamsPutter.putStringParam(context, SP_FILE_NAME, KEY_ONLINE_TOKEN, token);
	}
	
	/**
	 * 获取当前登陆用户token
	 * @param context
	 * @return
	 */
	public static String getLoginUserToken(Context context) {
		return ParamsGetter.getStringParam(context, SP_FILE_NAME,
				KEY_ONLINE_TOKEN);
	}
}

package com.zplay.android.sdk.online.constants;

public class APIList {
	// 接口
	public static final String POST_URL = "http://g.passport.zplay.cn";
	// 登陆接口,废弃不再使用
	//public static final String LOGIN_URL = POST_URL + "/singleUser/login";
	// 请求订单号接口
	public static final String REQUEST_REQUEST_ORDERID = "http://g.account.zplay.cn/juhezhifu/getOrderId";
	// 用户反馈接口
	public static final String USER_FEEDBACK = "http://g.passport.zplay.cn/feedback/add";
	// 游客登录接口
	public final static String VISITOR_LOGIN = POST_URL + "/visitor/loginNew";
	// 游客账号绑定账号
	public static final String BECOME_BIND_USER = POST_URL
			+ "/visitor/becomeBindUserNew";
}

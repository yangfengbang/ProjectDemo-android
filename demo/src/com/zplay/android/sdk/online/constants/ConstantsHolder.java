package com.zplay.android.sdk.online.constants;

public class ConstantsHolder {

	public static boolean isDebug = true;

	public final static int SDK_VERSION = 11;

	public final static String FILE_CONFIG = "ZplayConfig.xml";
	public final static String KEY_CHANNEL = "ChannelID";
	// 游戏ID
	public final static String KEY_GAMEID = "GameID";
	public final static String KEY_APPID = "AppID";
	public final static String KEY_PAYID = "PAYID";
	
	public final static String KEY_GAME_PRIVATE_KEY = "GAME_PRIVATE_KEY";
	public final static String KEY_GAME_PUBLIC_KEY = "GAME_PUBLIC_KEY";
	public final static String KEY_PAY_PRIVATE_KEY = "PAY_PRIVATE_KEY";
	public final static String KEY_PAY_PUBLIC_KEY = "PAY_PUBLIC_KEY";
	// 是否是debug模式
	public final static String KEY_DEBUG_MODE = "DebugMode";
	// 屏幕方向
	public final static String KEY_ORIENTATION = "Orientation";
	// 游戏key
	public final static String KEY_GAMEKEY = "GameKey";
	// 游戏Gid
	public static final String KEY_GID = "Gid";
	// 商户ID
	public final static String KEY_MERCHANTID = "GerchantId";
	// 当乐服务器序列号
	public final static String KEY_SERVERSEQNUM = "ServerSeqNum";
	// sdkkey
	public final static String KEY_SDKKEY = "Zlay_SDK_KEY";
	// GameName
	public final static String KEY_GAMENAME = "Zlay_GAMENAME";

	public final static String NODE_CHANNEL_GAMEID = "infos";
	public final static String KEY_USE_MM_CHANNEL = "USE_MM_CHANNEL";
	public final static String BUOY_SECRET = "BUOY_SECRET";

	/** sdk key */
	public final static String SDK_KEY = "zplay_key";
	/** 第三平台类型 */
	public final static String KEY_PLATFORM_TYPE = "platform";
	/** 第三方授权成功后获取到的code或token，都传给code参数 */
	public final static String KEY_TOKEN = "code";
	public final static String KEY_UID = "uid";
	/** token */
	public final static String KEY_ZPLAY_TOKEN = "token";
	/** 获取订单号 */
	public final static String KEY_ZPLAY_GAMEORDERID = "gameOrderId";
	/** 商品数量 */
	public final static String KEY_ZPLAY_QUANTITY = "quantity";
	/** 商品总金额 */
	public final static String KEY_ZPLAY_TOTALFEE = "totalfee";
	/** 支付方式 */
	public final static String KEY_ZPLAY_FEEWAY = "feeway";
	/** 手机唯一标示 */
	public final static String KEY_ZPLAY_IMEI = "imei";
	/** ios使用，android为空 */
	public final static String KEY_ZPLAY_IDFA = "idfa";
	/** 手机系统类型 */
	public final static String KEY_ZPLAY_PLATFORM = "platform";
	/** 用户uid */
	public final static String KEY_ZPLAY_GAMEUSERID = "gameUserId";
	/** 支付预留信息 */
	public static final String KEY_ZPLAY_CPDEFINEINFO = "cpDefineInfo";
	/** 商品名称 */
	public static final String KEY_ZPLAY_PRODUCTNAME = "name";
	/** 加密串 */
	public static final String KEY_ZPLAY_SING = "sign";
	/** 加密串密钥 */
	public static final String ZPLAY_SING_KEY = "zplay_single_login_signkey_201704";
	/** 密钥 */
	public static final String KEY_ZPLAY_SECRET_KEY = "frtu897645aeg";
	/** 用户反馈标题 */
	public static final String KEY_ZPLAY_USER_FEEDBACK_TITLE = "title";
	/** 用户反馈内容 */
	public static final String KEY_ZPLAY_USER_FEEDBACK_CONTENT = "content";
	/** 版本号 */
	public static final String KEY_ZPLAY_GAME_VER = "ver";
	/** 渠道名称 */
	public static final String KEY_ZPLAY_SITE_NAME = "sitename";

	/** imei */
	public static final String KEY_ZPLAY_USER_IMEI = "user_imei";
	/** androidID */
	public static final String KEY_ZPLAY_USER_ANDROIDID = "android_id";
	/** channelID */
	public static final String KEY_ZPLAY_USER_CHANNEL_ID = "game_channel_id";
	/** apk version */
	public static final String KEY_ZPLAY_USER_GAME_VERSION = "game_version";
	public static String ZPLAY_KEY_IMEI = "user_imei";
	public static String ZPLAY_ANDROID_ID = "android_id";
	/** 设备标识 */
	public static String UINFO_DEVICE_ID = "device_key";
	/** log开关 */
	public static final String KEY_ZPLAY_LOG_SWITCH = "logSwitch";
	/** login开关 */
	public static final String KEY_ZPLAY_LOGIN_ONOFF = "LoginONOFF";
	/** visitor_uid */
	public static final String KEY_ZPLAY_VISITORUID = "zplay_uid";
}

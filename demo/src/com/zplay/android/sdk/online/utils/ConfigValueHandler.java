package com.zplay.android.sdk.online.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;

import com.zplay.android.sdk.online.constants.ConstantsHolder;

import android.R.integer;
import android.content.Context;

/**
 * config是指assets目录下的文件{@linkplain ConstantsHolder#FILE_CONFIG}
 * 
 * @author glzlaohuai
 * @date 2013-4-18
 */
public class ConfigValueHandler {

	// 保存着channel以及gameID文件的解析结果的缓存
	private static SoftReference<Map<String, String>> XMLCache;
	private final static String TAG = "ConfigValueHandler";

	/**
	 * 构造{@linkplain #XMLCache}
	 * 
	 * @param context
	 */
	private static void buildCache(Context context) {
		if (XMLCache == null || XMLCache.get() == null) {
			Map<String, Object> data = null;
			try {
				data = XMLParser.paraserXML(context.getAssets().open(
						ConstantsHolder.FILE_CONFIG));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 保存着channel以及gameID的map结构
			@SuppressWarnings("unchecked")
			Map<String, String> infosMap = (Map<String, String>) data
					.get(ConstantsHolder.NODE_CHANNEL_GAMEID);
			XMLCache = new SoftReference<Map<String, String>>(infosMap);
		}
	}

	/**
	 * 获取渠道（2013-4-19 11:41:55修改）
	 * 
	 * @param context
	 * @return
	 */
	public static String getChannel(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_CHANNEL).trim();
	}

	/**
	 * 获取gameID(2013-4-19 11:53:36修改)
	 * 开始设计是从manifest中的meta处获取，但是因为设计到孟宪国的批量打包工具，所以此处按照以前的设计来获取
	 * 
	 * @param context
	 * @return
	 */
	public static String getGameID(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GAMEID).trim();
	}

	public static String getAppID(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_APPID).trim();
	}

	public static String getPayID(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_PAYID).trim();
	}

	// public static String getBuoySecret(Context context) {
	// buildCache(context);
	// return XMLCache.get().get(ConstantsHolder.BUOY_SECRET).trim();
	// }
	//
	// public static String getLoginPublicKey(Context context) {
	// buildCache(context);
	// return XMLCache.get().get(ConstantsHolder.KEY_LOGIN_RSA_PUBLIC).trim();
	// }
	public static String getGamePrivateKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GAME_PRIVATE_KEY).trim();
	}

	public static String getGamePublicKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GAME_PUBLIC_KEY).trim();
	}

	public static String getPayPrivateKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_PAY_PRIVATE_KEY).trim();
	}

	public static String getPayPublicKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_PAY_PUBLIC_KEY).trim();
	}

	/**
	 * 是否使用mm配置的渠道
	 * 
	 * @param context
	 * @return
	 */
	public static boolean useMMChannel(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_USE_MM_CHANNEL).trim()
				.equals("1") ? true : false;
	}

	/**
	 * 是否是debug模式 0 -- 非debug模式 1 -- debug模式
	 * 
	 * @param context
	 * @return true debug模式 false 非debug模式
	 */
	public static boolean isDebugMode(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_DEBUG_MODE).trim()
				.equals("0") ? true : false;
	}

	/**
	 * 屏幕方向 1表示竖屏，2表示横屏
	 * 
	 * @param context
	 * @return 0 true 非0 false 0-竖屏 1-横屏
	 */
	public static int orientation(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_ORIENTATION).trim()
				.equals("0") ? 1 : 2;
	}

	/**
	 * 获取游戏Key
	 * 
	 * @param context
	 * @return
	 */
	public static String getGameKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GAMEKEY).trim();
	}

	public static String getGid(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GID).trim();
	}

	/**
	 * 获取商户ID
	 * 
	 * @param context
	 * @return
	 */
	public static String getMerchantId(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_MERCHANTID).trim();
	}

	/**
	 * 获取当乐服务器序列号
	 * 
	 * @param context
	 * @return
	 */
	public static String getServerSeqNum(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_SERVERSEQNUM).trim();
	}

	/**
	 * 获取zplaySDKkey
	 * 
	 * @param context
	 * @return
	 */
	public static String getSDKKey(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_SDKKEY).trim();
	}

	/**
	 * 获取游戏名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getGameName(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_GAMENAME).trim();
	}

	/**
	 * Log开关, 0表示开,1表示关
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getLogSwitch(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_ZPLAY_LOG_SWITCH).trim()
				.equals("0") ? true : false;
	}

	/**
	 * Login开关, 0表示开,1表示关
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getLoginONOFF(Context context) {
		buildCache(context);
		return XMLCache.get().get(ConstantsHolder.KEY_ZPLAY_LOGIN_ONOFF).trim()
				.equals("0") ? true : false;
	}
	// public static String getOtherApiAPPID(Context context){
	// buildCache(context);
	// return XMLCache.get().get(ConstantsHolder.KEY_OTHER_APPID).trim();
	// }
	//
	// public static int getBannerSize(Context context){
	// buildCache(context);
	// return
	// Integer.valueOf(XMLCache.get().get(ConstantsHolder.KEY_OTHER_BANNER_SIZE).trim());
	// }
	//
	// public static int getInstertitialSize(Context context) {
	// buildCache(context);
	// return
	// Integer.valueOf(XMLCache.get().get(ConstantsHolder.KEY_OTHER_INSTERTITIAL_SIZE).trim());
	// }

}

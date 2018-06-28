package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 从配置文件中获取数据
 * 
 * @author laohuai
 * 
 */
 class ParamsGetter {

	/**
	 * 获取int类型数据，如果没有的话返回为-1
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public final static int getIntParam(Context context, String fileName,
			String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getInt(key, -1);
	}

	/**
	 * 获取string类型数据,如果没有的话返回为null
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public final static String getStringParam(Context context, String fileName,
			String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getString(key, "null");
	}
	
	/**
	 * 获取string类型数据,如果没有的话返回为dif
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public final static String getStringParam(Context context, String fileName,
			String key,String dif) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getString(key, dif);
	}

	/**
	 * 返回long类型数据，如果没有的话返回为-1
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public final static long getLongParam(Context context, String fileName,
			String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getLong(key, -1);
	}

	/**
	 * 获取boolean类型的值
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public final static boolean getBooleanParam(Context context,
			String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getBoolean(key, true);
	}

}

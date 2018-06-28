package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 将数据写入配置文件中
 * 
 * @author A05
 * 
 */

 class ParamsPutter {

	/**
	 * 写入int数据
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public final static void putIntParams(Context context, String fileName,
			String key, int value) {
		SharedPreferences preference = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 写入string数据
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public final static void putStringParam(Context context, String fileName,
			String key, String value) {
		SharedPreferences preference = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 写入long类型数据
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public final static void putLongParam(Context context, String fileName,
			String key, Long value) {
		SharedPreferences preference = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 向配置文件中写入boolean类型数据
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public final static void putBooleanParam(Context context, String fileName,
			String key, boolean value) {
		SharedPreferences preference = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}

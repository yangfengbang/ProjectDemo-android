package com.zplay.android.sdk.online.utils;

import android.util.Log;

/**
 * Log工具
 * 
 * @author
 * 
 */

public class LogUtils {

	public static boolean isShowLog = true;

	public static void v(String tag, String msg) {
		if (isShowLog) {
			Log.v(tag, "--" + msg);

		}
	}

	public static void i(String tag, String msg) {
		if (isShowLog) {
			Log.i(tag, "--" + msg);

		}
	}

	public static void d(String tag, String msg) {
		if (isShowLog) {
			Log.d(tag, "--" + msg);
		}
	}
}

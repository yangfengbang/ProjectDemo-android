package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 获取资源
 * 
 * @author glzlaohuai
 * @date 2013-8-13
 */
public class ResourceGetter {

	public static int getColor(Context context, int resID) {
		return context.getResources().getColor(resID);
	}

	public static Drawable getDrawable(Context context, int resID) {
		return context.getResources().getDrawable(resID);
	}

	public static String getString(Context context, int resID) {
		return context.getResources().getString(resID);
	}

	public static int getIDByName(Context context, String idName, String type) {
		return context.getResources().getIdentifier(idName, type,
				context.getPackageName());
	}

}

package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class ZplayWindowSizeUtils {
	
	public static int px2dip(Context context, int px){
		float scale = context.getResources().getDisplayMetrics().density;
		return ((int)(px / scale + 0.5f));
	}

	public static int dip2px(Context context, int dp){
		float scale = context.getResources().getDisplayMetrics().density;
		return ((int)(dp * scale +0.5f));
	}
	
	public static int getDeviceType(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		double inch = Math.sqrt(Math.pow(dm.widthPixels, 2)+Math.pow(dm.heightPixels, 2))/(160*dm.density);
		if (inch >= 8.0d) {
			return 1;
		}
		return 0;
	}
}

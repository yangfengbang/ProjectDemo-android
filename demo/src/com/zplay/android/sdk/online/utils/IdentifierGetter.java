package com.zplay.android.sdk.online.utils;

import android.content.Context;

public class IdentifierGetter {

	/**
	 * 获取资源在r文件中的标示
	 * 
	 * @param context
	 * @param resName
	 * @param resType
	 * @return
	 */
	public static int getIndentifier(Context context, String resName,
			String resType) {
		return context.getResources().getIdentifier(resName, resType,
				context.getPackageName());
	}

	public static int getLayoutIndentifier(Context context, String layoutName) {
		return getIndentifier(context, layoutName, "layout");
	}

	public static int getIDIndentifier(Context context, String idName) {
		return getIndentifier(context, idName, "id");
	}

	public static int getStyleIdentifier(Context context, String styleName) {
		return getIndentifier(context, styleName, "style");
	}

	public static int getDrawableIdentifier(Context context, String drawableName) {
		return getIndentifier(context, drawableName, "drawable");
	}
	
	public static int getStringIdentifier(Context context, String drawableName) {
		return getIndentifier(context, drawableName, "string");
	}

}

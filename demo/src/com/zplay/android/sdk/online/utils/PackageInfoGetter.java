package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

/**
 * 获取已经安装的程序的信息【图标、版本、名称】
 * 
 * @author glzlaohuai
 * @date 2013-4-8
 */
public class PackageInfoGetter {

	/**
	 * 获取app的图标
	 * 
	 * @param packname
	 * @return
	 */
	public static Drawable getAppIcon(PackageManager pm, String pkg) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(pkg, 0);
			return info.loadIcon(pm);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getAppIconID(Context context) {
		PackageManager pm = context.getPackageManager();
		String packageName = context.getPackageName();

		try {
			ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
			return info.icon;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 获取程序的版本verName
	 * 
	 * @param pm
	 * @param pkg
	 * @return
	 */
	public static String getAppVersionName(PackageManager pm, String pkg) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(pkg, 0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取程序的版本verName
	 * 
	 * @param pm
	 * @param pkg
	 * @return
	 */
	public static int getAppVersionCode(PackageManager pm, String pkg) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(pkg, 0);
			return packinfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取程序名称
	 * 
	 * @param pkg
	 * @return
	 */
	public static String getAppName(PackageManager pm, String pkg) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(pkg, 0);
			return info.loadLabel(pm).toString();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取启动配置为launcher的intent
	 * 
	 * @param context
	 * @param pkg
	 * @return 如果该app没有配置，那么返回null
	 */
	public static Intent getLaunchIntent(Context context, String pkg) {
		PackageManager pm = context.getPackageManager();
		Intent launchIntent = null;
		try {
			launchIntent = pm.getLaunchIntentForPackage(pkg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return launchIntent;
	}

}

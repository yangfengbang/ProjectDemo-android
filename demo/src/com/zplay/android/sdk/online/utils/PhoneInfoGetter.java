package com.zplay.android.sdk.online.utils;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.webkit.WebView;

/**
 * 获取手机信息
 * 
 * @author laohuai
 */
public class PhoneInfoGetter {

	private final static String TAG = "phoneInfo";

	/**
	 * 获取手机的生产厂商
	 * 
	 * @param context
	 * @return
	 */
	public static String getManufacture() {
		return android.os.Build.MANUFACTURER ;
	}

	/**
	 * 获取手机的型号
	 */
	public static String getModel(){
		return android.os.Build.MODEL;
	}
	
	
	
	/**
	 * 获取系统版本号
	 * 
	 * @return
	 */
	public static String getSysVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取android版本号int
	 * @return
	 */
	public static int getAndroidSDK(){
		return android.os.Build.VERSION.SDK_INT;
	}
	
	
	
	
	/**
	 * 获取imei信息
	 *  IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
    	IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
    	其组成为：
			1. 前6位数(TAC)是”型号核准号码”，一般代表机型
			2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
			3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
			4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		String imei = "";
		 if (isPrmissionExist(context, "android.permission.READ_PHONE_STATE")) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId();
		imei = imei == null ? "" : imei;
		LogUtils.v(TAG, "imei:" + imei);
		 }
		return imei;
	}
	
	public static boolean isPrmissionExist(Context context,String prissionName){
		PackageManager pm = context.getPackageManager();  
		    boolean permission = (PackageManager.PERMISSION_GRANTED ==   
		           pm.checkPermission(prissionName, context.getPackageName()));  
		 return permission;
	}
	/** 
	 * 返回当前程序版本名 
	 */  
	public static String getAppVersionName(Context context) {  
	    String versionName = "";  
	    try {  
	        // ---get the package info---  
	        PackageManager pm = context.getPackageManager();  
	        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);  
	        versionName = pi.versionName;  
	        if (versionName == null || versionName.length() <= 0) {  
	            return "";  
	        }  
	    } catch (Exception e) {  
	        //Log.e("VersionInfo", "Exception", e);  
	    }  
	    return versionName;  
	}  
	/**
	 * 获取imsi信息
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telephonyManager.getSubscriberId();
		if (imsi == null) {
			imsi = "";
		}
		return imsi;
	}

	/**
	 * 获取设备屏幕分辩密度
	 * 
	 * @param context
	 * @return
	 */
	
	public static float getDisplayDensity(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}

	/**
	 * 获取设备屏幕分辨率密度dpi
	 * @param context
	 * @return
	 */
	
	public static int getDisplayDensityDpi(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.densityDpi;
	}
	
	
	/**
	 * 获取手机屏幕分辨率
	 * @param context
	 * @return
	 */
	public static int[] getDisplayMetrics(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return new int[]{dm.widthPixels, dm.heightPixels};
	}

	/**
	 * 获取语言设置
	 * 
	 * @param context
	 * @return
	 */
	public static String getLanguage() {
		return Locale.getDefault().toString();
	}

	/**
	 * 获取国家设置
	 * 
	 * @param context
	 * @return
	 */
	public static String getCountry(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String zone = telephonyManager.getSimCountryIso();
		if (zone == null) {
			zone = "";
		}
		return zone;
	}

	/**
	 * 获取mac地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getMAC(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifiManager.getConnectionInfo();
		if (info != null) {
			String mac = info.getMacAddress();
			if (mac == null) {
				mac = "";
			}
			return mac;
		} else {
			return "";
		}
	}
	
	/**
	 * 获取手机PLMN
	 * @param context
	 * @return
	 */
	public static String getPLMN(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String plmn = manager.getSimOperator();
		if (plmn == null || plmn.equals("")) {
			return "";
		} else {
			if (plmn.length() > 6) {
				plmn = plmn.split(",")[0].replace(",", "");
			}
			return plmn.replace(",", "");
		}
	}
    /**
     * 获取手机webview的userAgent
     * @param web
     * @return
     */
	public static String getUserAgent(WebView web) {
		return web.getSettings().getUserAgentString();
	}

	/**
	 * 获取androidID
	 * @param context
	 * @return
	 */
	public static String getAndroidID(Context context){
		String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		return androidId == null ? "" : androidId;
	}
	
	public static int getOperator(Context context){
		String imsi = getIMSI(context);
		if (imsi != null && imsi.length() > 0) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
				return 1;
			}
			if (imsi.startsWith("46001")) {
				return 2;
			}
			if (imsi.startsWith("46003")) {
				return 3;
			}
		}
		return 0;
	}
	
	public static boolean isChinaMoblie(Context context){
		String imsi  = getIMSI(context);
		if (imsi != null && imsi.length() > 0 ) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
				return true;
			}
		}
		return false;
	}
	
	public static int getDevTypeByInch(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		double inch = Math.sqrt(Math.pow(dm.widthPixels, 2)
				+ Math.pow(dm.heightPixels, 2))
				/ (160 * dm.density);
		if (inch >= 8.0D) {
			return 1;
		}else {
			return 0;
		}
	}
}

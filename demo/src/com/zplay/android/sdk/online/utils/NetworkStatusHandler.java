package com.zplay.android.sdk.online.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkStatusHandler {

	// 判断是否有可用的网络连接
	public static boolean isNetWorkAvaliable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if (info == null) {
				return false;
			} else {
				for (NetworkInfo nf : info) {
					if (nf.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
				return false;
			}
		}
	}

	/**
	 * 当前是否为wifi网络连接
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isWIFIConnected(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		String typeName = null;

		if (networkInfo != null) {
			typeName = networkInfo.getTypeName();
		} else {
			typeName = "null";
		}
		return typeName.trim().equalsIgnoreCase("wifi");
	}

	/**
	 * 获取连接的网络名
	 * 
	 * @param context
	 * @return
	 */
	public static String getConnectedNetName(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		String typeName = "unknown";
		if (networkInfo != null) {
			typeName = networkInfo.getTypeName();
			if (typeName.trim().equalsIgnoreCase("wifi")) {
				typeName = "wifi";
			} else {
				typeName = getConnectionType(context);
			}
		}
		return typeName;
	}

	private static String getConnectionType(Context context) {
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		int type = manager.getNetworkType();
		System.err.println(type);
		switch (type) {
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_CDMA:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return "2g";
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return "3g";
		case TelephonyManager.NETWORK_TYPE_LTE:
			return "4g";
		default:
			return "unknown";
		}
	}

}

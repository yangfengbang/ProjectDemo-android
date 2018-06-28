package com.zplay.android.sdk.online.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ZplaySignUtils {

	private static final String TAG = "ZplaySignUtils";

	public static String getMogoSign(String[] keys, String[] values){
		List<String> list = new ArrayList<String>();
 		if (keys.length != values.length) {
			throw new IllegalArgumentException();
		}else {
			for (int i = 0; i < keys.length; i++) {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(keys[i]);
				buffer.append("=");
				buffer.append(values[i]);
				list.add(buffer.toString());
			}
		}
 		String[] arr = list.toArray(new String[]{});
 		StringBuffer sb = new StringBuffer("");
 		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length-1) {
				sb.append(arr[i]);
				sb.append("&");
			}else {
				sb.append(arr[i]);
			}
		}
 		sb.append("9a70A90F3FDk4182901DF4C14A536728");
 		MyLog.v(TAG, "mogo sign " + sb.toString());
 		MyLog.e(TAG, Encrypter.doMD5Encode(sb.toString()));
 		
 		return Encrypter.doMD5Encode(sb.toString());
	}
	
	public static String getZplaySign(String[] keys, String[] values){
		List<String> list = new ArrayList<String>();
 		if (keys.length != values.length) {
			throw new IllegalArgumentException();
		}else {
			for (int i = 0; i < keys.length-1; i++) {
				StringBuffer buffer = new StringBuffer("");
				buffer.append(keys[i]);
				buffer.append("=");
				buffer.append(values[i]);
				list.add(buffer.toString());
			}
		}
 		String[] arr = list.toArray(new String[]{});
 		Arrays.sort(arr);
 		StringBuffer sb = new StringBuffer("");
 		for (String string : arr) {
 			sb.append(string);
 			sb.append("&");
		}
 		sb.append("key=zy888");
 		MyLog.v(TAG, "zplay sign " + sb.toString());
 		return Encrypter.doMD5EncodeWithUppercase(sb.toString());
	}
	
}

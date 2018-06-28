package com.zplay.android.sdk.online.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class ManifestManager {

//	public static String getActivityMetaData(Context context, String key) {
//		ActivityInfo info=context.getPackageManager().getActivityInfo(new ComponentName(context, MetaDataActivity.class),  
//                PackageManager.GET_META_DATA);  
//		String msg=info.metaData.getString(key);
//		if(msg != null) {
//			
//			return msg;
//		}
//		return "";
//	}
	
	
	public static String getApplicationMetaData(Context context, String key) {
		try {
			ApplicationInfo appInfo = context.getPackageManager()  
			        .getApplicationInfo(context.getPackageName(),   
			PackageManager.GET_META_DATA);  
			String msg=appInfo.metaData.getString("myMsg");
			if(msg != null){
				
				return msg;  
			}
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
}

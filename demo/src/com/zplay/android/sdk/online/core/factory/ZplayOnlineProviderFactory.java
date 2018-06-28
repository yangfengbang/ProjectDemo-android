package com.zplay.android.sdk.online.core.factory;

import com.zplay.android.sdk.online.bean.Providers;
import com.zplay.android.sdk.online.listener.ZplayBaseOnlineAdapter;

public class ZplayOnlineProviderFactory {

	public static ZplayBaseOnlineAdapter createOnlineAdapter(Providers provider){
		try {
			Class<ZplayBaseOnlineAdapter> clazz = (Class<ZplayBaseOnlineAdapter>) Class.forName(provider.getRefName());
			ZplayBaseOnlineAdapter newInstance = clazz.newInstance();
			return newInstance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}

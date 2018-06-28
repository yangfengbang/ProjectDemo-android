package com.zplay.android.sdk.online;

import com.huawei.android.hms.agent.HMSAgent;

import android.app.Application;
import android.content.Context;

public class ZplayOnlinePayApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		executeZplayApplicationOnCreate(getApplicationContext());
	}

	public void executeZplayApplicationOnCreate(Context context) {
		HMSAgent.init(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		HMSAgent.destroy();
	}

}

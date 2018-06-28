package com.zplay.android.sdk.online.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zplay.android.sdk.online.bean.Providers;
import com.zplay.android.sdk.online.bean.ZplayBaseInitParams;
import com.zplay.android.sdk.online.bean.ZplayBaseOrder;
import com.zplay.android.sdk.online.bean.ZplayBaseUserInfo;
import com.zplay.android.sdk.online.core.factory.ZplayOnlineProviderFactory;
import com.zplay.android.sdk.online.listener.ZplayBaseOnlineAdapter;
import com.zplay.android.sdk.online.listener.ZplayOnlineExitListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLoginListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineLogoutListener;
import com.zplay.android.sdk.online.listener.ZplayOnlinePayResultListener;
import com.zplay.android.sdk.online.listener.ZplayOnlineSwitchListener;

public class ZplayOnlineHelper {

	private static ZplayOnlineHelper helper;
	private Providers provider;
	private ZplayBaseOnlineAdapter adapter;

	private ZplayOnlineHelper(Providers provider) {
		this.provider = provider;
	}

	public static synchronized ZplayOnlineHelper getHelper(Providers provider) {
		if (provider != null) {
			if (helper == null) {
				helper = new ZplayOnlineHelper(provider);
			}
			return helper;
		} else {
			throw new IllegalArgumentException("invalid provider name");
		}
	}

	public void initAdapter(Activity activity,
			ZplayOnlineLoginListener logListener,
			ZplayOnlinePayResultListener payListener,
			ZplayBaseInitParams params, ZplayOnlineExitListener exitListener,
			ZplayOnlineLogoutListener logoutListener,
			ZplayOnlineSwitchListener switchListener) {
		adapter = ZplayOnlineProviderFactory.createOnlineAdapter(provider);
		if (adapter != null) {
			adapter.initAdapter(activity, logListener, payListener, params,
					exitListener, logoutListener, switchListener);
		} else {
			throw new IllegalArgumentException(
					"reflect adapter has exception please check");
		}
	}

	public void login(Activity activity) {
		if (adapter != null) {
			adapter.login(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void visitorLogin(Activity activity) {
		if (adapter != null) {
			adapter.visitorLogin(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void logout(Activity activity) {
		if (adapter != null) {
			adapter.logout(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void doPay(Activity activity, final ZplayBaseOrder order) {
		if (adapter != null) {
			adapter.doPay(activity, order);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void releaseAdapter(Activity activity) {
		if (adapter != null) {
			adapter.releaseAdapter(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onActivityResult(Context context, int requestCode,
			int resultCode, Intent data) {
		if (adapter != null) {
			adapter.onActivityResult(context, requestCode, resultCode, data);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void userCenter(Activity activity) {
		if (adapter != null) {
			adapter.userCenter(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onQuit(Activity activity) {
		if (adapter != null) {
			adapter.onQuit(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onZplayResume(Activity activity) {
		if (adapter != null) {
			adapter.onZplayResume(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onZplayPause(Activity activity) {
		if (adapter != null) {
			adapter.onZplayPause(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onZplaySwitchUser(Activity activity) {
		if (adapter != null) {
			adapter.switchUser(activity);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onZplaySdkSubmitExtendData(ZplayBaseUserInfo userInfo) {
		if (adapter != null) {
			adapter.sdkSubmitExtendData(userInfo);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}

	public void onZplayUserFeedback(Activity activity, String title,
			String content) {
		if (adapter != null) {
			adapter.onZplayUserFeedback(activity, title, content);
		} else {
			throw new IllegalArgumentException(
					"please call initAdapter method first");
		}
	}
}

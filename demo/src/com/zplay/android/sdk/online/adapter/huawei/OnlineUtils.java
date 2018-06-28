package com.zplay.android.sdk.online.adapter.huawei;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.zplay.android.sdk.online.constants.APIList;
import com.zplay.android.sdk.online.constants.ConstantsHolder;
import com.zplay.android.sdk.online.utils.IdentifierGetter;
import com.zplay.android.sdk.online.utils.JsonResolveUtils;
import com.zplay.android.sdk.online.utils.LogUtils;
import com.zplay.android.sdk.online.utils.ParamsMapBuilder;
import com.zplay.android.sdk.online.utils.Task;
import com.zplay.android.sdk.online.utils.TaskHandler;

public class OnlineUtils {

	/**
	 * 用户反馈
	 * 
	 * @param activity
	 * @param zplayToken
	 */
	@SuppressWarnings("unchecked")
	public static void userFeedback(final Activity activity, String zplayToken,
			String title, String content, String TAG) {
		if (!TextUtils.isEmpty(zplayToken)) {
			new TaskHandler(activity, true,
					IdentifierGetter.getStringIdentifier(activity,
							"zplay_feedback"), new Task() {
						public void doTask(String data, String msg) {
							if (data == null) {
								Toast.makeText(
										activity,
										IdentifierGetter.getStringIdentifier(
												activity,
												"zplay_request_data_null"), 0)
										.show();
							} else {
								try {
									JSONObject jsonObject = JsonResolveUtils
											.buildJSON(data);
									int msgCode = jsonObject.getInt("errno");
									if (msgCode == 0) {
										Toast.makeText(
												activity,
												IdentifierGetter
														.getStringIdentifier(
																activity,
																"zplay_feedback_success"),
												0).show();
									} else {
										Toast.makeText(
												activity,
												IdentifierGetter
														.getStringIdentifier(
																activity,
																"zplay_data_erro"),
												0).show();
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}
					}).execute(ParamsMapBuilder.buildParams(
					APIList.USER_FEEDBACK,

					new String[] { ConstantsHolder.KEY_ZPLAY_TOKEN,
							ConstantsHolder.KEY_ZPLAY_USER_FEEDBACK_TITLE,
							ConstantsHolder.KEY_ZPLAY_USER_FEEDBACK_CONTENT },
					new String[] { zplayToken, title, content }));

		} else {
			LogUtils.d(TAG, "zplayToken为null");

		}
	}
}

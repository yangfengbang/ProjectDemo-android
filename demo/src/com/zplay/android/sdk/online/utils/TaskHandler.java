package com.zplay.android.sdk.online.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.zplay.android.sdk.online.utils.WebMethodHandler.ResultObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TaskHandler extends
		AsyncTask<Map<String, Object>, Integer, Map<String, Object>> {
	private Context context;
	private Task task;

	private Dialog loadingDialog;
	private boolean isLoadingDialogShow;
	private int loadingTips;

	/**
	 * @param context
	 * @param task
	 */
	public TaskHandler(Context context, Task task) {
		this.context = context;
		this.task = task;

		isLoadingDialogShow = false;
	}

	/**
	 * 如果需要展示loading对话框，亦即isLoadingDialogshow参数为true，那么必须使用activity
	 * 
	 * @param activity
	 * @param isLoadingDialogShow
	 * @param task
	 */
	public TaskHandler(Activity activity, boolean isLoadingDialogShow,
			int loadingTips, Task task) {
		this.context = activity;
		this.isLoadingDialogShow = isLoadingDialogShow;
		this.loadingTips = loadingTips;
		this.task = task;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// 显示对话框
		if (isLoadingDialogShow) {
			if (context instanceof Activity) {
				loadingDialog = new Dialog(context,
						IdentifierGetter.getStyleIdentifier(context,
								"zplayDialogWindow"));
				loadingDialog.setCancelable(false);
				LayoutInflater inflater = LayoutInflater.from(context);
				View dialogView = inflater.inflate(IdentifierGetter
						.getLayoutIndentifier(context, "zplay_loading"), null);
				TextView loadingView = (TextView) dialogView
						.findViewById(IdentifierGetter.getIDIndentifier(
								context, "zplay_loading_tipsView"));
//				if (loadingTips != null) {
//					loadingView.setText(loadingTips);
//				}
				loadingView.setText(loadingTips);
				loadingDialog.setContentView(dialogView);
				loadingDialog.show();
			}
		}
	}

	protected Map<String, Object> doInBackground(Map<String, Object>... params) {
		Map<String, Object> urlParamsMap = params[0];
		ResultObject resultObject = null;
		// 从map中获取接口地址以及参数
		String url = (String) urlParamsMap.get("url");
		String[] keys = (String[]) urlParamsMap.get("keys");
		String[] values = (String[]) urlParamsMap.get("values");
		
		resultObject = WebMethodHandler.accessWebByPost(context, url, keys,
				values);
		

		// 状态
		int status = resultObject.getStatus();
		InputStream stream = resultObject.getInputStream();
		String errorMsg = resultObject.getErrorMsg();

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		if (stream != null) {
			result.put("data", StreamParser.parseStream(stream));
		}
		result.put("msg", errorMsg);
		return result;
	}

	@Override
	protected void onPostExecute(Map<String, Object> result) {
		super.onPostExecute(result);

		// dismiss掉dialog，如果有dialog正在展示
		if (isLoadingDialogShow && loadingDialog != null
				&& loadingDialog.isShowing()) {
			loadingDialog.dismiss();
			loadingDialog = null;
		}

		String data = null;
		if (result.get("data") != null) {
			data = (String) result.get("data");
		}
		String errorMsg = null;
		if (result.get("msg") != null) {
			errorMsg = (String) result.get("msg");
		}
		if (task != null) {
			task.doTask(data, errorMsg);
		}
	}
}
package com.zplay.android.sdk.online.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.zplay.android.sdk.online.constants.APPConfig;

public class WebMethodHandler {
	private final static String TAG = "net";
	// request/response成功
	public final static int OK = 0;
	// 失败
	public final static int ERROR = 1;

	/**
	 * post方式访问网络
	 * 
	 * @param context
	 * @param url
	 * @param keys
	 * @param values
	 * @param charsetName
	 * @return
	 */
	public static ResultObject accessWebByPost(Context context, String url,
			String value) {
		// 因为post方式不能输出url，所以这里使用get形式的url进行输出
		try {
			LogUtils.v("url", URLDecoder.decode(
					url
							+ ParamsBuilder.buildGetParams(new String[] { "" },
									new String[] { value }),
					APPConfig.NetConfig.DEFAULT_CHAR_SET));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		int resultCode = ERROR;
		InputStream resultStream = null;
		String errorMsg = null;
		ResultObject result = null;

		// 没有可用网络连接
		if (!NetworkStatusHandler.isNetWorkAvaliable(context)) {
			resultCode = ERROR;
			errorMsg = "没有可用网络连接";
			result = new ResultObject(resultCode, errorMsg, resultStream);
			LogUtils.v(TAG, "没有可用网络连接");
			return result;
		}
		HttpResponse response = null;
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/json");
		try {
			post.setEntity(new StringEntity(value,
					APPConfig.NetConfig.DEFAULT_CHAR_SET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			response = HttpClientHolder.getInstance().execute(post);
			if (response.getStatusLine().getStatusCode() != 200) {
				resultCode = ERROR;
				errorMsg = " http返回码："
						+ response.getStatusLine().getStatusCode();
				result = new ResultObject(resultCode, errorMsg, resultStream);
				LogUtils.v(TAG, "response返回码："
						+ response.getStatusLine().getStatusCode());
				return result;
			}
			resultCode = OK;
			resultStream = response.getEntity().getContent();
			result = new ResultObject(resultCode, errorMsg, resultStream);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtils.v(TAG, "异常信息：" + ex.getMessage());
			return new ResultObject(ERROR, ex.getMessage(), null);
		} finally {
			LogUtils.v(TAG, "关闭所有超时的连接");
			post.abort();
			HttpClientHolder.getInstance().getConnectionManager()
					.closeExpiredConnections();
		}
	}

	/**
	 * post方式访问网络
	 * 
	 * @param context
	 * @param url
	 * @param keys
	 * @param values
	 * @param charsetName
	 * @return
	 */
	public static ResultObject accessWebByPost(Context context, String url,
			String[] keys, String[] values) {
		// 因为post方式不能输出url，所以这里使用get形式的url进行输出
		String tempUrl = url;
		tempUrl += ParamsBuilder.buildGetParams(keys, values);
		try {
			LogUtils.v("url", URLDecoder.decode(tempUrl,
					APPConfig.NetConfig.DEFAULT_CHAR_SET));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		int resultCode = ERROR;
		InputStream resultStream = null;
		String errorMsg = null;
		ResultObject result = null;

		// 没有可用网络连接
		if (!NetworkStatusHandler.isNetWorkAvaliable(context)) {
			resultCode = ERROR;
			errorMsg = "没有可用网络连接";
			result = new ResultObject(resultCode, errorMsg, resultStream);
			LogUtils.v(TAG, "没有可用网络连接");
			return result;
		}

		HttpResponse response = null;
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity((HttpEntity) new UrlEncodedFormEntity(ParamsBuilder
					.buildPostParams(keys, values),
					APPConfig.NetConfig.DEFAULT_CHAR_SET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			response = HttpClientHolder.getInstance().execute(post);
			// 返回码不是200
			if (response.getStatusLine().getStatusCode() != 200) {
				resultCode = ERROR;
				errorMsg = " http返回码："
						+ response.getStatusLine().getStatusCode();
				result = new ResultObject(resultCode, errorMsg, resultStream);
				LogUtils.v(TAG, "response返回码："
						+ response.getStatusLine().getStatusCode());
				return result;
			}
			// 提交成功&返回数据成功
			resultCode = OK;
			resultStream = response.getEntity().getContent();
			result = new ResultObject(resultCode, errorMsg, resultStream);
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtils.v(TAG, "异常信息：" + ex.getMessage());
			return new ResultObject(ERROR, ex.getMessage(), null);
		} finally {
			LogUtils.v(TAG, "关闭所有超时的连接");
			post.abort();
			HttpClientHolder.getInstance().getConnectionManager()
					.closeExpiredConnections();
		}
	}

	/**
	 * 保存着请求网络以后返回的请求：1、状态，2、成功以后返回的流
	 * 
	 * @author laohuai
	 * 
	 */
	public static class ResultObject {
		private int status;
		private InputStream inputStream;
		private String errorMsg;

		public ResultObject(int status, String errorMsg, InputStream inputStream) {
			this.status = status;
			this.inputStream = inputStream;
			this.errorMsg = errorMsg;

		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
	}

}

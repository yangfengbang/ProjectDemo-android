package com.zplay.android.sdk.online.utils;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * get the httpclient
 * 
 * @author laohuai
 * 
 */
class HttpClientHolder {
	private static DefaultHttpClient client;

	private static void buildInstance() {

		if (client == null) {
			client = new DefaultHttpClient();
			HttpParams params = new BasicHttpParams();
			ConnManagerParams.setMaxConnectionsPerRoute(params,
					new ConnPerRouteBean(10));
			ConnManagerParams.setMaxTotalConnections(params, 10);

			HttpConnectionParams.setConnectionTimeout(params,
					APPConfig.NetConfig.CONNECT_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params,
					APPConfig.NetConfig.READ_TIME_OUT);
			HttpConnectionParams.setTcpNoDelay(params, true);
			HttpConnectionParams.setSocketBufferSize(params, 8192);
			// disable stale check
			HttpConnectionParams.setStaleCheckingEnabled(params, false);

			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));

			ClientConnectionManager cm = new ThreadSafeClientConnManager(
					params, schemeRegistry);
			client = new DefaultHttpClient(cm, params);
		}

	}

	public static DefaultHttpClient getInstance() {
		buildInstance();
		return client;
	}

}

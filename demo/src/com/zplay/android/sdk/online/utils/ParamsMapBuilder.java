package com.zplay.android.sdk.online.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于构建AsyncTask在执行时候需要的参数
 * 
 * @author laohuai
 * 
 */
public class ParamsMapBuilder {
	public static Map<String, Object> buildParams(String url, String[] keys,
			String[] values) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("keys", keys);
		map.put("values", values);
		return map;
	}

}

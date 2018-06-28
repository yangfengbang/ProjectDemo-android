package com.zplay.android.sdk.online.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonResolveUtils {


	public static double getDoubleFromJson(JSONObject json, String key, double defaultVaule) {
		double result = defaultVaule;
		if (json != null) {
			try {
				if (json.has(key)) {
					result = json.getDouble(key);
				}
			} catch (JSONException e) {
				e.toString();
			}
		}
		return result;
	}

	public static boolean getBooleanFromJson(JSONObject json, String key) {
		boolean result = false;
		if (json != null) {
			try {
				if (json.has(key)) {
					result = json.getBoolean(key);
				}
			} catch (JSONException e) {
				e.toString();
			}
		}
		return result;
	}
	
	
	public static long getLongFromJson(JSONObject json, String key, long defaultValue) {
		long result = defaultValue;
		if (json != null) {
			try {
				if (json.has(key)) {
					result = json.getLong(key);
				}
			} catch (JSONException e) {
				e.toString();
			}
		}
		return result;
	}

	public static int getIntFromJson(JSONObject json, String key, int defaultValue) {
		int result = defaultValue;
		if (json != null) {
			try {
				if (json.has(key)) {
					result = json.getInt(key);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getStringFromJson(JSONObject json, String key, String defaultValue) {
		String result = defaultValue;
		if (json != null) {
			try {
				if (json.has(key)) {
					result = json.getString(key);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static JSONArray getJsonArrayFromJson(JSONObject json, String key) {
		JSONArray array = null;
		if (json != null) {
			try {
				if (json.has(key)) {
					array = json.getJSONArray(key);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	public static JSONObject getJsonObjectFromJson(JSONObject json, String key) {
		JSONObject obj = null;
		if (json != null) {
			try {
				if (json.has(key)) {
					obj = json.getJSONObject(key);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public static Object resolveJson2JaveBean(JSONObject json, Class<?> clazz) {
		Object obj = null;
		if (json != null) {
			try {
				obj = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String simpleName = field.getType().getName();
					if (simpleName.startsWith("[")) { // array
						
					} else if (simpleName.endsWith("List")){ // list
						Type type = field.getGenericType();
						if (type instanceof ParameterizedType) {
							Type[] rawType = ((ParameterizedType) type).getActualTypeArguments();
							if (rawType[0] != null && rawType[0] instanceof Class) {
								List<Object> list = null;
								JSONArray jsonArrayFromJson = JsonResolveUtils.getJsonArrayFromJson(json, field.getName());
								if (jsonArrayFromJson != null && jsonArrayFromJson.length() > 0) {
									list = new ArrayList<Object>();
									for (int i = 0; i < jsonArrayFromJson.length(); i++) {
										list.add(resolveJson2JaveBean(jsonArrayFromJson.getJSONObject(i), (Class<?>)rawType[0]));
									}
								}
								field.set(obj, list);
							}
						}
						
					} else {
						if (simpleName.endsWith("String")) {
							field.set(obj, JsonResolveUtils.getStringFromJson(json, field.getName(),""));
						} else if (simpleName.endsWith("int") || simpleName.endsWith("Integer")) {
							field.set(obj, JsonResolveUtils.getIntFromJson(json, field.getName(),0));
						} else if (simpleName.endsWith("long")) {
							field.set(obj, JsonResolveUtils.getLongFromJson(json, field.getName(),0L));
						} else if (simpleName.endsWith("double")) {
							field.set(obj, JsonResolveUtils.getDoubleFromJson(json, field.getName(),0.0d));
						}else if (simpleName.endsWith("boolean")) {
							field.set(obj, JsonResolveUtils.getBooleanFromJson(json, field.getName()));
						}else {
							field.set(obj, resolveJson2JaveBean(JsonResolveUtils.getJsonObjectFromJson(json, field.getName()), Class.forName(simpleName)));
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;

	}
	
	
	public static String crateJsomString(Map<String, String> datas) { 
		JSONObject json = new JSONObject();
			try {
				for (Entry<String, String> map : datas.entrySet()) {
				json.put(map.getKey(), map.getValue());
				}
			} catch (JSONException e) {
				LogUtils.d("JsonResolveUtils", "jso构建异常");
				e.printStackTrace();
		}
			
			return json.toString();
	}
	
	
	public static JSONObject buildJSON(String json) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.v("JsonResolveUtils", "\"" + json + "\"" + "构造为jsonObject时候出现异常");
			try {
				jsonObject = new JSONObject(
						"{data:{},msg:{code:-111,text:\"服务器异常，请稍候重试...\"}}");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return jsonObject;
	}

}

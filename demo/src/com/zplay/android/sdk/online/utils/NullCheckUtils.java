package com.zplay.android.sdk.online.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NullCheckUtils {

	public static final boolean isNotNull(String str){
		if (str != null && str.length() > 0) {
			return true;
		}
		return false;
	}
	
	public static final boolean isNotEmptyCollection(Collection<?> collection){
		if (collection != null && collection.size() > 0) {
			return true;
		}
		return false;
	}
	
	public static final boolean isNotEmptyMap(Map<?, ?> map){
		if (map != null && map.size() > 0) {
			return true;
		}
		return false;
	}
}

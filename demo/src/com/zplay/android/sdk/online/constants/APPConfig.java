package com.zplay.android.sdk.online.constants;

/**
 * 保存程序运行期间需要的配置信息
 * 
 * @author laohuai
 * 
 */
public class APPConfig {
	/**
	 * 网络交互时候的一些配置信息
	 * 
	 * @author laohuai
	 * 
	 */
	public final static class NetConfig {
		// 默认的编码方式
		public final static String DEFAULT_CHAR_SET = "utf-8";
		// 超时时间设置
		public final static int CONNECT_TIME_OUT = 50000;
		// 读取超时
		public final static int READ_TIME_OUT = 60000;
	}

}

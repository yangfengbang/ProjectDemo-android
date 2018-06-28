package com.zplay.android.sdk.online.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.impl.conn.Wire;



import com.zplay.android.sdk.online.constants.ConstantsHolder;

import android.os.Environment;
import android.util.Log;

public class MyLog {

	private static final String TAG = "MyLog";
	private static final String LOG_FILE_NAME = "/report_info.txt";

 



	public static void v(String tag, String msg) {
		if (ConstantsHolder.isDebug) {
			Log.v(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (ConstantsHolder.isDebug) {
			Log.e(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (ConstantsHolder.isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void writeLog(String log){
		if (ConstantsHolder.isDebug) {
			writeLogToSDCard(log);
		}
	}
	
	
	
	
	private static void writeLogToSDCard(String log) {
		FileOutputStream write = null;
		try {
			// 判断是否存在SD卡
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				// 获取SD卡的目录
				File file = Environment.getExternalStorageDirectory();
				write = new FileOutputStream(
						file.getCanonicalPath() + LOG_FILE_NAME, true);
				write.write(log.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (write != null) {
				try {
					write.close();
				} catch (Exception e1) {
				}
			}
		}
	}

	
	
	
	
	
}

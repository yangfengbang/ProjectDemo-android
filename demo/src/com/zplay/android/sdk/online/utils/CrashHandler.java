package com.zplay.android.sdk.online.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler INSTANCE;
    private Context mContext;
	private static final String CRASH_INFO = "/muti_crash_log.txt";
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Error : ", e);
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        ex.printStackTrace();
        // 保存错误报告文件
        saveCrashInfoToFile(ex);
        return true;
    }

    private void saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString()+"\n\n\n";
        printWriter.close();
        FileOutputStream trace = null;
        try {
        	File file = Environment.getExternalStorageDirectory();
        	trace = new FileOutputStream(
					file.getCanonicalPath() + CRASH_INFO, true);
        	trace.write(result.getBytes());
            trace.flush();
            trace.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
        		if (trace  != null) {
        			trace.close();
        		}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
    }


}
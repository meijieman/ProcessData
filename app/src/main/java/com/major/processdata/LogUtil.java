package com.major.processdata;

import android.util.Log;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/5/22 23:18
 */
public class LogUtil {

    private static final String TAG = "elegant";

    public static void i(String msg) {
        String str = "";
        StackTraceElement[] stackTrace = new Thread().getStackTrace();
        if (stackTrace.length > 3) {
            str = stackTrace[3].toString();
        }
        str += ": ";
        str += msg;
        Log.i(TAG, "" + str);
    }

    public static void w(String msg) {
        String str = "";
        StackTraceElement[] stackTrace = new Thread().getStackTrace();
        if (stackTrace.length > 3) {
            str = stackTrace[3].toString();
        }
        str += ": ";
        str += msg;
        Log.w(TAG, "" + str);
    }
}

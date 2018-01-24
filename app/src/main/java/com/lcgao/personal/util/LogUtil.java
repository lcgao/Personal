package com.lcgao.personal.util;

import android.util.Log;

/**
 * Created by lcgao on 2017/6/7.
 */

public class LogUtil {
    public static final String TAG = "personal_log";

    public static void d(String content) {
        Log.d(TAG, content);
    }
    public static void d(Object o){
        d(o + "");
    }

    public static void l(String content) {
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        String classLocation = new Exception().getStackTrace()[1].getClassName();
        Log.d(TAG, classLocation + "." + methodName + ": " + content);
    }

    public static void l(){
        l("");
    }
}

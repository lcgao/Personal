package com.lcgao.personal.util;

import android.content.Context;
import android.widget.Toast;

import com.lcgao.personal.MyApplication;

/**
 * Created by lcgao on 2017/6/7.
 */

public class ToastUtil {
    private static Context context = MyApplication.getInstance().getApplicationContext();

    public static void l(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void s(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void l(Object o) {
        ToastUtil.l(o + "");
    }

    public static void s(Object o){
        ToastUtil.s(o + "");
    }
}

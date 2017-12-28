package com.lcgao.android_personal;

import android.app.Application;

/**
 * Created by lcgao on 2017/12/27.
 */

public class MyApplication extends Application {
    private static MyApplication INSTANCE;
    public static MyApplication getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}

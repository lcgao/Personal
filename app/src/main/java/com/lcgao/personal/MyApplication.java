package com.lcgao.personal;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by lcgao on 2017/12/27.
 */

public class MyApplication extends Application {
    private static MyApplication INSTANCE;

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        initRouter(this);
    }

    public void initRouter(Application application) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }
}

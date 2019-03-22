package com.lcgao.music_module;

import android.app.Application;

import com.lcgao.music_module.music.data.model.DaoMaster;
import com.lcgao.music_module.music.data.model.DaoSession;
import com.lcgao.music_module.music.data.source.local.MusicsDbHelper;
import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        mDaoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, MusicsDbHelper.DATABASE_NAME)
                .getWritableDb())
                .newSession();
//        if(mDaoSession.getMusicDao().loadAll().size() == 0){
//            mDaoSession.getMusicDao().insert(new Music());
//        }
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }

    public static MyApplication getInstance(){
        return INSTANCE;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }
}

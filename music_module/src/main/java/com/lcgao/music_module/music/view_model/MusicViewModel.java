package com.lcgao.music_module.music.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import com.lcgao.music_module.util.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MusicViewModel extends ViewModel {
    private static final String TAG = "MusicViewModel: ";
    private static final int ONE_SECOND = 1000;
    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();
    private long mInitialTime;

    public MusicViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    public LiveData<Long> getElapsedTime(){
        return mElapsedTime;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtil.d(TAG + "onCleared()");
    }
}

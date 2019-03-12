package com.lcgao.music_module.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.lcgao.music_module.event.RxBus;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.PlayMusicInfo;
import com.lcgao.music_module.music.view.PlayMusicActivity;
import com.lcgao.music_module.util.LogUtil;

import java.util.List;

public class PlayMusicService extends Service {
    private static final String TAG = "PlayMusicService: ";

    public static final String EXTRA_PLAY_MUSIC_INFO = "play_music_info";

    private PlayMusicInfo mPlayMusicInfo;

    private List<Music> mMusics;

    private boolean mIsPause = false;

    public class MyBinder extends Binder {
        public PlayMusicService getService(){
            return PlayMusicService.this;
        }
    }

    public PlayMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG + " --> onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG + " --> onStartCommand()");
        if(intent == null){
            return super.onStartCommand(intent, flags, startId);
        }
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        MediaManager.playMusic(mPlayMusicInfo.getMusic().getPath(), new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.d(TAG + "MediaManager play completely.");
                mIsPause = true;
                mPlayMusicInfo.setPause(true);
                RxBus.getDefault().post(mPlayMusicInfo);
            }
        });
        RxBus.getDefault().post(mPlayMusicInfo);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG + " --> onBind()");
        if(intent == null){
            return new MyBinder();
        }
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        if(mPlayMusicInfo == null){
            return new MyBinder();
        }
        MediaManager.playMusic(mPlayMusicInfo.getMusic().getPath(), new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.d(TAG + "MediaManager play completion.");
            }
        });
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d(TAG + " --> onUnbind()");

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG + " --> onDestroy()");
        MediaManager.release();
    }

    public void playOrPause(){
        if(MediaManager.isPause()){
            MediaManager.resume();
            mIsPause = false;
            mPlayMusicInfo.setPause(false);
        }else {
            MediaManager.pause();
            mIsPause = true;
            mPlayMusicInfo.setPause(true);
        }
        RxBus.getDefault().post(mPlayMusicInfo);
    }


    public List<Music> getMusics() {
        return mMusics;
    }

    public void setMusics(List<Music> mMusics) {
        this.mMusics = mMusics;
    }

    public boolean isPause() {
        return mIsPause;
    }

    public void setIsPause(boolean mIsPause) {
        this.mIsPause = mIsPause;
        mPlayMusicInfo.setPause(mIsPause);
    }
}

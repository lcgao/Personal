package com.lcgao.music_module.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.lcgao.music_module.event.PlayMusicEvent;
import com.lcgao.music_module.event.RxBus;
import com.lcgao.music_module.event.UpdateProgressEvent;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.PlayMusicInfo;
import com.lcgao.music_module.music.view.PlayMusicActivity;
import com.lcgao.music_module.util.LogUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PlayMusicService extends Service {
    private static final String TAG = "PlayMusicService: ";

    public static final String EXTRA_PLAY_MUSIC_INFO = "play_music_info";

    private PlayMusicInfo mPlayMusicInfo;

    private List<Music> mMusics = new ArrayList<>();

    private int mCurrentPosition;

    private boolean mIsPause = false;

    private CompositeDisposable mCompositeDisposable;

    private Disposable mDisposableInterval;

    public class MyBinder extends Binder {
        public PlayMusicService getService() {
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

        initService(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG + " --> onBind()");
        initService(intent);
        return new MyBinder();
    }

    private void initService(Intent intent) {
        if (intent == null) {
            return;
        }
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        if (mPlayMusicInfo == null) {
            return;
        }
        Music music = mPlayMusicInfo.getPlayList().get(mPlayMusicInfo.getCurrentPosition());
        mCurrentPosition = mPlayMusicInfo.getCurrentPosition();
        if (mPlayMusicInfo.getPlayList() != null && !mPlayMusicInfo.getPlayList().isEmpty()) {
            mMusics.clear();
            mMusics.addAll(mPlayMusicInfo.getPlayList());
        }
        startPlayMusic(music);
    }

    /**
     * 开始播放音乐
     *
     * @param music 音乐对象
     */
    private void startPlayMusic(Music music) {
        MediaManager.playMusic(music.getPath(), new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.d(TAG + "MediaManager play completely.");
                mIsPause = true;
                mPlayMusicInfo.setPause(true);
                RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
                next();
            }
        });
        registerInterval(0, 1000);
        RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
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

    /**
     * 播放或暂停
     */
    public void playOrPause() {
        if (MediaManager.isPause()) {
            MediaManager.resume();
            mIsPause = false;
            mPlayMusicInfo.setPause(false);
            registerInterval(0, 1000);

        } else {
            MediaManager.pause();
            mIsPause = true;
            mPlayMusicInfo.setPause(true);
            cancelInterval();
        }
        RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
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

    /**
     * 播放跳转至指定位置
     *
     * @param position 时间位置
     */
    public void seekTo(long position) {
        MediaManager.seekTo(Integer.parseInt(position + ""));
    }

    /**
     * 下一曲
     */
    public void next() {
        if (++mCurrentPosition >= mMusics.size()) {
            mCurrentPosition = 0;
        }
        mPlayMusicInfo.setCurrentTime(0);
        mPlayMusicInfo.setPause(false);
        mPlayMusicInfo.setCurrentPosition(mCurrentPosition);
        startPlayMusic(mPlayMusicInfo.getPlayList().get(mCurrentPosition));
//        RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
//        MediaManager.playMusic(mMusics.get(mCurrentPosition).getPath(), new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//
//            }
//        });
    }

    /**
     * 上一曲
     */
    public void pre() {
        if (--mCurrentPosition < 0) {
            mCurrentPosition = mMusics.size() - 1;
        }
        mPlayMusicInfo.setCurrentTime(0);
        mPlayMusicInfo.setPause(false);
        mPlayMusicInfo.setCurrentPosition(mCurrentPosition);
        startPlayMusic(mPlayMusicInfo.getPlayList().get(mCurrentPosition));

//        RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
//        MediaManager.playMusic(mMusics.get(mCurrentPosition).getPath(), new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//
//            }
//        });
    }


    private void registerInterval(long delay, long period) {
        mDisposableInterval = Flowable.interval(0, period, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Long, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Long aLong) throws Exception {
                        LogUtil.d(TAG + "registerInterval() --> " + aLong);
                        long currentTime = MediaManager.getCurrentTime();
                        mPlayMusicInfo.setCurrentTime(currentTime);
                        RxBus.getDefault().post(new UpdateProgressEvent(currentTime));
                        return Flowable.just(aLong);
                    }
                })
                .subscribe();
    }

    private void cancelInterval() {
        mDisposableInterval.dispose();
    }
}

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

    private List<Music> mMusics;

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
        if(mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        LogUtil.d(TAG + " --> onStartCommand()");
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        MediaManager.playMusic(mPlayMusicInfo.getMusic().getPath(), new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogUtil.d(TAG + "MediaManager play completely.");
                mIsPause = true;
                mPlayMusicInfo.setPause(true);
                RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
                mCompositeDisposable.clear();
            }
        });
        registerInterval(0, 1000);

        RxBus.getDefault().post(new PlayMusicEvent(mPlayMusicInfo));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG + " --> onBind()");
        if(mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        if (intent == null) {
            return new MyBinder();
        }
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        if (mPlayMusicInfo == null) {
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
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

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

    public void setIsPause(boolean mIsPause) {
        this.mIsPause = mIsPause;
        mPlayMusicInfo.setPause(mIsPause);
    }

    private void registerInterval(long delay, long period) {
//        DisposableObserver<Long> disposableObserver = getIntervalObserver();
        mDisposableInterval = Flowable.interval(0, period,TimeUnit.MILLISECONDS)
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
        mCompositeDisposable.add(mDisposableInterval);
    }

    private DisposableObserver<Long> getIntervalObserver() {
        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                LogUtil.d(TAG + "registerInterval() --> " + aLong);
                long currentTime = MediaManager.getCurrentTime();
                RxBus.getDefault().post(new UpdateProgressEvent(currentTime));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void cancelInterval() {
        mCompositeDisposable.remove(mDisposableInterval);
    }
}
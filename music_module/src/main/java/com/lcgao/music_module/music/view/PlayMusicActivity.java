package com.lcgao.music_module.music.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.music_module.R;
import com.lcgao.music_module.event.PlayMusicEvent;
import com.lcgao.music_module.event.RxBus;
import com.lcgao.music_module.event.UpdateProgressEvent;
import com.lcgao.music_module.music.PlayMusicContract;
import com.lcgao.music_module.music.PlayMusicService;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.PlayMusicInfo;
import com.lcgao.music_module.util.LocalMusicHelper;
import com.lcgao.music_module.util.LogUtil;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PlayMusicActivity extends BaseActivity implements PlayMusicContract.View {
    private static final String TAG = "PlayMusicActivity: ";

    public static final String EXTRA_PLAY_MUSIC_INFO = "play_music_info";

    @BindView(R.id.toolbar_play)
    Toolbar mToolbar;

    @BindView(R.id.iv_layout_play_panel_play)
    ImageView mIvPlay;

    @BindView(R.id.tv_act_music_title)
    TextView mTvTitle;

    @BindView(R.id.tv_layout_play_panel_current_time)
    TextView mTvCurrentTime;

    @BindView(R.id.tv_layout_play_panel_total_time)
    TextView mTvTotalTime;

    @BindView(R.id.sb_layout_play_panel_progress)
    SeekBar mSbProgress;

    @BindView(R.id.siv_act_music_album)
    ShapedImageView mSivAlbumCover;

    private PlayMusicInfo mPlayMusicInfo;

    private PlayMusicService mPlayMusicService;

    private CompositeDisposable mCompositionDis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this);
//        subscribe();
        mCompositionDis = new CompositeDisposable();
        registerRxBus();
        Intent intent = getIntent();
        mPlayMusicInfo = intent.getParcelableExtra(EXTRA_PLAY_MUSIC_INFO);
        if (mPlayMusicInfo == null) {
            finish();
        }
        initView();
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("extra_test");
            LogUtil.d(TAG + "[onCreate] restore extra_test:" + test);
        }

        Intent intentToService = new Intent(PlayMusicActivity.this, PlayMusicService.class);
        intentToService.putExtra(PlayMusicService.EXTRA_PLAY_MUSIC_INFO, mPlayMusicInfo);
        bindService(intentToService, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mPlayMusicService = ((PlayMusicService.MyBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        Music music = mPlayMusicInfo.getPlayList().get(mPlayMusicInfo.getCurrentPosition());
        mTvTitle.setText(music.getTitle());
        Bitmap bitmap = LocalMusicHelper.getAlbumCover(getApplicationContext(), music.getAlbumId());
        mSivAlbumCover.setImageBitmap(bitmap);
        mIvPlay.setImageResource(mPlayMusicInfo.isPause() ? R.drawable.ic_play_btn_play : R.drawable.ic_play_btn_pause);
        mTvTotalTime.setText(new SimpleDateFormat("mm:ss").format(music.getDuration()));
        mSbProgress.setMax(Integer.parseInt(music.getDuration() + ""));
        mSbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvCurrentTime.setText(new SimpleDateFormat("mm:ss").format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayMusicService.seekTo(seekBar.getProgress());
            }
        });
        if (!mPlayMusicInfo.isPause()) {
            rotate(mSivAlbumCover);
        }
        mToolbar.setTitle(music.getTitle());
        mToolbar.setSubtitle(music.getArtist());
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        mToolbar.setTitleTextAppearance(this, R.style.ToolbarTitle);
        mToolbar.setSubtitleTextAppearance(this, R.style.SubToolbarTitle);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositionDis != null) {
            mCompositionDis.clear();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.d(TAG + "onSaveInstanceState");
        outState.putString("extra_test", "test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        LogUtil.d(TAG + "[onRestoreInstanceState] restore extra_test:" + test);
    }

    /**
     * 不停顿地旋转
     *
     * @param view 要旋转的view
     */
    private void rotate(View view) {
        if (view == null) {
            return;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        view.setAnimation(rotateAnimation);
        rotateAnimation.setDuration(30000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

    @OnClick(R.id.iv_layout_play_panel_play)
    public void onClickPlay() {
        rotate(mSivAlbumCover);
        mPlayMusicService.playOrPause();
    }

    @OnClick(R.id.iv_layout_play_panel_prev)
    public void onClickPrev() {
        rotate(mSivAlbumCover);
        mPlayMusicService.pre();
    }

    @OnClick(R.id.iv_layout_play_panel_next)
    public void onClickNext() {
        rotate(mSivAlbumCover);
        mPlayMusicService.next();
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void pauseMusic() {

    }

    @Override
    public void setPresenter(PlayMusicContract.Presenter presenter) {

    }

    private void registerRxBus() {
        Disposable disposableMusicPlayInfo = RxBus.getDefault().toObservable(PlayMusicEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PlayMusicEvent>() {
                    @Override
                    public void accept(PlayMusicEvent playMusicEvent) throws Exception {
                        mPlayMusicInfo = playMusicEvent.mPlayMusicInfo;
                        Music music = mPlayMusicInfo.getPlayList().get(mPlayMusicInfo.getCurrentPosition());
                        mTvTitle.setText(music.getTitle());
                        mToolbar.setTitle(music.getTitle());
                        mToolbar.setSubtitle(music.getArtist());
                        mTvCurrentTime.setText(new SimpleDateFormat("mm:ss").format(mPlayMusicInfo.getCurrentTime()));
                        mTvTotalTime.setText(new SimpleDateFormat("mm:ss").format(music.getDuration()));
                        Bitmap bitmap = LocalMusicHelper.getAlbumCover(getApplicationContext(), music.getAlbumId());
                        mSivAlbumCover.setImageBitmap(bitmap);
                        mIvPlay.setImageResource(mPlayMusicInfo.isPause() ? R.drawable.ic_play_btn_play : R.drawable.ic_play_btn_pause);
                    }
                });
        Disposable disposableUpdateProgress = RxBus.getDefault().toObservable(UpdateProgressEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateProgressEvent>() {
                    @Override
                    public void accept(UpdateProgressEvent updateProgressEvent) throws Exception {
                        LogUtil.d(TAG + "registerRxBus() --> " + updateProgressEvent.mCurrentTime);
                        mPlayMusicInfo.setCurrentTime(updateProgressEvent.mCurrentTime);
//                        mTvCurrentTime.setText(new SimpleDateFormat("mm:ss").format(mPlayMusicInfo.getCurrentTime()));
                        mSbProgress.setProgress(Integer.parseInt(updateProgressEvent.mCurrentTime + ""));
                    }
                });
        mCompositionDis.add(disposableMusicPlayInfo);
        mCompositionDis.add(disposableUpdateProgress);
    }
}

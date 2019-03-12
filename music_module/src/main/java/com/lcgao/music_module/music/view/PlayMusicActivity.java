package com.lcgao.music_module.music.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.music_module.R;
import com.lcgao.music_module.event.RxBus;
import com.lcgao.music_module.music.PlayMusicContract;
import com.lcgao.music_module.music.PlayMusicService;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.PlayMusicInfo;
import com.lcgao.music_module.music.view_model.MusicViewModel;
import com.lcgao.music_module.util.LogUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @BindView(R.id.siv_act_music_album)
    ShapedImageView mSivAlbum;

    @BindView(R.id.iv_play)
    ImageView mIvPlay;

    @BindView(R.id.tv_act_music_title)
    TextView mTvTitle;

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
        mToolbar.setTitle(mPlayMusicInfo.getMusic().getTitle());
        mToolbar.setSubtitle(mPlayMusicInfo.getMusic().getArtist());

//        mToolbar.getBackground().setAlpha(0);
        mToolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        mToolbar.setTitleTextAppearance(this, R.style.ToolbarTitle);
        mToolbar.setSubtitleTextAppearance(this, R.style.SubToolbarTitle);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        mTvTitle.setText(mPlayMusicInfo.getMusic().getTitle());
        mIvPlay.setImageResource(mPlayMusicInfo.isPause() ? R.drawable.ic_play_btn_play : R.drawable.ic_play_btn_pause);
        if (!mPlayMusicInfo.isPause()) {
            rotate(mSivAlbum);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
     * @param view
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

    String path = "/storage/emulated/0/netease/cloudmusic/Music/赵雷 - 彩虹下面.mp3";
    /**
     * /storage/emulated/0/netease/cloudmusic/Music/逃跑计划 - 夜空中最亮的星.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/薛之谦 - 演员.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/李荣浩 - 老街.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/赵雷 - 鼓楼.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/薛之谦 - 意外.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/刘惜君 - 我很快乐.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/金玟岐 - 岁月神偷.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/杨宗纬 叶蓓 - 我们好像在哪见过.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/Pianoboy高至豪 - The truth that you leave.mp3
     * <p>
     * /storage/emulated/0/netease/cloudmusic/Music/薛之谦 - 方圆几里.mp3
     */
    MediaPlayer mediaPlayer;

    @OnClick(R.id.iv_play)
    public void onClickPlay() {
        rotate(mSivAlbum);
        mPlayMusicService.playOrPause();
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
        Disposable disposableMusicPlayInfo = RxBus.getDefault().toObservable(PlayMusicInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PlayMusicInfo>() {
                    @Override
                    public void accept(PlayMusicInfo playMusicInfo) throws Exception {
                        mPlayMusicInfo = playMusicInfo;
                        mTvTitle.setText(playMusicInfo.getMusic().getTitle());
                        mIvPlay.setImageResource(playMusicInfo.isPause() ? R.drawable.ic_play_btn_pause : R.drawable.ic_play_btn_play);
                    }
                });
        mCompositionDis.add(disposableMusicPlayInfo);
    }
}

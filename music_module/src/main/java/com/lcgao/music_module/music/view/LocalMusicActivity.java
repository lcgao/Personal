package com.lcgao.music_module.music.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.music_module.R;
import com.lcgao.music_module.adapter.CommonAdapter;
import com.lcgao.music_module.adapter.ViewHolder;
import com.lcgao.music_module.music.MusicsContract;
import com.lcgao.music_module.music.MusicsPresenter;
import com.lcgao.music_module.music.PlayMusicService;
import com.lcgao.music_module.music.data.MusicsRepository;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.PlayMusicInfo;
import com.lcgao.music_module.music.data.source.local.MusicsLocalDataSource;
import com.lcgao.music_module.music.data.source.remote.MusicsRemoteDataSource;
import com.lcgao.music_module.widget.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalMusicActivity extends BaseActivity implements MusicsContract.View {
    private static final String TAG = "LocalMusicActivity: ";

    private MusicsContract.Presenter mPresenter;

    @BindView(R.id.rv_layout_music_list)
    RecyclerView mRvMusicList;
    @BindView(R.id.ll_nothing)
    LinearLayout mLlNothing;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    private CommonAdapter<Music> mAdapter;

    private List<Music> mMusicList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        ButterKnife.bind(this);
        mPresenter = new MusicsPresenter(
                MusicsRepository.getInstance(MusicsLocalDataSource.getInstance(),
                        MusicsRemoteDataSource.getInstance()), this);
        initView();
//        initParas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter != null){
            mPresenter.subscribe();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPresenter != null){
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        mToolbar.setTitle("本地音乐");
        mToolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMusicList.setLayoutManager(layoutManager);
        mRvMusicList.setHasFixedSize(true);
        mRvMusicList.getItemAnimator().setChangeDuration(0);
//        mRvMusicList.addItemDecoration(new RecyclerViewDiv);
        mAdapter = new CommonAdapter<Music>(this, R.layout.item_music_list, mMusicList) {
            @Override
            public void convert(ViewHolder holder, final Music music) {
                holder.setText(R.id.tv_item_music_list_name, music.getTitle());
                holder.setText(R.id.tv_item_music_list_singer, music.getArtist() + " - " + music.getAlbum());
                holder.setOnClickListener(R.id.ll_item_music_list, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlayMusicInfo playMusicInfo = new PlayMusicInfo(music, mMusicList, false);
                        Intent intentToService = new Intent(LocalMusicActivity.this, PlayMusicService.class);
                        intentToService.putExtra(PlayMusicService.EXTRA_PLAY_MUSIC_INFO, playMusicInfo);
                        startService(intentToService);
                        Intent intent = new Intent(LocalMusicActivity.this, PlayMusicActivity.class);
                        intent.putExtra(PlayMusicActivity.EXTRA_PLAY_MUSIC_INFO, playMusicInfo);
                        startActivity(intent);
                    }
                });
            }
        };
        mRvMusicList.setAdapter(mAdapter);
        mRvMusicList.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST, 20, 10));
        mSrlRefresh.setColorSchemeColors(getResources().getColor(R.color.themecolor));
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadMusics(true);
            }
        });
    }

    public void setData(List<Music> musicList) {
        mMusicList.clear();
        mMusicList.addAll(musicList);
        mLlNothing.setVisibility(View.GONE);
        mAdapter.replaceData(musicList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_local_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_local_music_scan) {
            startActivity(new Intent(LocalMusicActivity.this, ScanLocalMusicActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMusics(List<Music> musics) {
        setData(musics);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mSrlRefresh.setRefreshing(active);
    }

    @Override
    public void showNoMusics() {
        mLlNothing.setVisibility(View.VISIBLE);
        return;
    }

    @Override
    public void setPresenter(MusicsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mSrlRefresh.isRefreshing()){
                mSrlRefresh.setRefreshing(false);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

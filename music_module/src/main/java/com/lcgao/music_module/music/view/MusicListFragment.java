package com.lcgao.music_module.music.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lcgao.music_module.R;
import com.lcgao.music_module.adapter.CommonAdapter;
import com.lcgao.music_module.adapter.ViewHolder;
import com.lcgao.music_module.music.model.SongInfo;
import com.lcgao.music_module.widget.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicListFragment extends Fragment {
    private static final String TAG = "MusicListFragment: ";
    @BindView(R.id.rv_layout_music_list)
    RecyclerView mRvMusicList;
    @BindView(R.id.ll_nothing)
    LinearLayout llNothing;
    private CommonAdapter<SongInfo> mAdapter;

//    private MusicListViewModel mViewModel;

    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_music_list, container, false);
        ButterKnife.bind(this, view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMusicList.setLayoutManager(layoutManager);
        mRvMusicList.setHasFixedSize(true);
        mRvMusicList.getItemAnimator().setChangeDuration(0);
//        mRvMusicList.addItemDecoration(new RecyclerViewDiv);
        mAdapter = new CommonAdapter<SongInfo>(getActivity(), R.layout.item_music_list, new ArrayList<SongInfo>()) {
            @Override
            public void convert(ViewHolder holder, SongInfo songInfo) {
                holder.setText(R.id.tv_item_music_list_name, songInfo.getName());
                holder.setText(R.id.tv_item_music_list_singer, songInfo.getSinger() + "-" + songInfo.getAlbum());
            }
        };
        mRvMusicList.setAdapter(mAdapter);
        mRvMusicList.addItemDecoration(new RecyclerViewDecoration(getActivity(), RecyclerViewDecoration.VERTICAL_LIST, 20, 10));
        List<SongInfo> songInfos = new ArrayList<>();
        SongInfo songInfo1 = new SongInfo("春风十里", "鹿先森乐队", "xxx", (6 * 60 + 15) * 1000, 10 * 1024 * 1024, "xxx", "所有的酒，都不如你");
        SongInfo songInfo2 = new SongInfo("成都", "赵雷", "xxx", (6 * 60 + 15) * 1000, 10 * 1024 * 1024, "xxx", "无法长大");
        SongInfo songInfo3 = new SongInfo("See You Again", "Wiz Khalifa", "xxx", (6 * 60 + 15) * 1000, 10 * 1024 * 1024, "xxx", "Furious7");
        SongInfo songInfo4 = new SongInfo("夜空中最亮的星", "逃跑计划", "xxx", (6 * 60 + 15) * 1000, 10 * 1024 * 1024, "xxx", "世界");
        SongInfo songInfo5 = new SongInfo("Always With Me", "木村弓/奥户巴寿", "xxx", (6 * 60 + 15) * 1000, 10 * 1024 * 1024, "xxx", "幸福的味道");
        songInfos.add(songInfo1);
        songInfos.add(songInfo2);
        songInfos.add(songInfo3);
        songInfos.add(songInfo4);
        songInfos.add(songInfo5);
        setData(songInfos);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        mViewModel = ViewModelProviders.of(this).get(MusicListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
//        mViewModel.start();
    }

    public void setData(List<SongInfo> newsContents) {
        if (newsContents == null || newsContents.size() == 0) {
            llNothing.setVisibility(View.VISIBLE);
            return;
        }
        llNothing.setVisibility(View.GONE);
//        mNewsContent.clear();
//        mNewsContent.addAll(newsContents);
        mAdapter.replaceData(newsContents);
    }
}

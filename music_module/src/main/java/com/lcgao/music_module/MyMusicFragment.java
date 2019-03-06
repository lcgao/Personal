package com.lcgao.music_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.lcgao.music_module.music.view.LocalMusicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lcgao on 2018/1/11.
 */

public class MyMusicFragment extends Fragment {

    @BindView(R.id.ll_frag_my_music_local_music)
    LinearLayout mLlLocalMusic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_music, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.ll_frag_my_music_local_music)
    public void onClickLocalMusic(){
        getActivity().startActivity(new Intent(getActivity(), LocalMusicActivity.class));
    }
}

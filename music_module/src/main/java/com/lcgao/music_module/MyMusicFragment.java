package com.lcgao.music_module;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lcgao.music_module.music.view.LocalMusicActivity;
import com.lcgao.music_module.util.LocalMusicHelper;

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
        if(LocalMusicHelper.requestPermission(getActivity())) {
            getActivity().startActivity(new Intent(getActivity(), LocalMusicActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getActivity().startActivity(new Intent(getActivity(), LocalMusicActivity.class));
        }
    }
}

package com.lcgao.personal.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lcgao.personal.R;
import com.lcgao.personal.profile.recent_read.RecentReadActivity;
import com.lcgao.personal.profile.setting.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcgao on 2017/12/27.
 */

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @OnClick(R.id.layout_recent_read)
    public void onClickRecentRead(){
        startActivity(new Intent(getActivity(), RecentReadActivity.class));
    }

    @OnClick(R.id.layout_setting)
    public void onClickSetting(){
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }
    private void initView() {

    }
}

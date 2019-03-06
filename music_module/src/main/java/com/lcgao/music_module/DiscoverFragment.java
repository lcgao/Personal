package com.lcgao.music_module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.lcgao.music_module.adapter.FragmentAdapter;
import com.lcgao.music_module.recommend.model.FMFragment;
import com.lcgao.music_module.recommend.model.FriendFragment;
import com.lcgao.music_module.recommend.model.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lcgao on 2018/1/11.
 */

public class DiscoverFragment extends Fragment {
    @BindView(R.id.s_tab_layout)
    SlidingTabLayout mSTabLayout;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);
        mTitles.add("推荐");
        mTitles.add("朋友");
        mTitles.add("电台");
        mFragments.add(new RecommendFragment());
        mFragments.add(new FriendFragment());
        mFragments.add(new FMFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(fragmentAdapter);
        mSTabLayout.setViewPager(mViewPager);
        return view;
    }
}

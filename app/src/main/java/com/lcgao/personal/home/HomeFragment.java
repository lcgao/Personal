package com.lcgao.personal.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcgao.personal.R;
import com.lcgao.personal.adapter.FragmentAdapter;
import com.lcgao.personal.home.douban.DoubanFragment;
import com.lcgao.personal.home.essay.EssayFragment;
import com.lcgao.personal.home.one.OneFragment;
import com.lcgao.personal.home.zhihu.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lcgao on 2017/12/27.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.tl_fragment_home)
    TabLayout tlIndicator;
    @BindView(R.id.vp_fragment_home)
    ViewPager vpFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        List<String> titleList = new ArrayList<>();
        titleList.add("知乎日报");
        titleList.add("一个");
        titleList.add("豆瓣电影");
        titleList.add("每日一文");
        List<Fragment> fragmentList = new ArrayList<>();
        ZhihuFragment zhihuFrag = new ZhihuFragment();
        OneFragment oneFrag = new OneFragment();
        DoubanFragment doubanFrag = new DoubanFragment();
        EssayFragment essayFrag = new EssayFragment();
        fragmentList.add(zhihuFrag);
        fragmentList.add(oneFrag);
        fragmentList.add(doubanFrag);
        fragmentList.add(essayFrag);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), titleList, fragmentList);

        vpFragments.setAdapter(fragmentAdapter);

        tlIndicator.setupWithViewPager(vpFragments,false);
    }

}

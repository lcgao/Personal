package com.lcgao.personal.favourite.news;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lcgao.common_library.base.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {
    @BindView(R.id.tl_act_news)
    TabLayout tlIndicator;
    @BindView(R.id.vp_act_news)
    ViewPager vpFragments;
    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }

    @Override
    public void initParas(Bundle paras) {

    }

    @Override
    public void initView() {
        ButterKnife.bind(NewsActivity.this);
        toolbar.setTitle("新闻");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        List<String> titles = new ArrayList<>();
        titles.add("热点");
//        titles.add("视频");
        titles.add("体育");
        titles.add("娱乐");
        titles.add("问答");
        titles.add("图片");
        titles.add("科技");
        titles.add("汽车");
        titles.add("财经");
        titles.add("军事");
//        titles.add("国际");
//        titles.add("段子");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            NewsFragment newsFragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("category", titles.get(i));
            newsFragment.setArguments(bundle);
            fragments.add(newsFragment);
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragments);

        vpFragments.setAdapter(fragmentAdapter);
        tlIndicator.setTabMode(TabLayout.MODE_SCROLLABLE);
        tlIndicator.setupWithViewPager(vpFragments, false);
    }
}

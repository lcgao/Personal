package com.lcgao.personal.favourite.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lcgao.personal.BaseActivity;
import com.lcgao.personal.R;
import com.lcgao.personal.adapter.FragmentAdapter;
import com.lcgao.personal.home.one.OneFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
        List<String> titles = new ArrayList<>();
        titles.add("热点");
        titles.add("视频");
        titles.add("娱乐");
        titles.add("问答");
        titles.add("图片");
        titles.add("科技");
        titles.add("汽车");
        titles.add("体育");
        titles.add("财经");
        titles.add("军事");
        titles.add("国际");
        titles.add("段子");
        titles.add("趣图");
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

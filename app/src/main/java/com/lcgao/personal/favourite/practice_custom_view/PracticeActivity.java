package com.lcgao.personal.favourite.practice_custom_view;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lcgao.personal.BaseActivity;
import com.lcgao.personal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PracticeActivity extends BaseActivity {
    @BindView(R.id.tl_act_news)
    TabLayout tlIndicator;
    @BindView(R.id.vp_act_news)
    ViewPager vpFragments;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    final List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_object_animate, R.layout.practice_object_animator));

        pageModels.add(new PageModel(R.string.title_draw, R.layout.practice_dispatch_draw));
        pageModels.add(new PageModel(R.string.title_draw, R.layout.practice_draw_view));
        pageModels.add(new PageModel(R.string.title_clip, R.layout.practice_clip));
        pageModels.add(new PageModel(R.string.title_linear_gradient, R.layout.practice_linear_gradient));
        pageModels.add(new PageModel(R.string.title_mask_filter, R.layout.practice_mask_filter));
        pageModels.add(new PageModel(R.string.title_pie_chart, R.layout.practice_pie_chart));
    }

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
        ButterKnife.bind(PracticeActivity.this);
        toolbar.setTitle("CustomView");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(android.support.design.R.drawable.abc_ic_ab_back_material);
//        List<String> titles = new ArrayList<>();
//        titles.add("LinearGradientView");
//        titles.add("MaskFilterView");

        FragmentPagerAdapter fragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.practiceLayoutRes);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        };

        vpFragments.setAdapter(fragmentAdapter);
        tlIndicator.setTabMode(TabLayout.MODE_SCROLLABLE);
        tlIndicator.setupWithViewPager(vpFragments, false);
    }

    private class PageModel {
        //        @LayoutRes
//        int sampleLayoutRes;
        @StringRes
        int titleRes;
        @LayoutRes
        int practiceLayoutRes;

        PageModel(@StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
//            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }
}

package com.lcgao.personal.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        toolbar.setTitle("阅读");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        List<String> titleList = new ArrayList<>();
        titleList.add("知乎日报");
        titleList.add("每日一文");
        titleList.add("一个");
        titleList.add("豆瓣电影");
        List<Fragment> fragmentList = new ArrayList<>();
        ZhihuFragment zhihuFrag = new ZhihuFragment();
        OneFragment oneFrag = new OneFragment();
        DoubanFragment doubanFrag = new DoubanFragment();
        EssayFragment essayFrag = new EssayFragment();
        fragmentList.add(zhihuFrag);
        fragmentList.add(essayFrag);
        fragmentList.add(oneFrag);
        fragmentList.add(doubanFrag);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), titleList, fragmentList);

        vpFragments.setAdapter(fragmentAdapter);
//        tlIndicator.setTabMode(TabLayout.MODE_SCROLLABLE);
        tlIndicator.setupWithViewPager(vpFragments, false);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.my_navigation_items, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.navigation_home:
//                ToastUtil.s("Home");
//                break;
//            case R.id.navigation_favourite:
//                ToastUtil.s("Favourite");
//
//                break;
//            case R.id.navigation_profile:
//                ToastUtil.s("Profile");
//
//                break;
//            default:
//                ToastUtil.s("Default");
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}

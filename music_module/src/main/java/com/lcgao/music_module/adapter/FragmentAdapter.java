package com.lcgao.music_module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lcgao on 2017/5/26.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<String> titleList;
    private List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fragmentManager, List<String> titleList, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}

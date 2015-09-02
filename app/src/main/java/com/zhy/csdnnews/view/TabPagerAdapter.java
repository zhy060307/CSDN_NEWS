package com.zhy.csdnnews.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhy.csdnnews.utils.CConfigKey;

public class TabPagerAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = new String[]{"业界", "移动", "研发", "程序员杂志", "云计算"};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TabItem tab = new TabItem();
        Bundle bundle = new Bundle();
        bundle.putInt(CConfigKey.NEWS_TYPE, position + 1);
        tab.setArguments(bundle);
        return tab;
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }
}

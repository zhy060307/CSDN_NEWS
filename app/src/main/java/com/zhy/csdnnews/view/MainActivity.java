package com.zhy.csdnnews.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;
import com.zhy.csdnnews.R;


public class MainActivity extends FragmentActivity {

    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabPageIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.id_pager);
        viewPager.setAdapter(pagerAdapter);
        tabPageIndicator.setViewPager(viewPager, 0);
    }


}

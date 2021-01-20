package com.working.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页的ViewPager适配器
 */
public class IndexPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList = new ArrayList<>();

    public IndexPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mFragmentList.addAll(fragments);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

}

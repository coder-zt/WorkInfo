package com.working;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.working.adapter.IndexPagerAdapter;
import com.working.base.BaseActivity;
import com.working.fragment.IndexFragment;
import com.working.fragment.MessageFragment;
import com.working.fragment.MineFragment;
import com.working.fragment.StatFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager mVpContainer;
    private BottomNavigationView mBnvBar;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mVpContainer = (ViewPager)findViewById(R.id.vp_index);
        mBnvBar = (BottomNavigationView)findViewById(R.id.bnv_view);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mBnvBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onNavBarClicked(item);
                return true;
            }
        });
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new StatFragment());
//        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
        mAdapter = new IndexPagerAdapter(fragments, getSupportFragmentManager());
        mVpContainer.setAdapter(mAdapter);
    }

    private void onNavBarClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_index://首页
                mVpContainer.setCurrentItem(0);
                getWindow().setStatusBarColor(Color.WHITE);
                break;
            case R.id.action_stat://统计
                mVpContainer.setCurrentItem(1);
                getWindow().setStatusBarColor(Color.WHITE);
                break;
//            case R.id.action_message://消息
//                mVpContainer.setCurrentItem(2);
//                getWindow().setStatusBarColor(Color.WHITE);
//                break;
            case R.id.action_mine://我的
                mVpContainer.setCurrentItem(2);

                getWindow().setStatusBarColor(Color.parseColor("#2C81FE"));
                break;
        }
    }
}

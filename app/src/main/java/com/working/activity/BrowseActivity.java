package com.working.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.working.R;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityBrowsrBinding;
import com.working.utils.ToastUtil;
import com.working.view.ZoomImageView;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 视频和图片的浏览页面
 */
public class BrowseActivity extends BaseActivity {
    private boolean isShowImage;
    private ZoomImageView mIvImage;
    private JzvdStd mJzvdStd;
    private String mData;
    private ActivityBrowsrBinding mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_browsr);
        mViewDataBinding.setActivity(this);

        initView();
        initData();
    }

    private void initView() {
        mIvImage = findViewById(R.id.zoomImageView);
        mJzvdStd = (JzvdStd) findViewById(R.id.jz_video);
    }

    private void initData() {
        mData = getIntent().getStringExtra("data");
        if (mData != null) {
            if (mData.endsWith("mp4")) {
                isShowImage = false;
                mIvImage.setVisibility(View.GONE);
                mJzvdStd.setVisibility(View.VISIBLE);
            }else{
                isShowImage = true;
                mJzvdStd.setVisibility(View.GONE);
                mIvImage.setVisibility(View.VISIBLE);
            }
            return;
        }
        ToastUtil.showMessage("没有数据！");
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isShowImage) {
            Glide.with(this).load(mData).into(mIvImage);
        }else{
            mJzvdStd.setUp(mData, "");
        }

    }
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Jzvd.releaseAllVideos();
    }

}

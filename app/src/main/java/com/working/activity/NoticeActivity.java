package com.working.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.gson.Gson;
import com.working.R;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityNoticeBinding;
import com.working.domain.IndexNotice;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class NoticeActivity extends BaseActivity {

    private ActivityNoticeBinding mViewDataBinding;
    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice);
        mViewDataBinding.setActivity(this);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        String data = getIntent().getStringExtra("data");
        IndexNotice.DataBean.RecordsBean noticeData = new Gson().fromJson(data,IndexNotice.DataBean.RecordsBean.class);
        mViewDataBinding.setData(noticeData);
    }
}

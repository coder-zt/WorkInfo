package com.working.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.working.R;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityChangePsdBinding;
import com.working.domain.ChangePsdBean;
import com.working.domain.LoginInfo;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.impl.ChangePsdPresenterImpl;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;

public class ChangePsdActivity extends BaseCommitActivity {

    private static final String TAG = "ChangePsdActivity";
    private ActivityChangePsdBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.input_bg_color));
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_psd);
        mBinding.setClickEvent(this);
    }

    @Override
    protected ICommitPresenter createPresenter() {
        return new ChangePsdPresenterImpl();
    }



    public void submit(String oldPsd, String newPsd, String surePsd){
        if(oldPsd == null || oldPsd.isEmpty()){
            ToastUtil.showMessage("旧密码不能为空！");
            return;
        }
        if(newPsd ==null || newPsd.isEmpty()){
            ToastUtil.showMessage("新密码不能为空！");
            return;
        }else if(newPsd.length() <6){
            ToastUtil.showMessage("密码至少6位！");
            return;
        }
        if(surePsd ==null || surePsd.isEmpty()){
            ToastUtil.showMessage("确认密码不能为空！");
            return;
        }
        if(TextUtils.equals(oldPsd, newPsd)){
            ToastUtil.showMessage("新密码与旧密码相同！");
            return;
        }
        if(!TextUtils.equals(surePsd, newPsd)){
            ToastUtil.showMessage("两次输入新密码不同！");
            return;
        }
        commitData(new ChangePsdBean(oldPsd, newPsd, surePsd), true);
    }

    @Override
    protected void onCommitFail() {

    }

    @Override
    protected void addImageUrl(String link) {

    }
}

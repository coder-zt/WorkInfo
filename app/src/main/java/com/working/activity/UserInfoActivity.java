package com.working.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.adapter.UserInfoAdapter;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityUserInfoBinding;
import com.working.domain.LoginInfo;
import com.working.domain.UserInfo;
import com.working.interfaces.IUserCallback;
import com.working.presenter.ILoginPresenter;
import com.working.presenter.impl.LoginPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.UserDataMan;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends BaseActivity implements IUserCallback {

    private ActivityUserInfoBinding mBinding;
    private RecyclerView mRecyclerView;
    private UserInfoAdapter mAdapter;
    private ILoginPresenter mPresenter = new LoginPresenterImpl();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.input_bg_color));
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        mBinding.setActivity(this);
        LoginInfo loginInfo = UserDataMan.getInstance().getLoginInfo();
        if (loginInfo != null) {
            mPresenter.registerCallback(this);
            mPresenter.getUserInfo(loginInfo.getUser_id());
        }
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserInfoAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onLogout(){
        UserDataMan.getInstance().logout();
        AppRouter.toLoginActivity(this);
        finish();
    }

    @Override
    public void onUserAuthLoaded(LoginInfo loginInfo) {

    }

    @Override
    public void onUserInfoLoaded(UserInfo info) {
        UserInfo.DataBean data = info.getData();
        if (data == null) {
            return;
        }
        mBinding.setUser(data);
        List<Pair<String, String>> datas = new ArrayList<>();
        datas.add(new Pair<>("昵称：", data.getName()));
        datas.add(new Pair<>("用户姓名：", data.getRealName()));
        datas.add(new Pair<>("姓名：", data.getSexName()));
        datas.add(new Pair<>("岗位：", data.getPostName()));
        datas.add(new Pair<>("用户ID：", data.getId()));
        datas.add(new Pair<>("用户角色：", data.getRoleName()));
        datas.add(new Pair<>("所属部门：", data.getDeptName()));
        mAdapter.setData(datas);
    }

    @Override
    public void onLoadedFail() {

    }
}

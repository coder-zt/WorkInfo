package com.working.presenter.impl;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.LoginInfo;
import com.working.domain.UserInfo;
import com.working.interfaces.IUserCallback;
import com.working.models.AppModels;
import com.working.presenter.ILoginPresenter;
import com.working.utils.RetrofitManager;
import com.working.utils.UserDataMan;

public class LoginPresenterImpl extends BasePresenterImpl implements ILoginPresenter {

    private static final String TAG = "LoginPresenterImpl";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getUserAuth(String userName, String passWord) {
        AppModels.getInstance().getUserAuth(userName, passWord, new AppModels.OnUserAuthListener() {
            @Override
            public void onUserAuthLoaded(LoginInfo loginInfo) {
                if (mCallback != null) {
                    ((IUserCallback)mCallback).onUserAuthLoaded(loginInfo);
                }
            }

            @Override
            public void onLoadedFail(String msg) {
                if(mCallback != null){
                    ((IUserCallback)mCallback).onLoadedFail();
                }
                Log.d(TAG, "onLoadedFail: " + msg);
            }
        });
    }

    @Override
    public void refreshUserAuth() {
        String token = UserDataMan.getInstance().getLoginInfo().getRefresh_token();
        AppModels.getInstance().refreshUserAuth(token, new AppModels.OnUserAuthListener() {
            @Override
            public void onUserAuthLoaded(LoginInfo loginInfo) {
                if (mCallback != null) {
                    ((IUserCallback)mCallback).onUserAuthLoaded(loginInfo);
                }
            }

            @Override
            public void onLoadedFail(String msg) {
                Log.d(TAG, "onLoadedFail: " + msg);
            }
        });
    }

    @Override
    public void getUserInfo(String id) {

        AppModels.getInstance().getUserInfo(id, new AppModels.OnUserInfoLoadListener() {
            @Override
            public void onUserInfoLoaded(UserInfo userInfo) {
                if (userInfo != null && mCallback!= null) {
                    ((IUserCallback)mCallback).onUserInfoLoaded(userInfo);
                }
            }

            @Override
            public void onLoadedFail(String msg) {
                Log.d(TAG, "onLoadedFail: " + msg);
            }
        });
    }

}

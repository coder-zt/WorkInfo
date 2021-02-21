package com.working.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.working.WorkingApp;
import com.working.domain.LoginInfo;

public class UserDataMan {

    private static UserDataMan instance;
    private LoginInfo mLoginInfo;

    private UserDataMan(){

    }

    public boolean init(LoginInfo loginInfo, boolean isLogin){
        if (loginInfo == null && isLogin) {
            ToastUtil.showMessage("用户信息为空！");
            return false;
        }
        if(!isLogin){//使用sp初始化
           return initUserWithSp();
        }else{//保存用户信息
            mLoginInfo = loginInfo;
            saveUserInfo();
        }
        return true;
    }

    private boolean initUserWithSp() {
        Gson gson = new Gson();
        SharedPreferences sp = WorkingApp.getContext().getSharedPreferences(AppConfig.SP_KEY, Context.MODE_PRIVATE);
        String userInfo = sp.getString(AppConfig.SP_USER_KEY, null);
        if (userInfo == null) {
            return false;
        }
        Log.d(TAG, "initUserWithSp: " + userInfo);
        mLoginInfo = gson.fromJson(userInfo, LoginInfo.class);

        return mLoginInfo != null;
    }

    private void saveUserInfo() {
        Gson gson = new Gson();
        String userInfo = gson.toJson(mLoginInfo);
        Log.d(TAG, "saveUserInfo: " + "保存用户信息！ userInfo--->" + userInfo);
        SharedPreferences sp = WorkingApp.getContext().getSharedPreferences(AppConfig.SP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(AppConfig.SP_USER_KEY,userInfo);
        edit.apply();
    }

    public static synchronized  UserDataMan getInstance(){
            if(instance == null){
                instance = new UserDataMan();
            }
            return instance;
    }

    public LoginInfo getLoginInfo(){
        return mLoginInfo;
    }

    public void logout() {
        SharedPreferences sp = WorkingApp.getContext().getSharedPreferences(AppConfig.SP_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(AppConfig.SP_USER_KEY,"");
        edit.apply();
        mLoginInfo = null;
    }

    public boolean isLogin() {
        return mLoginInfo != null;
    }

    /**
     * 判断是否有一级审核权限
     * @return
     */
    public boolean checkFirstApprovalGrant(){
        if (mLoginInfo != null) {
            String role_id = mLoginInfo.getRole_id();
            if (role_id != null) {
                return role_id.contains(AppConfig.DADUI_ID);
            }
        }
        return false;
    }

    /**
     * 判断是否有二级审核权限
     * @return
     */
    public boolean checkSecondApprovalGrant(){
        if (mLoginInfo != null) {
            String role_id = mLoginInfo.getRole_id();
            if (role_id != null) {
                return role_id.contains(AppConfig.ZHIDUI_ID);
            }
        }
        return false;
    }

    private static final String TAG = "UserDataMan";
    /**
     * 判断是否有库存管理权限
     * @return
     */
    public boolean checkMaterialGrant(){
        if (mLoginInfo != null) {
            String role_id = mLoginInfo.getRole_id();
            Log.d(TAG, "checkMaterialGrant: " + role_id);
            if (role_id != null) {
                return role_id.contains(AppConfig.MATERIAL_ID);
            }
        }
        return false;
    }

    public boolean checkApprovalGrant() {
        return checkFirstApprovalGrant() || checkSecondApprovalGrant();
    }
}

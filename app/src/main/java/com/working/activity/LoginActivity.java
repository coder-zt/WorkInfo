package com.working.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.working.R;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityLoginBinding;
import com.working.domain.LoginInfo;
import com.working.domain.UserInfo;
import com.working.interfaces.IUserCallback;
import com.working.presenter.ILoginPresenter;
import com.working.presenter.impl.LoginPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.DevelopConfig;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements IUserCallback {

    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding mDataBinding;
    private ILoginPresenter mPresenter;
    private boolean isPhoneNumber;
    private ProgressDialog mWaitingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenterImpl();
        mWaitingDialog = new ProgressDialog(this);
        mPresenter.registerCallback(this);
        checkUserInfo();
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mDataBinding.setIsPwd(true);
        mDataBinding.setClickEvent(new OnClickedListener() {
            @Override
            public void onSwitchMethod() {
                isPhoneNumber = !isPhoneNumber;
                mDataBinding.setIsPwd(isPhoneNumber);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onLogin(String userInfo, String password, boolean isPwd) {
                if(DevelopConfig.DEBUG && userInfo == null){
                    if(password == null){
                        userInfo = "yg01";
                    }
                    else if(password.equals("1")){
                        userInfo = "shenhe1";
                    }
                    else if(password.equals("2")){
                        userInfo = "shenhe21";
                    }
                    else if(password.equals("3")){
                        userInfo = "material";
                    }
                    password = "123456";
                }
                if (TextUtils.isEmpty(userInfo)) {
                    ToastUtil.showMessage("用户信息不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showMessage("验证信息不能为空！");
                    return;
                }
                login(userInfo, password, isPwd);
            }

            @Override
            public void getCheckCode() {
                Log.d(TAG, "getCheckCode: ");
                // TODO: 2020/12/21 获取验证码
            }
        });
    }

    private void checkUserInfo() {
        Log.d(TAG, "checkUserInfo: " + "初始化用户信息！");
        if(UserDataMan.getInstance().init(null, false)){
            //如果有用户信息刷新用户信息
            showWaitingDialog();
            mPresenter.refreshUserAuth();
        }
    }

    private void login(String userInfo, String password, boolean isPwd) {
        if(isPwd){
            showWaitingDialog();
            mPresenter.getUserAuth(userInfo, password);
        }

    }

    @Override
    public void onUserAuthLoaded(LoginInfo loginInfo) {
        if (!mWaitingDialog.isShowing()) {
            return;
        }
        mWaitingDialog.dismiss();
        Log.d(TAG, "onUserAuthLoaded: " + "初始化用户信息！");
        if (loginInfo == null) {
            ToastUtil.showMessage("用户信息不存在！");
            return;
        }
        UserDataMan.getInstance().init(loginInfo, true);
        AppRouter.toMainActivity(LoginActivity.this);
        finish();
    }

    @Override
    public void onUserInfoLoaded(UserInfo info) {

    }

    @Override
    public void onLoadedFail() {
        dismissWaitingDialog();
    }


    public interface OnClickedListener{

        void onSwitchMethod();

        void onLogin(String userInfo, String password, boolean isPwd);

        void getCheckCode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unregisterCallback();
        }
        dismissWaitingDialog();
    }

    /**
     * show等待Dialog
     */
    protected void showWaitingDialog() {
        mWaitingDialog.setTitle("登录中");
        mWaitingDialog.setMessage("等待中...");
        mWaitingDialog.setIndeterminate(true);
        mWaitingDialog.setCancelable(true);
        if (!mWaitingDialog.isShowing()) {
            mWaitingDialog.show();
        }
    }


    /**
     * show等待Dialog
     */
    protected void dismissWaitingDialog() {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }


}

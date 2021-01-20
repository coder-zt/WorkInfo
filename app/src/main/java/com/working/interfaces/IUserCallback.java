package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.LoginInfo;
import com.working.domain.UserInfo;

public interface IUserCallback extends IBaseCallback {

    void onUserAuthLoaded(LoginInfo loginInfo);

    void onUserInfoLoaded(UserInfo info);

    void onLoadedFail();
}

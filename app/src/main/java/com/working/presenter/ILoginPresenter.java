package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.interfaces.IInspectCallback;
import com.working.interfaces.IUserCallback;

/**
 * 登录页presenter
 */
public interface ILoginPresenter extends IBasePresenter{

    void getUserAuth(String userName, String passWord);


    void refreshUserAuth();

    void getUserInfo(String id);

}

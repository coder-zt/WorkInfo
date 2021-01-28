package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.domain.ChangePsdBean;

/**
 * 登录页presenter
 */
public interface IChangePsdPresenter extends IBasePresenter{

    void changePassword(ChangePsdBean data);
}

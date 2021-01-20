package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.interfaces.IIndexInfoCallback;
import com.working.interfaces.IInspectCallback;

/**
 * 首页页面的Presenter接口
 */
public interface IIndexInfoPresenter extends IBasePresenter{

    void loadNoticeData();

    void loadMoreNoticeData();
}

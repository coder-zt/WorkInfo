package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.domain.AppBaseDataBean;
import com.working.interfaces.IListCallback;
import com.working.interfaces.IPurchaseCallback;

import java.io.Serializable;

/**
 * 获取列表数据BasePresenter
 */
public interface IListPresenter extends IBasePresenter,Serializable {

    /**
     * 获取列表数据
     */
    void loadListData(boolean isCommit);

    /**
     * 获取更多列表数据
     */
    void loadListDataMore(boolean isCommit);
}

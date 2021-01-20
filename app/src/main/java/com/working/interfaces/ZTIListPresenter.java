package com.working.interfaces;

import com.working.base.IBasePresenter;

public interface ZTIListPresenter extends IBasePresenter {

    /**
     * 获取列表数据
     */
    void loadListData(boolean isCommit, String startTime, String endTime);

    /**
     * 获取更多列表数据
     */
    void loadListDataMore(boolean isCommit, String startTime, String endTime);
}

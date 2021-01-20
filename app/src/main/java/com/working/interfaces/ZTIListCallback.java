package com.working.interfaces;

import com.working.base.IBaseCallback;

import java.util.List;

public interface ZTIListCallback<T> extends IBaseCallback {

    /**
     * 获取提交的清单
     */
    void onListLoaded(List<T> recordsBeans, boolean isCommit);

    /**
     * 获取提交的清单失败
     */
    void onListLoadedFail(boolean isCommit);

    /**
     * 获取提交的清单
     */
    void onListLoadedMore(List<T> recordsBeans, boolean isCommit);

    /**
     * 获取提交的清单
     */
    void onListLoadedMoreFail(boolean isCommit);

}

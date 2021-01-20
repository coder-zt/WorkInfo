package com.working.presenter;

import com.working.base.IBasePresenter;

/**
 * 上传/更新记录的接口（所有）
 */
public interface ICommitPresenter<T> extends IBasePresenter {

    /**
     * 上传/更新：相比于上传要多传一个Id
     * @param uploadData
     */
    void uploadData(T uploadData);
}

package com.working.interfaces;


import com.working.base.IBaseCallback;

/**
 * 添加/更新记录(所有)(提交)
 */
public interface ICommitCallback extends IBaseCallback {

    /**
     * 添加记录完成
     */
    void onCommitFinished();

    /**
     * 添加记录失败
     *
     * @param msg
     */
    void onCommitFail(String msg);
}

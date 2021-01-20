package com.working.interfaces;


import com.working.base.IBaseCallback;

/**
 * 添加/更新记录(所有)
 */
public interface IApprovalCallback extends IBaseCallback {

    /**
     * 添加记录完成
     */
    void onApprovalFinished();

    /**
     * 添加记录失败
     *
     * @param msg
     */
    void onApprovalFail(String msg);
}

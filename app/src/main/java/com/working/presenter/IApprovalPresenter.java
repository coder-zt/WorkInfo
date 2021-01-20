package com.working.presenter;

import com.working.domain.ApprovalOutBean;
import com.working.domain.ApprovalBean;

/**
 * 上传/更新记录的接口（所有）
 */
public interface IApprovalPresenter extends ICommitPresenter {

    /**
     * 上传/更新：相比于上传要多传一个Id
     * @param uploadData
     */
    void approvalData(ApprovalBean uploadData);

    /**
     * 上传/更新：相比于上传要多传一个Id
     * @param uploadData
     */
    void approvalOutData(ApprovalOutBean uploadData);

    /**
     * 上传/更新：相比于上传要多传一个Id
     * @param uploadData
     */
    void createOrder(ApprovalBean uploadData);
}

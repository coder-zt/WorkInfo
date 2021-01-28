package com.working.domain;

/**
 * 提交数据的接口
 */
public interface ICommitData {

    /**
     * 设置数据状态
     * @param status
     */
    void setStatus(int status);

    /**
     * 获取数据的提交状态
     * @return
     */
    int getStatus();
}

package com.working.presenter;

import com.working.base.IBaseCallback;
import com.working.base.IBasePresenter;

public interface IRepairInfo extends IBasePresenter {

    /**
     * 获取维修方法
     * @param damageType
     */
    void getRepairMethod(int damageType);

    /**
     * 获取维修内容
     * @param damageType
     * @param methodId
     */
    void getRepairContent(int damageType, int methodId);
}

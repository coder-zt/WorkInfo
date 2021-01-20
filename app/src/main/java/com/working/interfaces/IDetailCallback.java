package com.working.interfaces;

import com.working.base.IBaseCallback;

import java.util.List;

public interface IDetailCallback<T> extends IBaseCallback {

    /**
     * 获取单个Item的详细数据
     * @param data
     */
    void onDetailDataLoaded( T data);

    /**
     * 获取单个Item的详细数据失败
     */
    void onDetailDataLoadedFail();
}

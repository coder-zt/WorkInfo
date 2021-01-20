package com.working.interfaces;

import com.working.base.IBasePresenter;

public interface ZTIDetailPresenter extends IBasePresenter {

    /**
     * 获取item的详细数据
     */
    void loadDetailInfo(String id);
}

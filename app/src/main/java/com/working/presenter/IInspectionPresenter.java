package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.interfaces.IInspectCallback;

/**
 * 巡检记录页面的Presenter接口
 */
public interface IInspectionPresenter extends IBasePresenter {

    void loadMoreData(String startTime, String endTime);

    void loadChooseData(String startTime, String endTime);
}

package com.working.presenter;

import com.working.base.IBasePresenter;

public interface IStatPresenter extends IBasePresenter {

    public void getStatInfo(String deptId, String startTime, String endTime);
}

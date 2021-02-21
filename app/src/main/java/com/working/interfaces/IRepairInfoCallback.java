package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.RepairContent;
import com.working.domain.RepairMethod;

import java.util.List;

public interface IRepairInfoCallback extends IBaseCallback {

    void onRepairMethodLoaded(List<RepairMethod.DataBean> data);

    void onRepairMethodLoadedFail(String msg);

    void onRepairContentLoaded(List<RepairContent.DataBean> data);

    void onRepairContentLoadedFail(String msg);
}

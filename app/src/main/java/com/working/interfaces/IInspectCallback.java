package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.InspectionList;
import com.working.domain.RecordsBean;

import java.util.List;

public interface IInspectCallback extends IBaseCallback {

    void onInspectInfoLoaded(List<InspectionList.DataBean.RecordsBean> data);

    void onInspectInfoLoadedError();

    void onInspectInfoMoreLoaded(List<InspectionList.DataBean.RecordsBean> data);

    void onInspectInfoMoreLoadedError();
}

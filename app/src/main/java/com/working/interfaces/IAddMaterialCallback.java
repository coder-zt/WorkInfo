package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.IndexNotice;
import com.working.domain.MaterialListData;

import java.util.List;

public interface IAddMaterialCallback extends IBaseCallback {

    void onMaterialListLoaded(List<MaterialListData.DataBean> data);

    void onLoadFail(String msg);
}

package com.working.interfaces;

import com.working.base.IBaseCallback;
import com.working.domain.MaterialList;
import com.working.domain.MaterialListData;

import java.util.List;

public interface IMaterialListCallback extends IBaseCallback {

    void onMaterialListLoaded(List<MaterialList.DataBean> data);

    void onLoadFail(String msg);
}

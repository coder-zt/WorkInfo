package com.working.presenter;

import com.working.base.IBasePresenter;
import com.working.interfaces.IAddMaterialCallback;
import com.working.interfaces.IIndexInfoCallback;

/**
 * 获取新增下拉物料的Presenter接口
 */
public interface IAddMaterialPresenter extends IBasePresenter {

    void getMaterialList();
}

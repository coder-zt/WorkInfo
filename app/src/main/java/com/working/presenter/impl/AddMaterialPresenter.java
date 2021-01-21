package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.MaterialListData;
import com.working.interfaces.IAddMaterialCallback;
import com.working.models.AppModels;
import com.working.presenter.IAddMaterialPresenter;

import java.util.List;

public class AddMaterialPresenter extends BasePresenterImpl implements IAddMaterialPresenter {

    @Override
    public void getMaterialList() {
        AppModels.getInstance().getPullDownMaterialList(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                MaterialListData data = null;
                if (msg.obj instanceof MaterialListData) {
                    data = (MaterialListData) msg.obj;
                }
                if (msg.what == -1 || data == null || data.getData() == null) {
                    if (mCallback != null) {
                        ((IAddMaterialCallback)mCallback).onLoadFail("新增物料数据加载失败");
                    }
                    return true;
                }
                List<MaterialListData.DataBean> records = data.getData();
                if (records == null) {
                    if (mCallback != null) {
                        ((IAddMaterialCallback) mCallback).onLoadFail("新增物料数据加载失败");

                    }
                    return true;
                }
                if (mCallback != null) {
                    ((IAddMaterialCallback) mCallback).onMaterialListLoaded(records);
                }
                return true;
            }
        });
    }
}

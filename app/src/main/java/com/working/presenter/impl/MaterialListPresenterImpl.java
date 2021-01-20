package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.MaterialList;
import com.working.interfaces.IMaterialListCallback;
import com.working.models.AppModels;
import com.working.presenter.IMaterialListPresenter;

public class MaterialListPresenterImpl extends BasePresenterImpl implements IMaterialListPresenter {
    @Override
    public void getMaterialList() {
        AppModels.getInstance().getMaterialList(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof MaterialList) {
                    MaterialList data = (MaterialList)msg.obj;
                    if (data.getCode() == 200) {
                        ((IMaterialListCallback)mCallback).onMaterialListLoaded(data.getData());
                    }
                }else{
                    ((IMaterialListCallback)mCallback).onLoadFail("加载物料列表失败");
                }
                return true;
            }
        });
    }
}

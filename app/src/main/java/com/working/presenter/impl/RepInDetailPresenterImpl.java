package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.OrderDetail;
import com.working.domain.RepInInfoData;
import com.working.interfaces.IDetailCallback;
import com.working.models.AppModels;
import com.working.presenter.IDetailPresenter;

/**
 * 入库详细数据的presenter
 */
public class RepInDetailPresenterImpl extends BasePresenterImpl implements IDetailPresenter {
    @Override
    public void getDetailData(String id) {
        AppModels.getInstance().getRepertoryInDetail(id, new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof RepInInfoData) {
                    RepInInfoData data = (RepInInfoData) msg.obj;
                    if (mCallback != null) {
                        ((IDetailCallback<RepInInfoData.DataBean>) mCallback)
                                .onDetailDataLoaded(data.getData());
                    }
                } else {
                    if (mCallback != null) {
                        ((IDetailCallback<RepInInfoData.DataBean>) mCallback).onDetailDataLoadedFail();
                    }
                }
                return true;
            }
        });
    }
}
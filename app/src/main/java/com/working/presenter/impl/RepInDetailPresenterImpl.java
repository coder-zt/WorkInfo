package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.InStockDetail;
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
                if (msg.obj instanceof InStockDetail) {
                    InStockDetail data = (InStockDetail) msg.obj;
                    if (mCallback != null) {
                        ((IDetailCallback<InStockDetail.DataBean>) mCallback)
                                .onDetailDataLoaded(data.getData());
                    }
                } else {
                    if (mCallback != null) {
                        ((IDetailCallback<InStockDetail.DataBean>) mCallback).onDetailDataLoadedFail();
                    }
                }
                return true;
            }
        });
    }
}
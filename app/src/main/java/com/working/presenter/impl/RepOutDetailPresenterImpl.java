package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.OutStockDetail;
import com.working.interfaces.IDetailCallback;
import com.working.models.AppModels;
import com.working.presenter.IDetailPresenter;

public class RepOutDetailPresenterImpl extends BasePresenterImpl implements IDetailPresenter {
    @Override
    public void getDetailData(String id) {
            AppModels.getInstance().getRepOutInfo(id,  new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    if (msg.obj instanceof OutStockDetail) {
                        OutStockDetail data = (OutStockDetail) msg.obj;
                        if (mCallback != null) {
                            ((IDetailCallback<OutStockDetail.DataBean>) mCallback)
                                    .onDetailDataLoaded(data.getData());
                        }
                    } else {
                        if (mCallback != null) {
                            ((IDetailCallback<OutStockDetail.DataBean>) mCallback).onDetailDataLoadedFail();
                        }
                    }
                    return true;
                }
            });
    }
}

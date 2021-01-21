package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.PurchaseDetail;
import com.working.interfaces.IDetailCallback;
import com.working.models.AppModels;
import com.working.presenter.IDetailPresenter;

/**
 * 采购单的详细信息
 */
public class DetailPurchasePresenterImpl extends BasePresenterImpl implements IDetailPresenter {

    @Override
    public void getDetailData(String id) {
        AppModels.getInstance().getPurchaseDetail(id, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(msg.obj instanceof PurchaseDetail){
                    PurchaseDetail data = (PurchaseDetail)msg.obj;
                    if (mCallback != null) {
                        ((IDetailCallback<PurchaseDetail.DataBean>)mCallback)
                                .onDetailDataLoaded(data.getData());
                    }
                }else{
                    if (mCallback != null) {
                        ((IDetailCallback<PurchaseDetail.DataBean>) mCallback)
                                .onDetailDataLoadedFail();
                    }
                }
                return true;
            }
        });
    }
}
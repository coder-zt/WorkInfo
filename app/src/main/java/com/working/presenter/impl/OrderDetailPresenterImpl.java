package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.OrderDetail;
import com.working.interfaces.IDetailCallback;
import com.working.models.AppModels;
import com.working.presenter.IDetailPresenter;

/**
 * 订单详细数据的presenter
 */
public class OrderDetailPresenterImpl extends BasePresenterImpl implements IDetailPresenter {
    @Override
    public void getDetailData(String id) {
        AppModels.getInstance().getOrderDetail(id, new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof OrderDetail) {
                    OrderDetail data = (OrderDetail) msg.obj;
                    if (data != null && data.getCode() == 200) {
                        ((IDetailCallback<OrderDetail.DataBean>) mCallback)
                                .onDetailDataLoaded(data.getData());
                    }
                } else {
                    ((IDetailCallback<OrderDetail.DataBean>) mCallback).onDetailDataLoadedFail();
                }
                return true;
            }
        });
    }
}
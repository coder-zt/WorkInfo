package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepOutInfoBean;
import com.working.interfaces.IDetailCallback;
import com.working.models.AppModels;
import com.working.presenter.IDetailPresenter;

public class RepOutDetailPresenterImpl extends BasePresenterImpl implements IDetailPresenter {
    @Override
    public void getDetailData(String id) {
            AppModels.getInstance().getRepOutInfo(id,  new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    if (msg.obj instanceof RepOutInfoBean) {
                        RepOutInfoBean data = (RepOutInfoBean) msg.obj;
                        if (mCallback != null) {
                            ((IDetailCallback<RepOutInfoBean.DataBean>) mCallback)
                                    .onDetailDataLoaded(data.getData());
                        }
                    } else {
                        if (mCallback != null) {
                            ((IDetailCallback<RepOutInfoBean.DataBean>) mCallback).onDetailDataLoadedFail();
                        }
                    }
                    return true;
                }
            });
    }
}

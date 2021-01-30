package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.base.IBaseCallback;
import com.working.domain.ApprovalRecord;
import com.working.domain.StatBean;
import com.working.interfaces.IStatCallback;
import com.working.interfaces.ZTIListCallback;
import com.working.models.AppModels;
import com.working.presenter.IStatPresenter;

import java.util.List;

public class StatPresenterImpl extends BasePresenterImpl implements IStatPresenter {
    @Override
    public void getStatInfo(String deptId, String startTime, String endTime) {
        AppModels.getInstance().getStatInfo(deptId, startTime, endTime, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    if (msg.obj instanceof StatBean) {
                        StatBean bean = (StatBean) msg.obj;
                        ((IStatCallback) mCallback).onStatDataLoaded(bean);
                        return true;
                    }
                }
                if (mCallback != null) {
                    ((IStatCallback) mCallback).onStatDataLoadFailed("加载失败");
                }
                return true;
            }
        });
    }
}

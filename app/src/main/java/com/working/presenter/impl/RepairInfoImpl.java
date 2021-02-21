package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepairContent;
import com.working.domain.RepairMethod;
import com.working.interfaces.IRepairInfoCallback;
import com.working.models.AppModels;
import com.working.presenter.IRepairInfo;

public class RepairInfoImpl extends BasePresenterImpl implements IRepairInfo {
    @Override
    public void getRepairMethod(int damageType) {
        AppModels.getInstance().getRepairMethod(damageType, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof RepairMethod) {
                    RepairMethod data = (RepairMethod)msg.obj;
                    if (mCallback != null) {
                        ((IRepairInfoCallback)mCallback)
                                .onRepairMethodLoaded(data.getData());
                    }
                }else{
                    if (mCallback != null) {
                        ((IRepairInfoCallback)mCallback)
                                .onRepairMethodLoadedFail((String)msg.obj);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void getRepairContent(int damageType, int methodId) {
        AppModels.getInstance().getRepairContent(damageType, methodId, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof RepairContent) {
                    RepairContent data = (RepairContent)msg.obj;
                    if (mCallback != null) {
                        ((IRepairInfoCallback)mCallback)
                                .onRepairContentLoaded(data.getData());
                    }
                }else{
                    if (mCallback != null) {
                        ((IRepairInfoCallback)mCallback)
                                .onRepairContentLoadedFail((String)msg.obj);
                    }
                }
                return true;
            }
        });
    }
}

package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.ApprovalOutBean;
import com.working.domain.ApprovalBean;
import com.working.interfaces.ICommitCallback;
import com.working.models.AppModels;
import com.working.presenter.IApprovalPresenter;

public class ApprovalPresenterImpl extends BasePresenterImpl implements IApprovalPresenter {
    @Override
    public void approvalData(ApprovalBean approvalData) {
        AppModels.getInstance().approvalPurchase(approvalData, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    ((ICommitCallback) mCallback).onCommitFinished();
                } else {
                    String info = (String) msg.obj;
                    if (mCallback != null) {
                        ((ICommitCallback) mCallback).onCommitFail(info);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void approvalOutData(ApprovalOutBean approvalData) {
        AppModels.getInstance().approvalOut(approvalData, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1 ) {
                    if(mCallback != null){
                        ((ICommitCallback) mCallback).onCommitFinished();
                    }
                } else {
                    String info = (String) msg.obj;
                    if(mCallback != null) {
                        ((ICommitCallback) mCallback).onCommitFail(info);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void createOrder(ApprovalBean uploadData) {
        AppModels.getInstance().createOrder(uploadData, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    if(mCallback != null){
                        ((ICommitCallback) mCallback).onCommitFinished();
                    }
                } else {
                    String info = (String) msg.obj;
                    if(mCallback != null) {
                        ((ICommitCallback) mCallback).onCommitFail(info);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void uploadData(Object uploadData) {

    }
}

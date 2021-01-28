package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.ChangePsdBean;
import com.working.interfaces.ICommitCallback;
import com.working.models.AppModels;
import com.working.presenter.ICommitPresenter;

public class ChangePsdPresenterImpl extends BasePresenterImpl implements ICommitPresenter<ChangePsdBean> {
    @Override
    public void uploadData(ChangePsdBean data) {
        AppModels.getInstance().changePassword(data, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(mCallback == null){
                    return true;
                }
                if (msg.what != -1) {
                    ((ICommitCallback)mCallback).onCommitFinished();
                }else{
                    String info = (String)msg.obj;
                    ((ICommitCallback)mCallback).onCommitFail(info);
                }
                return true;
            }
        });
    }
}

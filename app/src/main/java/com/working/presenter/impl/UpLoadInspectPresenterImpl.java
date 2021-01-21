package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.InspectionFormData;
import com.working.interfaces.ICommitCallback;
import com.working.models.AppModels;
import com.working.other.MessageEvent;
import com.working.presenter.ICommitPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * 上传/更新巡检记录信息
 */
public class UpLoadInspectPresenterImpl extends BasePresenterImpl implements ICommitPresenter<InspectionFormData> {

    /**
     * 上传/更新：相比于上传要多传一个Id
     *
     * @param information
     */
    @Override
    public void uploadData(InspectionFormData information) {
        AppModels.getInstance().uploadInspection(information, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(mCallback == null){
                    return true;
                }
                if (msg.what != -1) {
                    ((ICommitCallback)mCallback).onCommitFinished();
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.upload_data_success));
                }else{
                    String info = (String)msg.obj;
                    ((ICommitCallback)mCallback).onCommitFail(info);
                }
                return true;
            }
        });
    }


}

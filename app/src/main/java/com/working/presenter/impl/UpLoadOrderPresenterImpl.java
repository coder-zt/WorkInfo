package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.OrderDetail;
import com.working.interfaces.ICommitCallback;
import com.working.models.AppModels;
import com.working.presenter.ICommitPresenter;

/**
 * 上传/更新巡检记录信息
 */
public class UpLoadOrderPresenterImpl extends BasePresenterImpl implements ICommitPresenter<OrderDetail.DataBean> {

    /**
     * 上传/更新：相比于上传要多传一个Id
     *
     * @param information
     */
    @Override
    public void uploadData(OrderDetail.DataBean information) {
        AppModels.getInstance().uploadOrder(information, new Handler.Callback() {
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

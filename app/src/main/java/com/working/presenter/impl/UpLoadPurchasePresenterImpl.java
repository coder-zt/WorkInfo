package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.PurchaseDetail;
import com.working.interfaces.ICommitCallback;
import com.working.models.AppModels;
import com.working.presenter.ICommitPresenter;

/**
 * 上传/更新采购清单
 */
public class UpLoadPurchasePresenterImpl extends BasePresenterImpl implements ICommitPresenter<PurchaseDetail.DataBean> {

    /**
     * 上传/更新：相比于上传要多传一个Id
     *
     * @param information
     */
    @Override
    public void uploadData(PurchaseDetail.DataBean information) {
        AppModels.getInstance().uploadPurchase(information, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
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

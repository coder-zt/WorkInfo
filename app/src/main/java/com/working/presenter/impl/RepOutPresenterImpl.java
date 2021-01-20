package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepOut;
import com.working.domain.RepOutInfoBean;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

public class RepOutPresenterImpl  extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepOutList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof RepOut) {
                            RepOut data = (RepOut)msg.obj;
                            if (data != null && data.getCode() == 200) {
                                ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback)
                                        .onListLoaded(data.getData().getRecords(), isCommit);
                                return true;
                            }
                        }
                        ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback).onListLoadedFail(isCommit);
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepOutList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof RepOut){
                            RepOut data = (RepOut)msg.obj;
                            if (data.getCode() == 200) {
                                ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(data.getData().getRecords(), isCommit);
                                return true;
                            }
                        }
                        ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback).onListLoadedMoreFail(isCommit);
                        return true;
                    }
                });
    }

//    @Override
//    public void loadDetailInfo(final String id) {
//        AppModels.getInstance().getRepOutInfo(id, new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                if (msg.obj instanceof RepOutInfoBean) {
//                    RepOutInfoBean data = (RepOutInfoBean)msg.obj;
//                    if (data.getCode() == 200) {
//                        ((ZTIListCallback<RepOut.DataBean.RecordsBean,
//                                RepOutInfoBean.DataBean>)mCallback)
//                                .onDetailDataLoaded(id, data.getData());
//                    }
//                    return true;
//                }
//                ((ZTIListCallback<RepOut,
//                        RepOutInfoBean.DataBean>)mCallback).onDetailDataLoadedFail();
//                return true;
//            }
//        });
//    }
}

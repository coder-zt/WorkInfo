package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepBalData;
import com.working.domain.RepBalInfoData;
import com.working.domain.Response;
import com.working.interfaces.ICommitCallback;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

public class RepBalPresenterImpl extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepBalList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof RepBalData) {
                            RepBalData data = (RepBalData) msg.obj;
                            if (data != null && data.getCode() == 200) {
                                ((ZTIListCallback<RepBalData.DataBean.RecordsBean>) mCallback)
                                        .onListLoaded(data.getData().getRecords(), isCommit);
                                return true;
                            }
                        }
                        ((ZTIListCallback<RepBalData>)mCallback).onListLoadedFail(isCommit);
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepBalList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof RepBalData){
                            RepBalData data = (RepBalData)msg.obj;
                            if (data.getCode() == 200) {
                                ((ZTIListCallback<RepBalData.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(data.getData().getRecords(), isCommit);
                                return true;
                            }
                        }
                        ((ZTIListCallback<RepBalData.DataBean.RecordsBean>)mCallback).onListLoadedMoreFail(isCommit);
                        return true;
                    }
                });
    }

//    @Override
//    public void loadDetailInfo(final String id) {
//        AppModels.getInstance().getRepBalInfo(id, new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                if(msg.obj instanceof  RepBalInfoData){
//                    RepBalInfoData data = (RepBalInfoData)msg.obj;
//                    if (data.getCode() == 200) {
//                        ((ZTIListCallback<RepBalData.DataBean.RecordsBean,
//                                RepBalInfoData.DataBean>)mCallback)
//                                .onDetailDataLoaded(id, data.getData());
//                    }
//                } else{
//                    ((ZTIListCallback<RepBalData,
//                            RepBalInfoData.DataBean>)mCallback).onDetailDataLoadedFail();
//                }
//                return true;
//            }
//        });
//    }

    public void uploadRepBal(RepBalData.DataBean.RecordsBean information){
        AppModels.getInstance().uploadRepBal(information, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(msg.obj instanceof Response){
                    Response response = (Response)msg.obj;
                    if (response.getCode() == 200) {
                        ((ICommitCallback)mCallback).onCommitFinished();
                    }else{
                        ((ICommitCallback)mCallback).onCommitFail(response.getMsg());
                    }
                }

                return true;
            }
        });
    }
}

package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepertoryIn;
import com.working.domain.RepInInfoData;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

/**
 * 入库Presenter
 */
public class RePInPresenterImpl extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepertoryInList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            RepertoryIn data = (RepertoryIn)msg.obj;
            if (data != null && data.getCode() == 200) {
                ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                        .onListLoaded(data.getData().getRecords(), isCommit);
            }else{
                ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                        .onListLoadedFail(isCommit);
            }
            return true;
            }
        });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepertoryInList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    RepertoryIn data = (RepertoryIn)msg.obj;
                    if (data.getCode() == 200) {
                        ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                                .onListLoadedMore(data.getData().getRecords(), isCommit);
                    }else{
                        ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                                .onListLoadedMoreFail(isCommit);
                    }
                    return true;
                }
            });
    }

//    @Override
//    public void loadDetailInfo(final String id) {
//        AppModels.getInstance().getRepertoryInDetail(id, new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                RepInInfoData data = (RepInInfoData)msg.obj;
//                if (data.getCode() == 200) {
//                    if (mCallback != null) {
//                        ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean,
//                                RepInInfoData.DataBean>)mCallback)
//                                .onDetailDataLoaded(id, data.getData());
//                    }
//
//                }else{
//                    if (mCallback != null) {
//                        ((ZTIListCallback<RepertoryIn,
//                                RepInInfoData.DataBean>)mCallback).onDetailDataLoadedFail();
//                    }
//                }
//                return true;
//            }
//        });
//    }
}

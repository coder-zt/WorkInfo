package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.ApprovalRecord;
import com.working.domain.InspectionList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.List;

public class ApprovalRecordPresenterImpl extends BasePresenterImpl<ApprovalRecord.DataBean.RecordsBean> implements ZTIListPresenter {


    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getApprovalRecord(getPage(isCommit, false),
                pageSize, startTime, endTime, new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    ApprovalRecord data = (ApprovalRecord)msg.obj;
                    if (data != null) {
                        ApprovalRecord.DataBean dataBean = data.getData();
                        List<ApprovalRecord.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((ZTIListCallback<ApprovalRecord.DataBean.RecordsBean>) mCallback).onListLoaded(reversData(recordsBeans), true);
                        }
                        return false;
                    }
                }
                if (mCallback != null) {
                    setPageOnError(true);
                    ((ZTIListCallback<ApprovalRecord.DataBean.RecordsBean>) mCallback).onListLoadedFail(true);
                }
                return false;
            }
        });
    }

    @Override
    public void  loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getInspectionList(getPage(isCommit, true), pageSize,
                startTime, endTime, new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    ApprovalRecord data = (ApprovalRecord)msg.obj;
                    if (data != null) {
                        ApprovalRecord.DataBean dataBean = data.getData();
                        List<ApprovalRecord.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((ZTIListCallback<ApprovalRecord.DataBean.RecordsBean>) mCallback).onListLoadedMore(reversData(recordsBeans),true);
                        }
                        return true;
                    }
                }
                if(mCallback != null){
                    setPageOnError(true);
                    ((ZTIListCallback<ApprovalRecord.DataBean.RecordsBean>) mCallback).onListLoadedMoreFail(true);
                }
                return false;
            }
        });
    }

}

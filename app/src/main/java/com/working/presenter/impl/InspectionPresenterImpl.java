package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.working.base.BasePresenterImpl;
import com.working.domain.InspectionList;
import com.working.domain.Purchase;
import com.working.domain.RecordsBean;
import com.working.interfaces.IInspectCallback;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppApi;
import com.working.models.AppModels;
import com.working.presenter.IInspectionPresenter;

import java.util.Comparator;
import java.util.List;

import okhttp3.RequestBody;

public class InspectionPresenterImpl extends BasePresenterImpl<InspectionList.DataBean.RecordsBean> implements ZTIListPresenter {


    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getInspectionList(getPage(isCommit, false),
                pageSize, startTime, endTime, new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((ZTIListCallback<InspectionList.DataBean.RecordsBean>) mCallback).onListLoaded(reversData(recordsBeans), true);
                        }
                        return false;
                    }
                }
                if (mCallback != null) {
                    setPageOnError(true);
                    ((ZTIListCallback<InspectionList.DataBean.RecordsBean>) mCallback).onListLoadedFail(true);
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
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((ZTIListCallback<InspectionList.DataBean.RecordsBean>) mCallback).onListLoadedMore(reversData(recordsBeans),true);
                        }
                        return true;
                    }
                }
                if(mCallback != null){
                    setPageOnError(true);
                    ((ZTIListCallback<InspectionList.DataBean.RecordsBean>) mCallback).onListLoadedMoreFail(true);
                }
                return false;
            }
        });
    }

}

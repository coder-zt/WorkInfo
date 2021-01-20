package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.working.base.BasePresenterImpl;
import com.working.domain.InspectionList;
import com.working.domain.RecordsBean;
import com.working.interfaces.IInspectCallback;
import com.working.models.AppApi;
import com.working.models.AppModels;
import com.working.presenter.IInspectionPresenter;

import java.util.List;

import okhttp3.RequestBody;

public class InspectionPresenterImpl extends BasePresenterImpl implements IInspectionPresenter {

    private final int pageSize = 20;
    private int page = 1;

    @Override
    public void loadData() {
        page = 1;
        AppModels.getInstance().getInspectionList(page, pageSize, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        ((IInspectCallback)mCallback).onInspectInfoLoaded(recordsBeans);
                        return false;
                    }
                }
                ((IInspectCallback)mCallback).onInspectInfoLoadedError();
                return false;
            }
        });
    }

    @Override
    public void loadMoreData(String startTime, String endTime) {
        page++;
        AppModels.getInstance().getInspectionList(page, pageSize, startTime, endTime, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        ((IInspectCallback)mCallback).onInspectInfoMoreLoaded(recordsBeans);
                        return false;
                    }
                }
                ((IInspectCallback)mCallback).onInspectInfoMoreLoadedError();
                return false;
            }
        });
    }

    @Override
    public void loadChooseData(String startTime, String endTime) {
        page = 1;
        AppModels.getInstance().getInspectionList(page, pageSize, startTime, endTime, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        ((IInspectCallback)mCallback).onInspectInfoLoaded(recordsBeans);
                        return false;
                    }
                }
                ((IInspectCallback)mCallback).onInspectInfoMoreLoadedError();
                return false;
            }
        });
    }

}

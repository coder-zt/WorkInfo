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
import com.working.models.AppApi;
import com.working.models.AppModels;
import com.working.presenter.IInspectionPresenter;

import java.util.Comparator;
import java.util.List;

import okhttp3.RequestBody;

public class InspectionPresenterImpl extends BasePresenterImpl implements IInspectionPresenter {

    private final int pageSize = 20;
    private int page = 1;

    @Override
    public void loadMoreData(String startTime, String endTime) {
        page++;
        AppModels.getInstance().getInspectionList(page, pageSize, startTime, endTime, new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((IInspectCallback) mCallback).onInspectInfoMoreLoaded(reversData(recordsBeans));
                        }
                        return false;
                    }
                }
                if (mCallback != null) {
                    ((IInspectCallback) mCallback).onInspectInfoMoreLoadedError();
                }
                return false;
            }
        });
    }

    @Override
    public void loadChooseData(String startTime, String endTime) {
        page = 1;
        AppModels.getInstance().getInspectionList(page, pageSize, startTime, endTime, new Handler.Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what != -1) {
                    InspectionList data = (InspectionList)msg.obj;
                    if (data != null) {
                        InspectionList.DataBean dataBean = data.getData();
                        List<InspectionList.DataBean.RecordsBean> recordsBeans = dataBean.getRecords();
                        if (mCallback != null) {
                            ((IInspectCallback)mCallback).onInspectInfoLoaded(reversData(recordsBeans));
                        }
                        return true;
                    }
                }
                if(mCallback != null){
                    ((IInspectCallback)mCallback).onInspectInfoMoreLoadedError();
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<InspectionList.DataBean.RecordsBean> reversData(List<InspectionList.DataBean.RecordsBean> records) {
        records.sort(new Comparator<InspectionList.DataBean.RecordsBean>() {
            @Override
            public int compare(InspectionList.DataBean.RecordsBean o1, InspectionList.DataBean.RecordsBean o2) {
                String updateTime1 = o1.getUpdateTime();
                String updateTime2 = o2.getUpdateTime();
                return updateTime1.compareTo(updateTime2);
            }
        });
        return records;
    }

}

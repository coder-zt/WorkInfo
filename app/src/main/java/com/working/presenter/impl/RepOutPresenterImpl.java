package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.OutStockList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.Comparator;
import java.util.List;

public class RepOutPresenterImpl  extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepOutList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof OutStockList) {
                            OutStockList data = (OutStockList)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<OutStockList.DataBean.RecordsBean>)mCallback)
                                        .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            ((ZTIListCallback<OutStockList.DataBean.RecordsBean>) mCallback).onListLoadedFail(isCommit);
                        }
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepOutList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof OutStockList){
                            OutStockList data = (OutStockList)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<OutStockList.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            ((ZTIListCallback<OutStockList.DataBean.RecordsBean>) mCallback).onListLoadedMoreFail(isCommit);
                        }
                        return true;
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<OutStockList.DataBean.RecordsBean> reversData(List<OutStockList.DataBean.RecordsBean> records) {
        records.sort(new Comparator<OutStockList.DataBean.RecordsBean>() {
            @Override
            public int compare(OutStockList.DataBean.RecordsBean o1, OutStockList.DataBean.RecordsBean o2) {
                String updateTime1 = o1.getUpdateTime();
                String updateTime2 = o2.getUpdateTime();
                return updateTime1.compareTo(updateTime2) * -1;
            }
        });
        return records;
    }



}

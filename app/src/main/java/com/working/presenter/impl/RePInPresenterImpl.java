package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepertoryIn;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.Comparator;
import java.util.List;

/**
 * 入库Presenter
 */
public class RePInPresenterImpl extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepertoryInList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean handleMessage(@NonNull Message msg) {
                if(msg.obj instanceof RepertoryIn ){
                    RepertoryIn data = (RepertoryIn)msg.obj;
                    if (mCallback != null) {
                        ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                                .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                    }
                } else{
                    if (mCallback != null) {
                        ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>) mCallback)
                                .onListLoadedFail(isCommit);
                    }
                }
            return true;
            }
        });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepertoryInList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    if(msg.obj instanceof RepertoryIn ){
                        RepertoryIn data = (RepertoryIn)msg.obj;
                        if (mCallback != null) {
                            ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>)mCallback)
                                .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                        }
                    } else{
                        if (mCallback != null) {
                            ((ZTIListCallback<RepertoryIn.DataBean.RecordsBean>) mCallback)
                                    .onListLoadedMoreFail(isCommit);
                        }
                    }
                    return true;
                }
            });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<RepertoryIn.DataBean.RecordsBean> reversData(List<RepertoryIn.DataBean.RecordsBean> records) {
        records.sort(new Comparator<RepertoryIn.DataBean.RecordsBean>() {
            @Override
            public int compare(RepertoryIn.DataBean.RecordsBean o1, RepertoryIn.DataBean.RecordsBean o2) {
                String updateTime1 = o1.getUpdateTime();
                String updateTime2 = o2.getUpdateTime();
                return updateTime1.compareTo(updateTime2);
            }
        });
        return records;
    }

}

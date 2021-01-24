package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.InStockList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.Comparator;
import java.util.List;

/**
 * 入库Presenter
 */
public class InStockPresenterImpl extends BasePresenterImpl<InStockList.DataBean.RecordsBean> implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepertoryInList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public boolean handleMessage(@NonNull Message msg) {
                if(msg.obj instanceof InStockList){
                    InStockList data = (InStockList)msg.obj;
                    if (mCallback != null) {
                        ((ZTIListCallback<InStockList.DataBean.RecordsBean>)mCallback)
                                .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                    }
                } else{
                    if (mCallback != null) {
                        setPageOnError(isCommit);
                        ((ZTIListCallback<InStockList.DataBean.RecordsBean>) mCallback)
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
                    if(msg.obj instanceof InStockList){
                        InStockList data = (InStockList)msg.obj;
                        if (mCallback != null) {
                            ((ZTIListCallback<InStockList.DataBean.RecordsBean>)mCallback)
                                .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                        }
                    } else{
                        if (mCallback != null) {
                            setPageOnError(isCommit);
                            ((ZTIListCallback<InStockList.DataBean.RecordsBean>) mCallback)
                                    .onListLoadedMoreFail(isCommit);
                        }
                    }
                    return true;
                }
            });
    }

}

package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepOut;
import com.working.domain.RepOutInfoBean;
import com.working.domain.RepertoryIn;
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
                        if (msg.obj instanceof RepOut) {
                            RepOut data = (RepOut)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback)
                                        .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            ((ZTIListCallback<RepOut.DataBean.RecordsBean>) mCallback).onListLoadedFail(isCommit);
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
                        if(msg.obj instanceof RepOut){
                            RepOut data = (RepOut)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<RepOut.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            ((ZTIListCallback<RepOut.DataBean.RecordsBean>) mCallback).onListLoadedMoreFail(isCommit);
                        }
                        return true;
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<RepOut.DataBean.RecordsBean> reversData(List<RepOut.DataBean.RecordsBean> records) {
        records.sort(new Comparator<RepOut.DataBean.RecordsBean>() {
            @Override
            public int compare(RepOut.DataBean.RecordsBean o1, RepOut.DataBean.RecordsBean o2) {
                String updateTime1 = o1.getUpdateTime();
                String updateTime2 = o2.getUpdateTime();
                return updateTime1.compareTo(updateTime2) * -1;
            }
        });
        return records;
    }



}

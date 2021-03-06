package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.RepBalData;
import com.working.interfaces.ICommitCallback;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.Comparator;
import java.util.List;

public class MaterialPresenterImpl extends BasePresenterImpl<RepBalData.DataBean.RecordsBean> implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepBalList(getPage(isCommit, false), pageSize,
                startTime, endTime, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof RepBalData) {
                            RepBalData data = (RepBalData) msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<RepBalData.DataBean.RecordsBean>) mCallback)
                                        .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            setPageOnError(isCommit);
                            ((ZTIListCallback<RepBalData>) mCallback).onListLoadedFail(isCommit);
                        }
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getRepBalList(getPage(isCommit, true), pageSize,
                startTime, endTime, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if(msg.obj instanceof RepBalData){
                            RepBalData data = (RepBalData)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<RepBalData.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                                return true;
                            }
                        }
                        if (mCallback != null) {
                            setPageOnError(isCommit);
                            ((ZTIListCallback<RepBalData.DataBean.RecordsBean>) mCallback).onListLoadedMoreFail(isCommit);
                        }
                        return true;
                    }
                });
    }


    public void uploadRepBal(RepBalData.DataBean.RecordsBean information){
        AppModels.getInstance().uploadRepBal(information, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (mCallback != null) {
                    if (msg.what != -1) {
                        ((ICommitCallback)mCallback).onCommitFinished();
                    }else{
                        ((ICommitCallback)mCallback).onCommitFail((String)msg.obj);
                    }
                }
                return true;
            }
        });
    }


}

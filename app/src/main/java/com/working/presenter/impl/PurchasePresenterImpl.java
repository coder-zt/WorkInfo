package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.domain.Purchase;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

/**
 * 采购清单的Presenter
 */
public class PurchasePresenterImpl extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getPurchaseList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof Purchase) {
                            Purchase data = (Purchase)msg.obj;
                            if (data.getCode() == 200) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                        .onListLoaded(data.getData().getRecords(), isCommit);
                            }
                        }else{
                            ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                    .onListLoadedFail(isCommit);
                        }
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getPurchaseList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof Purchase) {
                            Purchase data = (Purchase)msg.obj;
                            if (data.getCode() == 200) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(data.getData().getRecords(), isCommit);
                            }

                        }else{
                            ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                    .onListLoadedMoreFail(isCommit);
                        }
                        return true;
                    }
                });
    }
}

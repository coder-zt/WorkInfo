package com.working.presenter.impl;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.working.base.BasePresenterImpl;
import com.working.domain.Purchase;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.models.AppModels;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 采购清单的Presenter
 */
public class PurchasePresenterImpl extends BasePresenterImpl implements ZTIListPresenter {

    @Override
    public void loadListData(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getPurchaseList(getPage(isCommit, false), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof Purchase) {
                            Purchase data = (Purchase)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                        .onListLoaded(reversData(data.getData().getRecords()), isCommit);
                            }
                        }else{
                            if (mCallback != null) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>) mCallback)
                                        .onListLoadedFail(isCommit);
                            }
                        }
                        return true;
                    }
                });
    }

    @Override
    public void loadListDataMore(final boolean isCommit, String startTime, String endTime) {
        AppModels.getInstance().getPurchaseList(getPage(isCommit, true), pageSize,
                startTime, endTime, isCommit ? 1 : 0, new Handler.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if (msg.obj instanceof Purchase) {
                            Purchase data = (Purchase)msg.obj;
                            if (mCallback != null) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>)mCallback)
                                        .onListLoadedMore(reversData(data.getData().getRecords()), isCommit);
                            }

                        }else{
                            setPageOnError(isCommit);
                            if (mCallback != null) {
                                ((ZTIListCallback<Purchase.DataBean.RecordsBean>) mCallback)
                                        .onListLoadedMoreFail(isCommit);
                            }
                        }
                        return true;
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Purchase.DataBean.RecordsBean> reversData(List<Purchase.DataBean.RecordsBean> records) {
        records.sort(new Comparator<Purchase.DataBean.RecordsBean>() {
            @Override
            public int compare(Purchase.DataBean.RecordsBean o1, Purchase.DataBean.RecordsBean o2) {
                String updateTime1 = o1.getUpdateTime();
                String updateTime2 = o2.getUpdateTime();
                return updateTime1.compareTo(updateTime2) * -1;
            }
        });
        return records;
    }
}

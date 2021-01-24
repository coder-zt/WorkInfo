package com.working.base;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.working.domain.InspectionList;

import java.util.Comparator;
import java.util.List;

/**
 * presenter基类
 */
public class BasePresenterImpl<T extends IOrderInfo> implements IBasePresenter{

    protected IBaseCallback mCallback;
    protected int page_1 = 1;//上报页数
    protected int page_0 = 1;//未上报页数
    protected final int pageSize = 20;//当前页数

    @Override
    public void registerCallback(IBaseCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback() {
        mCallback = null;
    }


    /**
     * 获取加载数据的页数
     * @param isCommit
     * @param isLoadMore
     * @return
     */
    protected int getPage(boolean isCommit, boolean isLoadMore) {
        if (isLoadMore) {
            if(isCommit){
                return ++page_1;
            }else{
                return ++page_0;
            }
        }else{
            if(isCommit){
                return page_1 = 1;
            }else{
                return page_0 = 1;
            }
        }

    }

    /**
     * 修改页数当请求数据更多时
     * @param isCommit
     */
    protected void setPageOnError(boolean isCommit) {

        if(isCommit){
            page_1--;
        }else{
            page_0--;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<T> reversData(List<T> records) {
        records.sort(new Comparator<T>() {
            @Override
            public int compare(IOrderInfo o1, IOrderInfo o2) {
                String updateTime1 = o1.getOrderInfo();
                String updateTime2 = o2.getOrderInfo();
                return updateTime1.compareTo(updateTime2) * -1;
            }
        });
        return records;
    }
}

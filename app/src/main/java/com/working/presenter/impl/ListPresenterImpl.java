package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.working.base.BasePresenterImpl;
import com.working.interfaces.IListCallback;
import com.working.models.AppModels;
import com.working.presenter.IListPresenter;

public abstract class ListPresenterImpl<D, E> extends BasePresenterImpl implements IListPresenter{
    protected int page_1 = 1;//上报页数
    protected int page_0 = 1;//未上报页数
    protected final int pageSize = 20;//当前页数
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
}

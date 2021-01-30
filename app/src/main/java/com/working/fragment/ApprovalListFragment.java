package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.ApprovalRecordAdapter;
import com.working.adapter.InspectionInfoAdapter;
import com.working.base.ListFragment;
import com.working.domain.ApprovalRecord;
import com.working.domain.InspectionList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.ApprovalRecordPresenterImpl;
import com.working.presenter.impl.InspectionPresenterImpl;
import com.working.utils.AppRouter;

import java.util.ArrayList;
import java.util.List;

public class ApprovalListFragment extends ListFragment<ApprovalRecord.DataBean.RecordsBean>
        implements ZTIListCallback<ApprovalRecord.DataBean.RecordsBean> {

    private ZTIListPresenter mPresenter;
    private ApprovalRecordAdapter mAdapter;

    public static ListFragment getInstance(boolean isCommit){
        if(!isCommit){
            return null;
        }
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new ApprovalListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new ApprovalRecordAdapter() ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<ApprovalRecord.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        List<ApprovalRecord.DataBean.RecordsBean> moreData = new ArrayList<>(recordsBeans);
        mAdapter.setData(moreData);
    }


    @Override
    public void onListLoadedMore(List<ApprovalRecord.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        List<ApprovalRecord.DataBean.RecordsBean> moreData = new ArrayList<>(recordsBeans);
        mAdapter.addData(moreData);
    }



    @Override
    public void search(String info) {
        if(mAdapter != null){
            mAdapter.search(info);
        }
    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new ApprovalRecordPresenterImpl();
        return mPresenter;
    }


}


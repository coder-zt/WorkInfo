package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.InspectionInfoAdapter;
import com.working.base.ListFragment;
import com.working.domain.IStockInfo;
import com.working.domain.InStockList;
import com.working.domain.InspectionList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.InspectionPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.UserDataMan;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存清单的fragment
 */
public class InspectionListFragment extends ListFragment<InspectionList.DataBean.RecordsBean>
        implements ZTIListCallback<InspectionList.DataBean.RecordsBean> {

    private ZTIListPresenter mPresenter;
    private InspectionInfoAdapter mAdapter;

    public static ListFragment getInstance(boolean isCommit){
        if (!isCommit) {//该页面只有一个fragment
            return null;
        }
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new InspectionListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new InspectionInfoAdapter(new InspectionInfoAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(InspectionList.DataBean.RecordsBean data) {
                AppRouter.toAddInspectionActivity(getActivity(), data);
            }
        }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<InspectionList.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        List<InspectionList.DataBean.RecordsBean> moreData = new ArrayList<>(recordsBeans);
        mAdapter.setData(moreData);
    }


    @Override
    public void onListLoadedMore(List<InspectionList.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        List<InspectionList.DataBean.RecordsBean> moreData = new ArrayList<>(recordsBeans);
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
        mPresenter = new InspectionPresenterImpl();
        return mPresenter;
    }


}


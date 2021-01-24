package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.StockListAdapter;
import com.working.base.ListFragment;
import com.working.domain.IStockInfo;
import com.working.domain.OutStockList;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.OutStockPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.UserDataMan;

import java.util.ArrayList;
import java.util.List;

public class OutStockListFragment extends
        ListFragment<OutStockList.DataBean.RecordsBean>
        implements ZTIListCallback<OutStockList.DataBean.RecordsBean> {


    private OutStockPresenterImpl mPresenter;
    private StockListAdapter mAdapter;

    public static ListFragment getInstance(boolean isCommit){
        if (!isCommit && (UserDataMan.getInstance().checkSecondApprovalGrant()
                ||UserDataMan.getInstance().checkFirstApprovalGrant())) {
            return null;
        }
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new OutStockListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new StockListAdapter(getActivity(), new StockListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String id) {
                AppRouter.toRepOutInfoActivity(getActivity(), id);
            }

        }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<OutStockList.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        List<IStockInfo> moreData = new ArrayList<>(recordsBeans);
        mAdapter.setCollectData(moreData);
    }


    @Override
    public void onListLoadedMore(List<OutStockList.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        List<IStockInfo> moreData = new ArrayList<>(recordsBeans);
        mAdapter.addCollectData(moreData);
    }


    @Override
    public void search(String info) {
        if (mAdapter != null) {
            mAdapter.search(info);
        }
    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new OutStockPresenterImpl();
        return mPresenter;
    }
}


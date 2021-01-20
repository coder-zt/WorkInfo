package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.ReperInAdapter;
import com.working.base.ListFragment;
import com.working.domain.RepertoryIn;
import com.working.domain.RepInInfoData;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.RePInPresenterImpl;
import com.working.utils.AppRouter;

import java.util.List;

public class RepertoryInListFragment extends ListFragment<RepertoryIn.DataBean.RecordsBean>
        implements ZTIListCallback<RepertoryIn.DataBean.RecordsBean> {


    private RePInPresenterImpl mPresenter;
    private ReperInAdapter mAdapter;

    public static ListFragment getInstance(boolean isCommit){
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new RepertoryInListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new ReperInAdapter(getActivity(), new ReperInAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RepertoryIn.DataBean.RecordsBean data) {
                AppRouter.toRepInInfoActivity(getActivity(), data.getId());
            }
        }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<RepertoryIn.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        mAdapter.setCollectData(recordsBeans);
    }


    @Override
    public void onListLoadedMore(List<RepertoryIn.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollectData(recordsBeans);
    }

//    @Override
//    public void search(String info) {
//        mAdapter.search(info);
//    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new RePInPresenterImpl();
        return mPresenter;
    }
}

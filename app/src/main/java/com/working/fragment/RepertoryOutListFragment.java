package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.adapter.ReperOutAdapter;
import com.working.base.ListFragment;
import com.working.domain.RepOut;
import com.working.domain.RepOutInfoBean;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.RepOutPresenterImpl;
import com.working.utils.AppRouter;

import java.util.List;

public class RepertoryOutListFragment extends
        ListFragment<RepOut.DataBean.RecordsBean>
        implements ZTIListCallback<RepOut.DataBean.RecordsBean> {


    private RepOutPresenterImpl mPresenter;
    private ReperOutAdapter mAdapter;

    public static ListFragment getInstance(boolean isCommit){
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new RepertoryOutListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new ReperOutAdapter(getActivity(), new ReperOutAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RepOut.DataBean.RecordsBean data) {
                AppRouter.toRepOutInfoActivity(getActivity(), data.getId());
            }
        }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<RepOut.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
       mAdapter.setCollectData(recordsBeans);
    }


    @Override
    public void onListLoadedMore(List<RepOut.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollectData(recordsBeans);
    }


//    @Override
//    public void search(String info) {
//        mAdapter.search(info);
//    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new RepOutPresenterImpl();
        return mPresenter;
    }
}


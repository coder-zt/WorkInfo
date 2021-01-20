package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.base.ListFragment;
import com.working.domain.Purchase;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.PurchasePresenterImpl;
import com.working.utils.AppRouter;

import java.util.List;

public class PurchaseListFragment  extends
        ListFragment<Purchase.DataBean.RecordsBean>
        implements ZTIListCallback<Purchase.DataBean.RecordsBean> {


    private BaseDataAdapter<Purchase.DataBean.RecordsBean> mAdapter;
    private ZTIListPresenter mPresenter;

    public static ListFragment getInstance(boolean isCommit){
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new PurchaseListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "PurchaseListFragment";
    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new BaseDataAdapter<>(R.layout.recycler_purchase,
                new BaseDataAdapter.OnItemClickedListener<Purchase.DataBean.RecordsBean>() {
                    @Override
                    public void onItemClicked(Purchase.DataBean.RecordsBean data, int position) {
                        AppRouter.toPurchaseDetailActivity(getActivity(), data.getId(), data.getApprovalStatus());
                    }
                }) ;
        return mAdapter;
    }
    @Override
    public void onListLoaded(List<Purchase.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        mAdapter.clearData();
        mAdapter.setData(recordsBeans);
    }


    @Override
    public void onListLoadedMore(List<Purchase.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollect(recordsBeans);
    }


//    @Override
//    public void search(String info) {
//        mAdapter.search(info);
//    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        return new PurchasePresenterImpl();
    }
}

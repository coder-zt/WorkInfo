package com.working.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.base.ListFragment;
import com.working.domain.Order;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.OrderPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.UserDataMan;

import java.util.List;

public class OrderListFragment  extends ListFragment<Order.DataBean.RecordsBean>
        implements ZTIListCallback<Order.DataBean.RecordsBean> {

    private static final String TAG = "OrderListFragment";
    private BaseDataAdapter<Order.DataBean.RecordsBean> mAdapter;
    private ZTIListPresenter mPresenter;

    public static ListFragment getInstance(boolean isCommit){
        if (!isCommit && (UserDataMan.getInstance().checkSecondApprovalGrant()
                ||UserDataMan.getInstance().checkFirstApprovalGrant())) {
            return null;
        }
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new OrderListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new OrderPresenterImpl();
        return mPresenter;
    }

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {
        mAdapter = new BaseDataAdapter<>(R.layout.recycler_order,
                new BaseDataAdapter.OnItemClickedListener<Order.DataBean.RecordsBean>() {
                    @Override
                    public void onItemClicked(Order.DataBean.RecordsBean data, int position) {
                        AppRouter.toOrderDetailActivity(getActivity(), data.getId());
                    }
                }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(
            List<Order.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        mAdapter.clearData();
        mAdapter.setData(recordsBeans);
    }

    @Override
    public void onListLoadedMore(
            List<Order.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollect(recordsBeans);
    }

    @Override
    public void search(String info) {
        mAdapter.search(info);
    }

}

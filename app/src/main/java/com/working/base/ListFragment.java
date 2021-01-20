package com.working.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.working.R;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.setting.StatusData;
import com.working.view.DataLoadUtilLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 清单页面的fragment的基类
 * @param <T>
 */
public abstract class ListFragment<T>
        extends BaseFragment<ViewDataBinding> implements ZTIListCallback<T> {
    private static final String TAG = "BaseListFragment";
    protected Map<String, T> detailInfoCacheMap = new HashMap<>();
    private boolean mIsCommitted;
    private TwinklingRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private DataLoadUtilLayout mDataLoadUtilLayout;
    private ZTIListPresenter mPresenter;
    private String mStartTime;
    private String mEndTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsCommitted = getArguments().getBoolean("isCommit");
        mPresenter = getSubPresenter();
        mPresenter.registerCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataLoadUtilLayout.setStatus(StatusData.LOADING);
        mPresenter.loadListData(mIsCommitted, mStartTime, mEndTime);
    }

    protected abstract ZTIListPresenter getSubPresenter();

    @Override
    protected void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(getRecyclerAdapter());
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mPresenter.loadListData(mIsCommitted, mStartTime, mEndTime);
                mRefreshLayout.setEnableLoadmore(true);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.loadListDataMore(mIsCommitted, mStartTime, mEndTime);
            }
        });
        FrameLayout statusLayout = view.findViewById(R.id.status_layout);
        mDataLoadUtilLayout = new DataLoadUtilLayout(getActivity(), statusLayout, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mDataLoadUtilLayout.setStatus(StatusData.LOADING);
                mPresenter.loadListData(mIsCommitted, mStartTime, mEndTime);
            }
        });
    }



    protected abstract RecyclerView.Adapter getRecyclerAdapter();

    @Override
    public void onListLoaded(List<T> recordsBeans, boolean isCommit) {
        if (recordsBeans.size() == 0) {
            mDataLoadUtilLayout.setStatus(StatusData.EMPTY);
        }else{
            mDataLoadUtilLayout.setStatus(StatusData.LOADED);
        }
        mRefreshLayout.finishRefreshing();
    }


    @Override
    public void onListLoadedFail(boolean isCommit) {
        mDataLoadUtilLayout.setStatus(StatusData.ERROR);
        mRefreshLayout.finishRefreshing();
    }

    @Override
    public void onListLoadedMore(List<T> recordsBeans, boolean isCommit) {
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onListLoadedMoreFail(boolean isCommit) {
        mRefreshLayout.finishLoadmore();
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_base;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unregisterCallback();
    }

    public void setViewStatus(StatusData status){
        mDataLoadUtilLayout.setStatus(status);
    }

    public void search(String startTime, String endTime){
        mStartTime = startTime;
        mEndTime = endTime;
        mPresenter.loadListData(mIsCommitted, mStartTime, mEndTime);
    }
}

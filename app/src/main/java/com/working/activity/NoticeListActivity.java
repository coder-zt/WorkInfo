package com.working.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.working.R;
import com.working.adapter.InformationListAdapter;
import com.working.base.BaseActivity;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.ActivityListNoticeBinding;
import com.working.domain.IndexNotice;
import com.working.interfaces.IIndexInfoCallback;
import com.working.models.AppApi;
import com.working.presenter.IIndexInfoPresenter;
import com.working.presenter.impl.IndexInfoPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.view.DataLoadUtilLayout;

import java.util.List;

import static com.working.WorkingApp.getContext;

public class NoticeListActivity extends BaseActivity implements IIndexInfoCallback {

    private static final String TAG = "NoticeListActivity";
    private ActivityListNoticeBinding mViewDataBinding;
    private DataLoadUtilLayout mDataLoadUtilLayout;
    private IIndexInfoPresenter mPresenter;
    private InformationListAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_notice);
        mViewDataBinding.setActivity(this);
        mPresenter = new IndexInfoPresenterImpl();
        mPresenter.registerCallback(this);
        initView();
        initData();
    }

    private void initView() {
        mAdapter = new InformationListAdapter(new BaseRecyclerAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(Object data) {
                AppRouter.toNoticeActivity(NoticeListActivity.this, (IndexNotice.DataBean.RecordsBean) data);
            }
        });
        mViewDataBinding.rvInfoContainer.setAdapter(mAdapter);
        mDataLoadUtilLayout = new DataLoadUtilLayout(getContext(), mViewDataBinding.flStatus, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mDataLoadUtilLayout.setStatus(StatusData.LOADING);
                mPresenter.loadNoticeData();
            }
        });
        mViewDataBinding.refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mViewDataBinding.refreshLayout.setEnableLoadmore(true);
                mDataLoadUtilLayout.setStatus(StatusData.LOADING);
                mPresenter.loadNoticeData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.loadMoreNoticeData();
            }
        });
    }

    private void initData() {
        mDataLoadUtilLayout.setStatus(StatusData.LOADING);
        mPresenter.loadNoticeData();
    }


    @Override
    public void onIndexNoticeLoaded(List<IndexNotice.DataBean.RecordsBean> data) {
        Log.d(TAG, "onIndexNoticeLoaded: " + data.size());
        if(data.size() == 0){
            mDataLoadUtilLayout.setStatus(StatusData.EMPTY);
        }else{
            mDataLoadUtilLayout.setStatus(StatusData.LOADED);
        }
        mAdapter.setData(data);
        mViewDataBinding.refreshLayout.finishRefreshing();
    }

    @Override
    public void onLoadFail(String msg) {
        Log.d(TAG, "onLoadFail: " + msg);
        mViewDataBinding.refreshLayout.finishRefreshing();
        mDataLoadUtilLayout.setStatus(StatusData.ERROR);
    }

    @Override
    public void onIndexNoticeLoadedMore(List<IndexNotice.DataBean.RecordsBean> data) {
        Log.d(TAG, "onIndexNoticeLoadedMore: " + data.size());
        if (data.size() == 0) {
            mViewDataBinding.refreshLayout.setEnableLoadmore(false);
        }
        mAdapter.addData(data);
        mViewDataBinding.refreshLayout.finishLoadmore();

    }

    @Override
    public void onLoadMoreFail(String msg) {
        Log.d(TAG, "onLoadMoreFail: " + msg);
        mViewDataBinding.refreshLayout.finishLoadmore();
        mViewDataBinding.refreshLayout.setEnableLoadmore(false);
    }
}

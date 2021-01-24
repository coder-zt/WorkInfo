package com.working.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.working.R;
import com.working.adapter.FunctionListAdapter;
import com.working.adapter.InformationListAdapter;
import com.working.adapter.LooperPagerAdapter;
import com.working.base.BaseFragment;
import com.working.base.BaseRecyclerAdapter;
import com.working.domain.IndexNotice;
import com.working.interfaces.IIndexInfoCallback;
import com.working.presenter.IIndexInfoPresenter;
import com.working.presenter.impl.IndexInfoPresenterImpl;
import com.working.setting.MineMenuItem;
import com.working.setting.StatusData;
import com.working.utils.AppConfig;
import com.working.utils.AppRouter;
import com.working.utils.UIHelper;
import com.working.view.AutoLoopViewPager;
import com.working.view.DataLoadUtilLayout;
import com.working.view.UniformDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-首页Fragment
 */
public class IndexFragment extends BaseFragment implements IIndexInfoCallback {

    private RecyclerView mRvFunctionBtns;
    private static final String TAG = "IndexFragment";
    private RecyclerView mRvInfoContianer;
    private AutoLoopViewPager mLoopImageContainer;
    private LinearLayout mLooperPointContainer;
    private IIndexInfoPresenter mPresenter;
    private InformationListAdapter mAdapter;
    private TwinklingRefreshLayout mRefreshLayout;
    private FrameLayout mFlStatusContainer;
    private DataLoadUtilLayout mDataLoadUtilLayout;

    @Override
    protected void initView(View view) {
        Log.d(TAG, "initView: ");
        //顶部banner
        mLoopImageContainer = (AutoLoopViewPager)view.findViewById(R.id.looper_pager);
        mLooperPointContainer = (LinearLayout)view.findViewById(R.id.looper_point_container);
        initLooperContainer();
        //功能按键
        mRvFunctionBtns = (RecyclerView)view.findViewById(R.id.rv_function_btns);
        initFunctionView();
        //公告信息
        mRvInfoContianer = (RecyclerView)view.findViewById(R.id.rv_info_container);
        mRefreshLayout = (TwinklingRefreshLayout)view.findViewById(R.id.refresh_layout);
        mFlStatusContainer = (FrameLayout)view.findViewById(R.id.fl_status);
        mDataLoadUtilLayout = new DataLoadUtilLayout(getContext(), mFlStatusContainer, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mDataLoadUtilLayout.setStatus(StatusData.LOADING);
                mPresenter.loadNoticeData();
            }
        });
        initInformationView();
    }

    private void initLooperContainer() {
        LooperPagerAdapter adapter = new LooperPagerAdapter();
        mLoopImageContainer.setAdapter(adapter);
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.banner_3);
        data.add(R.mipmap.banner_4);
        adapter.setData(data);
        mLoopImageContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateLooperIndicator(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int i = 0; i < data.size(); i++) {
            View point = new View(getContext());
            int size = UIHelper.dp2px(8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
            layoutParams.leftMargin = UIHelper.dp2px(5);
            layoutParams.rightMargin = UIHelper.dp2px(5);
            point.setLayoutParams(layoutParams);
            if(i == 0) {
                point.setBackgroundResource(R.drawable.indicator_white);
            } else {
                point.setBackgroundResource(R.drawable.indicator_gray);
            }
            mLooperPointContainer.addView(point);
        }
    }

    /**
     * 设置公告的适配器
     */
    private void initInformationView() {
        mRvInfoContianer.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new InformationListAdapter(new BaseRecyclerAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(Object data) {
                AppRouter.toNoticeActivity(getActivity(), (IndexNotice.DataBean.RecordsBean) data);
            }
        });
        mRvInfoContianer.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mRefreshLayout.setEnableLoadmore(true);
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

    @Override
    protected void initData(Bundle arguments) {
        super.initData(arguments);
        mDataLoadUtilLayout.setStatus(StatusData.LOADING);
        mPresenter = new IndexInfoPresenterImpl();
        mPresenter.registerCallback(this);
        mPresenter.loadNoticeData();
    }

    /**
     * 切换指示器
     *
     * @param targetPosition
     */
    private void updateLooperIndicator(int targetPosition) {
        int childCount = mLooperPointContainer.getChildCount();
        targetPosition %= childCount;
        for(int i = 0; i < childCount; i++) {
            View point = mLooperPointContainer.getChildAt(i);
            if(i == targetPosition) {
                point.setBackgroundResource(R.drawable.indicator_white);
            } else {
                point.setBackgroundResource(R.drawable.indicator_gray);
            }
        }
    }


    private void initFunctionView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRvFunctionBtns.setLayoutManager(layoutManager);
        FunctionListAdapter adapter = new FunctionListAdapter();
        mRvFunctionBtns.setAdapter(adapter);
        List<MineMenuItem> mDatas = new ArrayList<>();
        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.inspection_icon_big),
                "巡检记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toListActivity(getActivity(), AppConfig.ACTIVITY_INSPECTION);
            }
        }));

        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.purchase_icon_big),
                "采购清单", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toListActivity(getActivity(), AppConfig.ACTIVITY_PURCHASE);
            }
        }));

        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.order_icon_big),
                "购买记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toListActivity(getActivity(), AppConfig.ACTIVITY_ORDER);
            }
        }));
        mDatas.add(new MineMenuItem(getResources().getDrawable(R.mipmap.repertory_icon_big),
                "库存记录", new MineMenuItem.OnItemClickedListener() {
            @Override
            public void onItemClick() {
                AppRouter.toRepertoryManageActivity(getActivity());
            }
        }));
        adapter.setData(mDatas);
        int screenWidth = UIHelper.getScreenWidth(getActivity());
        int space = screenWidth - UIHelper.dp2px(78) * mDatas.size();
        mRvFunctionBtns.addItemDecoration(new UniformDecoration(space, mDatas.size()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoopImageContainer.startLoop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLoopImageContainer.stopLoop();
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
        mRefreshLayout.finishRefreshing();
    }

    @Override
    public void onLoadFail(String msg) {
        Log.d(TAG, "onLoadFail: " + msg);
        mRefreshLayout.finishRefreshing();
        mDataLoadUtilLayout.setStatus(StatusData.ERROR);
    }

    @Override
    public void onIndexNoticeLoadedMore(List<IndexNotice.DataBean.RecordsBean> data) {
        Log.d(TAG, "onIndexNoticeLoadedMore: " + data.size());
        if (data.size() == 0) {
            mRefreshLayout.setEnableLoadmore(false);
        }
        mAdapter.addData(data);
        mRefreshLayout.finishLoadmore();

    }

    @Override
    public void onLoadMoreFail(String msg) {
        Log.d(TAG, "onLoadMoreFail: " + msg);
        mRefreshLayout.finishLoadmore();
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterCallback();
    }
}

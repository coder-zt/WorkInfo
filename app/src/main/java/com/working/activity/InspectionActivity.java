package com.working.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.working.R;
import com.working.adapter.InspectionInfoAdapter;
import com.working.base.BaseActivity;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.ActivityInspectionBinding;
import com.working.domain.InspectionList;
import com.working.domain.RecordsBean;
import com.working.interfaces.IInspectCallback;
import com.working.presenter.IInspectionPresenter;
import com.working.presenter.impl.InspectionPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.TimeUtil;
import com.working.utils.ToastUtil;
import com.working.view.DataLoadUtilLayout;

import java.util.Calendar;
import java.util.List;

/**
 * 巡检记录的浏览界面
 */
public class InspectionActivity extends BaseActivity implements IInspectCallback {

    private ActivityInspectionBinding mBinding;
    private RecyclerView mRecyclerView;
    private InspectionInfoAdapter mAdapter;
    private IInspectionPresenter mPresenter = new InspectionPresenterImpl();
    private FrameLayout mStatusView;
    private MutableLiveData<StatusData> mStatusData = new MutableLiveData<>(StatusData.LOADING);
    private DataLoadUtilLayout mDataLoadUtilLayout;
    private TwinklingRefreshLayout mRefreshLayout;

    private boolean mIsSearch;
    private String mStartTime;
    private String mEndTime;
    private long end,start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_inspection);
        mBinding.setActivity(this);
        initView();
        loadData();
        mPresenter.registerCallback(this);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InspectionInfoAdapter(new BaseRecyclerAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(Object data) {
                InspectionList.DataBean.RecordsBean recordsBean = (InspectionList.DataBean.RecordsBean)data;
                AppRouter.toAddInspectionActivity(InspectionActivity.this, recordsBean);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mStatusView = (FrameLayout)findViewById(R.id.status_layout);
        mDataLoadUtilLayout = new DataLoadUtilLayout(this, mStatusView, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mPresenter.loadData();
            }
        });
        mStatusData.observe(this, new Observer<StatusData>() {
            @Override
            public void onChanged(StatusData statusData) {
                mDataLoadUtilLayout.setStatus(statusData);
            }
        });
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mRefreshLayout.setEnableLoadmore(true);
                mDataLoadUtilLayout.setStatus(StatusData.LOADING);
                mPresenter.loadData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.loadMoreData();
            }
        });
    }

    private void loadData() {
        mStatusData.postValue(StatusData.LOADING);
        mPresenter.loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onSelectTime(boolean isEnd){
        //获取日历的一个对象
        Calendar calendar = Calendar.getInstance();
        //获取年月日时分的信息
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(isEnd){
                    mEndTime = String.format("%04d-%02d-%02d",year, month+1, dayOfMonth);
                    end = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mBinding.endTime.setText(mEndTime);
                    if (mStartTime != null) {
                        if (start > end){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        loadData(mStartTime, mEndTime);
                    }
                }else {
                    mStartTime = String.format("%04d-%02d-%02d",year, month+1, dayOfMonth);
                    start = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mBinding.startTime.setText(mStartTime);
                    if (mEndTime != null) {
                        if (start > end){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        loadData(mStartTime, mEndTime);
                    }
                }
            }
        },year,month,day).show();
    }

    private void loadData(String startTime, String endTime){
        mPresenter.loadChooseData(startTime, endTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterCallback();
    }

    public void toAddInspection(){
        AppRouter.toAddInspectionActivity(this, null);
    }


    @Override
    public void onInspectInfoLoaded(List<InspectionList.DataBean.RecordsBean> data) {
        mRefreshLayout.finishRefreshing();
        if(data == null || data.size() ==0){
            mStatusData.postValue(StatusData.EMPTY);
        }else{
            mStatusData.postValue(StatusData.LOADED);
        }
        mAdapter.setData(data);
    }

    @Override
    public void onInspectInfoLoadedError() {
        mRefreshLayout.finishRefreshing();
        mStatusData.postValue(StatusData.ERROR);
    }

    @Override
    public void onInspectInfoMoreLoaded(List<InspectionList.DataBean.RecordsBean> data) {
        mRefreshLayout.finishLoadmore();
        if(data == null || data.size() ==0){
            mRefreshLayout.setEnableLoadmore(false);
        }
        mAdapter.addData(data);
    }

    @Override
    public void onInspectInfoMoreLoadedError() {
        mRefreshLayout.finishLoadmore();
        mRefreshLayout.setEnableLoadmore(false);
    }

}

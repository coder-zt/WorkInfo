package com.working.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.working.R;
import com.working.base.BaseFragment;
import com.working.base.IBasePresenter;
import com.working.databinding.FragmentStatBinding;
import com.working.databinding.FragmentStatBindingImpl;
import com.working.domain.StatBean;
import com.working.interfaces.IStatCallback;
import com.working.presenter.IStatPresenter;
import com.working.presenter.impl.StatPresenterImpl;
import com.working.utils.TimeUtil;
import com.working.utils.ToastUtil;

import java.util.Calendar;
import java.util.List;

/**
 * 首页-统计Fragment
 */
public class StatFragment extends BaseFragment<FragmentStatBinding> implements IStatCallback {

    IStatPresenter mIStatPresenter = new StatPresenterImpl();

    private static final String TAG = "StatFragment";
    private String mEndTime;
    private String mStartTime;
    private long mStart;
    private long mEnd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIStatPresenter.registerCallback(this);
    }

    @Override
    protected void initView(View view) {
getDataBinding().setFragment(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIStatPresenter.getStatInfo("", "", "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stat;
    }

    public void onSelectTime(boolean isEnd) {
        //获取日历的一个对象
        Calendar calendar = Calendar.getInstance();
        //获取年月日时分的信息
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (isEnd) {
                    mEnd = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mEndTime = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    getDataBinding().endTime.setText(mEndTime);
                    if (mStartTime != null) {
                        if (mStart > mEnd){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        filterWithFragments(mStartTime, mEndTime);
                    }
                } else {
                    mStart = TimeUtil.getTimeInMills(year, month,dayOfMonth);
                    mStartTime = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    getDataBinding().startTime.setText(mStartTime);
                    if (mEndTime != null) {
                        if (mStart > mEnd){
                            ToastUtil.showMessage("开始时间不得大于结束时间");
                            return;
                        }
                        filterWithFragments(mStartTime, mEndTime);
                    }
                }
            }
        }, year, month, day).show();
    }

    private void filterWithFragments(String startTime, String endTime) {
        mIStatPresenter.getStatInfo("", startTime, endTime);
    }

    @Override
    public void onStatDataLoaded(StatBean data) {
        getDataBinding().setData(data);
    }

    @Override
    public void onStatDataLoadFailed(String msg) {
        Log.d(TAG, "onStatDataLoadFailed: " + msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIStatPresenter.unregisterCallback();
    }
}

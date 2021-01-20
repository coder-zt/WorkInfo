package com.working.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.OrderDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityOrderDetailBinding;
import com.working.domain.MaterialListData;
import com.working.domain.Order;
import com.working.domain.OrderDetail;
import com.working.interfaces.IDetailCallback;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.OrderDetailPresenterImpl;
import com.working.presenter.impl.UpLoadOrderPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.DataBeanMapCache;
import com.working.utils.ToastUtil;
import com.working.view.DataLoadUtilLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 购买清单的详情页（普通人员）
 */
public class OrderDetailActivity extends BaseCommitActivity<OrderDetail.DataBean> implements IDetailCallback<OrderDetail.DataBean> {

    private final int REQUEST_CODE = 1;
    private List<OrderDetail.DataBean.OrderItemListBean> mDetailInfo = new ArrayList<>();
    private ActivityOrderDetailBinding mDataBinding;
    private OrderDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private boolean isCommit;
    private IDetailPresenter mIDetailPresenter = new OrderDetailPresenterImpl();
    private DataLoadUtilLayout mLoadUtilLayout;
    private String mData;
    private OrderDetail.DataBean mCommitData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        mDataBinding.setActivity(this);
        mDataBinding.setAccount(0.0f);
        mDataBinding.setTitle("购买清单详情(草稿)");
        mIDetailPresenter.registerCallback(this);
        initView();
        initData();
    }

    private static final String TAG = "OrderDetailActivity";

    protected void initView() {
        mAdapter = new OrderDetailAdapter(this, new OrderDetailAdapter.OnDataContainerListener() {
            @Override
            public void onDataContainerChanged(List<OrderDetail.DataBean.OrderItemListBean> data, String urls) {
                mDetailInfo.clear();
                mDetailInfo.addAll(data);
                if (mCommitData == null) {
                    mCommitData = new OrderDetail.DataBean();
                    mCommitData.setStatus(0);
                }
                mCommitData.setOrderItemList(mDetailInfo);
                mCommitData.setPicUrl(urls);
                countAccount();
            }
        }, mImageListener);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mDataBinding.statusLayout, () -> {
            mIDetailPresenter.getDetailData(mData);
        });

    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(OrderDetailActivity.this, mSelectedStr, REQUEST_CODE);
    }


    public void initData() {
        mData = getIntent().getStringExtra("data");
        if(mData != null){
            mIDetailPresenter.getDetailData(mData);
            mLoadUtilLayout.setStatus(StatusData.LOADING);
        }else{
            mAdapter.setPicUrls("");
        }
    }


    /**
     * 计算总账
     */
    private void countAccount() {
        BigDecimal sum = new BigDecimal("0.0");
        if (mCommitData != null) {
            for (OrderDetail.DataBean.OrderItemListBean orderItemListBean : mCommitData.getOrderItemList()) {
                String priceStr = orderItemListBean.getPrice();
                String productQuantity = orderItemListBean.getProductQuantity();
                BigDecimal bigPrice = new BigDecimal(priceStr);
                BigDecimal bigCount = new BigDecimal(productQuantity);
                BigDecimal multiply = bigCount.multiply(bigPrice);
                BigDecimal add = sum.add(multiply);
                sum = add;
                mSelectedStr.add(orderItemListBean.getMaterialName());
            }
        }
        mDataBinding.setAccount(sum.floatValue());
    }


    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadOrderPresenterImpl();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                addMaterial(data);
                break;
        }
    }

    @Override
    protected void onCommitFail() {
        if (mCommitData != null) {
            mCommitData.setStatus(0);
        }
    }

    private boolean addMaterial(Intent data) {
            if (data != null) {
                String result = data.getStringExtra("result");
                if (result == null) {
                    ToastUtil.showMessage("选择新增用品失败！");
                    return false;
                }
                if (result.isEmpty()) {
                    ToastUtil.showMessage("选择新增用品失败！");
                    return false;
                }
                MaterialListData.DataBean dataBean = new Gson().fromJson(result, MaterialListData.DataBean.class);
                OrderDetail.DataBean.OrderItemListBean  datum1 = new OrderDetail.DataBean.OrderItemListBean();
                datum1.setMaterialId(String.valueOf(dataBean.getId()));
                datum1.setMaterialName(dataBean.getMaterialName());
                datum1.setOwned(dataBean.getOwned());
                datum1.setPrice(String.valueOf(dataBean.getCommonPrice()));
                datum1.setUnit(dataBean.getUnit());
                datum1.setProductQuantity("0.0");
                mAdapter.addData(datum1);
                return true;
            }
            return false;}

        @Override
    protected void onResume() {
        super.onResume();
    }

    public void commit(boolean isDraft){
        if (mCommitData == null) {
            ToastUtil.showMessage("数据为空！");
            return;
        }
        if (mCommitData.getStatus() == 1) {
            ToastUtil.showMessage("该数据已提交！");
            return;
        }
        mCommitData.setStatus(isDraft?0:1);
        Log.d(TAG, "commit: " + new Gson().toJson(mCommitData));
        commitData(mCommitData);
    }

    @Override
    protected void addImageUrl(String link) {
        mAdapter.addPicUrl(link);
    }

    @Override
    public void onDetailDataLoaded(OrderDetail.DataBean data) {
        mCommitData = data;
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        mAdapter.setData(data.getOrderItemList());
        mAdapter.setCommitted(data.getStatus() == 1);
        if (data.getStatus() == 1) {
            mDataBinding.setCommit(true);
            mDataBinding.setTitle("购买清单详情(已提交)");
        }else{
            mDataBinding.setTitle("购买清单详情(草稿)");
            mDataBinding.setCommit(false);
        }
        mAdapter.setPicUrls(data.getPicUrl());
        countAccount();
    }

    @Override
    public void onDetailDataLoadedFail() {
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }
}


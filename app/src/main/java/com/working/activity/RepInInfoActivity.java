package com.working.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.RepInDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityRepinDetailBinding;
import com.working.domain.MaterialListData;
import com.working.domain.RepInInfoData;
import com.working.interfaces.IDetailCallback;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.RepInDetailPresenterImpl;
import com.working.presenter.impl.UpLoadRepInPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;
import com.working.view.DataLoadUtilLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 入库清单的详情页面
 */
public class RepInInfoActivity extends BaseCommitActivity<RepInInfoData.DataBean> implements IDetailCallback<RepInInfoData.DataBean> {
    private static final String TAG = "RepertoryInDetailActivi";
    private RepInInfoData.DataBean mDataBean = new RepInInfoData.DataBean();
    private RepInDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private final int REQUEST_CODE = 1;
    private IDetailPresenter mIDetailPresenter = new RepInDetailPresenterImpl();
    private ActivityRepinDetailBinding mBing;
    private DataLoadUtilLayout mLoadUtilLayout;
    private String mIdData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBing = DataBindingUtil.setContentView(this, R.layout.activity_repin_detail);
        mBing.setActivity(this);
        mIDetailPresenter.registerCallback(this);
        initView();
        initData();
    }

    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadRepInPresenterImpl();
    }

    @Override
    protected void addImageUrl(String link) {
        mAdapter.addPicUrl(link);
    }


    private void accountCount() {
        BigDecimal sum = new BigDecimal("0.0");
        for (RepInInfoData.DataBean.InStockItemListBean inStockItemListBean : mDataBean.getInStockItemList()) {
            String priceStr = inStockItemListBean.getPrice();
            String productQuantity = inStockItemListBean.getProductQuantity();
            BigDecimal bigPrice = new BigDecimal(priceStr);
            BigDecimal bigCount = new BigDecimal(productQuantity);
            BigDecimal multiply = bigCount.multiply(bigPrice);
            BigDecimal add = sum.add(multiply);
            sum = add;
        }
        Log.d(TAG, "onCountChange: " + sum.floatValue());
        mBing.setAccount(sum.floatValue());
    }

    private void initView() {
        mAdapter = new RepInDetailAdapter(new RepInDetailAdapter.OnDataContainerListener() {
            @Override
            public void onAddMaterialClicked() {
                AppRouter.toAddMaterialActivity(RepInInfoActivity.this, mSelectedStr, REQUEST_CODE);
            }

            @Override
            public void onDataContainerChanged(List<RepInInfoData.DataBean.InStockItemListBean> data, String urls) {
                mDataBean.setInStockItemList(data);
                accountCount();
            }
        }, mImageListener);
        mBing.recyclerView.setAdapter(mAdapter);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mBing.statusLayout, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mIDetailPresenter.getDetailData(mIdData);
            }
        });
    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(RepInInfoActivity.this, mSelectedStr, REQUEST_CODE);
    }

    private void initData() {
        mIdData = getIntent().getStringExtra("data");
        if (mIdData != null) {
            mIDetailPresenter.getDetailData(mIdData);
        }else{
            mAdapter.setPicUrls("");
            mBing.setTitle("入库清单详情(草稿)");
        }
    }

    public void submitData(boolean isCommit){
        if (!UserDataMan.getInstance().checkMaterialGrant()) {
            ToastUtil.showMessage("权限不足，无法操作");
            return;
        }
        if (mDataBean.getStatus() == 1) {//已提交
            ToastUtil.showMessage("已提交数据无法修改");
            return;
        }
        mDataBean.setStatus(isCommit?1:0);
        if (mDataBean.getInStockItemList() != null) {
            if (mDataBean.getInStockItemList().size() ==0) {
                mDataBean.getInStockItemList().add(new RepInInfoData.DataBean.InStockItemListBean());
            }
        }else{
            mDataBean.setInStockItemList(new ArrayList<>());
            mDataBean.getInStockItemList().add(new RepInInfoData.DataBean.InStockItemListBean());
        }
        commitData(mDataBean);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE :
                if (data != null) {
                    String result = data.getStringExtra("result");
                    if (result == null) {
                        ToastUtil.showMessage("选择新增用品失败！");
                        return;
                    }
                    if (result.isEmpty()) {
                        ToastUtil.showMessage("选择新增用品失败！");
                        return;
                    }
                    MaterialListData.DataBean dataBean = new Gson().fromJson(result, MaterialListData.DataBean.class);

                    RepInInfoData.DataBean.InStockItemListBean datum = new RepInInfoData.DataBean.InStockItemListBean();
                    FileUtils.copyValue(datum, dataBean);
                    datum.setPrice(String.valueOf(dataBean.getCommonPrice()));
                    datum.setProductQuantity("0.0");
                    datum.setMaterialId(String.valueOf(dataBean.getId()));
                    mSelectedStr.add(datum.getMaterialName());
                    mAdapter.addData(datum);
                }
                break;
        }
    }

    @Override
    protected void onCommitFail() {
        mDataBean.setStatus(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDetailDataLoaded(RepInInfoData.DataBean data) {
        mDataBean = data;
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        mAdapter.setData(data.getInStockItemList());
        mAdapter.setCommitted(data.getStatus() == 1);
        if (data.getStatus() == 1) {
            mBing.setTitle("入库清单详情(已提交)");
            mBing.setCommit(true);
        }else{
            mBing.setTitle("入库清单详情(草稿)");
            mBing.setCommit(false);
        }
        mAdapter.setPicUrls(data.getPicUrl());
        accountCount();
    }

    @Override
    public void onDetailDataLoadedFail() {
    }
}

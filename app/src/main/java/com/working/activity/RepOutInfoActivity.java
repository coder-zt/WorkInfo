package com.working.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.RepOutDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityRepoutDetailBinding;
import com.working.domain.MaterialListData;
import com.working.domain.RepOutInfoBean;
import com.working.interfaces.IDetailCallback;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.RepOutDetailPresenterImpl;
import com.working.presenter.impl.UpLoadRepOutPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.utils.UIHelper;
import com.working.utils.UserDataMan;
import com.working.view.DataLoadUtilLayout;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepOutInfoActivity extends BaseCommitActivity<RepOutInfoBean.DataBean> implements IDetailCallback<RepOutInfoBean.DataBean> {
    private static final String TAG = "RepertoryInDetailActivi";
    private RepOutInfoBean.DataBean mDataBean;
    private RepOutDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private final int REQUEST_CODE = 111;
    private ActivityRepoutDetailBinding mDataBinding;
    private IDetailPresenter mPresenter = new RepOutDetailPresenterImpl();
    private String mIdData;
    private DataLoadUtilLayout mLoadUtilLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repout_detail);
        mDataBinding.setActivity(this);
        mPresenter.registerCallback(this);
        initView();
        initData();
    }

    private void initView() {
        mAdapter = new RepOutDetailAdapter(new RepOutDetailAdapter.OnDataContainerListener() {

            @Override
            public void onDataContainerChanged(List<RepOutInfoBean.DataBean.OutStockItemListBean> data, String urls) {
                mDataBean.setOutStockItemList(data);
                accountCount();
            }

            @Override
            public void onDataCountChange(int oldSize, int newSize) {
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(oldSize, true);
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(newSize, false);
            }
        }, mImageListener);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mDataBinding.statusLayout, () -> {
            mPresenter.getDetailData(mIdData);
        });
    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(RepOutInfoActivity.this, mSelectedStr, REQUEST_CODE);
    }

    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadRepOutPresenterImpl();
    }

    private void accountCount() {
        BigDecimal sum = new BigDecimal("0.0");
        for (RepOutInfoBean.DataBean.OutStockItemListBean data : mDataBean.getOutStockItemList()) {
            String priceStr = data.getPrice();
            String productQuantity = data.getProductQuantity();
            BigDecimal bigPrice = new BigDecimal(priceStr);
            BigDecimal bigCount = new BigDecimal(productQuantity);
            BigDecimal multiply = bigCount.multiply(bigPrice);
            BigDecimal add = sum.add(multiply);
            sum = add;
        }
        mDataBinding.setAccount(sum.floatValue());
    }

    private void initData() {
        mIdData = getIntent().getStringExtra("data");
        if (mIdData != null) {
            mPresenter.getDetailData(mIdData);
            mLoadUtilLayout.setStatus(StatusData.LOADING);
        }else{
            mDataBean = new RepOutInfoBean.DataBean();
            mDataBinding.setTitle("出库清单详情(草稿)");
            mAdapter.setPicUrls("");
            mDataBinding.setAccount(0.0f);
            mDataBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mDataBinding.recyclerView.setAdapter(mAdapter);
            }
        }
    }

    public void submitData(boolean isCommit){
        if (!UserDataMan.getInstance().checkMaterialGrant()) {
            ToastUtil.showMessage("权限不足，无法操作");
            return;
        }
        if (mDataBean.getStatus() == 1) {
            ToastUtil.showMessage("权限不足，无法操作");
            return;
        }
        mDataBean.setStatus(isCommit?0:1);
        if (mDataBean.getOutStockItemList() != null) {
            if (mDataBean.getOutStockItemList().size() ==0) {
                mDataBean.getOutStockItemList().add(new RepOutInfoBean.DataBean.OutStockItemListBean());
            }
        }else{
            mDataBean.setOutStockItemList(new ArrayList<>());
            mDataBean.getOutStockItemList().add(new RepOutInfoBean.DataBean.OutStockItemListBean());
        }
        commitData(mDataBean);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE :
                addMaterial(data);
                break;
        }

    }

    @Override
    protected void onCommitFail() {
        mDataBean.setStatus(0);
    }

    /**
     * 添加物料的回传信息处理
     *
     * @param data
     * @return
     */
    private boolean addMaterial(@Nullable Intent data) {
        if (data != null) {
            String result = data.getStringExtra("result");
            if (result == null) {
                ToastUtil.showMessage("选择新增用品失败！");
                return true;
            }
            if (result.isEmpty()) {
                ToastUtil.showMessage("选择新增用品失败！");
                return true;
            }
            MaterialListData.DataBean dataBean = new Gson().fromJson(result, MaterialListData.DataBean.class);
            RepOutInfoBean.DataBean.OutStockItemListBean datum = new RepOutInfoBean.DataBean.OutStockItemListBean();
            FileUtils.copyValue(datum, dataBean);
            datum.setPrice(String.valueOf(dataBean.getCommonPrice()));
            datum.setProductQuantity("0.0");
            datum.setMaterialId(dataBean.getId());
            datum.setId("");
            datum.setOutStockId(mDataBean.getId());
            mSelectedStr.add(datum.getMaterialName());
            mAdapter.addData(datum);
            if (mDataBean.getOutStockItemList() == null) {
                mDataBean.setOutStockItemList(new ArrayList<>());
                mDataBean.getOutStockItemList().add(datum);
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterCallback();
    }

    private void createSlideMenu() {
        View view = new View(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIHelper.dp2px(100)));
        mDataBinding.recyclerView.addFooterView(view);
        mDataBinding.recyclerView.setSwipeItemMenuEnabled(true);
        mDataBinding.recyclerView.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem right = new SwipeMenuItem(getApplicationContext());
                right.setBackground(R.drawable.delete_model_bg);
                right.setText("删除");
                right.setTextSize(20);
                right.setTextColor(Color.WHITE);
                right.setHeight(UIHelper.dp2px(100));
                right.setWidth(UIHelper.dp2px(100));
                rightMenu.addMenuItem(right);
            }
        });
        mDataBinding.recyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                mAdapter.deleteData(adapterPosition);
            }
        });
        mDataBinding.recyclerView.setSwipeItemMenuEnabled(0, false);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void addImageUrl(String link) {
        mAdapter.addPicUrl(link);
    }

    @Override
    public void onDetailDataLoaded(RepOutInfoBean.DataBean data) {
        mDataBean = data;
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        mAdapter.setData(data.getOutStockItemList());
        boolean grant = UserDataMan.getInstance().checkMaterialGrant();
        mAdapter.setCommitted(data.getStatus() == 1 || !grant);
        if (data.getStatus() == 1) {
            mDataBinding.setTitle("出库清单详情(已提交)");
            mDataBinding.setCommit(true);
            mDataBinding.recyclerView.setAdapter(mAdapter);
        }else{
            mDataBinding.setTitle("出库清单详情(草稿)");
            mDataBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mDataBinding.recyclerView.setAdapter(mAdapter);
            }
        }
        mAdapter.setPicUrls(data.getPicUrl());
        accountCount();
    }

    @Override
    public void onDetailDataLoadedFail() {
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }
}

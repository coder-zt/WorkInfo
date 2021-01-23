package com.working.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

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
    private ActivityRepinDetailBinding mDataBinding;
    private DataLoadUtilLayout mLoadUtilLayout;
    private String mIdData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repin_detail);
        mDataBinding.setActivity(this);
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
        mDataBinding.setAccount(sum.floatValue());
    }

    private void initView() {
        mAdapter = new RepInDetailAdapter(new RepInDetailAdapter.OnDataContainerListener() {

            @Override
            public void onDataContainerChanged(List<RepInInfoData.DataBean.InStockItemListBean> data, String urls) {
                mDataBean.setInStockItemList(data);
                mDataBean.setPicUrl(urls);
                accountCount();
            }

            @Override
            public void onDataCountChange(int oldSize, int newSize) {
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(oldSize, true);
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(newSize, false);
            }

        }, mImageListener);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mDataBinding.statusLayout, new DataLoadUtilLayout.OnErrorOnTry() {
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
            mDataBinding.setTitle("入库清单详情(草稿)");
            mDataBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            mDataBinding.setAccount(0.0f);
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
        if (mDataBean.getStatus() == 1) {//已提交
            ToastUtil.showMessage("已提交数据无法修改");
            return;
        }

        if (mDataBean.getInStockItemList() != null) {
            if (mDataBean.getInStockItemList().size()==0) {
                ToastUtil.showMessage("请添加物料！");
                return;
            }
        }else{
            ToastUtil.showMessage("请添加物料！");
            return;
        }
        for (RepInInfoData.DataBean.InStockItemListBean inStockItemListBean : mDataBean.getInStockItemList()) {
            if(Float.parseFloat(inStockItemListBean.getProductQuantity()) <= 0.0){
                ToastUtil.showMessage(inStockItemListBean.getMaterialName() + "的数量必须大于0");
                return;
            }
        }
        mDataBean.setStatus(isCommit?0:1);
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
                    datum.setId("");
                    datum.setPrice(String.valueOf(dataBean.getCommonPrice()));
                    datum.setProductQuantity("0.0");
                    datum.setMaterialId(String.valueOf(dataBean.getId()));
                    datum.setInStockId(mDataBean.getId());
                    mSelectedStr.add(datum.getMaterialName());
                    mAdapter.addData(datum);
                    if (mDataBean.getInStockItemList() == null) {
                        mDataBean.setInStockItemList(new ArrayList<>());
                        mDataBean.getInStockItemList().add(datum);
                    }
                }
                break;
        }
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
        mAdapter.setPicUrls(data.getPicUrl());
        mAdapter.setData(data.getInStockItemList());
        boolean grant = UserDataMan.getInstance().checkMaterialGrant();
        mAdapter.setCommitted(data.getStatus() == 1 || !grant);
        if (data.getStatus() == 1) {
            mDataBinding.setTitle("入库清单详情(已提交)");
            mDataBinding.setCommit(true);
            mDataBinding.recyclerView.setAdapter(mAdapter);
        }else{
            mDataBinding.setTitle("入库清单详情(草稿)");
            mDataBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mDataBinding.recyclerView.setAdapter(mAdapter);
            }
        }
        accountCount();
    }

    @Override
    public void onDetailDataLoadedFail() {
    }
}

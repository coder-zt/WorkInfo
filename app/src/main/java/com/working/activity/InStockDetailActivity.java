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
import com.working.adapter.CommonDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityRepinDetailBinding;
import com.working.domain.ImageCollectBean;
import com.working.domain.InStockDetail;
import com.working.domain.MaterialList;
import com.working.interfaces.IDetailCallback;
import com.working.interfaces.IRecyclerDetail;
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
public class InStockDetailActivity extends BaseCommitActivity<InStockDetail.DataBean> implements IDetailCallback<InStockDetail.DataBean> {
    private static final String TAG = "RepertoryInDetailActivi";
    private InStockDetail.DataBean mDataBean = new InStockDetail.DataBean();
    private CommonDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private final int REQUEST_CODE = 1;
    private IDetailPresenter mIDetailPresenter = new RepInDetailPresenterImpl();
    private ActivityRepinDetailBinding mBinding;
    private DataLoadUtilLayout mLoadUtilLayout;
    private String mIdData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repin_detail);
        mBinding.setActivity(this);
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
        Log.d(TAG, "addImageUrl: " + link);
        mAdapter.addPic(link);
    }


    private void accountCount() {
        BigDecimal sum = new BigDecimal("0.0");
        mSelectedStr.clear();
        for (InStockDetail.DataBean.InStockItemListBean inStockItemListBean : mDataBean.getInStockItemList()) {
            mSelectedStr.add(inStockItemListBean.getMaterialName());
            String priceStr = inStockItemListBean.getPrice();
            String productQuantity = inStockItemListBean.getProductQuantity();
            BigDecimal bigPrice = new BigDecimal(priceStr);
            BigDecimal bigCount = new BigDecimal(productQuantity);
            BigDecimal multiply = bigCount.multiply(bigPrice);
            BigDecimal add = sum.add(multiply);
            sum = add;
        }
        Log.d(TAG, "onCountChange: " + sum.floatValue());
        mBinding.setAccount(sum.floatValue());
    }

    private void initView() {
        mAdapter = new CommonDetailAdapter(new CommonDetailAdapter.OnAddViewClickedListener() {

            @Override
            public void onMaterialNumChanged(List<IRecyclerDetail> data, String pciUrls) {
                List<InStockDetail.DataBean.InStockItemListBean> tempData = new ArrayList<>();
                for (IRecyclerDetail datum : data) {
                    if (datum instanceof InStockDetail.DataBean.InStockItemListBean) {
                        tempData.add((InStockDetail.DataBean.InStockItemListBean)datum);
                    }
                }
                mDataBean.setInStockItemList(new ArrayList<InStockDetail.DataBean.InStockItemListBean>(tempData));
                mDataBean.setPicUrl(pciUrls);
                accountCount();
            }

            @Override
            public void onDataContainerCountChange(int oldCount, int newCount) {
                mBinding.recyclerView.setSwipeItemMenuEnabled(oldCount - 1, true);
                mBinding.recyclerView.setSwipeItemMenuEnabled(newCount - 1, false);
            }

        }, mImageListener);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mBinding.statusLayout, new DataLoadUtilLayout.OnErrorOnTry() {
            @Override
            public void onTry() {
                mIDetailPresenter.getDetailData(mIdData);
            }
        });
    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(InStockDetailActivity.this, mSelectedStr, REQUEST_CODE);
    }

    private void initData() {
        mIdData = getIntent().getStringExtra("data");
        if (mIdData != null) {
            mIDetailPresenter.getDetailData(mIdData);
        }else{
            ArrayList<IRecyclerDetail> iRecyclerDetails = new ArrayList<>();
            iRecyclerDetails.add(new ImageCollectBean(""));
            mAdapter.setData(iRecyclerDetails);
            mBinding.setTitle("入库清单详情(草稿)");
            mBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            mBinding.setAccount(0.0f);
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mBinding.recyclerView.setAdapter(mAdapter);
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
        for (InStockDetail.DataBean.InStockItemListBean inStockItemListBean : mDataBean.getInStockItemList()) {
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
        Log.d(TAG, "onActivityResult: ");
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
                    MaterialList.DataBean dataBean = new Gson().fromJson(result, MaterialList.DataBean.class);

                    InStockDetail.DataBean.InStockItemListBean datum = new InStockDetail.DataBean.InStockItemListBean();
                    FileUtils.copyValue(datum, dataBean);
                    datum.setId("");
                    datum.setPrice(dataBean.getPrice());
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
        mBinding.recyclerView.addFooterView(view);
        mBinding.recyclerView.setSwipeItemMenuEnabled(true);
        mBinding.recyclerView.setSwipeMenuCreator(new SwipeMenuCreator() {
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
        mBinding.recyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                mAdapter.deleteData(adapterPosition);
            }
        });
        mBinding.recyclerView.setSwipeItemMenuEnabled(0, false);
        mBinding.recyclerView.setAdapter(mAdapter);
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
    public void onDetailDataLoaded(InStockDetail.DataBean data) {
        mDataBean = data;
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        ArrayList<IRecyclerDetail> iRecyclerDetails = new ArrayList<>(data.getInStockItemList());
        iRecyclerDetails.add(new ImageCollectBean(data.getPicUrl()));
        mAdapter.setData(iRecyclerDetails);
        boolean grant = UserDataMan.getInstance().checkMaterialGrant();
        mAdapter.setCommitted(data.getStatus() == 1 || !grant);
        if (data.getStatus() == 1) {
            mBinding.setTitle("入库清单详情(已提交)");
            mBinding.setCommit(true);
            mBinding.recyclerView.setAdapter(mAdapter);
        }else{
            mBinding.setTitle("入库清单详情(草稿)");
            mBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mBinding.recyclerView.setAdapter(mAdapter);
            }
        }
        accountCount();
    }

    @Override
    public void onDetailDataLoadedFail() {
    }
}

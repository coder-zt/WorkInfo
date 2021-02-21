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
import com.working.adapter.CommonDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityRepoutDetailBinding;
import com.working.domain.ApprovalContentBean;
import com.working.domain.ImageCollectBean;
import com.working.domain.InStockDetail;
import com.working.domain.MaterialList;
import com.working.domain.OutStockDetail;
import com.working.interfaces.IDetailCallback;
import com.working.interfaces.IRecyclerDetail;
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

/**
 * 出库的详情页面
 */
public class OutStockDetailActivity extends BaseCommitActivity implements IDetailCallback<OutStockDetail.DataBean> {
    private static final String TAG = "RepertoryInDetailActivi";
    private OutStockDetail.DataBean mDataBean = new OutStockDetail.DataBean();
    private CommonDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private final int REQUEST_CODE = 1;
    private ActivityRepoutDetailBinding mBinding;
    private IDetailPresenter mPresenter = new RepOutDetailPresenterImpl();
    private String mIdData;
    private DataLoadUtilLayout mLoadUtilLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_repout_detail);
        mBinding.setActivity(this);
        mPresenter.registerCallback(this);
        initView();
        initData();
    }

    private void initView() {
        mAdapter = new CommonDetailAdapter(new CommonDetailAdapter.OnAddViewClickedListener() {

            @Override
            public void onMaterialNumChanged(List<IRecyclerDetail> data, String pciUrls) {
                List<OutStockDetail.DataBean.OutStockItemListBean> tempData = new ArrayList<>();
                for (IRecyclerDetail datum : data) {
                    if (datum instanceof OutStockDetail.DataBean.OutStockItemListBean) {
                        tempData.add((OutStockDetail.DataBean.OutStockItemListBean)datum);
                    }
                }
                mDataBean.setOutStockItemList(new ArrayList<OutStockDetail.DataBean.OutStockItemListBean>(tempData));
                mDataBean.setPicUrl(pciUrls);
                accountCount();
            }

            @Override
            public void onDataContainerCountChange(int oldCount, int newCount) {
                mBinding.recyclerView.setSwipeItemMenuEnabled(oldCount - 1, true);
                mBinding.recyclerView.setSwipeItemMenuEnabled(newCount - 1, false);
            }

        }, mImageListener);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mBinding.statusLayout, () -> {
            mPresenter.getDetailData(mIdData);
        });
    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(OutStockDetailActivity.this, mSelectedStr, REQUEST_CODE);
    }

    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadRepOutPresenterImpl();
    }

    private void accountCount() {
        BigDecimal sum = new BigDecimal("0.0");
        for (OutStockDetail.DataBean.OutStockItemListBean data : mDataBean.getOutStockItemList()) {
            String priceStr = data.getPrice();
            String productQuantity = data.getProductQuantity();
            BigDecimal bigPrice = new BigDecimal(priceStr);
            BigDecimal bigCount = new BigDecimal(productQuantity);
            BigDecimal multiply = bigCount.multiply(bigPrice);
            BigDecimal add = sum.add(multiply);
            sum = add;
        }
        mBinding.setAccount(sum.floatValue());
    }

    private void initData() {
        mIdData = getIntent().getStringExtra("data");
        if (mIdData != null) {
            mPresenter.getDetailData(mIdData);
            mLoadUtilLayout.setStatus(StatusData.LOADING);
        }else{
            ArrayList<IRecyclerDetail> iRecyclerDetails = new ArrayList<>();
            iRecyclerDetails.add(new ImageCollectBean(""));
            mBinding.setTitle("出库清单详情(草稿)");
            mAdapter.setData(iRecyclerDetails);
            mBinding.setAccount(0.0f);
            mBinding.setCommit(!UserDataMan.getInstance().checkMaterialGrant());
            if (UserDataMan.getInstance().checkMaterialGrant()) {
                createSlideMenu();
            }else{
                mBinding.recyclerView.setAdapter(mAdapter);
            }
        }
    }

    public void submitData(boolean isDraft){
        if (!UserDataMan.getInstance().checkMaterialGrant()) {
            ToastUtil.showMessage("权限不足，无法操作");
            return;
        }
        if (mDataBean.getStatus() == 1) {
            ToastUtil.showMessage("该数据已提交");
            return;
        }
        if (mDataBean.getOutStockItemList() != null) {
            if (mDataBean.getOutStockItemList().size() ==0) {
                ToastUtil.showMessage("请添加物料！");
                return;
            }
        }else{
            ToastUtil.showMessage("请添加物料！");
            return;
        }
        for (OutStockDetail.DataBean.OutStockItemListBean outStockItemListBean : mDataBean.getOutStockItemList()) {
            if(Float.parseFloat(outStockItemListBean.getProductQuantity()) <= 0.0f){
                ToastUtil.showMessage(outStockItemListBean.getMaterialName() + "的数量必须大于0！");
                return;
            }
        }
        commitData(mDataBean, isDraft);
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
            MaterialList.DataBean dataBean = new Gson().fromJson(result, MaterialList.DataBean.class);
            OutStockDetail.DataBean.OutStockItemListBean datum = new OutStockDetail.DataBean.OutStockItemListBean();
            FileUtils.copyValue(datum, dataBean);
            datum.setPrice(dataBean.getPrice());
            datum.setProductQuantity("0.0");
            datum.setMaterialId(dataBean.getId());
            datum.setId("");
            datum.setPrange(dataBean.getPrange());
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
    protected void addImageUrl(String link) {
        mAdapter.addPic(link);
    }

    @Override
    public void onDetailDataLoaded(OutStockDetail.DataBean data) {
        mDataBean = data;
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        ArrayList<IRecyclerDetail> iRecyclerDetails = new ArrayList<>(data.getOutStockItemList());
        iRecyclerDetails.add(new ImageCollectBean(data.getPicUrl()));
        //设置审核信息
        if(mDataBean.getApprovalStatus()>0){
            if (mDataBean.getAuditOpinion() != null) {
                iRecyclerDetails.add(new ApprovalContentBean("一级审核", mDataBean.getAuditOpinion().isEmpty()?"无":mDataBean.getAuditOpinion()));
            }
            if (mDataBean.getAuditOpinion2() != null) {
                iRecyclerDetails.add(new ApprovalContentBean("二级审核", mDataBean.getAuditOpinion2().isEmpty()?"无":mDataBean.getAuditOpinion2()));
            }
        }
        mAdapter.setData(iRecyclerDetails);
        boolean grant = UserDataMan.getInstance().checkMaterialGrant();
        mAdapter.setCommitted(data.getStatus() == 1 || !grant);
        if (data.getStatus() == 1) {
            mBinding.setTitle("出库清单详情(已提交)");
            mBinding.setCommit(true);
            mBinding.recyclerView.setAdapter(mAdapter);
        }else{
            mBinding.setTitle("出库清单详情(草稿)");
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
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }
}

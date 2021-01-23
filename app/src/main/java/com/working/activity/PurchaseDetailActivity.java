package com.working.activity;

import android.annotation.SuppressLint;
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
import com.working.adapter.PurchaseDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityPurcahseDetailBinding;
import com.working.domain.ApprovalBean;
import com.working.domain.MaterialListData;
import com.working.domain.PurchaseDetail;
import com.working.interfaces.IDetailCallback;
import com.working.interfaces.IPurchaseOrderData;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.ApprovalPresenterImpl;
import com.working.presenter.impl.DetailPurchasePresenterImpl;
import com.working.presenter.impl.UpLoadPurchasePresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.utils.UIHelper;
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
 * 采购订单的详细数据
 */
public class PurchaseDetailActivity extends BaseCommitActivity<IPurchaseOrderData>
implements IDetailCallback<PurchaseDetail.DataBean> {

    private final int REQUEST_CODE = 1;
    private ActivityPurcahseDetailBinding mBinding;
    List<String> mSelectedItem = new ArrayList<>();
    private ApprovalBean mApprovalBean = new ApprovalBean();
    private PurchaseDetail.DataBean mDataBean = new PurchaseDetail.DataBean();
    private PurchaseDetailAdapter mAdapter;
    private DataLoadUtilLayout mLoadUtilLayout;
    private int mApprovalStatus;
    private String mId;
    private IDetailPresenter mDetailPresenter = new DetailPurchasePresenterImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.setContentView(this, R.layout.activity_purcahse_detail);
        mBinding.setActivity(this);
        mDetailPresenter.registerCallback(this);
        initView();
        initData();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            addMaterial(data);
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
            PurchaseDetail.DataBean.PurchaseItemListBean datum = new  PurchaseDetail.DataBean.PurchaseItemListBean();
            FileUtils.copyValue(datum, dataBean);
            datum.setId("");
            datum.setPrice(String.valueOf(dataBean.getCommonPrice()));
            datum.setProductQuantity("0.0");
            datum.setPriceMarket(datum.getPrice());
            datum.setMaterialId(String.valueOf(dataBean.getId()));
            datum.setPurchaseId(mDataBean.getId());
            mSelectedItem.add(datum.getMaterialName());
            mAdapter.addData(datum);
        }
        return false;
    }

    @Override
    protected ICommitPresenter createPresenter() {
        if (mApprovalStatus != 2) {//提交上报和保存草稿
            return new UpLoadPurchasePresenterImpl();

        }else {//提交生成订单
            return new ApprovalPresenterImpl();
        }
    }

    @Override
    protected void addImageUrl(String link) {
        //空实现
    }

    private static final String TAG = "PurchaseDetailActivity";

    protected void initView() {
        mAdapter = new PurchaseDetailAdapter(new PurchaseDetailAdapter.OnAddViewClickedListener() {

            @Override
            public void onMaterialNumChanged(List<PurchaseDetail.DataBean.PurchaseItemListBean> data) {
                mDataBean.setPurchaseItemList(data);
                countAccount();
            }
        });
        mLoadUtilLayout = new DataLoadUtilLayout(this, mBinding.statusLayout, () -> {
            mDetailPresenter.getDetailData(mId);
        });
    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(PurchaseDetailActivity.this,mSelectedItem,REQUEST_CODE);
    }

    private void countAccount() {
        //计算总价
        BigDecimal sum = new BigDecimal("0.0");
        int i = 1;
        Log.d(TAG, "countAccount: ==============================================================");
        for (PurchaseDetail.DataBean.PurchaseItemListBean datum: mDataBean.getPurchaseItemList()) {
            String priceStr = datum.getPrice();
            if (priceStr.length() == 0) {
                continue;
            }
            String productQuantity = datum.getProductQuantity();
            BigDecimal bigPrice = new BigDecimal(priceStr);
            BigDecimal bigCount = new BigDecimal(productQuantity);
            BigDecimal multiply = bigCount.multiply(bigPrice);
            BigDecimal add = sum.add(multiply);
            sum = add;
            Log.d(TAG, "countAccount: " + i + "=====" + multiply.floatValue()+  "====" + add.floatValue());
            i++;
        }
        Log.d(TAG, "countAccount: ==============================================================");
        mBinding.setAccount(sum.floatValue());
    }


    protected void initData() {
        mId = getIntent().getStringExtra("data_id");
        mApprovalStatus = getIntent().getIntExtra("data_approval", -1);
        mDetailPresenter.getDetailData(mId);
        mLoadUtilLayout.setStatus(StatusData.LOADING);
    }



    public void submitData(boolean isDraft){
        if (mApprovalStatus == 2) {//提交生成创建订单
            commitData(mApprovalBean);
            return;
        }
        if(mApprovalStatus == 0 || mApprovalStatus == 3){//提交上报订单
            uploadCommit(isDraft);
            return;
        }
        ToastUtil.showMessage("清单审核中，无法操作！");
    }

    private void uploadCommit(boolean isDraft) {
        if (mDataBean == null) {
            ToastUtil.showMessage("提交数据为空，请退出重试！");
            return;
        }
        if (mDataBean.getStatus() == 1) {
            ToastUtil.showMessage("数据不可重复提交！");
            return;
        }
        if (mDataBean.getPurchaseItemList() != null) {
            if (mDataBean.getPurchaseItemList().size() == 0) {
                ToastUtil.showMessage("请添加物料！");
                return;
            }
        }else{
            ToastUtil.showMessage("请添加物料！");
            return;
        }
        for (PurchaseDetail.DataBean.PurchaseItemListBean purchaseItemListBean : mDataBean.getPurchaseItemList()) {
            if (Float.parseFloat(purchaseItemListBean.getProductQuantity()) <= 0.0f) {
                ToastUtil.showMessage(purchaseItemListBean.getMaterialName() + "的数据必须大于0！");
                return;
            }
        }
        mDataBean.setStatus(isDraft?0:1);
        //提交
        commitData(mDataBean);
    }

    @Override
    public void onDetailDataLoaded(PurchaseDetail.DataBean data) {
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        List<PurchaseDetail.DataBean.PurchaseItemListBean> purchaseItemList = data.getPurchaseItemList();
        for (PurchaseDetail.DataBean.PurchaseItemListBean purchaseItemListBean : purchaseItemList) {
            mSelectedItem.add(purchaseItemListBean.getMaterialName());
            float max = Float.parseFloat(purchaseItemListBean.getMax());
            float min = Float.parseFloat(purchaseItemListBean.getMin());
            float productQuantity = Float.parseFloat(purchaseItemListBean.getProductQuantity());
            if(productQuantity > Math.max(max, min)){
                productQuantity = Math.max(max, min);
            }
            if(productQuantity < Math.min(max, min)){
                productQuantity = Math.min(max, min);
            }
            purchaseItemListBean.setProductQuantity(String.valueOf(productQuantity));
        }
        mDataBean = data;
        countAccount();
        mAdapter.setData(purchaseItemList);
        mAdapter.setCommitted(data.getStatus() == 1);
        if (data != null) {
            mBinding.setApproval(data.getApprovalStatus());
            if (data.getStatus() == 1) {
                mBinding.setCommit(true);
                if (data.getApprovalStatus() == 3) {
                    mBinding.setTitle("采购清单详情(未通过)");
                    createSlideMenu();
                } else if (data.getApprovalStatus() == 2) {
                    mBinding.setTitle("采购清单详情(通过)");
                    setCreateData(data);
                    setRecyclerAdapter();
                } else {
                    mBinding.setTitle("采购清单详情(审核中)");
                    setRecyclerAdapter();
                }
            } else {
                mBinding.setCommit(false);
                mBinding.setTitle("采购清单详情(未上报)");
                createSlideMenu();
            }
        }
    }

    private void setRecyclerAdapter() {
        mBinding.recyclerView.setAdapter(mAdapter);
    }


    private void createSlideMenu() {
        View view = new View(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIHelper.dp2px(100)));
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

        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void setCreateData(PurchaseDetail.DataBean data) {
        mApprovalBean.setStatus(data.getStatus());
        mApprovalBean.setId(data.getId());
        mApprovalBean.setPurchaseName(data.getPurchaseName());
        mApprovalBean.setPurchaseNo(data.getPurchaseNo());
        mApprovalBean.setAuditOpinion(data.getAuditOpinion());
        mApprovalBean.setInspectionRecordId(data.getInspectionRecordId());
        mApprovalBean.setApprovalStatus(data.getApprovalStatus());
    }

    @Override
    public void onDetailDataLoadedFail() {
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }
}

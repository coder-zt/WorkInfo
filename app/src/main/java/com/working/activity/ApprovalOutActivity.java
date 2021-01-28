package com.working.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.ApprovalOutAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityOutApprovealLayoutBinding;
import com.working.domain.ApprovalOutBean;
import com.working.domain.OutStockDetail;
import com.working.interfaces.IDetailCallback;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.ApprovalPresenterImpl;
import com.working.presenter.impl.RepOutDetailPresenterImpl;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;
import com.working.view.DataLoadUtilLayout;

import java.util.ArrayList;
import java.util.List;

public class ApprovalOutActivity extends BaseCommitActivity
        implements IDetailCallback<OutStockDetail.DataBean> {


    private ActivityOutApprovealLayoutBinding mBinding;
    private OutStockDetail.DataBean mDetailData;
    private ApprovalOutAdapter mAdapter;
    private IDetailPresenter mIDetailPresenter = new RepOutDetailPresenterImpl();
    private String mId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_out_approveal_layout);
        mBinding.setActivity(this);
        super.onCreate(savedInstanceState);
        mIDetailPresenter.registerCallback(this);
        initView();
        initData();
    }


    protected void initView() {
        mAdapter = new ApprovalOutAdapter();
        mBinding.recyclerView.setAdapter(mAdapter);
        DataLoadUtilLayout loadUtilLayout = new DataLoadUtilLayout(this, mBinding.statusLayout,
                ()->{
                    mIDetailPresenter.getDetailData(mId);
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initData() {
        mId = getIntent().getStringExtra("data");
        if (mId != null) {
            mIDetailPresenter.getDetailData(mId);
        }
    }


    @Override
    protected ICommitPresenter createPresenter() {
        return new ApprovalPresenterImpl();
    }

    @Override
    protected void onCommitFail() {

    }

    @Override
    protected void addImageUrl(String link) {

    }


    public void approvalPurchase(boolean pass, String approval){
        ApprovalOutBean bean = new ApprovalOutBean();
        if (mDetailData == null) {
            return;
        }
        //检查权限
        if (mDetailData.getApprovalStatus() == 0) {
            if (!UserDataMan.getInstance().checkFirstApprovalGrant()) {
                ToastUtil.showMessage("权限不足,无法审批");
                return;
            }
            bean.setApprovalStatus(pass?1:3);
        }else if(mDetailData.getApprovalStatus() == 1){
            if (!UserDataMan.getInstance().checkSecondApprovalGrant()) {
                ToastUtil.showMessage("权限不足,无法审批");
                return;
            }
            bean.setApprovalStatus(pass?2:3);
        }else if(mDetailData.getApprovalStatus() == 2 || mDetailData.getApprovalStatus() == 3){
            ToastUtil.showMessage("已审核");
            return;
        }
        if (approval == null) {
          approval = "";
        }
        if(!pass && approval.length() == 0 ){
            ToastUtil.showMessage("拒绝请填写相关意见！");
            return;
        }else{
            approval = "同意";
        }
        bean.setAuditOpinion(approval);
        bean.setId(mDetailData.getId());
        bean.setOutStockNo(mDetailData.getOutStockNo());
        Log.d(TAG, "approvalPurchase: " + new Gson().toJson(bean));
        commitData(bean);
    }

    private static final String TAG = "ApprovalOutActivity";


    @Override
    public void onDetailDataLoaded(OutStockDetail.DataBean data) {
        mDetailData = data;
        if (data.getApprovalStatus() == 0) {
            mBinding.setTitle("出库清单(一级审核)");
        }else if(data.getApprovalStatus() == 1){
            mBinding.setTitle("出库清单(二级审核)");
        }else if(data.getApprovalStatus() == 2){
            mBinding.setTitle("出库清单(已通过)");
        }else if(data.getApprovalStatus() == 3){
            mBinding.setTitle("出库清单(未通过)");
        }
        String picUrl = data.getPicUrl();
        List<ApprovalOutAdapter.ItemData> adapterData = new ArrayList<>();
        for (OutStockDetail.DataBean.OutStockItemListBean outStockItemListBean : data.getOutStockItemList()) {
            ApprovalOutAdapter.ItemData itemData = new ApprovalOutAdapter.ItemData();
            FileUtils.copyValue(itemData, outStockItemListBean);
            itemData.setPic(false);
            adapterData.add(itemData);
        }
        ApprovalOutAdapter.ItemData itemData = new ApprovalOutAdapter.ItemData();
        itemData.setPic(true);
        itemData.setPicUrl(picUrl);
        adapterData.add(itemData);
        mAdapter.setData(adapterData);
    }

    @Override
    public void onDetailDataLoadedFail() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIDetailPresenter.unregisterCallback();
    }
}

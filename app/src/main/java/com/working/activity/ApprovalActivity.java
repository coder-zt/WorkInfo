package com.working.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.IndexPagerAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityPurcahseApprovealLayoutBinding;
import com.working.domain.ApprovalBean;
import com.working.domain.PurchaseDetail;
import com.working.fragment.InspectionFragment;
import com.working.fragment.PurchaseFragment;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.impl.ApprovalPresenterImpl;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;

import java.util.ArrayList;
import java.util.List;

public class ApprovalActivity extends BaseCommitActivity{

    private String mIdData;
    private String mInspectionId;
    private ApprovalBean mBean;

    @Override
    protected ICommitPresenter createPresenter() {
        return new ApprovalPresenterImpl();
    }

    @Override
    protected void onCommitFail() {
        mBean.setApprovalStatus(mBean.getApprovalStatus()-1);
    }

    private ViewPager mVpFragmentContainer;
    private ActivityPurcahseApprovealLayoutBinding mBinding;
    private PurchaseFragment mPurchaseFragment;
    private InspectionFragment mInspectionFragment;
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_purcahse_approveal_layout);
        mBinding.setActivity(this);
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }
    protected void initView() {
        mVpFragmentContainer = (ViewPager)findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();
        mInspectionFragment = new InspectionFragment();
        fragments.add(mInspectionFragment);

        mPurchaseFragment = new PurchaseFragment();
        fragments.add(mPurchaseFragment);
        mVpFragmentContainer.setAdapter(new IndexPagerAdapter(fragments,getSupportFragmentManager()));
        mVpFragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.setIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", mIdData);
        mPurchaseFragment.setArguments(bundle);
        Bundle bundle1 = new Bundle();
        bundle1.putString("data", mInspectionId);
        mInspectionFragment.setArguments(bundle1);
    }


    protected void initData() {
        mIdData = getIntent().getStringExtra("data");
        mInspectionId = getIntent().getStringExtra("data_inspection");
        if (mIdData == null){
            finish();
        }
        mVpFragmentContainer.setCurrentItem(1);
        mBinding.setIndex(1);
    }


    public void approvalPurchase(boolean pass, String approvalInfo){
        if (mBean == null) {
            ToastUtil.showMessage("获取数据有误！请退出重试");
            return;
        }
        //检查权限
        int appStatus = mBean.getApprovalStatus();
        if (mBean.getApprovalStatus() == 0) {
            if (!UserDataMan.getInstance().checkFirstApprovalGrant()) {
                ToastUtil.showMessage("权限不足,无法审批");
                return;
            }
            mBean.setApprovalStatus(pass?1:3);
        }else if(mBean.getApprovalStatus() == 1){
            if (!UserDataMan.getInstance().checkSecondApprovalGrant()) {
                ToastUtil.showMessage("权限不足,无法审批");
                return;
            }
            mBean.setApprovalStatus(pass?2:3);
        }else if(mBean.getApprovalStatus() == 2 || mBean.getApprovalStatus() == 3){
            ToastUtil.showMessage("已审核");
            return;
        }
        if (approvalInfo == null) {
            approvalInfo = "";
        }
        if(!pass && approvalInfo.length() == 0){
            ToastUtil.showMessage("拒绝请填写相关意见！");
            return;
        }else if(pass && approvalInfo.length() == 0){
            approvalInfo = "同意";
        }
        mBean.setAuditOpinion(approvalInfo);
        commitData(mBean, false);
    }

    private static final String TAG = "ApprovalActivity";

    /**
     * 设置当前ViewPager中的Item
     * @param position
     */
    public void onSelectItem(int position){
        mBinding.setIndex(position);
        mVpFragmentContainer.setCurrentItem(position);
    }

    @Override
    protected void addImageUrl(String link) {
        //空
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onDetailDataLoaded(PurchaseDetail.DataBean data) {
        mBean = new ApprovalBean();
        mBean.setApprovalStatus(data.getApprovalStatus());
        mBean.setInspectionRecordId(data.getInspectionRecordId());
        mBean.setId(data.getId());
        mBean.setStatus(data.getStatus());
        mBean.setPurchaseName(data.getPurchaseName());
        mBean.setPurchaseNo(data.getPurchaseNo());
        if (data.getApprovalStatus() == 0) {
            mBinding.setTitle("采购清单(一级审批)");
        }else if(data.getApprovalStatus() == 1){
            mBinding.setTitle("采购清单(二级审批)");
        }else if(data.getApprovalStatus() == 2){
            mBinding.setTitle("采购清单(已通过)");
        }else if(data.getApprovalStatus() == 3){
            mBinding.setTitle("采购清单(未通过)");
        }
    }
}

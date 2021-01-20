package com.working.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.working.R;
import com.working.activity.ApprovalActivity;
import com.working.adapter.PurchaseApprovalAdapter;
import com.working.base.BaseDataAdapter;
import com.working.base.BaseFragment;
import com.working.databinding.FragmentPurchaseBinding;
import com.working.domain.PurchaseDetail;
import com.working.interfaces.IDetailCallback;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.DetailPurchasePresenterImpl;
import com.working.setting.StatusData;
import com.working.view.DataLoadUtilLayout;

/**
 * 采购订单的审核页面的采购fragment
 */
public class PurchaseFragment extends BaseFragment<FragmentPurchaseBinding>
        implements IDetailCallback<PurchaseDetail.DataBean> {

    private PurchaseApprovalAdapter mAdapter;
    private IDetailPresenter mDetailPresenter = new DetailPurchasePresenterImpl();
    private String mId;
    private DataLoadUtilLayout mLoadUtilLayout;

    @Override
    protected void initView(View view) {
        getDataBinding().setActivity((ApprovalActivity) getActivity());
        mAdapter = new PurchaseApprovalAdapter(null, null);
        mAdapter.setCommitted(true);
        getDataBinding().recyclerView.setAdapter(mAdapter);
        mDetailPresenter.registerCallback(this);
        mLoadUtilLayout = new DataLoadUtilLayout(getContext(), getDataBinding().statusLayout, () -> {
                   mDetailPresenter.getDetailData(mId);
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        mId = arguments.getString("data");
        mDetailPresenter.getDetailData(mId);
        mLoadUtilLayout.setStatus(StatusData.LOADING);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_purchase;
    }

    @Override
    public void onDetailDataLoaded(PurchaseDetail.DataBean data) {
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        mAdapter.setData(data.getPurchaseItemList());
        ((ApprovalActivity)getActivity()).onDetailDataLoaded(data);
    }

    @Override
    public void onDetailDataLoadedFail() {
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDetailPresenter.unregisterCallback();
    }
}
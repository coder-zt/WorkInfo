package com.working.fragment;

import android.view.View;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.domain.PurchaseDetail;

import java.util.List;

public class BalanceFragment extends ApprovalFragment<PurchaseDetail.DataBean.PurchaseItemListBean>  {
    @Override
    protected void initView(View view) {
        super.initView(view);
    }



    @Override
    public void setData(List<PurchaseDetail.DataBean.PurchaseItemListBean> data) {
        super.setData(data);
    }

    @Override
    protected BaseDataAdapter<PurchaseDetail.DataBean.PurchaseItemListBean> getAdapter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }
}

package com.working.fragment;

import android.view.View;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.domain.OrderDetail;

public class OrderFragment extends ApprovalFragment<OrderDetail.DataBean.OrderItemListBean>  {

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected BaseDataAdapter<OrderDetail.DataBean.OrderItemListBean> getAdapter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }
}

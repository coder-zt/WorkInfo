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
import com.working.adapter.OrderDetailAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityOrderDetailBinding;
import com.working.domain.ImageCollectBean;
import com.working.domain.MaterialList;
import com.working.domain.MaterialListData;
import com.working.domain.Order;
import com.working.domain.OrderDetail;
import com.working.domain.PurchaseDetail;
import com.working.interfaces.IDetailCallback;
import com.working.interfaces.IRecyclerDetail;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IDetailPresenter;
import com.working.presenter.impl.OrderDetailPresenterImpl;
import com.working.presenter.impl.UpLoadOrderPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.AppRouter;
import com.working.utils.DataBeanMapCache;
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
 * 购买清单的详情页（普通人员）
 */
public class OrderDetailActivity extends BaseCommitActivity implements IDetailCallback<OrderDetail.DataBean> {

    private final int REQUEST_CODE = 1;
    private List<OrderDetail.DataBean.OrderItemListBean> mDetailInfo = new ArrayList<>();
    private ActivityOrderDetailBinding mDataBinding;
    private CommonDetailAdapter mAdapter;
    private List<String> mSelectedStr = new ArrayList<>();
    private boolean isCommit;
    private IDetailPresenter mIDetailPresenter = new OrderDetailPresenterImpl();
    private DataLoadUtilLayout mLoadUtilLayout;
    private String mData;
    private OrderDetail.DataBean mCommitData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        mDataBinding.setActivity(this);
        mIDetailPresenter.registerCallback(this);
        initView();
        initData();
    }

    private static final String TAG = "OrderDetailActivity";

    protected void initView() {
        mAdapter = new CommonDetailAdapter(new CommonDetailAdapter.OnAddViewClickedListener() {

            @Override
            public void onMaterialNumChanged(List<IRecyclerDetail> data, String picUrls) {
                List<OrderDetail.DataBean.OrderItemListBean> tempData = new ArrayList<>();
                for (IRecyclerDetail datum : data) {
                    if (datum instanceof OrderDetail.DataBean.OrderItemListBean) {
                        tempData.add((OrderDetail.DataBean.OrderItemListBean)datum);
                    }
                }
                if (mCommitData.getOrderItemList() == null) {
                    mCommitData.setOrderItemList(new ArrayList<>(tempData));
                }else{
                    mCommitData.setOrderItemList(tempData);
                }
                countAccount();
            }

            @Override
            public void onDataContainerCountChange(int oldCount, int newCount) {
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(oldCount - 1, true);
                mDataBinding.recyclerView.setSwipeItemMenuEnabled(newCount - 1, false);
            }
        }, mImageListener);
        mLoadUtilLayout = new DataLoadUtilLayout(this, mDataBinding.statusLayout, () -> {
            mIDetailPresenter.getDetailData(mData);
        });

    }

    public void onAddViewClicked() {
        AppRouter.toAddMaterialActivity(OrderDetailActivity.this, mSelectedStr, REQUEST_CODE);
    }


    public void initData() {
        mData = getIntent().getStringExtra("data");
        if(mData != null){
            mIDetailPresenter.getDetailData(mData);
            mLoadUtilLayout.setStatus(StatusData.LOADING);
        }else{
            mDataBinding.setAccount(0.0f);
            mDataBinding.setTitle("购买清单详情(草稿)");
            ArrayList<IRecyclerDetail> iRecyclerDetails = new ArrayList<>();
            iRecyclerDetails.add(new ImageCollectBean(""));
            mAdapter.setData(iRecyclerDetails);
            createSlideMenu();
            mCommitData = new OrderDetail.DataBean();
        }
    }


    /**
     * 计算总账
     */
    private void countAccount() {
        BigDecimal sum = new BigDecimal("0.0");
        if (mCommitData != null) {
            for (OrderDetail.DataBean.OrderItemListBean orderItemListBean : mCommitData.getOrderItemList()) {
                String priceStr = orderItemListBean.getPrice();
                String productQuantity = orderItemListBean.getProductQuantity();
                if(priceStr.length() == 0 || productQuantity.length() == 0){
                    continue;
                }
                BigDecimal bigPrice = new BigDecimal(priceStr);
                BigDecimal bigCount = new BigDecimal(productQuantity);
                BigDecimal multiply = bigCount.multiply(bigPrice);
                BigDecimal add = sum.add(multiply);
                sum = add;
                mSelectedStr.add(orderItemListBean.getMaterialName());
            }
        }
        mDataBinding.setAccount(sum.floatValue());
    }


    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadOrderPresenterImpl();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                addMaterial(data);
                break;
        }
    }

    @Override
    protected void onCommitFail() {
        if (mCommitData != null) {
            mCommitData.setStatus(0);
        }
    }

    private boolean addMaterial(Intent data) {
            if (data != null) {
                String result = data.getStringExtra("result");
                if (result == null) {
                    ToastUtil.showMessage("选择新增用品失败！");
                    return false;
                }
                if (result.isEmpty()) {
                    ToastUtil.showMessage("选择新增用品失败！");
                    return false;
                }
                MaterialList.DataBean dataBean = new Gson().fromJson(result, MaterialList.DataBean.class);
                OrderDetail.DataBean.OrderItemListBean  datum1 = new OrderDetail.DataBean.OrderItemListBean();
                datum1.setOrderId("");
                datum1.setMaterialId(String.valueOf(dataBean.getId()));
                datum1.setMaterialName(dataBean.getMaterialName());
                datum1.setOwned(dataBean.getOwned());
                datum1.setPrange(dataBean.getPrange());
                datum1.setPrice(dataBean.getPrice());
                datum1.setUnit(dataBean.getUnit());
                if (mCommitData != null) {
                    datum1.setOrderId(mCommitData.getId());
                }
                datum1.setProductQuantity("0.0");
                mAdapter.addData(datum1);
                return true;
            }
            return false;}

        @Override
    protected void onResume() {
        super.onResume();
    }

    public void commit(boolean isDraft){
        if (mCommitData == null) {
            ToastUtil.showMessage("数据为空！");
            return;
        }
        if (mCommitData.getStatus() == 1) {
            ToastUtil.showMessage("该数据已提交！");
            return;
        }
        if (mCommitData.getOrderItemList() != null) {
            if (mCommitData.getOrderItemList().size() == 0) {
                ToastUtil.showMessage("请添加物料！");
                return;
            }
        }else{
            ToastUtil.showMessage("请添加物料！");
            return;
        }
        for (OrderDetail.DataBean.OrderItemListBean orderItemListBean : mCommitData.getOrderItemList()) {
            if (Float.parseFloat(orderItemListBean.getProductQuantity()) <= 0.0f) {
                ToastUtil.showMessage(orderItemListBean.getMaterialName() + "的数据必须大于0！");
                return;
            }
        }
        commitData(mCommitData, isDraft);
    }

    @Override
    protected void addImageUrl(String link) {
        mAdapter.addPic(link);
    }

    private void setRecyclerAdapter() {
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDetailDataLoaded(OrderDetail.DataBean data) {
        mLoadUtilLayout.setStatus(StatusData.LOADED);
        mCommitData = data;
        mAdapter.setCommitted(data.getStatus() == 1);
        if (data.getStatus() == 1) {
            mDataBinding.setCommit(true);
            mDataBinding.setTitle("购买清单详情(已提交)");
            setRecyclerAdapter();
        }else{
            mDataBinding.setTitle("购买清单详情(草稿)");
            createSlideMenu();
            mDataBinding.setCommit(false);
        }
        ArrayList<IRecyclerDetail> recyclerDetails = new ArrayList<>(data.getOrderItemList());
        recyclerDetails.add(new ImageCollectBean(mCommitData.getPicUrl()));
        mAdapter.setData(recyclerDetails);
        countAccount();
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
    public void onDetailDataLoadedFail() {
        mLoadUtilLayout.setStatus(StatusData.ERROR);
    }
}


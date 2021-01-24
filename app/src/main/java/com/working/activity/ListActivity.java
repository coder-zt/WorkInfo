package com.working.activity;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import com.working.adapter.RVListAdapter;
import com.working.base.ZTBaseListActivity;
import com.working.base.ListFragment;
import com.working.domain.MaterialListData;
import com.working.fragment.ApprovalListFragment;
import com.working.fragment.InspectionFragment;
import com.working.fragment.InspectionListFragment;
import com.working.fragment.OrderListFragment;
import com.working.fragment.PurchaseListFragment;
import com.working.fragment.MaterialListFragment;
import com.working.fragment.InStockListFragment;
import com.working.fragment.OutStockListFragment;
import com.working.interfaces.IAddMaterialCallback;
import com.working.presenter.IAddMaterialPresenter;
import com.working.presenter.impl.AddMaterialPresenter;
import com.working.utils.AppConfig;
import com.working.utils.AppRouter;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;
import com.working.view.TypePopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存管理->入库清单
 */
public class ListActivity extends ZTBaseListActivity implements IAddMaterialCallback {

    private static final String TAG = "ListActivity";
    private IAddMaterialPresenter mMPresenter;
    private List<MaterialListData.DataBean> mData = new ArrayList<>();;
    private List<String> mShowData = new ArrayList<>();;

    @Override
    protected Boolean getIsCreate() {
        switch (mActivityCode){
            case AppConfig.ACTIVITY_INSPECTION:
            case AppConfig.ACTIVITY_ORDER:
                return true;
            case AppConfig.ACTIVITY_PURCHASE:
            case AppConfig.ACTIVITY_APPROVAL:
                return false;
            case AppConfig.ACTIVITY_STOCK_IN:
            case AppConfig.ACTIVITY_STOCK_OUT:
            case AppConfig.ACTIVITY_MATERIAL:
                return UserDataMan.getInstance().checkMaterialGrant();

        }
        return false;
    }

    @Override
    protected String getActivityTitle() {
        switch (mActivityCode){
            case AppConfig.ACTIVITY_INSPECTION:
                return "巡检记录";
            case AppConfig.ACTIVITY_PURCHASE:
                return "采购清单";
            case AppConfig.ACTIVITY_ORDER:
                return "购买清单";
            case AppConfig.ACTIVITY_STOCK_IN:
                return "入库清单";
            case AppConfig.ACTIVITY_STOCK_OUT:
                return "出库清单";
            case AppConfig.ACTIVITY_MATERIAL:
                mMPresenter = new AddMaterialPresenter();
                mMPresenter.registerCallback(this);
                mMPresenter.getMaterialList();
                return "库存清单";
            case AppConfig.ACTIVITY_APPROVAL:
                return "审批记录";
        }
        return null;
    }

    @Override
    protected ListFragment getListFragment(boolean iusCommit) {
        switch (mActivityCode){
            case AppConfig.ACTIVITY_INSPECTION:
                return InspectionListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_PURCHASE:
                return PurchaseListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_ORDER://购买订单
                return OrderListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_STOCK_IN://入库的fragment
                return InStockListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_STOCK_OUT://出库的fragment
                return OutStockListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_MATERIAL://库存管理
                return MaterialListFragment.getInstance(iusCommit);
            case AppConfig.ACTIVITY_APPROVAL://审批记录的fragment
                return ApprovalListFragment.getInstance(iusCommit);
        }
        return null;
    }


    @Override
    public void create() {
        switch (mActivityCode){
            case AppConfig.ACTIVITY_INSPECTION://创建新的巡检记录
                AppRouter.toAddInspectionActivity(this, null);
                break;
            case AppConfig.ACTIVITY_PURCHASE:
            case AppConfig.ACTIVITY_APPROVAL:
                break;
            case AppConfig.ACTIVITY_ORDER://创建新的购买清单
                AppRouter.toOrderDetailActivity(this, null);
                break;
            case AppConfig.ACTIVITY_STOCK_IN://创建新的入库清单
                AppRouter.toRepInInfoActivity(this, null);
                break;
            case AppConfig.ACTIVITY_STOCK_OUT://创建新的入库清单
                AppRouter.toRepOutInfoActivity(this, null);
                break;
            case AppConfig.ACTIVITY_MATERIAL://创建新的库存清单
               //直接展示物料列表
                if (mShowData != null && mShowData.size() > 0) {
                    TypePopWindow popWindow = new TypePopWindow(this, mShowData, new RVListAdapter.OnItemClickedListener() {
                        @Override
                        public void onItemClicked(int index, String typeName) {
                            if (mUnCommitFragment instanceof MaterialListFragment) {
                                mData.get(index - 1).setId("");
                                ((MaterialListFragment)mUnCommitFragment).addNewMaterial(mData.get(index - 1));
                            }
                        }
                    });
                    popWindow.showAtLocation(mVpFragmentContainer, Gravity.BOTTOM, 0 , 0);
                }else{
                    ToastUtil.showMessage("获取物料清单失败!");
                }
                break;
        }
    }



    @Override
    public void onMaterialListLoaded(List<MaterialListData.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        mShowData.clear();
        for (MaterialListData.DataBean datum : data) {
            mShowData.add(datum.getMaterialName());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (mUnCommitFragment instanceof MaterialListFragment) {
                if(((MaterialListFragment)mUnCommitFragment).checkNoSaveData()){
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLoadFail(String msg) {
        Log.d(TAG, "onLoadFail: " + "获取物料列表失败!");
    }
}

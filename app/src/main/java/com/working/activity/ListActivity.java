package com.working.activity;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import com.working.adapter.RVListAdapter;
import com.working.base.ZTBaseListActivity;
import com.working.base.ListFragment;
import com.working.domain.MaterialList;
import com.working.fragment.OrderListFragment;
import com.working.fragment.PurchaseListFragment;
import com.working.fragment.RepBalListFragment;
import com.working.fragment.InStockListFragment;
import com.working.fragment.OutStockListFragment;
import com.working.interfaces.IMaterialListCallback;
import com.working.presenter.impl.MaterialListPresenterImpl;
import com.working.utils.AppRouter;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;
import com.working.view.TypePopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存管理->入库清单
 */
public class ListActivity extends ZTBaseListActivity implements IMaterialListCallback {

    private static final String TAG = "ListActivity";
    private MaterialListPresenterImpl mMPresenter;
    private List<MaterialList.DataBean> mData = new ArrayList<>();;
    private List<String> mShowData = new ArrayList<>();;

    @Override
    protected Boolean getIsCreate() {
        switch (mActivityCode){
            case 2:
                return false;
            case 3:
                return true;
            case 4:
            case 5:
            case 6:
                return UserDataMan.getInstance().checkMaterialGrant();

        }
        return false;
    }

    @Override
    protected String getActivityTitle() {
        switch (mActivityCode){
            case 2:
                return "采购清单";
            case 3:
                return "购买清单";
            case 4:
                return "入库清单";
            case 5:
                return "出库清单";
            case 6:
                mMPresenter = new MaterialListPresenterImpl();
                mMPresenter.registerCallback(this);
                mMPresenter.getMaterialList();
                return "库存清单";
        }
        return null;
    }

    @Override
    protected ListFragment getListFragment(boolean iusCommit) {
        switch (mActivityCode){
            case 2:
                return PurchaseListFragment.getInstance(iusCommit);
            case 3:
                return OrderListFragment.getInstance(iusCommit);
            case 4://入库的fragment
                return InStockListFragment.getInstance(iusCommit);
            case 5://出库的fragment
                return OutStockListFragment.getInstance(iusCommit);
            case 6:
                if (iusCommit) {
                    return null;
                }
                return RepBalListFragment.getInstance(iusCommit);
        }
        return null;
    }


    @Override
    public void create() {
        switch (mActivityCode){
            case 2:
                break;
            case 3:
                AppRouter.toOrderDetailActivity(this, null);
                break;
            case 4://创建新的入库清单
                AppRouter.toRepInInfoActivity(this, null);
                break;
            case 5://创建新的入库清单
                AppRouter.toRepOutInfoActivity(this, null);
                break;
            case 6://创建新的库存清单
               //直接展示物料列表
                if (mShowData != null && mShowData.size() > 0) {
                    TypePopWindow popWindow = new TypePopWindow(this, mShowData, new RVListAdapter.OnItemClickedListener() {
                        @Override
                        public void onItemClicked(int index, String typeName) {
                            if (mUnCommitFragment instanceof RepBalListFragment) {
                                mData.get(index - 1).setId("");
                                ((RepBalListFragment)mUnCommitFragment).addNewMaterial(mData.get(index - 1));
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
    public void onMaterialListLoaded(List<MaterialList.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        mShowData.clear();
        for (MaterialList.DataBean datum : data) {
            mShowData.add(datum.getMaterialName());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (mUnCommitFragment instanceof RepBalListFragment) {
                if(((RepBalListFragment)mUnCommitFragment).checkNoSaveData()){
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

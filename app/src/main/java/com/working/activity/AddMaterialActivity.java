package com.working.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.RVListAdapter;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityAddGoodsBinding;
import com.working.domain.MaterialListData;
import com.working.interfaces.IAddMaterialCallback;
import com.working.presenter.IAddMaterialPresenter;
import com.working.presenter.impl.AddMaterialPresenter;
import com.working.utils.ToastUtil;
import com.working.view.TypePopWindow;

import java.util.ArrayList;
import java.util.List;

public class AddMaterialActivity extends BaseActivity implements IAddMaterialCallback {


    private static final String TAG = "AddMaterialActivity";
    private ActivityAddGoodsBinding mBinding;
    List<MaterialListData.DataBean> mData = new ArrayList();
    List<String> mSelectStr = new ArrayList();
    IAddMaterialPresenter mPresenter = new AddMaterialPresenter();
    private boolean mIsLoading;
    private ArrayList<String> mSelectedData;
    private int mMaterialNum = -1;
    private MaterialListData.DataBean mSelectDateBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.registerCallback(this);
        getSelectData();
        mPresenter.getMaterialList();
        mIsLoading = true;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_goods);
        mBinding.setActivity(this);
    }

    private void getSelectData() {
        mSelectedData = getIntent().getStringArrayListExtra("data");
        if (mSelectedData != null) {
            Log.d(TAG, "getSelectData: " + mSelectedData.size());
        } else {
            Log.d(TAG, "getSelectData: is null");
            mSelectedData = new ArrayList<>();
        }
    }


    public void selectOwner() {
        if (mSelectDateBean == null) {
//                    mSelectDateBean = new MaterialListData.DataBean();
            ToastUtil.showMessage("请选择产品名称后重试！");
            return;
        }
        List<String> str = new ArrayList<>();
        str.add("自有");
        str.add("没有");
        TypePopWindow typePopWindow = new TypePopWindow(this, str, new RVListAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int index, String typeName) {
                if (index == 1) {
                    mSelectDateBean.setOwned(1);
                    mBinding.setItem(mSelectDateBean);
                } else {
                    mSelectDateBean.setOwned(0);
                    mBinding.setItem(mSelectDateBean);
                }
            }
        });
        typePopWindow.showAsDropDown(findViewById(R.id.textView22));
    }

    public void selectMaterial() {
        if (mIsLoading) {
            ToastUtil.showMessage("数据正在加载中，请稍后再试!");
            return;
        }
        if (mSelectStr.size() == 0) {
            mPresenter.getMaterialList();
            mIsLoading = true;
            return;
        }
        TypePopWindow typePopWindow = new TypePopWindow(this, mSelectStr, new RVListAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int index, String typeName) {
                Log.d(TAG, "onItemClicked: " + index + typeName);
                mMaterialNum = index - 1;
                mSelectDateBean = mData.get(mMaterialNum);
                mSelectDateBean.setOwned(-1);
                mBinding.setItem(mSelectDateBean);
            }
        });
        typePopWindow.showAsDropDown(findViewById(R.id.editText2));
    }


    public void save() {
        if (mMaterialNum == -1) {
            ToastUtil.showMessage("请选选择新增物料！");
            return;
        }
        MaterialListData.DataBean dataBean = mData.get(mMaterialNum);
        if (dataBean.getOwned() == -1) {
            ToastUtil.showMessage("点击选择是否自有");
            return;
        }
        Intent in = new Intent();
        in.putExtra("result", new Gson().toJson(dataBean));
        setResult(1, in);
        finish();
    }

    @Override
    public void onMaterialListLoaded(List<MaterialListData.DataBean> data) {
        mData.addAll(data);
        List<Integer> indexList = new ArrayList<>();
        int index = 0;
        for (MaterialListData.DataBean datum : mData) {
            //过滤已选数据
            if (!mSelectedData.contains(datum.getMaterialName())) {
                mSelectStr.add(datum.getMaterialName());
            } else {
                Log.d(TAG, "onMaterialListLoaded: 过滤--> " + index);
                indexList.add(index);
            }
            index++;
        }
        //清除已选数据
        for (int i = indexList.size() - 1; i >= 0; i--) {
            Log.d(TAG, "onMaterialListLoaded: 过滤--> " + (int) indexList.get(i));
            mData.remove((int) indexList.get(i));
        }
        mIsLoading = false;
    }

    @Override
    public void onLoadFail(String msg) {
        mIsLoading = false;
    }
}

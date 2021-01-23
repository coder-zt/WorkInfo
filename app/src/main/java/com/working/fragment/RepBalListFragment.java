package com.working.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.adapter.MaterialAdapter;
import com.working.base.BaseDataAdapter;
import com.working.base.ListFragment;
import com.working.domain.MaterialList;
import com.working.domain.PostedFileBean;
import com.working.domain.RepBalData;
import com.working.domain.RepertoryIn;
import com.working.interfaces.ICommitCallback;
import com.working.interfaces.IUploadFileCallback;
import com.working.interfaces.ZTIListCallback;
import com.working.interfaces.ZTIListPresenter;
import com.working.presenter.impl.RepBalPresenterImpl;
import com.working.setting.StatusData;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.utils.UserDataMan;
import com.working.view.CommonDialog;

import java.util.ArrayList;
import java.util.List;

public class RepBalListFragment extends ListFragment<RepBalData.DataBean.RecordsBean>
        implements ZTIListCallback<RepBalData.DataBean.RecordsBean>, ICommitCallback {


    private RepBalPresenterImpl mPresenter;
    private MaterialAdapter mAdapter;
    private ProgressDialog mWaitingDialog;
    private boolean isBack;
    private int mFailBean;
    private int mCountBean;

    public static ListFragment getInstance(boolean isCommit){
        Bundle data =new Bundle();
        data.putBoolean("isCommit", isCommit);
        ListFragment fragment = new RepBalListFragment();
        fragment.setArguments(data);
        return fragment;
    }

    private static final String TAG = "RepertoryInListFragment";

    @Override
    protected RecyclerView.Adapter getRecyclerAdapter() {//recycler_balance_layout
        mAdapter = new MaterialAdapter(new MaterialAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RepBalData.DataBean.RecordsBean data) {
                if (data.getStatus() == 0) {
                    return;
                }
                isBack = false;
                List<RepBalData.DataBean.RecordsBean> list = new ArrayList<>();
                list.add(data);
                showCommitDialog(list, false);
            }
        });
        return mAdapter;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mWaitingDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void onListLoaded(List<RepBalData.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        mAdapter.setCollectData(recordsBeans);
    }


    @Override
    public void onListLoadedMore(List<RepBalData.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollectData(recordsBeans);
    }


    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new RepBalPresenterImpl();
        return mPresenter;
    }

    public void addNewMaterial(MaterialList.DataBean dataBean) {
        RepBalData.DataBean.RecordsBean data = new RepBalData.DataBean.RecordsBean();
        FileUtils.copyValue(data, dataBean);
        data.setStatus(0);
        mAdapter.addTopData(data);
        setViewStatus(StatusData.LOADED);
    }



    /**
     * show等待Dialog
     */
    private void showWaitingDialog() {
        mWaitingDialog.setTitle("上传数据中");
        mWaitingDialog.setMessage("等待中...");
        mWaitingDialog.setIndeterminate(true);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }


    @Override
    public void onCommitFinished() {
        mAdapter.update(updateList.get(updateIndex));
        updateIndex++;
        if(updateIndex < updateList.size()){
            mPresenter.uploadRepBal(updateList.get(updateIndex));
        }else{
            mWaitingDialog.dismiss();
            showResultDialog("保存完成！ ", true);
        }
    }

    @Override
    public void onCommitFail(String msg) {
        updateList.get(updateIndex).setStatus(-1);
        updateIndex++;
        if(updateIndex < updateList.size()){
            mPresenter.uploadRepBal(updateList.get(updateIndex));
        }else{
            mWaitingDialog.dismiss();
            showResultDialog(msg, false);
        }
    }


    private void showResultDialog(String msg, final boolean success){
        final CommonDialog dialog = new CommonDialog(getContext());
        dialog.setMessage(msg)
                .setImageResId(success?R.mipmap.success_icon:R.mipmap.fail_icon)
                .setTitle(success?"上传成功":"上传失败")
                .setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
                if(success && isBack){
                    getActivity().finish();
                }
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
            }
        }).show();
    }

    private void showCommitDialog(List<RepBalData.DataBean.RecordsBean> data, boolean isBack){
        final CommonDialog dialog = new CommonDialog(getContext());
        dialog.setMessage("是否更新已修改的物料的库存数据")
                .setImageResId(R.mipmap.success_icon)
                .setTitle("更新数据")
                .setSingle(false).
                setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
                commitData(data);
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
                if (isBack) {
                    getActivity().finish();
                }
            }
        }).show();
    }

    private int updateIndex = 0;
    private List<RepBalData.DataBean.RecordsBean> updateList;
    private void commitData(List<RepBalData.DataBean.RecordsBean> data) {
        updateList = data;
        updateIndex = 0;
        if (!mWaitingDialog.isShowing()) {
            showWaitingDialog();
        }
        if(updateIndex < updateList.size()){
            updateList.get(updateIndex).setStatus(0);
            mPresenter.uploadRepBal(updateList.get(updateIndex));
        }
    }

    public boolean checkNoSaveData() {
        return mAdapter.checkHaveNoSaveData(new MaterialAdapter.OnNoSaveDataListener() {
            @Override
            public void onNoSaveData(List<RepBalData.DataBean.RecordsBean> data) {
                showCommitDialog(data, true);
                isBack = true;
            }
        });
    }
}


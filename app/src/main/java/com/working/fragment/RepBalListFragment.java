package com.working.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.base.ListFragment;
import com.working.domain.MaterialList;
import com.working.domain.PostedFileBean;
import com.working.domain.RepBalData;
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

import java.util.List;

public class RepBalListFragment extends ListFragment<RepBalData.DataBean.RecordsBean>
        implements ZTIListCallback<RepBalData.DataBean.RecordsBean>, ICommitCallback {


    private RepBalPresenterImpl mPresenter;
    private BaseDataAdapter<RepBalData.DataBean.RecordsBean> mAdapter;
    private ProgressDialog mWaitingDialog;

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
        mAdapter = new BaseDataAdapter<>(R.layout.recycler_balance_layout,
                new BaseDataAdapter.OnItemClickedListener<RepBalData.DataBean.RecordsBean>() {
                    @Override
                    public void onItemClicked(final RepBalData.DataBean.RecordsBean data, final int position) {
                        if (data.getStatus() == 1) {
                            return;
                        }
                        //判断权限
                        if (!UserDataMan.getInstance().checkMaterialGrant()) {
                            ToastUtil.showMessage("你没有修改该数据的权限!");
                            return;
                        }
                        FileUtils.getCommitMethod(getActivity(), new Handler.Callback() {
                            @Override
                            public boolean handleMessage(@NonNull Message msg) {
                                if (msg.what == 0) {
                                    data.setStatus(0);

                                }else{
                                    data.setStatus(1);
                                }
                                data.setQuantity(String.valueOf(position/10000.0F));
                                String quantity = data.getQuantity();//现存
                                String freezeQuantity = data.getFreezeQuantity();//冻结
                                float availableQuantity = Float.parseFloat(quantity)
                                        - Float.parseFloat(freezeQuantity);//计算可用
                                data.setAvailableQuantity(String.valueOf(availableQuantity));
                                showWaitingDialog();
                                data.setId("");//设置id为空，不然上传失败
                                mPresenter.uploadRepBal(data);
                                return true;
                            }
                        });
                    }
                }) ;
        return mAdapter;
    }

    @Override
    public void onListLoaded(List<RepBalData.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoaded(recordsBeans, isCommit);
        mAdapter.clearData();
        mAdapter.addCollect(recordsBeans);
    }


    @Override
    public void onListLoadedMore(List<RepBalData.DataBean.RecordsBean> recordsBeans, boolean isCommit) {
        super.onListLoadedMore(recordsBeans, isCommit);
        mAdapter.addCollect(recordsBeans);
    }

//    @Override
//    public void search(String info) {
//        mAdapter.search(info);
//    }

    @Override
    protected ZTIListPresenter getSubPresenter() {
        mPresenter = new RepBalPresenterImpl();
        return mPresenter;
    }

    public void addNewMaterial(MaterialList.DataBean dataBean) {
        RepBalData.DataBean.RecordsBean data = new RepBalData.DataBean.RecordsBean();
        FileUtils.copyValue(data, dataBean);
        data.setStatus(0);
        mAdapter.addData(data);
        setViewStatus(StatusData.LOADED);
    }



    /**
     * show等待Dialog
     */
    private void showWaitingDialog() {
        mWaitingDialog = new ProgressDialog(getActivity());
        mWaitingDialog.setTitle("上传数据中");
        mWaitingDialog.setMessage("等待中...");
        mWaitingDialog.setIndeterminate(true);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }


    @Override
    public void onCommitFinished() {
        mWaitingDialog.dismiss();
        showResultDialog("上传结束！", true);
    }

    @Override
    public void onCommitFail(String msg) {

        mWaitingDialog.dismiss();
        showResultDialog(msg, false);
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
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
            }
        }).show();
    }
}


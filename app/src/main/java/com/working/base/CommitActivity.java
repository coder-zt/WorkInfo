package com.working.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.working.R;
import com.working.domain.ApprovalOutBean;
import com.working.domain.ApprovalBean;
import com.working.interfaces.ICommitCallback;
import com.working.presenter.IApprovalPresenter;
import com.working.presenter.ICommitPresenter;

/**
 *
 * @param <T>
 */
public abstract class CommitActivity<T> extends BaseActivity implements ICommitCallback {


    ICommitPresenter mPresenter;
    private ProgressDialog mWaitingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.registerCallback(this);
    }


    /**
     * 由子类来创建Presenter
     * @return
     */
    protected abstract ICommitPresenter createPresenter();

    protected void uploadData(T data){
        if (mPresenter instanceof IApprovalPresenter) {
            if( data instanceof  ApprovalBean){
                ((IApprovalPresenter)mPresenter).approvalData((ApprovalBean)data);
            }else if(data instanceof ApprovalOutBean){
                ((IApprovalPresenter)mPresenter).approvalOutData((ApprovalOutBean) data);
            }
        }else{
            mPresenter.uploadData(data);
        }
        showWaitingDialog();
    }

    @Override
    public void onCommitFinished() {
        dismissWaitingDialog();
        showNormalDialog("上传成功！", true);
    }

    @Override
    public void onCommitFail(String msg) {
        dismissWaitingDialog();
        showNormalDialog(msg, false);
    }

    protected void showNormalDialog(String msg, final boolean success){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        if (success) {
            normalDialog.setIcon(R.mipmap.icon_upload_ok);
        }else{
            normalDialog.setIcon(R.mipmap.icon_upload_error);
        }
        normalDialog.setTitle("上传结果");
        normalDialog.setMessage(msg);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(success){
                            finish();
                        }
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(success){
                            finish();
                        }
                    }
                });
        // 显示
        normalDialog.show();
    }


    /**
     * show等待Dialog
     */
    protected void showWaitingDialog() {
        mWaitingDialog = new ProgressDialog(this);
        mWaitingDialog.setTitle("上传数据中");
        mWaitingDialog.setMessage("等待中...");
        mWaitingDialog.setIndeterminate(true);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }

    /**
     * show等待Dialog
     */
    protected void dismissWaitingDialog() {
        if (mWaitingDialog != null) {
            mWaitingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterCallback();
    }
}

package com.working.presenter.impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.working.base.BasePresenterImpl;
import com.working.domain.PostedFileBean;
import com.working.interfaces.IUploadFileCallback;
import com.working.models.AppModels;
import com.working.presenter.IUploadFilePresenter;

public class UploadFilePresenterImpl extends BasePresenterImpl implements IUploadFilePresenter {

    private static final String TAG = "UploadFilePresenterImpl";
    @Override
    public void uploadFile(String path) {
        AppModels.getInstance().uploadFile(path, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof PostedFileBean) {
                    PostedFileBean bean = (PostedFileBean) msg.obj;
                    if (bean != null && bean.getCode() == 200) {
                        Log.d(TAG, "handleMessage: " + new Gson().toJson(bean));
                        if (mCallback != null) {
                            ((IUploadFileCallback) mCallback).onUploadFileFinished(bean);
                        }
                        return true;
                    }
                }
                if (mCallback != null) {
                    ((IUploadFileCallback)mCallback).onUploadFileFail("图片上传失败");
                }
                return true;
            }
        });
    }
}

package com.working.interfaces;


import com.working.base.IBaseCallback;
import com.working.domain.PostedFileBean;

/**
 * 上传文件
 */
public interface IUploadFileCallback extends IBaseCallback {

    /**
     * 文件上传完成
     */
    void onUploadFileFinished(PostedFileBean bean);

    /**
     * 文件上传失败
     *
     * @param msg
     */
    void onUploadFileFail(String msg);
}

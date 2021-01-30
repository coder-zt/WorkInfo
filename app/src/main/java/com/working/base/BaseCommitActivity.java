package com.working.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.working.R;
import com.working.adapter.ImageCollectAdapter;
import com.working.domain.ApprovalBean;
import com.working.domain.ApprovalOutBean;
import com.working.domain.ICommitData;
import com.working.domain.PostedFileBean;
import com.working.interfaces.ICommitCallback;
import com.working.interfaces.IUploadFileCallback;
import com.working.presenter.IApprovalPresenter;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IUploadFilePresenter;
import com.working.presenter.impl.UploadFilePresenterImpl;
import com.working.utils.AppConfig;
import com.working.utils.AppRouter;
import com.working.utils.Glide4Engine;
import com.working.utils.GlideEngine;
import com.working.utils.ToastUtil;
import com.working.view.CommonDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.bzcoder.mediapicker.SmartMediaPicker;
import me.bzcoder.mediapicker.config.MediaPickerEnum;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * 该activity提交数据
 * 选择图片和视频
 * 支持录制和拍照
 *
 */
public abstract class BaseCommitActivity extends BaseActivity
        implements ICommitCallback, IUploadFileCallback {

    private static final String TAG = "BaseCommitActivity";
    //提交数据的presenter
    ICommitPresenter mPresenter;
    //文件上传的presenter
    IUploadFilePresenter mFilePresenter;
    private ProgressDialog mWaitingDialog;

    protected ImageCollectAdapter.OnImageClickListener mImageListener =
            new ImageCollectAdapter.OnImageClickListener() {
        @Override
        public void onImageClicked(String url) {
            AppRouter.toBrowseActivity(BaseCommitActivity.this, url);
        }

        @Override
        public void addRecord() {
            getRecordFile(BaseCommitActivity.this);
        }

        @Override
        public void onVideoClicked(String url) {
            AppRouter.toBrowseActivity(BaseCommitActivity.this, url);
        }
    };
    private int mFileCount;
    private int mFileSuccess;
    private int mFileFail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mWaitingDialog = new ProgressDialog(this);
        mPresenter.registerCallback(this);
        mFilePresenter = new UploadFilePresenterImpl();
        mFilePresenter.registerCallback(this);
    }


    /**
     * 由子类来创建提交数据的Presenter
     * @return
     */
    protected abstract ICommitPresenter createPresenter();

    /**
     * 提交数据
     * @param data
     */
    protected void commitData(ICommitData data, boolean isDraft){//审批数据和提交数据
        boolean alreadyCommitted = data.getStatus() == 1;
        if (mPresenter instanceof IApprovalPresenter){
            alreadyCommitted = false;
        }
        //询问是否提交数据
        final CommonDialog dialog = new CommonDialog(this);
        boolean finalAlreadyCommitted = alreadyCommitted;
        dialog.setMessage(isDraft?"是否保存为草稿?":"是否提交该数据？")
                .setImageResId(R.mipmap.question_icon)
                .setTitle(isDraft?"保存草稿":"提交数据")
                .setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                dialog.dismiss();
                if (finalAlreadyCommitted) {
                    ToastUtil.showMessage("数据不可重复提交！");
                    return;
                }else{
                    data.setStatus(isDraft?0:1);
                }
                postData(data);
            }

            @Override
            public void onNegtiveClick() {
                dialog.dismiss();
            }
        }).show();

    }


    private void postData(ICommitData data){
        if (mPresenter instanceof IApprovalPresenter){
            if( data instanceof  ApprovalBean){
                ((IApprovalPresenter)mPresenter).approvalData((ApprovalBean)data);
            }else if(data instanceof ApprovalOutBean){
                ((IApprovalPresenter)mPresenter).approvalOutData((ApprovalOutBean) data);
            }
        }else{
            mPresenter.uploadData(data);
        }
        showWaitingDialog("上传数据中");
    }

    /**
     * 上传文件
     */
    private  void uploadFile(String path, boolean ispic){
        if(ispic){
            final String imagePath = getFilesDir() + "/uploadImage/";
            File file = new File(path);
            compress(imagePath, file);
        }else{
            mFilePresenter.uploadFile(path);
            showWaitingDialog("上传视频中");
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK) {
            //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
            ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
            upDataUploadData(resultPhotos.size());
            Log.d(TAG, "onActivityResult: mFileCount == " + mFileCount);
            for (Photo resultPhoto : resultPhotos) {
                uploadFile(resultPhoto.path, true);
            }
            return;
        }
        List<String> resultData = SmartMediaPicker.getResultData(this, requestCode, resultCode, data);
        if (resultData != null && resultData.size() > 0) {
            upDataUploadData(resultData.size());
            Log.d(TAG, "onActivityResult: mFileCount == " + mFileCount);
            for (String resultDatum : resultData) {
                uploadFile(resultDatum,false);
            }
        }
    }

    private void upDataUploadData(int size) {
        mFileCount = size;
        mFileSuccess = 0;
        mFileFail = 0;
    }

    /**
     * 压缩文件并上传
     *
     * @param imagePath
     * @param file
     */
    private void compress(final String imagePath, File file) {
        Luban.with(this).load(file)
                .ignoreBy(100)
                .setTargetDir(imagePath)
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        //文件不存在择创建文件夹
                        File file = new File(imagePath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        return System.currentTimeMillis() + ".jpg";
                    }
                })
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        showWaitingDialog("压缩图片中");
                    }

                    @Override
                    public void onSuccess(File file) {
                        dismissWaitingDialog();
                        mFilePresenter.uploadFile(file.getAbsolutePath());
                        if (!mWaitingDialog.isShowing()) {
                            showWaitingDialog("上传图片中");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG , "onError: " + e.getMessage());
                    }
                }).launch();
    }


    //================================选择文件和相机====================================

    /**
     * 获取图片和视频
     */
    protected void getRecordFile(final FragmentActivity activity){
        getTakeRecordMethod(this, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 0://相册
                        getRecordWithAlbum(activity);
                        break;
                    case 1://拍照
                        getPhotoWithCamera(activity);
                        break;
                    case 2://拍视频
                        getRecordWithCamera(activity);
                        break;
                }
                return true;
            }
        });
    }

    private void getPhotoWithCamera(FragmentActivity activity) {
        EasyPhotos.createCamera(this)//参数说明：上下文
                .setFileProviderAuthority("com.working.fileprovider")//参数说明：见下方`FileProvider的配置`
                .start(111);
    }

    private void getRecordWithCamera(FragmentActivity activity) {
        SmartMediaPicker.builder(activity)
                //最大视频长度
                .withMaxVideoLength(15 * 1000)
                //最大视频文件大小 单位MB
                .withMaxVideoSize(1)
                //最大图片高度 默认1920
                .withMaxHeight(4032)
                //最大图片宽度 默认1920
                .withMaxWidth(3024)
                //最大图片大小 单位MB
                .withMaxImageSize(10)
                //设置图片加载引擎
                .withImageEngine(new Glide4Engine())
                //前置摄像头拍摄是否镜像翻转图像
                .withIsMirror(false)
                //弹出类别，默认弹出底部选择栏，也可以选择单独跳转
                .withMediaPickerType(MediaPickerEnum.CAMERA)
                .build()
                .show();
    }


    private static void getRecordWithAlbum(Activity activity) {
        EasyPhotos.createAlbum(activity, false, GlideEngine.getInstance())
                .setCount(9)//参数说明：最大可选数，默认1
                .start(111);
    }

    /**
     * 选择获取图像的方式
     *
     * @param context
     * @param callback
     */
    private static void getTakeRecordMethod(final Context context, final Handler.Callback callback) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("请选这获取方式");
        final String[] items = new String[]{
                "相册","相机(图片)","相机(视频)"
        };
        //-1表示没有默认选择
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            //which表示点击的是哪一个选项
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Message message = Message.obtain();
                message.what = which;
                callback.handleMessage(message);
                //对话框消失
                dialog.dismiss();
            }
        });

        builder.show();
    }

    //===============================提交数据的回调=====================================
    @Override
    public void onCommitFinished() {
        dismissWaitingDialog();
        showResultDialog("提交数据成功！", true, false);
    }

    @Override
    public void onCommitFail(String msg) {
        dismissWaitingDialog();
        onCommitFail();
        showResultDialog(msg, false, false);
    }

    protected abstract void onCommitFail();

    //==================================上传文件的回调====================================
    @Override
    public void onUploadFileFinished(PostedFileBean bean) {
        mFileSuccess++;
        showUploadFileResult();
        String link = bean.getData().getLink();
        addImageUrl(link);
    }

    protected abstract void addImageUrl(String link);

    @Override
    public void onUploadFileFail(String msg) {
        mFileFail++;
        showUploadFileResult();
    }

    private void showUploadFileResult(){
        Log.d(TAG, "onActivityResult: result == " +"共上传文件" + mFileCount + "个，其中成功"+ mFileSuccess + "个，失败" + mFileFail + "个");
        if(mFileCount == mFileFail + mFileSuccess){
            dismissWaitingDialog();
            String msg = "共上传文件" + mFileCount + "个，其中成功"+ mFileSuccess + "个，失败" + mFileFail + "个";
            showResultDialog( msg,true, true);
        }
    }


    //===========================dialog相关操作============================================

    private void showResultDialog(String msg, final boolean success, final boolean isFile){
            final CommonDialog dialog = new CommonDialog(this);
            dialog.setMessage(msg)
                    .setImageResId(success?R.mipmap.success_icon:R.mipmap.fail_icon)
                .setTitle(success?"上传成功":"上传失败")
                    .setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                @Override
                public void onPositiveClick() {
                    dialog.dismiss();
                    if(success && !isFile){
                        finish();
                    }
                }

                @Override
                public void onNegtiveClick() {
                    dialog.dismiss();
                    if(success && !isFile){
                        finish();
                    }
                }
            }).show();
        }


    /**
     * show等待Dialog
     */
    private void showWaitingDialog(String title) {
        mWaitingDialog.setTitle(title);
        mWaitingDialog.setMessage("等待中...");
        mWaitingDialog.setIndeterminate(true);
        mWaitingDialog.setCancelable(false);
        mWaitingDialog.show();
    }

    /**
     * show等待Dialog
     */
    private void dismissWaitingDialog() {
        mWaitingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterCallback();
        mFilePresenter.unregisterCallback();
    }
}

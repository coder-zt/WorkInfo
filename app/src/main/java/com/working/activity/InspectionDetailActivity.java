package com.working.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.InspectionRecordingAdapter;
import com.working.base.BaseCommitActivity;
import com.working.databinding.ActivityAddInspectionBinding;
import com.working.domain.InspectionFormData;
import com.working.domain.InspectionList;
import com.working.domain.RepairContent;
import com.working.domain.RepairMethod;
import com.working.interfaces.IRepairInfoCallback;
import com.working.other.MessageEvent;
import com.working.presenter.ICommitPresenter;
import com.working.presenter.IRepairInfo;
import com.working.presenter.impl.RepairInfoImpl;
import com.working.presenter.impl.UpLoadInspectPresenterImpl;
import com.working.setting.InspectionFiledInfo;
import com.working.utils.DevelopConfig;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加巡检记录的activity
 */
public class InspectionDetailActivity extends BaseCommitActivity implements IRepairInfoCallback {

    private static final String TAG = "AddInspectionActivity";

    private List<InspectionFiledInfo> mInspectFiled;
    private ActivityAddInspectionBinding mDataBinding;
    private List<InspectionFiledInfo> mShowFiledInfos;
    private boolean isUpdate;
    private InspectionFormData mInformation;
    private InspectionList.DataBean.RecordsBean mRecordsBean;
    private RecyclerView mRecycler;
    private InspectionRecordingAdapter mAdapter;
    //病害类型、维修方法、维修内容的进程code
    private int progressCode = 0;
    private IRepairInfo mRepairInfo = new RepairInfoImpl();
    private InspectionFiledInfo mMethodInfo;
    private InspectionFiledInfo mContentInfo;
    private List<RepairContent.DataBean> mContentData;
    private List<RepairMethod.DataBean> mMMethodData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_inspection);
        mDataBinding.setActivity(this);
        mRepairInfo.registerCallback(this);
        initView();
        initData();
        loadInspectionFileds();
    }

    private void initView() {
        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InspectionRecordingAdapter(this, mImageListener, new InspectionRecordingAdapter.OnGetRepairInfoListener() {
            @Override
            public void selectDamageAfter(int damageType) {
                progressCode = 1;
                if (mRepairInfo != null) {
                    mRepairInfo.getRepairMethod(damageType);
                }
                mMethodInfo.setRange(null);
                mMethodInfo.setValue("点击选择维修方法");
                mContentInfo.setRange(null);
                mContentInfo.setValue("点击选择维修内容");
            }

            @Override
            public void selectRepairMethodAfter(int damageType, int index) {
                progressCode = 2;
                int methodId = -1;
                if (mMMethodData != null && index <= mMMethodData.size()) {
                    methodId = mMMethodData.get(index-1).getRepairMethodId();
                    mMethodInfo.setValue(mMMethodData.get(index-1).getRepairMethodName());
                }
                mMethodInfo.setRangeIndex(methodId);
                if (mRepairInfo != null) {
                    mRepairInfo.getRepairContent(damageType, methodId);
                }
                mContentInfo.setRange(null);
                mContentInfo.setValue("点击选择维修内容");
            }

            @Override
            public void selectRepairContentAfter(int index) {
                progressCode = 3;
                if (mContentData != null && index <= mContentData.size()) {
                    mContentInfo.setRangeIndex(mContentData.get(index-1).getRepairContentId());
                    mContentInfo.setValue(mContentData.get(index-1).getRepairContentName());

                }
            }
        });
        mRecycler.setAdapter(mAdapter);
    }

    protected void initData() {
        String data = getIntent().getStringExtra("data");
        if (data == null) {
            mAdapter.setCommit(false);
            isUpdate = false;
            return;
        }
        isUpdate = true;
        mRecordsBean = new Gson().fromJson(data, InspectionList.DataBean.RecordsBean.class);
        mInformation = new InspectionFormData();
        FileUtils.copyValue(mInformation, mRecordsBean);
        mAdapter.setCommit(mRecordsBean.getStatus() == 1);
    }


    @Override
    protected ICommitPresenter createPresenter() {
        return new UpLoadInspectPresenterImpl();
    }

    @Override
    protected void onCommitFail() {
        mInformation.setStatus(0);
    }


    public void loadInspectionFileds(){
        //获取需要填写的参数
        mInspectFiled = FileUtils.getInspectFiled();
        mShowFiledInfos = new ArrayList<>();
        for (InspectionFiledInfo filedInfo : mInspectFiled) {
            if (filedInfo.getShow() == 1) {
                mShowFiledInfos.add(filedInfo);
            }
            if(TextUtils.equals(filedInfo.getAlias(),"维修方法")){
                mMethodInfo = filedInfo;
            }else if(TextUtils.equals(filedInfo.getAlias(),"维修内容")){
                mContentInfo = filedInfo;
            }
        }
        if (mInformation != null) {
            if (mRepairInfo != null) {
                mRepairInfo.getRepairMethod(mInformation.getDiseaseType());
                mRepairInfo.getRepairContent(mInformation.getDiseaseType(), Integer.parseInt(mInformation.getRepairMethodId()));

            }
            for (InspectionFiledInfo filedInfo : mShowFiledInfos) {
                //缺损质量
                if(TextUtils.equals(filedInfo.getAlias(),"缺损质量")){
                    filedInfo.setValue(mInformation.getDefectQuality());
                //病害类型
                }else if(TextUtils.equals(filedInfo.getAlias(),"病害类型")){
                    int rangeIndex = mInformation.getDiseaseType();
                    if(DevelopConfig.DEBUG){
                        rangeIndex = 13;
                    }
                    filedInfo.setRangeIndex(rangeIndex);
                    if(rangeIndex == 0){
                        filedInfo.setValue("点击选择病害类型");
                    }else{
                        filedInfo.setValue(filedInfo.getRange().get(rangeIndex - 1));
                    }
                }else if(TextUtils.equals(filedInfo.getAlias(),"维修方法")){
                    filedInfo.setRangeIndex(Integer.parseInt(mRecordsBean.getRepairMethodId()));
                    if(mInformation.getRepairMethodId().isEmpty()){
                        filedInfo.setValue("点击选择维修方法");
                    }else{
                        filedInfo.setValue(mRecordsBean.getRepairMethod());
                    }
                }else if(TextUtils.equals(filedInfo.getAlias(),"维修内容")){
                    filedInfo.setRangeIndex(Integer.parseInt(mRecordsBean.getRepairContentId()));
                    if(mInformation.getRepairContentId().isEmpty()){
                        filedInfo.setValue("点击选择维修内容");
                    }else{
                        filedInfo.setValue(mRecordsBean.getRepairContent());
                    }
                //巡检记录描述
                }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录描述")){
                    filedInfo.setValue(mInformation.getExtension());
                //程度
                }else if(TextUtils.equals(filedInfo.getAlias(),"程度")){
                    filedInfo.setValue(mInformation.getExtent());
                //养护措施
                }else if(TextUtils.equals(filedInfo.getAlias(),"养护措施")){
                    filedInfo.setValue(mInformation.getMaintenanceMeasures());
                //性质
                }else if(TextUtils.equals(filedInfo.getAlias(),"性质")){
                    filedInfo.setValue(mInformation.getNature());
                //缺损位置-具体桩号
                }else if(TextUtils.equals(filedInfo.getAlias(),"缺损位置")){
                    filedInfo.setValue(mInformation.getPileNo());
                //标度
                }else if(TextUtils.equals(filedInfo.getAlias(),"标度")){
                    filedInfo.setValue(mInformation.getScale());
                //结构名称
                }else if(TextUtils.equals(filedInfo.getAlias(),"结构名称")){
                    filedInfo.setValue(mInformation.getStructureName());
                //巡检记录标题入
                }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录标题")){
                    filedInfo.setValue(mInformation.getTitle());
                }else if(TextUtils.equals(filedInfo.getAlias(),"上传图片")){
                    filedInfo.setValue(mRecordsBean.getPicUrl());
                }
            }
        }
        mAdapter.setData(mShowFiledInfos);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addInspection(boolean isDraft){

        if (mInformation == null) {
            mInformation = new InspectionFormData();
        }
        for (InspectionFiledInfo filedInfo : mShowFiledInfos) {
            //缺损质量->用户输入
            if(TextUtils.equals(filedInfo.getAlias(),"缺损质量")){
                mInformation.setDefectQuality(filedInfo.getValue());
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("缺损质量为空！");
                    return;
                }
            //病害类型->用户选择
            }else if(TextUtils.equals(filedInfo.getAlias(),"病害类型")){
                int rangeIndex = filedInfo.getRangeIndex();
                if (rangeIndex == 0) {
                    ToastUtil.showMessage("未选择病害类型！");
                    return;
                }
                mInformation.setDiseaseType(rangeIndex);
            //维修方法->用户选择
            }else if(TextUtils.equals(filedInfo.getAlias(),"维修方法")){
                int rangeIndex = filedInfo.getRangeIndex();
                if (rangeIndex == 0) {
                    ToastUtil.showMessage("未选择维修方法！");
                    return;
                }
                mInformation.setRepairMethodId(String.valueOf(rangeIndex));
                //维修内容->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"维修内容")){
                int rangeIndex = filedInfo.getRangeIndex();
                if (rangeIndex == 0) {
                    ToastUtil.showMessage("未选择维修内容！");
                    return;
                }
                mInformation.setRepairContentId(String.valueOf(rangeIndex));
                //巡检记录描述->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录描述")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("巡检记录描述为空！");
                    return;
                }
                mInformation.setExtension(filedInfo.getValue());
            //程度->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"程度")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("程度为空！");
                    return;
                }
                mInformation.setExtent(filedInfo.getValue());
            //养护措施->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"养护措施")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("养护措施为空！");
                    return;
                }
                mInformation.setMaintenanceMeasures(filedInfo.getValue());
            //性质->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"性质")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("性质为空！");
                    return;
                }
                mInformation.setNature(filedInfo.getValue());
            //缺损位置-具体桩号->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"缺损位置")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("缺损位置为空！");
                    return;
                }
                mInformation.setPileNo(filedInfo.getValue() );
            //标度->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"标度")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("标度为空！");
                    return;
                }
                mInformation.setScale(filedInfo.getValue());
            //结构名称->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"结构名称")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("结构名称为空！");
                    return;
                }
                mInformation.setStructureName(filedInfo.getValue());
            //巡检记录标题->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录标题")){
                if (filedInfo.getValue().isEmpty()) {
                    ToastUtil.showMessage("巡检记录标题为空！");
                    return;
                }
                mInformation.setTitle(filedInfo.getValue());
            }
        }
        //设置图片数据
        mInformation.setPicUrl(mAdapter.getImageCollect());
        if (isUpdate) {
            mInformation.setId(mRecordsBean.getId());
        }

//        Log.d(TAG, "addInspection: 提交信息：" +  new Gson().toJson(mInformation));
        commitData(mInformation, isDraft);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals(MessageEvent.upload_data_success)){
            finish();
        }
    }
    /**
     * layout访问的数据
     * @return
     */
    public boolean isUpdate() {
        return isUpdate;
    }


    @Override
    protected void addImageUrl(String link) {
        mAdapter.addImageUrl(link);
    }


    /**********************
     *选择维修方法和维修内容 *
     **********************/


    /**
     *
     * @param data
     */
    @Override
    public void onRepairMethodLoaded(List<RepairMethod.DataBean> data) {
        Log.d(TAG, "onRepairContentLoaded: " + "修复方法" + data.size());
        List<String> strs = new ArrayList<>();
        for (RepairMethod.DataBean datum : data) {
            strs.add(datum.getRepairMethodName());
        }
        mMMethodData = data;
        mMethodInfo.setRange(strs);
    }

    @Override
    public void onRepairMethodLoadedFail(String msg) {
        Log.d(TAG, "onRepairMethodLoadedFail: " + msg);
    }

    @Override
    public void onRepairContentLoaded(List<RepairContent.DataBean> data) {
        Log.d(TAG, "onRepairContentLoaded: " + "修复内容" + data.size());
        List<String> strs = new ArrayList<>();
        for (RepairContent.DataBean datum : data) {
            strs.add(datum.getRepairContentName());
        }
        mContentData = data;
        mContentInfo.setRange(strs);
    }

    @Override
    public void onRepairContentLoadedFail(String msg) {
        Log.d(TAG, "onRepairContentLoadedFail: " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRepairInfo.unregisterCallback();
    }
}

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
import com.working.other.MessageEvent;
import com.working.presenter.ICommitPresenter;
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
public class AddInspectionActivity extends BaseCommitActivity<InspectionFormData> {

    private static final String TAG = "AddInspectionActivity";

    private List<InspectionFiledInfo> mInspectFiled;
    private ActivityAddInspectionBinding mDataBinding;
    private List<InspectionFiledInfo> mFiledInfos;
    private List<InspectionFiledInfo> mShowFiledInfos;
    private boolean isUpdate;
    private InspectionFormData mInformation;
    private InspectionList.DataBean.RecordsBean mRecordsBean;
    private RecyclerView mRecycler;
    private InspectionRecordingAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_inspection);
        mDataBinding.setActivity(this);
        initView();
        initData();
        loadInspectionFileds();
    }

    private void initView() {
        mRecycler = findViewById(R.id.recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InspectionRecordingAdapter(this, mImageListener);
        mRecycler.setAdapter(mAdapter);
    }

    protected void initData() {
        String data = getIntent().getStringExtra("data");
        if (data == null) {
            isUpdate = false;
            return;
        }
        isUpdate = true;
        mRecordsBean = new Gson().fromJson(data, InspectionList.DataBean.RecordsBean.class);
        mInformation = new InspectionFormData();
        FileUtils.copyValue(mInformation, mRecordsBean);
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
        }
        if (mInformation != null) {
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
                    filedInfo.setValue(filedInfo.getRange().get(rangeIndex - 1));
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

        //已经提交的记录无法更新/或保存为草稿
        if(mInformation.getStatus() == 1){
            ToastUtil.showMessage("已提交的记录无法更新！");
            return;
        }
        //业务状态->0（草稿）->1(提交)
        mInformation.setStatus(isDraft?0:1);
        for (InspectionFiledInfo filedInfo : mShowFiledInfos) {
            //缺损质量->用户输入
            if(TextUtils.equals(filedInfo.getAlias(),"缺损质量")){
                mInformation.setDefectQuality(filedInfo.getValue());
            //病害类型->用户选择
            }else if(TextUtils.equals(filedInfo.getAlias(),"病害类型")){
                int rangeIndex = filedInfo.getRangeIndex();
                if(DevelopConfig.DEBUG){
                    rangeIndex = 13;
                }
                mInformation.setDiseaseType(rangeIndex);
            //巡检记录描述->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录描述")){
                mInformation.setExtension(filedInfo.getValue());
            //程度->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"程度")){
                mInformation.setExtent(filedInfo.getValue());
            //养护措施->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"养护措施")){
                mInformation.setMaintenanceMeasures(filedInfo.getValue());
            //性质->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"性质")){
                mInformation.setNature(filedInfo.getValue());
            //缺损位置-具体桩号->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"缺损位置")){
                mInformation.setPileNo(filedInfo.getValue() );
            //标度->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"标度")){
                mInformation.setScale(filedInfo.getValue());
            //结构名称->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"结构名称")){
                mInformation.setStructureName(filedInfo.getValue());
            //巡检记录标题->用户输入
            }else if(TextUtils.equals(filedInfo.getAlias(),"巡检记录标题")){
                mInformation.setTitle(filedInfo.getValue());
            }
        }
        //设置图片数据
        mInformation.setPicUrl(mAdapter.getImageCollect());
        if (isUpdate) {
            mInformation.setId(mRecordsBean.getId());
        }
        Log.d(TAG, "addInspection: " + new Gson().toJson(mInformation));
        commitData(mInformation);
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
}

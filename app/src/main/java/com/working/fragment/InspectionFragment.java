package com.working.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.working.R;
import com.working.adapter.ImageCollectAdapter;
import com.working.adapter.InspectionRecordingAdapter;
import com.working.base.BaseCommitActivity;
import com.working.base.BaseFragment;
import com.working.databinding.FragmentInspectionBinding;
import com.working.domain.InspectionDetail;
import com.working.domain.InspectionFormData;
import com.working.domain.InspectionList;
import com.working.models.AppModels;
import com.working.setting.InspectionFiledInfo;
import com.working.utils.AppRouter;
import com.working.utils.DevelopConfig;
import com.working.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购订单审批-巡检记录的fragment
 */
public class InspectionFragment extends BaseFragment<FragmentInspectionBinding> {

    private InspectionFormData mInformation;
    private List<InspectionFiledInfo> mInspectFiled;
    private InspectionDetail.DataBean mRecordsBean;
    private InspectionRecordingAdapter mAdapter;

    @Override
    protected void initView(View view) {
        mAdapter = new InspectionRecordingAdapter(getActivity(), new ImageCollectAdapter.OnImageClickListener() {
            @Override
            public void onImageClicked(String url) {
                AppRouter.toBrowseActivity(getActivity(), url);
            }

            @Override
            public void addRecord() {

            }

            @Override
            public void onVideoClicked(String url) {
                AppRouter.toBrowseActivity(getActivity(), url);
            }
        });
        getDataBinding().recyclerView.setAdapter(mAdapter);
    }

    private static final String TAG = "InspectionFragment";
    @Override
    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        String id = null;
        if (arguments != null) {
            id = arguments.getString("data");
        }
        if(id != null){
            AppModels.getInstance().getInspectionInfo(id, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    if (msg.obj instanceof InspectionDetail) {
                        mRecordsBean = ((InspectionDetail)msg.obj).getData();
                        mInformation = new InspectionFormData();
                        FileUtils.copyValue(mInformation, mRecordsBean);
                        loadInspectionFileds();
                    }
                    return false;
                }
            });
        }//id:1354792457353867266
    }//1354792456116547586

    public void loadInspectionFileds(){
        //获取需要填写的参数
        mInspectFiled = FileUtils.getInspectFiled();
        List<InspectionFiledInfo> mShowFiledInfos = new ArrayList<>();
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
                    filedInfo.setRangeIndex(rangeIndex);
                    if(rangeIndex == 0){
                        filedInfo.setValue("点击选择病害类型");
                    }else{
                        filedInfo.setValue(filedInfo.getRange().get(rangeIndex - 1));
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
        mAdapter.setCommit(true);
        mAdapter.setData(mShowFiledInfos);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inspection;
    }
}

package com.working.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.working.R;
import com.working.base.BaseFragment;
import com.working.databinding.FragmentInspectionBinding;
import com.working.domain.InspectionDetail;
import com.working.domain.InspectionList;
import com.working.models.AppModels;

/**
 * 采购订单审批-巡检记录的fragment
 */
public class InspectionFragment extends BaseFragment {

    @Override
    protected void initView(View view) {


    }

    private static final String TAG = "InspectionFragment";
    @Override
    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        String id = arguments.getString("data");
        AppModels.getInstance().getInspectionInfo(id, new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.obj instanceof InspectionDetail) {
                    InspectionDetail data = (InspectionDetail)msg.obj;
                    InspectionDetail.DataBean data1 = data.getData();
                    ((FragmentInspectionBinding)getDataBinding()).setData(data1);
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inspection;
    }
}

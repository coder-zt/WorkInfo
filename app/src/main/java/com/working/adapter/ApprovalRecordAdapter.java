package com.working.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.SearchAdapter;
import com.working.databinding.RecyclerApprovalRecordBinding;
import com.working.databinding.RecyclerInspectionInfoLayoutBinding;
import com.working.domain.ApprovalRecord;

import java.util.ArrayList;
import java.util.List;


/**
 * 巡检记录的适配器
 */
public class ApprovalRecordAdapter extends SearchAdapter<ApprovalRecordAdapter.RecordView, ApprovalRecord.DataBean.RecordsBean> {

    private List<ApprovalRecord.DataBean.RecordsBean> mData = new ArrayList<>();

    private static final String TAG = "FunctionListAdapter";

    public ApprovalRecordAdapter() {
//        mCallback = listener;
    }


    @NonNull
    @Override
    public RecordView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_approval_record, parent, false);
        RecyclerApprovalRecordBinding bind = DataBindingUtil.bind(view);
        return new RecordView(view, bind);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordView holder, int position) {
        holder.getBinding().setItemView(holder);
        holder.getBinding().setItem(filterData.get(position));
    }

    @Override
    public int getItemCount() {
        return filterData(mData);
    }



    public void addData(List<ApprovalRecord.DataBean.RecordsBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<ApprovalRecord.DataBean.RecordsBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }



    public class RecordView extends RecyclerView.ViewHolder {
        RecyclerApprovalRecordBinding mBinding = null;

        public RecordView(View view, RecyclerApprovalRecordBinding bind) {
            super(view);
            mBinding = bind;
        }

        public RecyclerApprovalRecordBinding getBinding() {
            return mBinding;
        }

//        public void onItemClick(ApprovalRecord.DataBean.RecordsBean data){
//            mCallback.onItemClick(data);
//        }
    }


//    public interface OnItemClickedListener{
//        void onItemClick(ApprovalRecord.DataBean.RecordsBean data);
//    }
}

package com.working.adapter;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerInspectionInfoLayoutBinding;
import com.working.domain.InspectionList;
import com.working.domain.RecordsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 巡检记录的适配器
 */
public class InspectionInfoAdapter extends RecyclerView.Adapter<InspectionInfoAdapter.InformationView>{
//        BaseRecyclerAdapter<InspectionInfoAdapter.InformationView,
//        InspectionList.DataBean.RecordsBean, RecyclerInspectionInfoLayoutBinding> {

    private List<InspectionList.DataBean.RecordsBean> mData = new ArrayList<>();

    private static final String TAG = "FunctionListAdapter";
    private final OnItemClickedListener mCallback;

    public InspectionInfoAdapter(OnItemClickedListener listener) {
        mCallback = listener;
    }


    @NonNull
    @Override
    public InformationView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_inspection_info_layout, parent, false);
        RecyclerInspectionInfoLayoutBinding bind = DataBindingUtil.bind(view);
        return new InformationView(view, bind);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationView holder, int position) {
        holder.getBinding().setItemView(holder);
        holder.getBinding().setItem(mData.get(position));
        if (position == mData.size()-1){
            holder.bottomView.setVisibility(View.VISIBLE);
        }else {
            holder.bottomView.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<InspectionList.DataBean.RecordsBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<InspectionList.DataBean.RecordsBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class InformationView extends RecyclerView.ViewHolder {
        RecyclerInspectionInfoLayoutBinding mBinding = null;

        ImageView bottomView;
        public InformationView(View view, RecyclerInspectionInfoLayoutBinding bind) {
            super(view);
            mBinding = bind;
            bottomView = view.findViewById(R.id.bottom_view);
        }

        public RecyclerInspectionInfoLayoutBinding getBinding() {
            return mBinding;
        }

        public void onItemClick(InspectionList.DataBean.RecordsBean data){
            mCallback.onItemClick(data);
        }
    }


    public interface OnItemClickedListener{
        void onItemClick(InspectionList.DataBean.RecordsBean data);
    }
}

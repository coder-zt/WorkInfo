package com.working.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.SearchAdapter;
import com.working.databinding.RecyclerBalanceLayoutBinding;
import com.working.domain.RepBalData;
import com.working.utils.UserDataMan;
import com.working.view.CounterView;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends SearchAdapter<MaterialAdapter.ItemView, RepBalData.DataBean.RecordsBean>
        implements IAddData<RepBalData.DataBean.RecordsBean>{

    List<RepBalData.DataBean.RecordsBean> mData = new ArrayList<>();
    private final OnItemClickListener mCallback;
    private String searchInfo = "";

    public MaterialAdapter(OnItemClickListener listener){
        mCallback = listener;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_balance_layout, parent, false);
        RecyclerBalanceLayoutBinding binding = DataBindingUtil.bind(view);
        return new ItemView(view, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        holder.mBinding.counterView.setNumChangeListener(new CounterView.OnNumberChangedListener() {
            @Override
            public void onNumChanged(float num) {
                float quantity = Float.parseFloat(filterData.get(position).getQuantity());
                if(num == quantity ){
                    return;
                }
                filterData.get(position).setQuantity(String.valueOf(num));
                String freezeQuantity = filterData.get(position).getFreezeQuantity();
                if(freezeQuantity != null && freezeQuantity.length() > 0){
                    float freezeNum = Float.parseFloat(freezeQuantity);
                    filterData.get(position).setAvailableQuantity(String.valueOf(num - freezeNum));
                }
                filterData.get(position).setStatus(-1);
                holder.mBinding.setData(mData.get(position));
            }
        });
        String freezeQuantity = filterData.get(position).getFreezeQuantity();
        if(freezeQuantity != null && freezeQuantity.length() > 0){
            holder.mBinding.counterView.setScopeValue(freezeQuantity, "9999.0");
        }
        holder.mBinding.setData(filterData.get(position));
        holder.mBinding.setClickObject(holder);
        holder.mBinding.counterView.setNum(Float.parseFloat(filterData.get(position).getQuantity()));
        holder.mBinding.counterView.setCanEdit(UserDataMan.getInstance().checkMaterialGrant());
    }

    @Override
    public int getItemCount() {
        return filterData(mData);
    }

    @Override
    public void setCollectData(List<RepBalData.DataBean.RecordsBean> data) {
        mData.clear();
        if (data != null) {
            for (RepBalData.DataBean.RecordsBean datum : data) {
                datum.setStatus(0);
            }
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addCollectData(List<RepBalData.DataBean.RecordsBean> data) {
        if (data != null) {
            for (RepBalData.DataBean.RecordsBean datum : data) {
                datum.setStatus(0);
            }
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addTopData(RepBalData.DataBean.RecordsBean data){
        if (data != null) {
            data.setStatus(-1);
            mData.add(0, data);
        }
        notifyDataSetChanged();
    }

    public boolean checkHaveNoSaveData(OnNoSaveDataListener listener){
        List<RepBalData.DataBean.RecordsBean> noSaveData = new ArrayList<>();
        if (mData != null) {
            for (RepBalData.DataBean.RecordsBean datum : mData) {
                if (datum.getStatus() == -1) {
                    noSaveData.add(datum);
                }
            }
        }
        if(noSaveData.size() > 0){
            if (listener != null) {
                listener.onNoSaveData(noSaveData);
            }
            return true;
        }
        return false;
    }

    public void update(RepBalData.DataBean.RecordsBean recordsBean) {
        if (mData.contains(recordsBean)) {
            int i = mData.indexOf(recordsBean);
            notifyItemChanged(i);
        }
    }


    public class ItemView extends RecyclerView.ViewHolder {

        private RecyclerBalanceLayoutBinding mBinding;
        public ItemView(@NonNull View itemView, RecyclerBalanceLayoutBinding binding) {
            super(itemView);
            mBinding = binding;
        }


        public void onItemClicked(RepBalData.DataBean.RecordsBean recordsBean){
            if (mCallback != null) {
                mCallback.onItemClicked(recordsBean);
            }
        }

        public RecyclerBalanceLayoutBinding getBinding() {
            return mBinding;
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(RepBalData.DataBean.RecordsBean data);
    }

    public interface OnNoSaveDataListener{
        void onNoSaveData(List<RepBalData.DataBean.RecordsBean> data);
    }
}

package com.working.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.databinding.RecyclerInLayoutBinding;
import com.working.domain.RepertoryIn;

import java.util.ArrayList;
import java.util.List;

public class ReperInAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements IAddData<RepertoryIn.DataBean.RecordsBean>{

    List<RepertoryIn.DataBean.RecordsBean> mData = new ArrayList<>();
    private final ImageAlbumAdapter mAdapter;
    private final OnItemClickListener mCallback;
    private String searchInfo = "";

    public ReperInAdapter(Activity activity, OnItemClickListener listener){
        mAdapter = new ImageAlbumAdapter();
        mCallback = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_in_layout, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerInLayoutBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setData(mData.get(position));
        bind.setClickObject((ItemView)holder);
        ((ItemView)holder).setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        List<RepertoryIn.DataBean.RecordsBean> tempData = new ArrayList<>();
        List<Integer> indexData = new ArrayList<>();
        int i = 0;
        for (RepertoryIn.DataBean.RecordsBean datum : mData) {
            if (!datum.getSearchInfo().contains(searchInfo)) {
                indexData.add(i);
            }
            i++;
        }
        i = 0;
        for (Integer indexDatum : indexData) {
            tempData.add(mData.remove((int)indexDatum - i));
            i++;
        }
        mData.addAll(tempData);
        int filter = mData.size() - indexData.size();
        return filter;
    }

    @Override
    public void setCollectData(List<RepertoryIn.DataBean.RecordsBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addCollectData(List<RepertoryIn.DataBean.RecordsBean> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class ItemView extends RecyclerView.ViewHolder {

        private final RecyclerView mRecyclerView;

        public ItemView(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
            mRecyclerView.setAdapter(mAdapter);
        }

        public void setData(RepertoryIn.DataBean.RecordsBean recordsBean) {
            String picUrl = recordsBean.getPicUrl();
            if(picUrl.length() == 0){
                return;
            }
            if(picUrl.contains(",")){
                String[] split = picUrl.split(",");
                List<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    imageUrls.add(split[i]);
                }
                mAdapter.setImageCollect(imageUrls);
            }else if(!picUrl.isEmpty()){
                List<String> imageUrls = new ArrayList<>();
                imageUrls.add(picUrl);
                mAdapter.setImageCollect(imageUrls);
            }
        }

        public void onItemClicked(RepertoryIn.DataBean.RecordsBean recordsBean){
            if (mCallback != null) {
                mCallback.onItemClicked(recordsBean);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(RepertoryIn.DataBean.RecordsBean data);
    }
}

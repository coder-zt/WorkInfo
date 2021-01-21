package com.working.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.activity.OrderDetailActivity;
import com.working.databinding.RecyclerMaterialLayoutBinding;
import com.working.databinding.RecyclerRepinDetailLayoutBinding;
import com.working.domain.RepInInfoData;
import com.working.utils.FileUtils;
import com.working.view.CounterView;

import java.util.ArrayList;
import java.util.List;

public class RepInDetailAdapter extends RecyclerView.Adapter {
    private List<RepInInfoData.DataBean.InStockItemListBean> mData = new ArrayList<>();
    private final int TYPE_DATA = 0;
    private final int TYPE_ADD = 1;
    private final int TYPE_PIC = 2;
    private boolean mIsCommitted = false;
    private OnDataContainerListener mCallback;
    private final ImageCollectAdapter mPicAdapter;

    public RepInDetailAdapter(OnDataContainerListener listener,
                              ImageCollectAdapter.OnImageClickListener imageClickListener) {
        mCallback = listener;
        mPicAdapter = new ImageCollectAdapter(imageClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case TYPE_DATA://物料
                RecyclerRepinDetailLayoutBinding binding = DataBindingUtil.inflate(inflater,
                        R.layout.recycler_repin_detail_layout, parent, false);
                return new MaterialDataView(binding.getRoot(), binding);
            case TYPE_PIC://图片
                View picView = inflater.inflate(R.layout.recycler_image_layout, parent, false);
                return new ImageCollectView(picView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MaterialDataView){
            ((MaterialDataView) holder).getBinding().setData(mData.get(position));
            ((MaterialDataView) holder).getBinding().counterView.setNumChangeListener(new CounterView.OnNumberChangedListener() {
                @Override
                public void onNumChanged(float num) {
                    RepInInfoData.DataBean.InStockItemListBean orderItemListBean = mData.get(position);
                    orderItemListBean.setProductQuantity(String.valueOf(num));
                    if (mCallback != null) {
                        mCallback.onDataContainerChanged(mData, mPicAdapter.getImageCollect());
                    }
                }
            });
            ((MaterialDataView) holder).getBinding().counterView.setCanEdit(!mIsCommitted);
            ((MaterialDataView) holder).getBinding().counterView.setNum(Float.parseFloat(mData.get(position).getProductQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int size = mData.size();
        if(position < size){//物料数据
            return TYPE_DATA;
        }else{//图片集合
            return TYPE_PIC;
        }
    }

    /**
     * 设置该数据是否是已提交数据
     *
     * @param committed
     */
    public void setCommitted(boolean committed){
        mIsCommitted = committed;
        mPicAdapter.setIsCommit(mIsCommitted);
    }

    /**
     * 设置详情的物料数据
     * @param data
     */
    public void setData(List<RepInInfoData.DataBean.InStockItemListBean> data){
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        if (mCallback != null) {
            mCallback.onDataCountChange(0, mData.size());
        }
        notifyDataSetChanged();
    }

    /**
     * 添加详情的物料数据
     * @param data
     */
    public void addData(RepInInfoData.DataBean.InStockItemListBean data){
        if (data != null) {
            mData.add(data);
            if (mCallback != null) {
                mCallback.onDataCountChange(mData.size()-1, mData.size());
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 设置文件
     * @param url
     */
    public void setPicUrls(String url){
        List<String> urls = new ArrayList<>();
        if (url.contains(",")) {
            String[] urlArray = url.split("\\.");
            for (String s : urlArray) {
                urls.add(s);
            }
        }else if(url.length() != 0){
            urls.add(url);
        }
        if (mPicAdapter != null) {
            mPicAdapter.setImageCollect(urls);
        }
    }

    /**
     * 添加文件数据
     * @param url
     */
    public void addPicUrl(String url){
        if (mPicAdapter != null) {
            mPicAdapter.addImage(url);
        }
        if (mCallback != null) {
            mCallback.onDataContainerChanged(mData, mPicAdapter.getImageCollect());
        }
    }

    public void deleteData(int adapterPosition) {
        mData.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        if (mCallback != null) {
            mCallback.onDataContainerChanged(mData, mPicAdapter.getImageCollect());
        }
    }

    /**
     * 物料
     */
    public class MaterialDataView extends RecyclerView.ViewHolder {
        private RecyclerRepinDetailLayoutBinding mBinding;
        public MaterialDataView(@NonNull View itemView, RecyclerRepinDetailLayoutBinding binding) {
            super(itemView);
            mBinding = binding;
        }

        public RecyclerRepinDetailLayoutBinding getBinding() {
            return mBinding;
        }
    }

    /**
     * 增加物料
     */
    public class ImageCollectView extends RecyclerView.ViewHolder {

        public ImageCollectView(@NonNull View itemView) {
            super(itemView);
            RecyclerView recyclerView = itemView.findViewById(R.id.recycler_view);
            TextView tvPicTitle = itemView.findViewById(R.id.tv_pic_title);
            recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
            recyclerView.setAdapter(mPicAdapter);
            tvPicTitle.setText(mIsCommitted?"相关文件":"上传文件");
        }
    }

    public interface OnDataContainerListener{

        void onDataContainerChanged(List<RepInInfoData.DataBean.InStockItemListBean> data, String urls) ;

        void onDataCountChange(int oldSize, int newSize) ;
    }
}

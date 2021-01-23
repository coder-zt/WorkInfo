package com.working.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.databinding.RecyclerRepoutApprovalLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ApprovalOutAdapter extends RecyclerView.Adapter {

    List<ItemData> mDataList = new ArrayList<>();
    private final ImageCollectAdapter mAdapter;

    public ApprovalOutAdapter(){
        mAdapter = new ImageCollectAdapter(null);
        mAdapter.setIsCommit(true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){//物料数据
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_repout_approval_layout, parent, false);
            RecyclerRepoutApprovalLayoutBinding mBind = DataBindingUtil.bind(view);
            return new ItemView(view,mBind);
        }else if(viewType == 2){//图片
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_image_layout, parent, false);
            return new ImageCollectView(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemView) {
            ((ItemView)holder).getBinding().setData(mDataList.get(position));
            ((ItemView)holder).getBinding().counterView.setCanEdit(false);
            ((ItemView) holder).getBinding().counterView.setNum(Float.parseFloat(mDataList.get(position).getProductQuantity()));
        }else if(holder instanceof  ImageCollectView){
            ((ImageCollectView)holder).setData(mDataList.get(position).getPicUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(!mDataList.get(position).isPic){
            return 1;
        }else{
            return 2;
        }
    }

    public void setData(List<ItemData> data){
        mDataList.clear();
        if (data != null) {
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public static class ItemView extends RecyclerView.ViewHolder{

        RecyclerRepoutApprovalLayoutBinding mBinding;
        public ItemView(@NonNull View itemView, RecyclerRepoutApprovalLayoutBinding bind) {
            super(itemView);
            mBinding = bind;
        }


        public RecyclerRepoutApprovalLayoutBinding getBinding(){
            return mBinding;
        }
    }

    public class ImageCollectView extends RecyclerView.ViewHolder {

        private final RecyclerView mRecycler;
        private final TextView mTv;


        public ImageCollectView(@NonNull View itemView) {
            super(itemView);
            mRecycler = itemView.findViewById(R.id.recycler_view);
            mRecycler.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
            mRecycler.setAdapter(mAdapter);
            mTv = itemView.findViewById(R.id.tv_pic_title);
        }

        public void setData(String url){
            if(url.contains(",")){
                String[] split = url.split(",");
                List<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    imageUrls.add(split[i]);
                }
                mAdapter.setImageCollect(imageUrls);
            }else if(!url.isEmpty()){
                List<String> imageUrls = new ArrayList<>();
                imageUrls.add(url);
                mAdapter.setImageCollect(imageUrls);
            }else{
                mTv.setVisibility(View.GONE);
                mAdapter.setImageCollect(null);
            }
        }
    }

    public static class ItemData{
        private String id;
        private String materialName;
        private String unit;
        private String price;
        private String productQuantity;
        private int owned;
        private int remainingQuantity;
        private String picUrl;
        private boolean isPic;

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(String productQuantity) {
            this.productQuantity = productQuantity;
        }

        public int getOwned() {
            return owned;
        }

        public void setOwned(int owned) {
            this.owned = owned;
        }

        public int getRemainingQuantity() {
            return remainingQuantity;
        }

        public void setRemainingQuantity(int remainingQuantity) {
            this.remainingQuantity = remainingQuantity;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public boolean isPic() {
            return isPic;
        }

        public void setPic(boolean pic) {
            isPic = pic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "物料名称：" + materialName +
                    "\n单位：" + unit +
                    "\n价格：" + price +
                    "\n出库存量："+ productQuantity +
                    "\n是否自有" + (owned == 1?"是":"否")+
                    "\n余量：" + remainingQuantity;
        }
    }

}

package com.working.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.activity.OrderDetailActivity;
import com.working.databinding.RecyclerOrderDetailLayoutBinding;
import com.working.databinding.RecyclerPurchaseApprovalLayoutBinding;
import com.working.domain.OrderDetail;
import com.working.domain.Purchase;
import com.working.domain.PurchaseDetail;
import com.working.view.CounterView;

import java.util.ArrayList;
import java.util.List;

/**
 * 详细数据的适配器
 *
 * 草稿状态：单个物料、添加物料、选择图片
 *
 * 已提交状态：单个物料、相关图片
 */
public class PurchaseApprovalAdapter extends RecyclerView.Adapter {
    private List<PurchaseDetail.DataBean.PurchaseItemListBean> mData = new ArrayList<>();
    private boolean committed;
    private final int TYPE_DATA = 0;
    private final int TYPE_ADD = 1;
    private final int TYPE_PIC = 2;
    private boolean mIsCommitted = false;
    private OnDataContainerListener mCallback;
    private final ImageCollectAdapter mPicAdapter;

    public PurchaseApprovalAdapter(OnDataContainerListener listener,
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
                RecyclerPurchaseApprovalLayoutBinding binding = DataBindingUtil.inflate(inflater,
                        R.layout.recycler_purchase_approval_layout, parent, false);
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
            ((MaterialDataView) holder).getBinding().counterView.setNum(Float.parseFloat(mData.get(position).getProductQuantity()));
            ((MaterialDataView) holder).getBinding().setIsCommit(true);//审批适配器都是已经提交的数据
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();//审批适配器不需要图片和添加物料
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
    public void setData(List<PurchaseDetail.DataBean.PurchaseItemListBean> data){
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加详情的物料数据
     * @param data
     */
    public void addData(PurchaseDetail.DataBean.PurchaseItemListBean data){
        if (data != null) {
            mData.add(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置文件
     * @param url
     */
    public void setPicUrls(String url){
        List<String> urls = new ArrayList<>();
        if (url.contains(",")) {
            String[] urlArray = url.split(",");
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
    }

    /**
     * 物料
     */
    public class MaterialDataView extends RecyclerView.ViewHolder {
        private RecyclerPurchaseApprovalLayoutBinding mBinding;
        public MaterialDataView(@NonNull View itemView, RecyclerPurchaseApprovalLayoutBinding binding) {
            super(itemView);
            mBinding = binding;
        }

        public RecyclerPurchaseApprovalLayoutBinding getBinding() {
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

        void onAddMaterialClicked();

        void onDataContainerChanged(List<PurchaseDetail.DataBean.PurchaseItemListBean> data, String urls) ;
    }
}

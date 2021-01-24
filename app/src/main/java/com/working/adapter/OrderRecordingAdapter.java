package com.working.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.databinding.RecyclerOrderDetailLayoutBinding;
import com.working.domain.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情页的RecyclerView的适配器
 */
public class OrderRecordingAdapter extends  RecyclerView.Adapter{

    private final Activity mActivity;
    List<OrderDetail.DataBean.OrderItemListBean> mData = new ArrayList();
    private final ImageCollectAdapter mAdapter;
    private RecyclerOrderDetailLayoutBinding mBind;

    public OrderRecordingAdapter(Activity activity,
                                 ImageCollectAdapter.OnImageClickListener listener) {
        mActivity = activity;
        mAdapter = new ImageCollectAdapter(listener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if(viewType == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_order_detail_layout, parent, false);
            mBind = DataBindingUtil.bind(view);
            return new RecordView(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_order_picture_layout, parent, false);
            return new PictureView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecordView) {
            ((RecordView)holder).setData(mBind, mData.get(position));
        }else{
            ((PictureView)holder).setData("");
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mData.size()){
            return 1;//选择图片Item
        }
        return super.getItemViewType(position);
    }

    public void setData(List<OrderDetail.DataBean.OrderItemListBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }


    public class RecordView extends RecyclerView.ViewHolder {

        public RecordView(@NonNull View itemView) {
            super(itemView);
        }

        public void setData(RecyclerOrderDetailLayoutBinding bind,
                            OrderDetail.DataBean.OrderItemListBean bean) {
            bind.setData(bean);
        }
    }
    public class PictureView extends RecyclerView.ViewHolder {

        private final RecyclerView mRecyclerView;

        public PictureView(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            GridLayoutManager gridManager = new GridLayoutManager(mActivity, 3);
            mRecyclerView.setLayoutManager(gridManager);
            mRecyclerView.setAdapter(mAdapter);
        }

        public void setData(String picUrl) {
                mAdapter.setImageCollect(picUrl);
        }
    }

}

package com.working.adapter;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.SearchAdapter;
import com.working.databinding.RecyclerStockBinding;
import com.working.domain.IStockInfo;
import com.working.domain.InStockList;
import com.working.utils.UIHelper;
import com.working.view.UniformDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 出入库清单的适配器
 */
public class StockListAdapter extends SearchAdapter<StockListAdapter.ItemView, IStockInfo>
        implements IAddData<IStockInfo>{

    private static final String TAG = "StockListAdapter";
    List<IStockInfo> mData = new ArrayList<>();
    private final OnItemClickListener mCallback;
    private final int mScreenWidth;

    public StockListAdapter(Activity activity, OnItemClickListener listener){
        mScreenWidth = UIHelper.getScreenWidth(activity);
        mCallback = listener;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_stock, parent, false);
        RecyclerStockBinding bind = DataBindingUtil.bind(view);
        return new ItemView(view, bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        holder.getBinding().setData(filterData.get(position));
        holder.getBinding().setClickObject(holder);
        holder.getBinding().recyclerView.setVisibility(filterData.get(position).getPicUrl().isEmpty()?View.GONE:View.VISIBLE);
        holder.getPicAdapter().setPicUrl(filterData.get(position).getPicUrl());
    }

    @Override
    public int getItemCount() {
        return filterData(mData);
    }

    @Override
    public void setCollectData(List<IStockInfo> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addCollectData(List<IStockInfo> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class ItemView extends RecyclerView.ViewHolder {

        RecyclerStockBinding mBinding;
        private final ImageAlbumAdapter picAdapter;

        public ItemView(View view, RecyclerStockBinding bind) {
            super(view);
            picAdapter = new ImageAlbumAdapter();
            mBinding = bind;
            mBinding.recyclerView.setAdapter(picAdapter);
            int space = mScreenWidth - UIHelper.dp2px(106 + 8) * 3;
            mBinding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.left = 0;
                    outRect.right = space/3;
                }
            });
        }

        public RecyclerStockBinding getBinding() {
            return mBinding;
        }

        public ImageAlbumAdapter getPicAdapter() {
            return picAdapter;
        }

        public void onItemClicked(IStockInfo recordsBean){
            if (mCallback != null) {
                mCallback.onItemClicked(recordsBean.getId());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(String id);
    }
}

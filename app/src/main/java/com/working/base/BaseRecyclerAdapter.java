package com.working.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.working.domain.RecordsBean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends BaseItemView, D, B extends ViewDataBinding> extends RecyclerView.Adapter<T> {
    private static final String TAG = "BaseRecyclerAdapter";
    protected final OnItemClickedListener mCallback;
    protected List<D> mDatas = new ArrayList<>();
    private Context mContext;
    private B dataBinding;

    public BaseRecyclerAdapter(OnItemClickedListener listener){
        mCallback = listener;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getItemLayoutId(),parent, false);
        dataBinding = DataBindingUtil.bind(view);
        return createViewHolder(view);
    }

    protected abstract T createViewHolder(View view);

    protected abstract int getItemLayoutId();

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        holder.setData(mDatas.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    public List<D> getData(){
       return mDatas;
    }

    public B getDataBinding() {
        return dataBinding;
    }

    public void addData(List<D> data) {
        if (data != null) {
            Log.d(TAG, "addData: list ass all");
            mDatas.addAll(data);

        }
        notifyDataSetChanged();
    }

    public void addData(D data) {
        if (data != null) {
            mDatas.add(data);
            Log.d(TAG, "addData: a data");
        }
        notifyDataSetChanged();
    }

    public void setData(List<D> data) {
        mDatas.clear();
        if (data != null) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }


    public interface OnItemClickedListener{
        void onItemClick(Object data);
    }
}

package com.working.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.domain.RepBalData;
import com.working.domain.ISearchInfo;
import com.working.view.CounterView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BaseDataAdapter<D extends ISearchInfo> extends RecyclerView.Adapter<BaseDataAdapter.DataView<D>> {

    private List<D> data = new ArrayList<>();
    private final int mLayerId;
    OnItemClickedListener<D> mCallback;
    private String searchInfo = "";

    public BaseDataAdapter(int layerId, OnItemClickedListener listener) {
        mLayerId = layerId;
        mCallback = listener;
    }

    @NonNull
    @Override
    public DataView<D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(mLayerId, parent, false);
        return new DataView<D>(view, DataBindingUtil.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull DataView<D> holder, int position) {
        holder.setData(data.get(position), position, mCallback);
    }

    @Override
    public int getItemCount() {
        List<D> tempData = new ArrayList<>();
        List<Integer> indexData = new ArrayList<>();
        int i = 0;
        for (D datum : data) {
            if (!datum.getSearchInfo().contains(searchInfo)) {
                indexData.add(i);
            }
            i++;
        }
        i = 0;
        for (Integer indexDatum : indexData) {
            tempData.add(data.remove((int)indexDatum - i));
            i++;
        }
        data.addAll(tempData);
        tempData = null;
        int filter = data.size() - indexData.size();
        return filter;
    }

    public void setData(List<D> data){
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addCollect(List<D> data){
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    private static final String TAG = "BaseDataAdapter";

    public void addData(D data){
        if (data != null) {
            this.data.add(data);
        }
        notifyDataSetChanged();
    }

    public void addTopData(D data){
        if (data != null) {
            this.data.add(0, data);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        if (data != null) {
            data.clear();
        }
    }

    public void search(String info) {
        searchInfo = info;
        notifyDataSetChanged();
    }

    public static class DataView<D> extends RecyclerView.ViewHolder {

        private OnItemClickedListener<D> mCallback;
        private int mPosition;
        private final CounterView mCounterView;
        private final ViewDataBinding mBinding;
        public DataView(@NonNull View itemView,ViewDataBinding dataBinding) {
            super(itemView);
            mCounterView = itemView.findViewById(R.id.counterView);
            mBinding = dataBinding;
        }

        public void setData(final D data, int position, OnItemClickedListener<D> callback) {
            mCallback = callback;
            mPosition = position;
            if (data instanceof RepBalData.DataBean.RecordsBean) {
                String quantity = ((RepBalData.DataBean.RecordsBean) data).getQuantity();
                mCounterView.setNum(Float.parseFloat(quantity));
            }
            //利用反射设置数据
            Class<?> aClass = mBinding.getClass();
            for (Method method : aClass.getMethods()) {
                if (method.getName().equals("setData")) {
                    try {
                        method.invoke(mBinding, data);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                if (method.getName().equals("setClickObject")) {
                    try {
                        method.invoke(mBinding, this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void onItemClicked(D data){
            if (mCallback != null) {
                int num = mPosition;
                if (mCounterView != null) {
                    num = (int)(mCounterView.getNum() * 10000);
                }
                mCallback.onItemClicked(data, num);
            }
        }
    }


    public interface OnItemClickedListener<D>{
        void onItemClicked(D data, int position);
    }



    public interface OnCommitListener<D>{

        void onItemClicked(D data, boolean isDraft);
    }
}

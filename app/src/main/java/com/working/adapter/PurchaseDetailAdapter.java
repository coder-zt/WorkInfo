package com.working.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerPurchaseDetailLayoutBinding;
import com.working.domain.PurchaseDetail;
import com.working.domain.RecordsBean;
import com.working.view.CounterView;

import java.util.ArrayList;
import java.util.List;


/**
 * 采购记录的适配器
 */
public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.InformationView> {

    private static final String TAG = "PurchaseDetailAdapter";
    private List<PurchaseDetail.DataBean.PurchaseItemListBean> mData = new ArrayList<>();
    private boolean committed;
    private final OnAddViewClickedListener mCallback;

    public PurchaseDetailAdapter(OnAddViewClickedListener listener){
        mCallback = listener;
    }

    @NonNull
    @Override
    public PurchaseDetailAdapter.InformationView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerPurchaseDetailLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.recycler_purchase_detail_layout, parent, false);
        return new InformationView(binding.getRoot(), binding);
    }


    @Override
    public void onBindViewHolder(@NonNull PurchaseDetailAdapter.InformationView holder, final int position) {
       if(position < mData.size()){
           holder.mBinding.editText.removeTextChangedListener(holder.getTextWatcher());
           holder.setTextWatcher(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                   mData.get(position).setPrice(s.toString());
                   if (mCallback != null) {
                       mCallback.onMaterialNumChanged(mData);
                   }
                   String marketPrice = mData.get(position).getPriceMarket();
                   if(s.length()> 0 &&  marketPrice != null){
                       float price = Float.parseFloat(s.toString());
                       float market = Float.parseFloat(marketPrice);
                       if(price == market){
                           holder.mBinding.tvShow.setVisibility(View.GONE);
                       }else if(price < market){
                           holder.mBinding.tvShow.setVisibility(View.VISIBLE);
                           holder.mBinding.tvShow.setText("低于于市场价");
                       }else{
                           holder.mBinding.tvShow.setVisibility(View.VISIBLE);
                           holder.mBinding.tvShow.setText("高于市场价");
                       }
                   }
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });
            holder.mBinding.editText.addTextChangedListener(holder.getTextWatcher());
            holder.mBinding.setItem(mData.get(position));
            holder.mBinding.setIsCommit(committed);
            holder.getBinding().counterView.setCanEdit(!committed);
            holder.mBinding.setClickItem(holder);
           holder.mBinding.counterView.setNumChangeListener(new CounterView.OnNumberChangedListener() {
               @Override
               public void onNumChanged(float num) {
                   if (mCallback != null) {
                       mData.get(position).setProductQuantity(String.valueOf(num));
                       mCallback.onMaterialNumChanged(mData);
                   }
               }
           });
           if(mData.get(position).getMin() != null &&mData.get(position).getMax() != null  ){
               float minValue = Float.parseFloat(mData.get(position).getMin());
               float maxValue = Float.parseFloat(mData.get(position).getMax());
               if(minValue > maxValue) {
                   float temp = minValue;
                   minValue = maxValue;
                   maxValue = temp;
               }
               holder.mBinding.counterView.setScopeValue(minValue, maxValue);
           }else{
               holder.mBinding.counterView.setScopeValue(0.0F,0.0F);
           }
            holder.mBinding.counterView.setNum(Float.parseFloat(mData.get(position).getProductQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: mData.size ---> " + mData.size());
        return mData.size();
    }

    public void setData(List<PurchaseDetail.DataBean.PurchaseItemListBean> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void deleteData(int index){
        mData.remove(index);
        notifyItemRemoved(index);
        if (mCallback != null) {
            mCallback.onMaterialNumChanged(mData);
        }
    }

    public void addData(PurchaseDetail.DataBean.PurchaseItemListBean data) {
        if (data != null) {
            mData.add(data);
        }
        notifyDataSetChanged();
    }

    public void setCommitted(boolean isCommitted){
        this.committed = isCommitted;
    }


    public class InformationView extends RecyclerView.ViewHolder {

        private RecyclerPurchaseDetailLayoutBinding mBinding;
        private TextWatcher mTextWatcher;

        public InformationView(@NonNull View itemView, RecyclerPurchaseDetailLayoutBinding binding){
            super(itemView);
            mBinding = binding;
        }



        public RecyclerPurchaseDetailLayoutBinding getBinding() {
            return mBinding;
        }

        public TextWatcher getTextWatcher() {
            return mTextWatcher;
        }

        public void setTextWatcher(TextWatcher textWatcher) {
            mTextWatcher = textWatcher;
        }
    }

    public interface OnAddViewClickedListener{

        void onMaterialNumChanged(List<PurchaseDetail.DataBean.PurchaseItemListBean> data);
    }
}

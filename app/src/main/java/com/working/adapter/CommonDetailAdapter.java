package com.working.adapter;

import android.content.Context;
import android.service.autofill.FieldClassification;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.databinding.RecyclerApprovalDetailBinding;
import com.working.databinding.RecyclerImageLayoutBinding;
import com.working.databinding.RecyclerPurchaseDetailLayoutBinding;
import com.working.interfaces.IRecyclerDetail;
import com.working.utils.AppConfig;
import com.working.view.CounterView;

import java.util.ArrayList;
import java.util.List;


/**
 * 采购记录的适配器
 */
public class CommonDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "PurchaseDetailAdapter";
    private List<IRecyclerDetail> mData = new ArrayList<>();
    private boolean mIsCommitted;
    private final OnAddViewClickedListener mCallback;
    private final ImageCollectAdapter mPicAdapter;


    public CommonDetailAdapter(OnAddViewClickedListener listener, ImageCollectAdapter.OnImageClickListener imageClickListener){
        mCallback = listener;
        mPicAdapter = new ImageCollectAdapter(imageClickListener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType == AppConfig.TYPE_APPROVAL){//审批意见
            RecyclerApprovalDetailBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.recycler_approval_detail, parent, false);
            return new ApprovalView(binding.getRoot(), binding);
        }else if(viewType == AppConfig.TYPE_PICTURE){//图片
            RecyclerImageLayoutBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.recycler_image_layout, parent, false);
            return new ImageCollectView(binding.getRoot(), binding);
        }else{//物料
            RecyclerPurchaseDetailLayoutBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.recycler_purchase_detail_layout, parent, false);
            return new InformationView(binding.getRoot(), binding);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
       if(position < mData.size()){
           if(holder instanceof InformationView){
               setInfoData((InformationView) holder, position);
           } else if(holder instanceof ApprovalView){
               ((ApprovalView)holder).getBinding().setApprovalInfo(mData.get(position).getApprovalInfo());
               ((ApprovalView)holder).getBinding().setGrade(mData.get(position).getApprovalGrade());
           }else if(holder instanceof ImageCollectView){
              mPicAdapter.setImageCollect(mData.get(position).getImageCollect());
           }

       }
    }

    /**
     * 设置物料的相关信息
     * @param holder
     * @param position
     */
    private void setInfoData(InformationView holder,  final int position) {
        holder.mBinding.tvShow.setVisibility(View.GONE);
        holder.mBinding.editText.removeTextChangedListener(holder.getTextWatcher());
        holder.setTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String priceStr = s.toString();
                if (TextUtils.equals(".", priceStr)) {
                    priceStr = "0.0";
                }
                if(priceStr.contains(".")){
                    String[] split = priceStr.split("\\.");
                    if (split.length == 2 && split[1].length()>4) {
                        String newDecl = split[1].substring(0,4);
                        String newValue = priceStr.replace(split[1], newDecl);
                        holder.mBinding.editText.setText(newValue);
                        return;
                    }
                }
                mData.get(position).setPrice(priceStr);
                if (mCallback != null) {
                    mCallback.onMaterialNumChanged(mData,mPicAdapter.getImageCollect());
                }
                String marketPrice = mData.get(position).getPriceMarket();
                if(s.length()> 0 &&  marketPrice != null){
                    float price = Float.parseFloat(priceStr);
                    float market = Float.parseFloat(marketPrice);
                    float prange =  Math.abs(mData.get(position).getPrange());
                    float min = 1.0f - (prange/100.0f);
                    float max = 1.0f + (prange/100.0f);
                    if(price >= market * min && price <= market * max){
                        holder.mBinding.tvShow.setVisibility(View.GONE);
                    }else if(price < market * min){
                        holder.mBinding.tvShow.setVisibility(View.VISIBLE);
                        holder.mBinding.tvShow.setText("低于市场价-" + prange + "%");
                    }else if(price > market * max){
                        holder.mBinding.tvShow.setVisibility(View.VISIBLE);
                        holder.mBinding.tvShow.setText("高于市场价+" + prange + "%");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.mBinding.editText.addTextChangedListener(holder.getTextWatcher());
        holder.mBinding.setItem(mData.get(position));
        holder.mBinding.setIsCommit(mIsCommitted);
        holder.getBinding().counterView.setCanEdit(!mIsCommitted);
        holder.mBinding.counterView.setNumChangeListener(new CounterView.OnNumberChangedListener() {
            @Override
            public void onNumChanged(float num) {
                if (mCallback != null) {
                    mData.get(position).setCount(String.valueOf(num));
                    mCallback.onMaterialNumChanged(mData, mPicAdapter.getImageCollect());
                }
            }
        });
        holder.mBinding.counterView.setScopeValue(mData.get(position).getMin(), mData.get(position).getMax());
        holder.mBinding.counterView.setNum(Float.parseFloat(mData.get(position).getCount()));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
}

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: mData.size ---> " + mData.size());
        return mData.size();
    }

    public void setData(List<IRecyclerDetail> data) {
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
            mCallback.onDataContainerCountChange(mData.size() -1, mData.size());
            mCallback.onMaterialNumChanged(mData, mPicAdapter.getImageCollect());
        }
    }

    public void addData(IRecyclerDetail data) {
        if (data != null) {
            int i = mData.size() - 1;
            for (; i >= 0; i--) {
               if(mData.get(i).getType()  == AppConfig.TYPE_MATERIAL) {
                   break;
               }
            }
            mData.add(i+1, data);
            if (mCallback != null) {
                mCallback.onDataContainerCountChange(mData.size() -1, mData.size());
                mCallback.onMaterialNumChanged(mData, mPicAdapter.getImageCollect());
            }
            notifyDataSetChanged();
        }
    }

    public void setCommitted(boolean isCommitted){
        this.mIsCommitted = isCommitted;
        mPicAdapter.setIsCommit(isCommitted);
    }

    public void addPic(String picUrl){
        mPicAdapter.addImage(picUrl);
        if (mCallback != null) {
            mCallback.onMaterialNumChanged(mData, mPicAdapter.getImageCollect());
        }
    }

    public String getImageCollect(){
        return  mPicAdapter.getImageCollect();
    }


    public static class InformationView extends RecyclerView.ViewHolder {

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

    /**
     * 审批意见
     */
    public static class ApprovalView extends RecyclerView.ViewHolder {

        private RecyclerApprovalDetailBinding mBinding;

        public ApprovalView(@NonNull View itemView, RecyclerApprovalDetailBinding binding){
            super(itemView);
            mBinding = binding;
        }

        public RecyclerApprovalDetailBinding getBinding() {
            return mBinding;
        }
    }

    /**
     * 图片集合
     */
    public class ImageCollectView extends RecyclerView.ViewHolder {

        private RecyclerImageLayoutBinding mBinding;

        public ImageCollectView(@NonNull View itemView, RecyclerImageLayoutBinding binding){
            super(itemView);
            mBinding = binding;
            mBinding.recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
            mBinding.recyclerView.setAdapter(mPicAdapter);
            mBinding.tvPicTitle.setText(mIsCommitted?"相关文件":"上传文件");
        }

        public RecyclerImageLayoutBinding getBinding() {
            return mBinding;
        }
    }

    public interface OnAddViewClickedListener{

        void onMaterialNumChanged(List<IRecyclerDetail> data, String picUrl);

        void onDataContainerCountChange(int oldCount, int newCount);
    }
}

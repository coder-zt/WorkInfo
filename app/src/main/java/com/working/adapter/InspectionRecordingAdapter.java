package com.working.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerInspectionRecyclerLayoutBinding;
import com.working.setting.InspectionFiledInfo;
import com.working.utils.FileUtils;
import com.working.utils.ToastUtil;
import com.working.view.TypePopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加巡检记录的recyclerD的适配器
 */
public class InspectionRecordingAdapter extends BaseRecyclerAdapter<InspectionRecordingAdapter.ItemView,
        InspectionFiledInfo, RecyclerInspectionRecyclerLayoutBinding> {

    private static final String TAG="InsingAdap";
    private Activity mActivity;
    private ImageCollectAdapter mAdapter;
    private OnGetRepairInfoListener mRepairListener;
    private int repairMethodCode =  -1;
    private int damageCode = -1;

    public InspectionRecordingAdapter(Activity activity,
                                      ImageCollectAdapter.OnImageClickListener listener,
                                      OnGetRepairInfoListener repairListener){
        super(null);
        mActivity = activity;
        mAdapter = new ImageCollectAdapter(listener);
        mRepairListener = repairListener;
    }

    @Override
    protected ItemView createViewHolder(View view) {
        return new ItemView(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recycler_inspection_recycler_layout;
    }

    public void addImageUrl(String url){
        mAdapter.addImage(url);
    }

    public String getImageCollect(){
       return  mAdapter.getImageCollect();
    }

    public void setCommit(boolean isCommit){
        mAdapter.setIsCommit(isCommit);
    }
    public class ItemView extends BaseItemView<InspectionFiledInfo> {
       TextView mTvTitle;
       EditText mEtInput;
       RecyclerView mRvPhoto;

        public ItemView(@NonNull View itemView) {
            super(itemView);
            mTvTitle = (TextView)itemView.findViewById(R.id.tv_alias);
            mEtInput = (EditText)itemView.findViewById(R.id.et_input);
            mRvPhoto = (RecyclerView) itemView.findViewById(R.id.recycler_photo);
        }

        @Override
        public void setData(final InspectionFiledInfo item, int position) {
            mTvTitle.setText(item.getAlias());
            if (item.getInput_type() == 1) {//选择对应值
                setSelectValueMethod(item);
            }else if(item.getInput_type() == 3){//图像选择输入
                setSelectImageMethod(item);
            }else{//填写信息
                setInputMethod(item);
            }
        }

        private void setSelectValueMethod(final InspectionFiledInfo item) {
            mEtInput.setFocusable(false);
            if (!item.getValue().isEmpty()) {
                mEtInput.setText(item.getValue());
            }
            mEtInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String showInfo = "";
                    if (item.getRange() == null) {
                        showInfo = "失败！";
                    } else if (item.getRange().size() == 0) {
                        showInfo = "为空!";
                    }
                    if (item.getRange() == null || item.getRange().size() == 0) {
                        if (TextUtils.equals(item.getAlias(),"病害类型")) {
                            ToastUtil.showMessage("获取病害类型" + showInfo);
                        }else if (TextUtils.equals(item.getAlias(),"维修方法")) {
                            if(damageCode == -1){
                                ToastUtil.showMessage("请先选择病害类型！");
                            }else{
                                ToastUtil.showMessage("获取维修方法" + showInfo);
                            }
                        }else if (TextUtils.equals(item.getAlias(),"维修内容")) {
                            if(repairMethodCode == -1){
                                ToastUtil.showMessage("请先选择维修方法");
                            }else{
                                ToastUtil.showMessage("获取维修内容" + showInfo);
                            }
                        }
                        return;
                    }

                    final TypePopWindow window = new TypePopWindow(v.getContext(),item.getRange(), new RVListAdapter.OnItemClickedListener() {
                        @Override
                        public void onItemClicked(int select, String typeName) {
                            Log.d(TAG, "onItemClicked: " + item.getField());
                            if (TextUtils.equals(item.getAlias(),"病害类型")) {
                                damageCode = select;
                                repairMethodCode = -1;
                                if (mRepairListener != null) {
                                    mRepairListener.selectDamageAfter(select);
                                }
                                item.setRangeIndex(select);
                            }
                            if (TextUtils.equals(item.getAlias(),"维修方法")) {
                                repairMethodCode = select;
                                if (mRepairListener != null) {
                                    mRepairListener.selectRepairMethodAfter(damageCode, select);
                                }
                            }
                            if (TextUtils.equals(item.getAlias(),"维修内容")) {
                                if (mRepairListener != null) {
                                    mRepairListener.selectRepairContentAfter(select);
                                }
                            }
                            item.setValue(typeName);
                            mEtInput.setText(item.getValue());
                            notifyDataSetChanged();
                        }
                    });
                    window.showAsDropDown(v);
                }
            });
        }

        private void setSelectImageMethod(InspectionFiledInfo item) {
            mEtInput.setVisibility(View.GONE);
            mRvPhoto.setVisibility(View.VISIBLE);
            GridLayoutManager gridManager = new GridLayoutManager(mActivity, 3);
            mRvPhoto.setLayoutManager(gridManager);
            mRvPhoto.setAdapter(mAdapter);
            String picUrl = item.getValue();
            mAdapter.setImageCollect(picUrl);
        }

        private void setInputMethod(final InspectionFiledInfo item) {
            if (item.getValue() != null) {
                mEtInput.setText(item.getValue());
            }
            mEtInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public interface OnGetRepairInfoListener{

        void selectDamageAfter(int damageType);

        void selectRepairMethodAfter(int damageType, int index);

        void selectRepairContentAfter(int index);
    }
}

package com.working.adapter;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerInspectionInfoLayoutBinding;
import com.working.domain.InspectionList;
import com.working.domain.RecordsBean;



/**
 * 巡检记录的适配器
 */
public class InspectionInfoAdapter extends BaseRecyclerAdapter<InspectionInfoAdapter.InformationView,
        InspectionList.DataBean.RecordsBean, RecyclerInspectionInfoLayoutBinding> {



    private static final String TAG = "FunctionListAdapter";

    public InspectionInfoAdapter(OnItemClickedListener listener) {
        super(listener);
    }


    @Override
    protected InformationView createViewHolder(View view) {
        return new InformationView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recycler_inspection_info_layout;
    }



    public class InformationView extends BaseItemView<InspectionList.DataBean.RecordsBean> {

        public InformationView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(InspectionList.DataBean.RecordsBean item, int position) {
            Log.d(TAG, "setData: " + position);
            getDataBinding().setItem(item);
            getDataBinding().setItemView(this);
        }

        public void onItemClick(InspectionList.DataBean.RecordsBean item){
            mCallback.onItemClick(item);
        }
    }
}

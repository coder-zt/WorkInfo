package com.working.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerIndexInfoLayoutBinding;
import com.working.domain.IndexNotice;

public class InformationListAdapter extends BaseRecyclerAdapter<InformationListAdapter.InformationView,
        IndexNotice.DataBean.RecordsBean, RecyclerIndexInfoLayoutBinding> {


    public InformationListAdapter(OnItemClickedListener listener) {
        super(listener);
    }

    private static final String TAG = "FunctionListAdapter";


    @Override
    protected InformationView createViewHolder(View view) {
        return new InformationView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recycler_index_info_layout;
    }


    public class InformationView extends BaseItemView<IndexNotice.DataBean.RecordsBean> {

        public InformationView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(IndexNotice.DataBean.RecordsBean item, int position) {
            Log.d(TAG, "setData: " + position);
            getDataBinding().setItem(item);
            getDataBinding().setItemView(this);
        }

        public void onItemClick(IndexNotice.DataBean.RecordsBean item){
            Log.d(TAG, "onItemClick: ");
            mCallback.onItemClick(item);
        }


    }
}

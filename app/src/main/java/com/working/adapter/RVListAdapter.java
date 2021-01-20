package com.working.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerSelectLayoutBinding;

public class RVListAdapter  extends BaseRecyclerAdapter<RVListAdapter.ItemView,
        String, RecyclerSelectLayoutBinding> {


    private OnItemClickedListener mCallback;

    public RVListAdapter() {
        super(null);
    }

    private static final String TAG = "RVListAdapter";


    @Override
    protected RVListAdapter.ItemView createViewHolder(View view) {
        return new RVListAdapter.ItemView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recycler_select_layout;
    }


    public class ItemView extends BaseItemView<String> {

        public ItemView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(String item, int position) {
            Log.d(TAG, "setData: " + position);
            getDataBinding().setItem(item);
            getDataBinding().setIndex(position+1);
            getDataBinding().setOnClick(mCallback);
        }

    }

    public void setOnItemClickedListener(OnItemClickedListener listener){
        mCallback = listener;
    }


    public interface OnItemClickedListener{
        void onItemClicked(int index, String typeName);
    }
}


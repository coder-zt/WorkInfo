package com.working.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerIndexFunctionLayoutBinding;
import com.working.setting.MineMenuItem;

public class FunctionListAdapter extends BaseRecyclerAdapter<FunctionListAdapter.FuntionView,
        MineMenuItem, RecyclerIndexFunctionLayoutBinding> {


    public FunctionListAdapter() {
        super(null);
    }

    private static final String TAG = "FunctionListAdapter";


    @Override
    protected FuntionView createViewHolder(View view) {
        return new FuntionView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.recycler_index_function_layout;
    }


    public class FuntionView extends BaseItemView<MineMenuItem> {

        public FuntionView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(MineMenuItem item, int position) {
            Log.d(TAG, "setData: " + position);
            getDataBinding().setItem(item);
        }

    }
}

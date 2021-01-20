package com.working.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.adapter.MineListAdapter.ItemView;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerMineData;
import com.working.setting.MineMenuItem;

public class MineListAdapter extends BaseRecyclerAdapter<ItemView, MineMenuItem, RecyclerMineData> {

    private static final String TAG = "MineListAdapter";

    public MineListAdapter() {
        super(null);
    }

    @Override
    protected MineListAdapter.ItemView createViewHolder(View view) {
        Log.d(TAG, "createViewHolder: ");
        return new ItemView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return  R.layout.recycler_mine_layout;
    }


    public class ItemView extends BaseItemView<MineMenuItem> {

        public ItemView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(MineMenuItem item, int position) {
            Log.d(TAG, "setData: ");
            getDataBinding().setItem(item);
        }

    }
}

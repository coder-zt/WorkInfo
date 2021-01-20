package com.working.adapter;

import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;

import com.working.R;
import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.databinding.RecyclerMineData;
import com.working.databinding.RecyclerUserInfoLayoutBinding;
import com.working.setting.MineMenuItem;

public class UserInfoAdapter extends BaseRecyclerAdapter<UserInfoAdapter.ItemView, Pair<String, String>, RecyclerUserInfoLayoutBinding> {

    private static final String TAG = "MineListAdapter";

    public UserInfoAdapter() {
        super(null);
    }

    @Override
    protected ItemView createViewHolder(View view) {
        Log.d(TAG, "createViewHolder: ");
        return new ItemView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return  R.layout.recycler_user_info_layout;
    }


    public class ItemView extends BaseItemView<Pair<String, String>> {

        public ItemView(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void setData(Pair<String, String> item, int position) {
            Log.d(TAG, "setData: ");
            getDataBinding().setPair(item);
        }

    }
}

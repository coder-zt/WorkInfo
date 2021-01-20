package com.working.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.base.BaseDataAdapter;
import com.working.base.BaseFragment;
import com.working.domain.ISearchInfo;

import java.util.List;

public abstract class ApprovalFragment<T extends ISearchInfo> extends BaseFragment {

    private RecyclerView mItemContainer;
    protected BaseDataAdapter<T> mAdapter;
    private ViewParent mParent;
    private View emptyLayout;

    @Override
    protected void initView(View view) {
        mItemContainer = view.findViewById(R.id.recycler_view);
        emptyLayout = view.findViewById(R.id.empty_layout);
        mItemContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter();
        mItemContainer.setAdapter(mAdapter);
    }

    public void setData(List<T> data){
        if (mAdapter != null) {
            mAdapter.setData(data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mItemContainer.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        }else{
            List<T> data =(List<T>) arguments.getSerializable("data");
            if (data != null && data.size() > 0) {
                mItemContainer.setVisibility(View.VISIBLE);
                emptyLayout.setVisibility(View.GONE);
                setData(data);
            }else{
                mItemContainer.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    protected abstract BaseDataAdapter<T> getAdapter();
    @Override
    protected int getLayoutId() {
        return 0;
    }
}

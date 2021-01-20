package com.working.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.adapter.RVListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TypePopWindow extends PopupWindow {

    private static final String TAG = "TypePopWindow";
    private RecyclerView mRvList;
    private RVListAdapter mAdapter;

    public TypePopWindow(Context context, List<String> values, RVListAdapter.OnItemClickedListener listener) {
        super(context);
        LayoutInflater from = LayoutInflater.from(context);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View rootView = from.inflate(R.layout.pop_type_layout, null, true);
        setContentView(rootView);
        setTouchable(true);
        setOutsideTouchable(true);
        setFocusable(true);
        initView(rootView, listener);
        initData(values);
    }

    private void initData(List<String> values) {
        if(values != null){
            mAdapter.setData(values);
        }
    }

    private void initView(View root, final RVListAdapter.OnItemClickedListener listener) {
        mRvList = (RecyclerView) root.findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mAdapter = new RVListAdapter();
        mAdapter.setOnItemClickedListener(new RVListAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int index, String typeName) {
                listener.onItemClicked(index, typeName);
                dismiss();
            }
        });
        mRvList.setAdapter(mAdapter);
    }

}

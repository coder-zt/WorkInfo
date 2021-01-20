package com.working.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private T mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        mDataBinding = DataBindingUtil.bind(inflate);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(getArguments());
    }

    protected void initData(Bundle arguments){};

    protected abstract void initView(View view);

    protected abstract int getLayoutId();

    protected T getDataBinding(){
        return mDataBinding;
    }

}

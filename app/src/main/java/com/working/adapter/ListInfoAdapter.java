package com.working.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.working.base.BaseItemView;
import com.working.base.BaseRecyclerAdapter;
import com.working.domain.BaseRecords;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 列表清单的适配器
 */
public class ListInfoAdapter<T extends BaseRecords, C> extends BaseRecyclerAdapter<ListInfoAdapter.InfoView,
        T, ViewDataBinding> {


    private int resId;
     public ListInfoAdapter(int resId,  BaseRecyclerAdapter.OnItemClickedListener listener) {
        super(listener);
        this.resId = resId;
    }

    private static final String TAG = "ListInfoAdapter";


    @Override
    protected InfoView createViewHolder(View view) {
        return new InfoView(view);
    }

    @Override
    protected int getItemLayoutId() {
        return resId;
    }


    public class InfoView extends BaseItemView<T> {

        private final View mV;

        public InfoView(@NonNull View itemView) {
            super(itemView);
            mV = itemView;
        }

        @Override
        public void setData(final T item, int position){
            //利用反射设置数据
            Class<?> aClass = getDataBinding().getClass();
            for (Method method : aClass.getMethods()) {
                if (method.getName().equals("setItem")) {
                    try {
                        method.invoke(getDataBinding(), item);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                if (method.getName().equals("setItemView")) {
                    try {
                        method.invoke(getDataBinding(), this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void onItemClick(T data) {
            mCallback.onItemClick(data);
        }
    }

}

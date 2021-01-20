package com.working.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView的ViewHolder
 * @param <D>
 */
public abstract class BaseItemView<D> extends RecyclerView.ViewHolder{

    public BaseItemView(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void setData(D d, int position);

    public void onItemClick(D data){}
}

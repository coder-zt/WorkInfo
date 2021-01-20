package com.working.domain;

import android.graphics.Color;

/**
 * 列表数据的详细数据
 * @param <T>
 */
abstract  class DetailData<T> {
    private T mDetailData;

    public T getDetailData() {
        return mDetailData;
    }

    public void setDetailData(T detailData) {
        mDetailData = detailData;
    }

    public abstract int getDetailSize();
    public String getStatusStr(){
        return "";
    }

    public int getStatusColor(){
        return Color.WHITE;
    }

}

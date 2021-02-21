package com.working.domain;

import com.working.adapter.CommonDetailAdapter;
import com.working.interfaces.IRecyclerDetail;
import com.working.utils.AppConfig;

public class ImageCollectBean implements IRecyclerDetail {
    String picUrls = "";
    public ImageCollectBean(String url){
        picUrls = url;
    }
    @Override
    public int getType() {
        return AppConfig.TYPE_PICTURE;
    }

    @Override
    public String getApprovalGrade() {
        return null;
    }

    @Override
    public String getApprovalInfo() {
        return null;
    }

    @Override
    public String getMaterialName() {
        return null;
    }

    @Override
    public String getUnit() {
        return null;
    }

    @Override
    public String getPrice() {
        return null;
    }

    @Override
    public String getPriceMarket() {
        return null;
    }

    @Override
    public void setPrice(String price) {

    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public void setCount(String count) {

    }

    @Override
    public String getMax() {
        return null;
    }

    @Override
    public String getMin() {
        return null;
    }

    @Override
    public String getImageCollect() {
        return picUrls;
    }

    @Override
    public float getPrange() {
        return 0;
    }
}

package com.working.domain;

import com.working.interfaces.IRecyclerDetail;
import com.working.utils.AppConfig;

public class ApprovalContentBean implements IRecyclerDetail {
    private String grade;
    private String content;
    public ApprovalContentBean(String grade, String content){
        this.grade = grade;
        this.content = content;
    }
    @Override
    public int getType() {
        return AppConfig.TYPE_APPROVAL;
    }

    @Override
    public String getApprovalGrade() {
        return grade;
    }

    @Override
    public String getApprovalInfo() {
        return content;
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
        return null;
    }
}

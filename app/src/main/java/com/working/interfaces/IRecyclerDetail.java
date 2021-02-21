package com.working.interfaces;

public interface IRecyclerDetail {

    int getType();

    //审批的信息
    String getApprovalGrade();

    String getApprovalInfo();

    //物料的信息
    String getMaterialName();

    String getUnit();

    String getPrice();

    String getPriceMarket();

    void setPrice(String price);

    String getCount();

    void setCount(String count);

    String getMax();

    String getMin();

    //图片集合
    String getImageCollect();

    float getPrange();
}

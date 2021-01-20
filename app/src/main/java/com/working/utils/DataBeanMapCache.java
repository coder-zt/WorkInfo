package com.working.utils;

import com.working.domain.OrderDetail;

import java.util.HashMap;
import java.util.Map;

public class DataBeanMapCache {

    private static DataBeanMapCache instance = null;
    private Map<String, OrderDetail.DataBean> mRecordDetailMap;

    private DataBeanMapCache() {
        mRecordDetailMap = new HashMap<>();
    }

    public static synchronized DataBeanMapCache getInstance(){
        if(instance == null){
            synchronized (DataBeanMapCache.class){
                if(instance == null){
                    instance = new DataBeanMapCache();
                }
            }
        }
        return instance;
    }

    public void setRecordDetailDataBean(String ID, OrderDetail.DataBean dataBean){
        mRecordDetailMap.put(ID, dataBean);
    }

    public OrderDetail.DataBean getRecordDetailDataBean(String ID){
        return mRecordDetailMap.get(ID);
    }



}

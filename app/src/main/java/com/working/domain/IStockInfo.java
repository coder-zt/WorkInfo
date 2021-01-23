package com.working.domain;

/**
 * 出入库清单的公共属性
 */
public interface IStockInfo extends ISearchInfo {

    String getStatusShow();

    String getUpdateTime();

    String getPicUrl();

    String getId();

    String getStockNo();

    int getStatus();
}

package com.working.interfaces;

import com.working.domain.OrderDetail;
import com.working.domain.RepOut;

import java.util.List;

/**
 * 获取购买清单的回调接口
 */
public interface IRepertoryOutCallback extends IListCallback<RepOut.DataBean.RecordsBean>  {

    /**
     * 获取购买清单的详情
     */
    void onRepertoryDetail(String id, List<OrderDetail.DataBean.OrderItemListBean> data);

    /**
     * 获取购买清单的详情失败
     */
    void onRepertoryDetailError(String msg);
}

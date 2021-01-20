package com.working.interfaces;

import com.working.domain.Order;
import com.working.domain.OrderDetail;

import java.util.List;

/**
 * 获取购买清单的回调接口
 */
public interface IOrderCallback  extends IListCallback<Order.DataBean.RecordsBean>  {

    /**
     * 获取购买清单的详情
     */
    void onLoadedOrderDetail(String id, List<OrderDetail.DataBean.OrderItemListBean> data);

    /**
     * 获取购买清单的详情失败
     */
    void onLoadedOrderDetailError(String msg);
}

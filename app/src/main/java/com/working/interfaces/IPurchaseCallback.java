package com.working.interfaces;

import com.working.domain.Purchase;
import com.working.domain.PurchaseDetail;

import java.util.List;

/**
 * 获取采购清单的接口
 */
public interface IPurchaseCallback extends IListCallback<Purchase.DataBean.RecordsBean> {

    /**
     * 获取采购清单的详情
     */
    void onLoadedPurchaseDetail(String id, List<PurchaseDetail.DataBean.PurchaseItemListBean> data);

    /**
     * 获取采购清单的详情失败
     */
    void onLoadedPurchaseDetailError(String msg);

}

package com.working.domain;

import com.working.interfaces.IPurchaseOrderData;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 采购清单的详情数据
 */
@NoArgsConstructor
@Data
public class PurchaseDetail{

    /**
     * code : 200
     * success : true
     * data : {"id":"1344281382158860290","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 21:57:11","updateUser":"1123598821738675204","updateTime":"2021-01-03 17:32:21","status":1,"isDeleted":0,"inspectionRecordId":"1344276266961862657","purchaseNo":"102020123000000","purchaseName":"测试","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 01:44:55","auditOpinion":"","approvalStatus":2,"purchaseItemList":[{"id":"1344281521866932225","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 21:57:45","updateUser":"1123598821738675201","updateTime":"2020-12-30 21:57:45","status":1,"isDeleted":0,"purchaseId":"1344281382158860290","materialId":"1","materialName":"沥青","unit":"2","price":"1.00","productQuantity":"9999.00","remainingQuantity":0,"min":-1,"max":-1,"owned":1}]}
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataBean  implements IPurchaseOrderData {
        /**
         * id : 1344281382158860290
         * createUser : 1123598821738675201
         * createDept : 1123598813738675203
         * createTime : 2020-12-30 21:57:11
         * updateUser : 1123598821738675204
         * updateTime : 2021-01-03 17:32:21
         * status : 1
         * isDeleted : 0
         * inspectionRecordId : 1344276266961862657
         * purchaseNo : 102020123000000
         * purchaseName : 测试
         * approvalUser : 1123598821738675203
         * approvalTime : 2021-01-03 01:44:55
         * auditOpinion :
         * approvalStatus : 2
         * purchaseItemList : [{"id":"1344281521866932225","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 21:57:45","updateUser":"1123598821738675201","updateTime":"2020-12-30 21:57:45","status":1,"isDeleted":0,"purchaseId":"1344281382158860290","materialId":"1","materialName":"沥青","unit":"2","price":"1.00","productQuantity":"9999.00","remainingQuantity":0,"min":-1,"max":-1,"owned":1}]
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String inspectionRecordId;
        private String purchaseNo;
        private String purchaseName;
        private String approvalUser;
        private String approvalTime;
        private String auditOpinion;
        private int approvalStatus;
        private List<PurchaseItemListBean> purchaseItemList;

        @NoArgsConstructor
        @Data
        public static class PurchaseItemListBean implements ISearchInfo{
            /**
             * id : 1344281521866932225
             * createUser : 1123598821738675201
             * createDept : 1123598813738675203
             * createTime : 2020-12-30 21:57:45
             * updateUser : 1123598821738675201
             * updateTime : 2020-12-30 21:57:45
             * status : 1
             * isDeleted : 0
             * purchaseId : 1344281382158860290
             * materialId : 1
             * materialName : 沥青
             * unit : 2
             * price : 1.00
             * productQuantity : 9999.00
             * remainingQuantity : 0
             * min : -1
             * max : -1
             * owned : 1
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String purchaseId;
            private String materialId;
            private String materialName;
            private String unit;
            private String price;
            private String priceMarket;
            private String productQuantity;
            private String remainingQuantity;
            private String min;
            private String max;
            private int owned;

            @Override
            public String getSearchInfo() {
                return materialName;
            }
        }
    }
}

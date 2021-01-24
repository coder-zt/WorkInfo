package com.working.domain;

import com.working.interfaces.IPurchaseOrderData;
import com.working.interfaces.IRecyclerDetail;

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
         * id : 1353227409007353857
         * createUser : 1341292276263723010
         * createDept : 1123598813738675203
         * createTime : 2021-01-24 14:25:31
         * updateUser : 1342396148266156034
         * updateTime : 2021-01-24 23:17:11
         * status : 1
         * isDeleted : 0
         * inspectionRecordId : 1353227407795200001
         * purchaseNo : 312021012400001
         * purchaseName : 测试审核通过采购单
         * approvalUser : 1123598821738675203
         * approvalTime : 2021-01-24 14:45:23
         * auditOpinion : 2
         * approvalUser2 : -1
         * approvalTime2 :
         * auditOpinion2 :
         * approvalStatus : 3
         * purchaseItemList : [{"id":"1353227409057685505","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1","materialName":"沥青","unit":"kg","price":"1.00","priceMarket":"1.00","productQuantity":"6.05","remainingQuantity":0,"min":"3.00","max":"10.00","owned":1},{"id":"1353227409095434242","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1343090944706203649","materialName":"矿粉","unit":"kg","price":"1.30","priceMarket":"1.30","productQuantity":"7.26","remainingQuantity":0,"min":"3.00","max":"11.00","owned":1},{"id":"1353227409141571586","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1344963574161862658","materialName":"砂","unit":"m3","price":"20.50","priceMarket":"20.50","productQuantity":"4.00","remainingQuantity":0,"min":"4.00","max":"12.00","owned":1},{"id":"1353227409183514625","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1348239825507758011","materialName":"碎石 0-3mm","unit":"m3","price":"10.20","priceMarket":"10.20","productQuantity":"3.00","remainingQuantity":0,"min":"3.00","max":"5.00","owned":1},{"id":"1353227409225457665","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1348239825507758091","materialName":"碎石 3-5mm","unit":"m3","price":"12.90","priceMarket":"12.90","productQuantity":"2.00","remainingQuantity":0,"min":"2.00","max":"12.00","owned":1},{"id":"1353227409267400705","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1348239825507758081","materialName":"碎石 5-10mm","unit":"m3","price":"111.00","priceMarket":"111.00","productQuantity":"1.00","remainingQuantity":0,"min":"1.00","max":"5.00","owned":1},{"id":"1353227409317732353","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"2","materialName":"碎石 10-15mm","unit":"m3","price":"12.00","priceMarket":"12.00","productQuantity":"4.00","remainingQuantity":0,"min":"2.00","max":"4.00","owned":1},{"id":"1353227409368064001","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-24 14:25:31","updateUser":"1342396148266156034","updateTime":"2021-01-24 23:17:11","status":1,"isDeleted":0,"purchaseId":"1353227409007353857","materialId":"1344964207975723009","materialName":"乳化沥青","unit":"m3","price":"32.00","priceMarket":"32.00","productQuantity":"5.00","remainingQuantity":0,"min":"1.00","max":"5.00","owned":1}]
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
        private int approvalUser2;
        private String approvalTime2;
        private String auditOpinion2;
        private int approvalStatus;
        private List<PurchaseItemListBean> purchaseItemList;

        @NoArgsConstructor
        @Data
        public static class PurchaseItemListBean implements ISearchInfo, IRecyclerDetail {
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

            @Override
            public int getType() {
                if(id != null){
                    return 0;
                }else{
                 return 2;
                }
            }


            @Override
            public String getCount() {
                return productQuantity;
            }

            @Override
            public void setCount(String count) {
                productQuantity = count;
            }

            @Override
            public String getImageCollect() {
                return null;
            }


            private String approvalGrade;
            private String approvalInfo;
        }
    }
}

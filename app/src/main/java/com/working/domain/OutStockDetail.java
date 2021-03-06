package com.working.domain;

import com.working.interfaces.IRecyclerDetail;
import com.working.utils.AppConfig;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出库订单的详情数据
 */
@NoArgsConstructor
@Data
public class OutStockDetail {

    /**
     * code : 200
     * success : true
     * data : {"id":"1345248306363744258","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 13:59:24","updateUser":"1341292276263723010","updateTime":"2021-01-02 13:59:24","status":1,"isDeleted":0,"outStockNo":"312021010200001","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"outStockItemList":[{"id":"1345248306380521473","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 13:59:24","updateUser":"1341292276263723010","updateTime":"2021-01-02 13:59:24","status":0,"isDeleted":0,"outStockId":"1345248306363744258","materialId":"1341702566756503554","materialName":"砂","unit":"m3","price":"2.00","productQuantity":"8.00","owned":1,"remainingQuantity":0}]}
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataBean implements ICommitData{
        /**
         * id : 1345248306363744258
         * createUser : 1341292276263723010
         * createDept : 1123598813738675203
         * createTime : 2021-01-02 13:59:24
         * updateUser : 1341292276263723010
         * updateTime : 2021-01-02 13:59:24
         * status : 1
         * isDeleted : 0
         * outStockNo : 312021010200001
         * picUrl :
         * approvalUser : 0
         * approvalTime :
         * auditOpinion :
         * approvalStatus : 0
         * outStockItemList : [{"id":"1345248306380521473","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 13:59:24","updateUser":"1341292276263723010","updateTime":"2021-01-02 13:59:24","status":0,"isDeleted":0,"outStockId":"1345248306363744258","materialId":"1341702566756503554","materialName":"砂","unit":"m3","price":"2.00","productQuantity":"8.00","owned":1,"remainingQuantity":0}]
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String outStockNo;
        private String picUrl;
        private String approvalUser;
        private String approvalTime;
        private String auditOpinion;
        private String approvalUser2;
        private String approvalTime2;
        private String auditOpinion2;
        private int approvalStatus;
        private List<OutStockItemListBean> outStockItemList;

        @NoArgsConstructor
        @Data
        public static class OutStockItemListBean implements IRecyclerDetail {
            /**
             * id : 1345248306380521473
             * createUser : 1341292276263723010
             * createDept : 1123598813738675203
             * createTime : 2021-01-02 13:59:24
             * updateUser : 1341292276263723010
             * updateTime : 2021-01-02 13:59:24
             * status : 0
             * isDeleted : 0
             * outStockId : 1345248306363744258
             * materialId : 1341702566756503554
             * materialName : 砂
             * unit : m3
             * price : 2.00
             * productQuantity : 8.00
             * owned : 1
             * remainingQuantity : 0
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String outStockId;
            private String materialId;
            private String materialName;
            private String unit;
            private String price;
            private String productQuantity;
            private int owned;
            private int remainingQuantity;
            private float prange;

            @Override
            public int getType() {
                return AppConfig.TYPE_MATERIAL;
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
            public String getPriceMarket() {
                return null;
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
    }
}

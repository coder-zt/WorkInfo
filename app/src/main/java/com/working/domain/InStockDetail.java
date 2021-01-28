package com.working.domain;

import com.working.interfaces.IRecyclerDetail;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InStockDetail {

    /**
     * code : 200
     * success : true
     * data : {"id":"1344206599311142913","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:00:02","updateUser":"1123598821738675201","updateTime":"2020-12-30 17:00:02","status":1,"isDeleted":0,"inStockNo":"102020123000000","picUrl":"","inStockItemList":[{"id":"1344206599520858114","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:00:02","updateUser":"1123598821738675201","updateTime":"2020-12-30 17:09:13","status":1,"isDeleted":0,"inStockId":"1344206599311142913","materialId":"1343090944706203649","materialName":"","unit":"kg","price":"1.60","productQuantity":"7.00","owned":1,"remainingQuantity":90},{"id":"1344208912268472322","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:09:13","updateUser":"1123598821738675201","updateTime":"2020-12-30 17:09:13","status":1,"isDeleted":0,"inStockId":"1344206599311142913","materialId":"1","materialName":"","unit":"kg","price":"2.10","productQuantity":"5.00","owned":1,"remainingQuantity":45}]}
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataBean implements ICommitData {
        /**
         * id : 1344206599311142913
         * createUser : 1123598821738675201
         * createDept : 1123598813738675201
         * createTime : 2020-12-30 17:00:02
         * updateUser : 1123598821738675201
         * updateTime : 2020-12-30 17:00:02
         * status : 1
         * isDeleted : 0
         * inStockNo : 102020123000000
         * picUrl :
         * inStockItemList : [{"id":"1344206599520858114","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:00:02","updateUser":"1123598821738675201","updateTime":"2020-12-30 17:09:13","status":1,"isDeleted":0,"inStockId":"1344206599311142913","materialId":"1343090944706203649","materialName":"","unit":"kg","price":"1.60","productQuantity":"7.00","owned":1,"remainingQuantity":90},{"id":"1344208912268472322","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:09:13","updateUser":"1123598821738675201","updateTime":"2020-12-30 17:09:13","status":1,"isDeleted":0,"inStockId":"1344206599311142913","materialId":"1","materialName":"","unit":"kg","price":"2.10","productQuantity":"5.00","owned":1,"remainingQuantity":45}]
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String inStockNo;
        private String picUrl;
        private List<InStockItemListBean> inStockItemList;

        @NoArgsConstructor
        @Data
        public static class InStockItemListBean implements IRecyclerDetail {
            /**
             * id : 1344206599520858114
             * createUser : 1123598821738675201
             * createDept : 1123598813738675201
             * createTime : 2020-12-30 17:00:02
             * updateUser : 1123598821738675201
             * updateTime : 2020-12-30 17:09:13
             * status : 1
             * isDeleted : 0
             * inStockId : 1344206599311142913
             * materialId : 1343090944706203649
             * materialName :
             * unit : kg
             * price : 1.60
             * productQuantity : 7.00
             * owned : 1
             * remainingQuantity : 90
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String inStockId;
            private String materialId;
            private String materialName;
            private String unit;
            private String price;
            private String productQuantity;
            private int owned;
            private int remainingQuantity;

            @Override
            public int getType() {
                return 0;
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

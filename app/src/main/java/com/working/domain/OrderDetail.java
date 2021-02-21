package com.working.domain;

import android.util.Log;

import com.working.interfaces.IRecyclerDetail;
import com.working.utils.AppConfig;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购买清单的详细数据
 */
@NoArgsConstructor
@Data
public class OrderDetail {

    /**
     * code : 200
     * success : true
     * data : {"id":"1343096207504953346","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-27 15:27:44","updateUser":"1123598821738675201","updateTime":"2020-12-27 15:27:44","status":1,"isDeleted":0,"orderNo":"1120201227101","orderName":"测试购买订单101","picUrl":"http://localhost/mino/test.jpg","orderItemList":[{"id":"1343096207504953322","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"orderId":"1343096207504953346","materialId":1,"materialName":"沥青","unit":"kg","price":"1.20","productQuantity":"3.00","remainingQuantity":5,"owned":1},{"id":"1343096207504953341","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"orderId":"1343096207504953346","materialId":"1343090640233287681","materialName":"矿粉","unit":"kg","price":"1.10","productQuantity":"2.00","remainingQuantity":89,"owned":1}]}
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
         * id : 1343096207504953346
         * createUser : 1123598821738675201
         * createDept : 1123598813738675201
         * createTime : 2020-12-27 15:27:44
         * updateUser : 1123598821738675201
         * updateTime : 2020-12-27 15:27:44
         * status : 1
         * isDeleted : 0
         * orderNo : 1120201227101
         * orderName : 测试购买订单101
         * picUrl : http://localhost/mino/test.jpg
         * orderItemList : [{"id":"1343096207504953322","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"orderId":"1343096207504953346","materialId":1,"materialName":"沥青","unit":"kg","price":"1.20","productQuantity":"3.00","remainingQuantity":5,"owned":1},{"id":"1343096207504953341","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"orderId":"1343096207504953346","materialId":"1343090640233287681","materialName":"矿粉","unit":"kg","price":"1.10","productQuantity":"2.00","remainingQuantity":89,"owned":1}]
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String orderNo;
        private String orderName;
        private String picUrl;
        private List<OrderItemListBean> orderItemList;

        @NoArgsConstructor
        @Data
        public static class OrderItemListBean implements ISearchInfo, IRecyclerDetail {
            /**
             * id : 1343096207504953322
             * createUser : -1
             * createDept : -1
             * createTime :
             * updateUser : -1
             * updateTime :
             * status : 0
             * isDeleted : 0
             * orderId : 1343096207504953346
             * materialId : 1
             * materialName : 沥青
             * unit : kg
             * price : 1.20
             * productQuantity : 3.00
             * remainingQuantity : 5
             * owned : 1
             */


            private String parentId;
            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String orderId;
            private String materialId;
            private String materialName;
            private String unit;
            private String price;
            private String marketPrice;
            private String productQuantity;
            private String remainingQuantity;
            private int owned;
            private float prange;


            @Override
            public String getSearchInfo() {
                return materialName;
            }

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

            private static final String TAG = "OrderItemListBean";

            public void setPrice(String price){
                Log.d(TAG, "setPrice: " + price);
                if (marketPrice == null) {
                    marketPrice = price;
                }
                this.price = price;
            }

            @Override
            public String getPriceMarket() {
                return marketPrice;
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

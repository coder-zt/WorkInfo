package com.working.domain;

import java.util.List;

/**
 * 购买清单的详细数据
 */
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateDept() {
            return createDept;
        }

        public void setCreateDept(String createDept) {
            this.createDept = createDept;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public List<OrderItemListBean> getOrderItemList() {
            return orderItemList;
        }

        public void setOrderItemList(List<OrderItemListBean> orderItemList) {
            this.orderItemList = orderItemList;
        }

        public static class OrderItemListBean implements ISearchInfo {
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
            private String productQuantity;
            private String remainingQuantity;
            private int owned;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateDept() {
                return createDept;
            }

            public void setCreateDept(String createDept) {
                this.createDept = createDept;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getMaterialId() {
                return materialId;
            }

            public void setMaterialId(String materialId) {
                this.materialId = materialId;
            }

            public String getMaterialName() {
                return materialName;
            }

            public void setMaterialName(String materialName) {
                this.materialName = materialName;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProductQuantity() {
                return productQuantity;
            }

            public void setProductQuantity(String productQuantity) {
                this.productQuantity = productQuantity;
            }

            public String getRemainingQuantity() {
                return remainingQuantity;
            }

            public void setRemainingQuantity(String remainingQuantity) {
                this.remainingQuantity = remainingQuantity;
            }

            public int getOwned() {
                return owned;
            }

            public void setOwned(int owned) {
                this.owned = owned;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            @Override
            public String getSearchInfo() {
                return materialName;
            }
        }
    }
}

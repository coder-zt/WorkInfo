package com.working.domain;

import java.util.List;

public class RepInInfoData {

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

    public static class DataBean {
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

        public String getInStockNo() {
            return inStockNo;
        }

        public void setInStockNo(String inStockNo) {
            this.inStockNo = inStockNo;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public List<InStockItemListBean> getInStockItemList() {
            return inStockItemList;
        }

        public void setInStockItemList(List<InStockItemListBean> inStockItemList) {
            this.inStockItemList = inStockItemList;
        }
        public static class InStockItemListBean {
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

            public String getInStockId() {
                return inStockId;
            }

            public void setInStockId(String inStockId) {
                this.inStockId = inStockId;
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

            public int getOwned() {
                return owned;
            }

            public void setOwned(int owned) {
                this.owned = owned;
            }

            public int getRemainingQuantity() {
                return remainingQuantity;
            }

            public void setRemainingQuantity(int remainingQuantity) {
                this.remainingQuantity = remainingQuantity;
            }
        }
    }
}

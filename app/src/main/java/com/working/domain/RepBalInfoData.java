package com.working.domain;

public class RepBalInfoData {

    /**
     * code : 200
     * success : true
     * data : {"id":"1343090944706203649","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2020-12-27 15:06:49","updateUser":"1123598821738675201","updateTime":"2020-12-27 15:07:08","status":1,"isDeleted":0,"commonId":"1341702497848283138","unit":"kg","price":"1.30","quantity":"100.00","freezeQuantity":-1,"availableQuantity":"100.00","company":"保定礦粉厰","version":0}
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
         * id : 1343090944706203649
         * createUser : 1341292276263723010
         * createDept : 1123598813738675203
         * createTime : 2020-12-27 15:06:49
         * updateUser : 1123598821738675201
         * updateTime : 2020-12-27 15:07:08
         * status : 1
         * isDeleted : 0
         * commonId : 1341702497848283138
         * unit : kg
         * price : 1.30
         * quantity : 100.00
         * freezeQuantity : -1
         * availableQuantity : 100.00
         * company : 保定礦粉厰
         * version : 0
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String commonId;
        private String unit;
        private String price;
        private String quantity;
        private int freezeQuantity;
        private String availableQuantity;
        private String company;
        private int version;

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

        public String getCommonId() {
            return commonId;
        }

        public void setCommonId(String commonId) {
            this.commonId = commonId;
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

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public int getFreezeQuantity() {
            return freezeQuantity;
        }

        public void setFreezeQuantity(int freezeQuantity) {
            this.freezeQuantity = freezeQuantity;
        }

        public String getAvailableQuantity() {
            return availableQuantity;
        }

        public void setAvailableQuantity(String availableQuantity) {
            this.availableQuantity = availableQuantity;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}

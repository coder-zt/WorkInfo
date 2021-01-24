package com.working.domain;

import java.util.List;

public class MaterialList {

    /**
     * code : 200
     * success : true
     * data : [{"id":"1","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1343090640233287681","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341703761604661249","unit":"kg","price":"1.20","quantity":"100.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试厂","version":0,"materialName":"石屑","materialDesc":"石屑"},{"id":"1343090944706203649","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341702497848283138","unit":"kg","price":"1.30","quantity":"100.00","freezeQuantity":-1,"availableQuantity":-1,"company":"保定礦粉厰","version":0,"materialName":"矿粉","materialDesc":"矿粉"},{"id":"1344963574161862658","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341702566756503554","unit":"m³","price":"20.50","quantity":"100.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试厂家","version":0,"materialName":"砂","materialDesc":"砂"},{"id":"1344964207975723009","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341703050342977537","unit":"kg","price":"32.00","quantity":"1010.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试沥青厂","version":0,"materialName":"乳化沥青","materialDesc":"乳化沥青"},{"id":"1344964670024445953","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341703509036257282","unit":"kg","price":"22.00","quantity":"9008.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试水泥厂","version":0,"materialName":"水泥","materialDesc":"水泥"},{"id":"1344966614679941122","createUser":-1,"createDept":-1,"createTime":"","updateUser":-1,"updateTime":"","status":-1,"isDeleted":-1,"commonId":"1341702423596519426","unit":"kg","price":"11.12","quantity":"1012.00","freezeQuantity":-1,"availableQuantity":-1,"company":"测试沥青厂","version":0,"materialName":"沥青","materialDesc":"沥青"}]
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private String msg;
    private List<DataBean> data;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * createUser : -1
         * createDept : -1
         * createTime :
         * updateUser : -1
         * updateTime :
         * status : -1
         * isDeleted : -1
         * commonId : 1341702423596519426
         * unit : kg
         * price : 1.00
         * quantity : 115.00
         * freezeQuantity : -1
         * availableQuantity : -1
         * company : 测试公司
         * version : 0
         * materialName : 沥青
         * materialDesc : 沥青
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
        private  String freezeQuantity;
        private String availableQuantity;
        private String company;
        private int version;
        private String materialName;
        private String materialDesc;
        private int owned;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }



        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public String getMaterialDesc() {
            return materialDesc;
        }

        public void setMaterialDesc(String materialDesc) {
            this.materialDesc = materialDesc;
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

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getFreezeQuantity() {
            return freezeQuantity;
        }

        public void setFreezeQuantity(String freezeQuantity) {
            this.freezeQuantity = freezeQuantity;
        }

        public void setOwned(int owned) {
            this.owned = owned;
        }

        public int getOwned() {
            return owned;
        }
    }
}

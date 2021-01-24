package com.working.domain;

import com.working.base.IOrderInfo;

import java.util.List;

public class InspectionList {
    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1342406065400168450","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-25 17:45:21","updateUser":"1123598821738675201","updateTime":"2020-12-30 21:33:50","status":0,"isDeleted":0,"title":"null_app_测试","extension":"abc巡检测试","diseaseType":13,"structureName":"null_app_测试","pileNo":"null_app_测试","defectQuality":"yanzhong ","nature":"null_app_测试","extent":"非常大的缺口","scale":"null_app_测试","picUrl":"http://114.115.215.68:9000/caster/upload/20201230/cbbc63b54d31b6b5d6b419993b455b20.jpg","maintenanceMeasures":"建议立即组织维修","userName":"花舞","avatar":""},{"id":"1345245558317318145","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 13:48:29","updateUser":"1342396148266156034","updateTime":"2021-01-02 13:48:29","status":1,"isDeleted":0,"title":"TEST","extension":"TEST","diseaseType":1,"structureName":"测试","pileNo":"1","defectQuality":"验证","nature":"通它","extent":"轻微","scale":"特色他","picUrl":"http://114.115.215.68:9000/caster/upload/20210102/624dc110a412f8d1d4424f937c3dca33.jpg","maintenanceMeasures":"保养","userName":"花舞","avatar":""}],"total":2,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":1}
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
         * records : [{"id":"1342406065400168450","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-25 17:45:21","updateUser":"1123598821738675201","updateTime":"2020-12-30 21:33:50","status":0,"isDeleted":0,"title":"null_app_测试","extension":"abc巡检测试","diseaseType":13,"structureName":"null_app_测试","pileNo":"null_app_测试","defectQuality":"yanzhong ","nature":"null_app_测试","extent":"非常大的缺口","scale":"null_app_测试","picUrl":"http://114.115.215.68:9000/caster/upload/20201230/cbbc63b54d31b6b5d6b419993b455b20.jpg","maintenanceMeasures":"建议立即组织维修","userName":"花舞","avatar":""},{"id":"1345245558317318145","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 13:48:29","updateUser":"1342396148266156034","updateTime":"2021-01-02 13:48:29","status":1,"isDeleted":0,"title":"TEST","extension":"TEST","diseaseType":1,"structureName":"测试","pileNo":"1","defectQuality":"验证","nature":"通它","extent":"轻微","scale":"特色他","picUrl":"http://114.115.215.68:9000/caster/upload/20210102/624dc110a412f8d1d4424f937c3dca33.jpg","maintenanceMeasures":"保养","userName":"花舞","avatar":""}]
         * total : 2
         * size : 10
         * current : 1
         * orders : []
         * optimizeCountSql : true
         * hitCount : false
         * countId :
         * maxLimit : -1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean optimizeCountSql;
        private boolean hitCount;
        private String countId;
        private int maxLimit;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isOptimizeCountSql() {
            return optimizeCountSql;
        }

        public void setOptimizeCountSql(boolean optimizeCountSql) {
            this.optimizeCountSql = optimizeCountSql;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public String getCountId() {
            return countId;
        }

        public void setCountId(String countId) {
            this.countId = countId;
        }

        public int getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(int maxLimit) {
            this.maxLimit = maxLimit;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean implements ISearchInfo, IOrderInfo {
            /**
             * id : 1342406065400168450
             * createUser : 1342396148266156034
             * createDept : 1123598813738675203
             * createTime : 2020-12-25 17:45:21
             * updateUser : 1123598821738675201
             * updateTime : 2020-12-30 21:33:50
             * status : 0
             * isDeleted : 0
             * title : null_app_测试
             * extension : abc巡检测试
             * diseaseType : 13
             * structureName : null_app_测试
             * pileNo : null_app_测试
             * defectQuality : yanzhong
             * nature : null_app_测试
             * extent : 非常大的缺口
             * scale : null_app_测试
             * picUrl : http://114.115.215.68:9000/caster/upload/20201230/cbbc63b54d31b6b5d6b419993b455b20.jpg
             * maintenanceMeasures : 建议立即组织维修
             * userName : 花舞
             * avatar :
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String title;
            private String extension;
            private int diseaseType;
            private String structureName;
            private String pileNo;
            private String defectQuality;
            private String nature;
            private String extent;
            private String scale;
            private String picUrl;
            private String maintenanceMeasures;
            private String userName;
            private String avatar;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExtension() {
                return extension;
            }

            public void setExtension(String extension) {
                this.extension = extension;
            }

            public int getDiseaseType() {
                return diseaseType;
            }

            public void setDiseaseType(int diseaseType) {
                this.diseaseType = diseaseType;
            }

            public String getStructureName() {
                return structureName;
            }

            public void setStructureName(String structureName) {
                this.structureName = structureName;
            }

            public String getPileNo() {
                return pileNo;
            }

            public void setPileNo(String pileNo) {
                this.pileNo = pileNo;
            }

            public String getDefectQuality() {
                return defectQuality;
            }

            public void setDefectQuality(String defectQuality) {
                this.defectQuality = defectQuality;
            }

            public String getNature() {
                return nature;
            }

            public void setNature(String nature) {
                this.nature = nature;
            }

            public String getExtent() {
                return extent;
            }

            public void setExtent(String extent) {
                this.extent = extent;
            }

            public String getScale() {
                return scale;
            }

            public void setScale(String scale) {
                this.scale = scale;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getMaintenanceMeasures() {
                return maintenanceMeasures;
            }

            public void setMaintenanceMeasures(String maintenanceMeasures) {
                this.maintenanceMeasures = maintenanceMeasures;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }


            public String getLeftInfo() {
                return  formatStr("性质", nature) + "<\\br>" +
                        formatStr("标度", scale) + "<\\br>" +
                        formatStr("缺损质量", defectQuality);
            }

            public String getRightInfo() {
                return  formatStr("缺损位置", pileNo) + "<\\br>" +
                        formatStr("结构名称", structureName) + "<\\br>" +
                        formatStr("程度", extent);
            }

            public String getMeasures() {
                return  formatStr("养护措施", maintenanceMeasures);
            }

            public String formatStr(String name, String value){
                return "<font color=\"#333333\">" + name + ": </font> <font color=\"#999999\">" + value + "</font>";
            }

            public String getExtensionShow() {
                return  formatStr("巡检记录描述", extension);
            }

            @Override
            public String getSearchInfo() {
                return title;
            }

            @Override
            public String getOrderInfo() {
                return updateTime;
            }
        }
    }
}

package com.working.domain;

import java.util.List;

/**
 * 首页公告信息
 */
public class IndexNotice {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1123598818738675223","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-05 20:03:31","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:51","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告","category":3,"releaseTime":"2018-12-31 20:03:31","content":"222","categoryName":"转发通知"},{"id":"1123598818738675224","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-28 10:32:26","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:34","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告2","category":1,"releaseTime":"2018-12-05 20:03:31","content":"333","categoryName":"发布通知"},{"id":"1123598818738675225","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-28 11:03:44","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:28","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告3","category":6,"releaseTime":"2018-12-29 00:00:00","content":"11111","categoryName":"事务通知"}],"total":3,"size":20,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":1}
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
         * records : [{"id":"1123598818738675223","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-05 20:03:31","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:51","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告","category":3,"releaseTime":"2018-12-31 20:03:31","content":"222","categoryName":"转发通知"},{"id":"1123598818738675224","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-28 10:32:26","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:34","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告2","category":1,"releaseTime":"2018-12-05 20:03:31","content":"333","categoryName":"发布通知"},{"id":"1123598818738675225","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2018-12-28 11:03:44","updateUser":"1123598821738675201","updateTime":"2018-12-28 11:10:28","status":1,"isDeleted":0,"tenantId":"000000","title":"测试公告3","category":6,"releaseTime":"2018-12-29 00:00:00","content":"11111","categoryName":"事务通知"}]
         * total : 3
         * size : 20
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

        public static class RecordsBean {
            /**
             * id : 1123598818738675223
             * createUser : 1123598821738675201
             * createDept : 1123598813738675201
             * createTime : 2018-12-05 20:03:31
             * updateUser : 1123598821738675201
             * updateTime : 2018-12-28 11:10:51
             * status : 1
             * isDeleted : 0
             * tenantId : 000000
             * title : 测试公告
             * category : 3
             * releaseTime : 2018-12-31 20:03:31
             * content : 222
             * categoryName : 转发通知
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String tenantId;
            private String title;
            private int category;
            private String releaseTime;
            private String content;
            private String categoryName;

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

            public String getTenantId() {
                return tenantId;
            }

            public void setTenantId(String tenantId) {
                this.tenantId = tenantId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }
        }
    }
}

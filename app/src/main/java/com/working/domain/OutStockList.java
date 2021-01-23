package com.working.domain;

import android.graphics.Color;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OutStockList {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1","createUser":"-1","createDept":"-1","createTime":"","updateUser":"-1","updateTime":"","status":0,"isDeleted":0,"outStockNo":"1120201227101","picUrl":"https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png","approvalUser":-1,"approvalTime":"","auditOpinion":"暂未审核","approvalStatus":0,"itemCount":2},{"id":"1345248306363744258","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 13:59:24","updateUser":"1341292276263723010","updateTime":"2021-01-02 13:59:24","status":1,"isDeleted":0,"outStockNo":"312021010200001","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345248494251786241","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:00:09","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:00:09","status":1,"isDeleted":0,"outStockNo":"312021010200002","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345248501797339138","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:00:10","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:00:11","status":1,"isDeleted":0,"outStockNo":"312021010200003","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345249538205663233","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:04:18","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:04:18","status":1,"isDeleted":0,"outStockNo":"312021010200004","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345249541565300737","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:04:18","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:04:18","status":1,"isDeleted":0,"outStockNo":"312021010200005","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345250205951442946","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:06:57","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:07:02","status":1,"isDeleted":0,"outStockNo":"312021010200006","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345268287197536258","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 15:18:48","updateUser":"1341292276263723010","updateTime":"2021-01-02 15:18:48","status":1,"isDeleted":0,"outStockNo":"312021010200000","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345278090523303938","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 15:57:45","updateUser":"1342396148266156034","updateTime":"2021-01-02 19:11:10","status":1,"isDeleted":0,"outStockNo":"312021010200001","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345628872342781953","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-03 15:11:38","updateUser":"1341292276263723010","updateTime":"2021-01-03 16:29:50","status":1,"isDeleted":0,"outStockNo":"312021010300000","picUrl":"http://114.115.215.68:9000/caster/upload/20210103/ad8db98a8473d6c9479cac4915493fd9.jpg","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":0}],"total":13,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":2}
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * records : [{"id":"1","createUser":"-1","createDept":"-1","createTime":"","updateUser":"-1","updateTime":"","status":0,"isDeleted":0,"outStockNo":"1120201227101","picUrl":"https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png","approvalUser":-1,"approvalTime":"","auditOpinion":"暂未审核","approvalStatus":0,"itemCount":2},{"id":"1345248306363744258","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 13:59:24","updateUser":"1341292276263723010","updateTime":"2021-01-02 13:59:24","status":1,"isDeleted":0,"outStockNo":"312021010200001","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345248494251786241","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:00:09","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:00:09","status":1,"isDeleted":0,"outStockNo":"312021010200002","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345248501797339138","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:00:10","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:00:11","status":1,"isDeleted":0,"outStockNo":"312021010200003","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345249538205663233","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:04:18","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:04:18","status":1,"isDeleted":0,"outStockNo":"312021010200004","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345249541565300737","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:04:18","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:04:18","status":1,"isDeleted":0,"outStockNo":"312021010200005","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345250205951442946","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 14:06:57","updateUser":"1341292276263723010","updateTime":"2021-01-02 14:07:02","status":1,"isDeleted":0,"outStockNo":"312021010200006","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345268287197536258","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 15:18:48","updateUser":"1341292276263723010","updateTime":"2021-01-02 15:18:48","status":1,"isDeleted":0,"outStockNo":"312021010200000","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345278090523303938","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 15:57:45","updateUser":"1342396148266156034","updateTime":"2021-01-02 19:11:10","status":1,"isDeleted":0,"outStockNo":"312021010200001","picUrl":"","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":1},{"id":"1345628872342781953","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-03 15:11:38","updateUser":"1341292276263723010","updateTime":"2021-01-03 16:29:50","status":1,"isDeleted":0,"outStockNo":"312021010300000","picUrl":"http://114.115.215.68:9000/caster/upload/20210103/ad8db98a8473d6c9479cac4915493fd9.jpg","approvalUser":0,"approvalTime":"","auditOpinion":"","approvalStatus":0,"itemCount":0}]
         * total : 13
         * size : 10
         * current : 1
         * orders : []
         * optimizeCountSql : true
         * hitCount : false
         * countId :
         * maxLimit : -1
         * searchCount : true
         * pages : 2
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

        @NoArgsConstructor
        @Data
        public static class RecordsBean implements IStockInfo {
            /**
             * id : 1
             * createUser : -1
             * createDept : -1
             * createTime :
             * updateUser : -1
             * updateTime :
             * status : 0
             * isDeleted : 0
             * outStockNo : 1120201227101
             * picUrl : https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png
             * approvalUser : -1
             * approvalTime :
             * auditOpinion : 暂未审核
             * approvalStatus : 0
             * itemCount : 2
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
            private int approvalStatus;
            private int itemCount;

            @Override
            public String getSearchInfo() {
                return outStockNo;
            }


            @Override
            public String getStockNo() {
                return outStockNo;
            }

            @Override
            public String getStatusShow() {
                if(status == 0){
                    return "草稿";
                }else{
                    if(approvalStatus < 2){
                        return "<font color=\"#ff7a03\">审核中</font>";
                    }else if(approvalStatus == 3){
                        return "<font color=\"#ff9696\">已拒绝</font>";
                    }else{
                        return "<font color=\"#27b13e\">已通过</font>";
                    }
                }
            }
        }
    }
}

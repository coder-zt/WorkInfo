package com.working.domain;

import com.working.base.IOrderInfo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApprovalRecord {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-27 19:01:12","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"processType":1,"damageUnit":"平米","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"通过","approvalResult":1,"approvalTime":"2020-12-27 19:00:50"},{"id":"2","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-27 19:02:12","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"processType":2,"damageUnit":"立方米","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"通过","approvalResult":2,"approvalTime":"2020-12-27 19:02:02"},{"id":"1344921937729552385","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:22:32","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:22:32","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"审核通过","approvalResult":1,"approvalTime":"2021-01-01 16:22:32"},{"id":"1344922714166525953","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:25:37","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:25:37","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"审核通过","approvalResult":1,"approvalTime":"2021-01-01 16:25:37"},{"id":"1345425732171481090","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-03 01:44:26","updateUser":"1123598821738675204","updateTime":"2021-01-03 01:44:26","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-03 01:44:26"},{"id":"1345425755626029057","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-03 01:44:31","updateUser":"1123598821738675204","updateTime":"2021-01-03 01:44:31","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-03 01:44:31"},{"id":"1346175357035769857","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-05 03:23:10","updateUser":"1123598821738675204","updateTime":"2021-01-05 03:23:10","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-05 03:23:10"},{"id":"1346175389298356226","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-05 03:23:18","updateUser":"1123598821738675204","updateTime":"2021-01-05 03:23:18","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-05 03:23:18"},{"id":"1346503772305649665","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-06 01:08:10","updateUser":"1123598821738675204","updateTime":"2021-01-06 01:08:10","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-06 01:08:10"},{"id":"1346536739946131457","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-06 03:19:10","updateUser":"1123598821738675204","updateTime":"2021-01-06 03:19:10","status":1,"isDeleted":0,"processType":2,"damageUnit":"","submitUser":"1341292276263723010","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-06 03:19:10"}],"total":13,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":2}
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
         * records : [{"id":"1","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-27 19:01:12","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"processType":1,"damageUnit":"平米","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"通过","approvalResult":1,"approvalTime":"2020-12-27 19:00:50"},{"id":"2","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-27 19:02:12","updateUser":-1,"updateTime":"","status":0,"isDeleted":0,"processType":2,"damageUnit":"立方米","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"通过","approvalResult":2,"approvalTime":"2020-12-27 19:02:02"},{"id":"1344921937729552385","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:22:32","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:22:32","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"审核通过","approvalResult":1,"approvalTime":"2021-01-01 16:22:32"},{"id":"1344922714166525953","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:25:37","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:25:37","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"审核通过","approvalResult":1,"approvalTime":"2021-01-01 16:25:37"},{"id":"1345425732171481090","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-03 01:44:26","updateUser":"1123598821738675204","updateTime":"2021-01-03 01:44:26","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-03 01:44:26"},{"id":"1345425755626029057","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-03 01:44:31","updateUser":"1123598821738675204","updateTime":"2021-01-03 01:44:31","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1123598821738675201","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-03 01:44:31"},{"id":"1346175357035769857","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-05 03:23:10","updateUser":"1123598821738675204","updateTime":"2021-01-05 03:23:10","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-05 03:23:10"},{"id":"1346175389298356226","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-05 03:23:18","updateUser":"1123598821738675204","updateTime":"2021-01-05 03:23:18","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-05 03:23:18"},{"id":"1346503772305649665","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-06 01:08:10","updateUser":"1123598821738675204","updateTime":"2021-01-06 01:08:10","status":1,"isDeleted":0,"processType":1,"damageUnit":"","submitUser":"1342396148266156034","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-06 01:08:10"},{"id":"1346536739946131457","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-06 03:19:10","updateUser":"1123598821738675204","updateTime":"2021-01-06 03:19:10","status":1,"isDeleted":0,"processType":2,"damageUnit":"","submitUser":"1341292276263723010","approvalUser":"1123598821738675204","approvalDesc":"同意","approvalResult":1,"approvalTime":"2021-01-06 03:19:10"}]
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
        public static class RecordsBean implements IOrderInfo,ISearchInfo {
            /**
             * id : 1
             * createUser : 1342396148266156034
             * createDept : 1123598813738675203
             * createTime : 2020-12-27 19:01:12
             * updateUser : -1
             * updateTime :
             * status : 0
             * isDeleted : 0
             * processType : 1
             * damageUnit : 平米
             * submitUser : 1342396148266156034
             * approvalUser : 1123598821738675204
             * approvalDesc : 通过
             * approvalResult : 1
             * approvalTime : 2020-12-27 19:00:50
             * submitUserName : 花舞
             * approvalUserName : 冯奇诺
             * processTypeName :
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private int processType;
            private String damageUnit;
            private String submitUser;
            private String approvalUser;
            private String approvalDesc;
            private int approvalResult;
            private String approvalTime;
            private String submitUserName;
            private String approvalUserName;
            private String processTypeName;

            @Override
            public String getOrderInfo() {
                return updateTime;
            }

            @Override
            public String getSearchInfo() {

                return new StringBuilder()
                        .append(processTypeName)
                        .append("\n")
                        .append(approvalDesc)
                        .append("\n")
                        .append(getApprovalResultStr())
                        .append("\n")
                        .append(submitUserName)
                        .append("\n")
                        .append(approvalUserName)
                        .toString();
            }

            public String getLeftInfo() {
                return  formatStr("审批流程", processTypeName + "") + "<\\br>" +
                        formatStr("审批意见", approvalDesc) + "<\\br>" +
                        formatStr("审批结果", getApprovalResultStr()) + "<\\br>" +
                        formatStr("提交人", submitUserName) + "<\\br>" +
                        formatStr("审批人", approvalUserName);
            }

            private String getApprovalResultStr() {
                switch (approvalResult){
                    case 1:
                        return "一级审核通过";
                    case 2:
                        return "二级审核通过";
                    case 3:
                        return "不通过";
                    default:
                        return "无";
                }
            }


            public String formatStr(String name, String value){
                return "<font color=\"#333333\">" + name + ": </font> <font color=\"#999999\">" + value + "</font>";
            }
        }
    }
}

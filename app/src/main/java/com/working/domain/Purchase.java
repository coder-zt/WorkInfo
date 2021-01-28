package com.working.domain;

import android.graphics.Color;

import com.working.base.IOrderInfo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Purchase {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1344281382158860290","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 21:57:11","updateUser":"1123598821738675204","updateTime":"2021-01-03 17:32:21","status":1,"isDeleted":0,"inspectionRecordId":"1344276266961862657","purchaseNo":"102020123000000","purchaseName":"测试","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 01:44:55","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1344301846142910466","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 23:18:30","updateUser":"1342396148266156034","updateTime":"2021-01-03 01:46:55","status":1,"isDeleted":0,"inspectionRecordId":"1344301845903835137","purchaseNo":"102020123000001","purchaseName":"Cs","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 01:45:17","auditOpinion":"","approvalStatus":2,"itemCount":3},{"id":"1345315571661340673","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 18:26:41","updateUser":"1342396148266156034","updateTime":"2021-01-03 17:34:08","status":1,"isDeleted":0,"inspectionRecordId":"1345315570780536834","purchaseNo":"312021010200001","purchaseName":"测试采购单","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 17:29:45","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1345326662685835265","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 19:10:46","updateUser":"1123598821738675203","updateTime":"2021-01-05 03:42:19","status":1,"isDeleted":0,"inspectionRecordId":"1345326662501285890","purchaseNo":"312021010200001","purchaseName":"测试一下采购单","approvalUser":"1123598821738675203","approvalTime":"2021-01-05 03:42:19","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1345379487872729089","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 22:40:40","updateUser":"1123598821738675204","updateTime":"2021-01-06 01:08:10","status":1,"isDeleted":0,"inspectionRecordId":"1345379447452221441","purchaseNo":"312021010200002","purchaseName":"测试一下生成采购采购单","approvalUser":"1123598821738675204","approvalTime":"2021-01-06 01:08:10","auditOpinion":"","approvalStatus":1,"itemCount":1}],"total":5,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":1}
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
         * records : [{"id":"1344281382158860290","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 21:57:11","updateUser":"1123598821738675204","updateTime":"2021-01-03 17:32:21","status":1,"isDeleted":0,"inspectionRecordId":"1344276266961862657","purchaseNo":"102020123000000","purchaseName":"测试","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 01:44:55","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1344301846142910466","createUser":"1123598821738675201","createDept":"1123598813738675203","createTime":"2020-12-30 23:18:30","updateUser":"1342396148266156034","updateTime":"2021-01-03 01:46:55","status":1,"isDeleted":0,"inspectionRecordId":"1344301845903835137","purchaseNo":"102020123000001","purchaseName":"Cs","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 01:45:17","auditOpinion":"","approvalStatus":2,"itemCount":3},{"id":"1345315571661340673","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 18:26:41","updateUser":"1342396148266156034","updateTime":"2021-01-03 17:34:08","status":1,"isDeleted":0,"inspectionRecordId":"1345315570780536834","purchaseNo":"312021010200001","purchaseName":"测试采购单","approvalUser":"1123598821738675203","approvalTime":"2021-01-03 17:29:45","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1345326662685835265","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 19:10:46","updateUser":"1123598821738675203","updateTime":"2021-01-05 03:42:19","status":1,"isDeleted":0,"inspectionRecordId":"1345326662501285890","purchaseNo":"312021010200001","purchaseName":"测试一下采购单","approvalUser":"1123598821738675203","approvalTime":"2021-01-05 03:42:19","auditOpinion":"","approvalStatus":2,"itemCount":1},{"id":"1345379487872729089","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-02 22:40:40","updateUser":"1123598821738675204","updateTime":"2021-01-06 01:08:10","status":1,"isDeleted":0,"inspectionRecordId":"1345379447452221441","purchaseNo":"312021010200002","purchaseName":"测试一下生成采购采购单","approvalUser":"1123598821738675204","approvalTime":"2021-01-06 01:08:10","auditOpinion":"","approvalStatus":1,"itemCount":1}]
         * total : 5
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

        @NoArgsConstructor
        @Data
        public static class RecordsBean implements ISearchInfo,IStatusShow, IOrderInfo {
            /**
             * id : 1353226929195753474
             * createUser : 1341292276263723010
             * createDept : 1123598813738675203
             * createTime : 2021-01-24 14:23:36
             * updateUser : 1123598821738675204
             * updateTime : 2021-01-24 19:16:09
             * status : 1
             * isDeleted : 0
             * inspectionRecordId : 1353226927958433793
             * purchaseNo : 312021012400000
             * purchaseName : 重新测试采购单
             * approvalUser : 1123598821738675204
             * approvalTime : 2021-01-24 19:16:09
             * auditOpinion : 同意
             * approvalUser2 : -1
             * approvalTime2 :
             * auditOpinion2 :
             * approvalStatus : 1
             * itemCount : 8
             * startTime :
             * endTime :
             */

            private String id;
            private String createUser;
            private String createDept;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int status;
            private int isDeleted;
            private String inspectionRecordId;
            private String purchaseNo;
            private String purchaseName;
            private String approvalUser;
            private String approvalTime;
            private String auditOpinion;
            private String approvalUser2;
            private String approvalTime2;
            private String auditOpinion2;
            private int approvalStatus;
            private int itemCount;
            private String startTime;
            private String endTime;

            @Override
            public String getSearchInfo() {
                return purchaseName;
            }

            @Override
            public String getStringShow() {
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

            @Override
            public int getColorShow() {
                if(status == 0){
                    return Color.RED;
                }else{
                    if(approvalStatus < 2){
                        return Color.BLUE;
                    }else if(approvalStatus == 3){
                        return Color.RED;
                    }else{
                        return Color.GREEN;
                    }
                }
            }

            @Override
            public String getOrderInfo() {
                return updateTime;
            }
        }
    }
}

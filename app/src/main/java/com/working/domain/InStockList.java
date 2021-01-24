package com.working.domain;

import android.graphics.Color;

import com.working.base.IOrderInfo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入库清单的数据
 */
@NoArgsConstructor
@Data
public class InStockList {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1344206599311142913","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:00:02","updateUser":"1123598821738675201","updateTime":"2021-01-03 21:20:21","status":1,"isDeleted":0,"inStockNo":"102020123000000","picUrl":"","itemCount":2},{"id":"1344994894522990594","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 21:12:26","updateUser":"1341292276263723010","updateTime":"2021-01-01 21:12:26","status":1,"isDeleted":0,"inStockNo":"312021010100005","picUrl":"","itemCount":2},{"id":"1344996584772976641","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 21:19:09","updateUser":"1341292276263723010","updateTime":"2021-01-01 21:19:09","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345090170160005121","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:31:01","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:31:01","status":1,"isDeleted":0,"inStockNo":"312021010200008","picUrl":"","itemCount":1},{"id":"1345095230961074178","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:51:08","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:51:08","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345096414149066753","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:55:50","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:55:50","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345096676783800321","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:56:53","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:56:53","status":1,"isDeleted":0,"inStockNo":"312021010200009","picUrl":"","itemCount":1},{"id":"1345096687269560321","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:56:55","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:56:55","status":1,"isDeleted":0,"inStockNo":"312021010200010","picUrl":"","itemCount":1},{"id":"1345098483740295169","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 04:04:03","updateUser":"1341292276263723010","updateTime":"2021-01-02 04:04:03","status":1,"isDeleted":0,"inStockNo":"312021010200011","picUrl":"","itemCount":1},{"id":"1345226448981573633","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 12:32:33","updateUser":"1341292276263723010","updateTime":"2021-01-02 12:32:33","status":1,"isDeleted":0,"inStockNo":"312021010200014","picUrl":"","itemCount":1}],"total":13,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":2}
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
         * records : [{"id":"1344206599311142913","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 17:00:02","updateUser":"1123598821738675201","updateTime":"2021-01-03 21:20:21","status":1,"isDeleted":0,"inStockNo":"102020123000000","picUrl":"","itemCount":2},{"id":"1344994894522990594","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 21:12:26","updateUser":"1341292276263723010","updateTime":"2021-01-01 21:12:26","status":1,"isDeleted":0,"inStockNo":"312021010100005","picUrl":"","itemCount":2},{"id":"1344996584772976641","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 21:19:09","updateUser":"1341292276263723010","updateTime":"2021-01-01 21:19:09","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345090170160005121","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:31:01","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:31:01","status":1,"isDeleted":0,"inStockNo":"312021010200008","picUrl":"","itemCount":1},{"id":"1345095230961074178","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:51:08","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:51:08","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345096414149066753","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:55:50","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:55:50","status":1,"isDeleted":0,"inStockNo":"312021010100006","picUrl":"","itemCount":1},{"id":"1345096676783800321","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:56:53","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:56:53","status":1,"isDeleted":0,"inStockNo":"312021010200009","picUrl":"","itemCount":1},{"id":"1345096687269560321","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 03:56:55","updateUser":"1341292276263723010","updateTime":"2021-01-02 03:56:55","status":1,"isDeleted":0,"inStockNo":"312021010200010","picUrl":"","itemCount":1},{"id":"1345098483740295169","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 04:04:03","updateUser":"1341292276263723010","updateTime":"2021-01-02 04:04:03","status":1,"isDeleted":0,"inStockNo":"312021010200011","picUrl":"","itemCount":1},{"id":"1345226448981573633","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 12:32:33","updateUser":"1341292276263723010","updateTime":"2021-01-02 12:32:33","status":1,"isDeleted":0,"inStockNo":"312021010200014","picUrl":"","itemCount":1}]
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
        public static class RecordsBean implements ISearchInfo, IStockInfo, IOrderInfo {
            /**
             * id : 1344206599311142913
             * createUser : 1123598821738675201
             * createDept : 1123598813738675201
             * createTime : 2020-12-30 17:00:02
             * updateUser : 1123598821738675201
             * updateTime : 2021-01-03 21:20:21
             * status : 1
             * isDeleted : 0
             * inStockNo : 102020123000000
             * picUrl :
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
            private String inStockNo;
            private String picUrl;
            private int itemCount;

            @Override
            public String getSearchInfo() {
                return inStockNo;
            }


            @Override
            public String getStockNo() {
                return inStockNo;
            }

            @Override
            public String getStatusShow() {
                if(status == 0){
                    return "<font color=\"#ff9696\">草稿</font>";
                }else{
                    return "<font color=\"#3a97ff\">已提交</font>";
                }
            }

            @Override
            public String getOrderInfo() {
                return updateTime;
            }
        }
    }
}

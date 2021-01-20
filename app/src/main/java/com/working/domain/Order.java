package com.working.domain;

import android.graphics.Color;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Order {


    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1343974849151795202","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 01:39:08","updateUser":"1123598821738675201","updateTime":"2020-12-30 01:39:08","status":1,"isDeleted":0,"orderNo":"112232","orderName":"_app_测试","picUrl":"图片url","itemCount":2},{"id":"1344351231149080578","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-31 02:34:45","updateUser":"1123598821738675201","updateTime":"2020-12-31 02:34:45","status":1,"isDeleted":0,"orderNo":"102020123100004","orderName":"Cs","picUrl":"图片url","itemCount":1},{"id":"1344928501584625666","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:48:36","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:48:36","status":1,"isDeleted":0,"orderNo":"202021010100005","orderName":"测试一下订单","picUrl":"http://114.115.215.68:9000/caster/upload/20210101/520a1fb0950b1d273d17a7f38ca63383.jpg","itemCount":2},{"id":"1344932101869056001","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 17:02:55","updateUser":"1123598821738675204","updateTime":"2021-01-01 17:02:55","status":0,"isDeleted":0,"orderNo":"202021010100000","orderName":"test","picUrl":"http://114.115.215.68:9000/caster/upload/20210101/772d0bb4af51fedcc3d8417960f43379.jpg","itemCount":1},{"id":"1346134877824643073","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 00:42:19","updateUser":"1342396148266156034","updateTime":"2021-01-05 00:42:19","status":0,"isDeleted":0,"orderNo":"312021010500000","orderName":"","picUrl":"http://114.115.215.68:9000/caster/upload/20210105/a018bae8fbedad80e08ebec4574ac4d7.jpg","itemCount":5},{"id":"1346141953586130946","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:10:26","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:10:26","status":1,"isDeleted":0,"orderNo":"312021010500008","orderName":"","picUrl":"http://114.115.215.68:9000/caster/upload/20210105/a018bae8fbedad80e08ebec4574ac4d7.jpg","itemCount":7},{"id":"1346142259317338113","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:11:39","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:11:39","status":1,"isDeleted":0,"orderNo":"312021010500009","orderName":"","picUrl":"","itemCount":9},{"id":"1346142702663659522","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:13:25","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:13:25","status":1,"isDeleted":0,"orderNo":"312021010500010","orderName":"","picUrl":"","itemCount":7},{"id":"1346142756774375425","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:13:37","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:13:37","status":1,"isDeleted":0,"orderNo":"312021010500011","orderName":"","picUrl":"","itemCount":7},{"id":"1346142946776346625","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:14:23","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:14:23","status":1,"isDeleted":0,"orderNo":"312021010500012","orderName":"","picUrl":"","itemCount":7}],"total":20,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":2}
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
         * records : [{"id":"1343974849151795202","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-30 01:39:08","updateUser":"1123598821738675201","updateTime":"2020-12-30 01:39:08","status":1,"isDeleted":0,"orderNo":"112232","orderName":"_app_测试","picUrl":"图片url","itemCount":2},{"id":"1344351231149080578","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-31 02:34:45","updateUser":"1123598821738675201","updateTime":"2020-12-31 02:34:45","status":1,"isDeleted":0,"orderNo":"102020123100004","orderName":"Cs","picUrl":"图片url","itemCount":1},{"id":"1344928501584625666","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 16:48:36","updateUser":"1123598821738675204","updateTime":"2021-01-01 16:48:36","status":1,"isDeleted":0,"orderNo":"202021010100005","orderName":"测试一下订单","picUrl":"http://114.115.215.68:9000/caster/upload/20210101/520a1fb0950b1d273d17a7f38ca63383.jpg","itemCount":2},{"id":"1344932101869056001","createUser":"1123598821738675204","createDept":"1123598813738675202","createTime":"2021-01-01 17:02:55","updateUser":"1123598821738675204","updateTime":"2021-01-01 17:02:55","status":0,"isDeleted":0,"orderNo":"202021010100000","orderName":"test","picUrl":"http://114.115.215.68:9000/caster/upload/20210101/772d0bb4af51fedcc3d8417960f43379.jpg","itemCount":1},{"id":"1346134877824643073","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 00:42:19","updateUser":"1342396148266156034","updateTime":"2021-01-05 00:42:19","status":0,"isDeleted":0,"orderNo":"312021010500000","orderName":"","picUrl":"http://114.115.215.68:9000/caster/upload/20210105/a018bae8fbedad80e08ebec4574ac4d7.jpg","itemCount":5},{"id":"1346141953586130946","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:10:26","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:10:26","status":1,"isDeleted":0,"orderNo":"312021010500008","orderName":"","picUrl":"http://114.115.215.68:9000/caster/upload/20210105/a018bae8fbedad80e08ebec4574ac4d7.jpg","itemCount":7},{"id":"1346142259317338113","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:11:39","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:11:39","status":1,"isDeleted":0,"orderNo":"312021010500009","orderName":"","picUrl":"","itemCount":9},{"id":"1346142702663659522","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:13:25","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:13:25","status":1,"isDeleted":0,"orderNo":"312021010500010","orderName":"","picUrl":"","itemCount":7},{"id":"1346142756774375425","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:13:37","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:13:37","status":1,"isDeleted":0,"orderNo":"312021010500011","orderName":"","picUrl":"","itemCount":7},{"id":"1346142946776346625","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2021-01-05 01:14:23","updateUser":"1342396148266156034","updateTime":"2021-01-05 01:14:23","status":1,"isDeleted":0,"orderNo":"312021010500012","orderName":"","picUrl":"","itemCount":7}]
         * total : 20
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
        public static class RecordsBean implements ISearchInfo,IStatusShow {
            /**
             * id : 1343974849151795202
             * createUser : 1123598821738675201
             * createDept : 1123598813738675201
             * createTime : 2020-12-30 01:39:08
             * updateUser : 1123598821738675201
             * updateTime : 2020-12-30 01:39:08
             * status : 1
             * isDeleted : 0
             * orderNo : 112232
             * orderName : _app_测试
             * picUrl : 图片url
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
            private String orderNo;
            private String orderName;
            private String picUrl;
            private int itemCount;

            @Override
            public String getSearchInfo() {
                return orderName;
            }

            @Override
            public String getStringShow() {
                if(status == 0){
                    return "<font color=\"#ff9696\">草稿</font>";
                }else{
                    return "<font color=\"#3a97ff\">已提交</font>";
                }
            }

            @Override
            public int getColorShow() {
                if(status == 1){
                    return Color.GREEN;
                }else{
                    return Color.RED;
                }
            }
        }
    }
}

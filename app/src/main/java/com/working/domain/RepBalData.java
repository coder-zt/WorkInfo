package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RepBalData {

    /**
     * code : 200
     * success : true
     * data : {"records":[{"id":"1343090944706203649","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2020-12-27 15:06:49","updateUser":"1123598821738675201","updateTime":"2020-12-27 15:07:08","status":1,"isDeleted":0,"commonId":"1341702497848283138","unit":"kg","price":"1.30","quantity":"100.00","freezeQuantity":-1,"availableQuantity":"100.00","company":"保定礦粉厰","version":0,"materialName":"矿粉","materialDesc":"矿粉"},{"id":"1344963574161862658","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 19:07:58","updateUser":"1341292276263723010","updateTime":"2021-01-01 19:07:58","status":1,"isDeleted":0,"commonId":"1341702566756503554","unit":"m³","price":"20.50","quantity":"100.00","freezeQuantity":-1,"availableQuantity":"100.00","company":"测试厂家","version":0,"materialName":"砂","materialDesc":"砂"},{"id":"1345391921987600385","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:30:05","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:30:05","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1345392724177600514","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:33:16","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:33:16","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"11.12","quantity":"1012.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试沥青厂","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1345394715712831489","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:41:11","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:41:11","status":1,"isDeleted":0,"commonId":"1341703050342977537","unit":"kg","price":"32.00","quantity":"1010.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试沥青厂","version":0,"materialName":"乳化沥青","materialDesc":"乳化沥青"},{"id":"1346165552179081217","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:44:12","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:44:12","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168585835278338","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:56:16","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:56:16","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168764307107842","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:56:58","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:56:58","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"116.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168809685282817","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:57:09","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:57:09","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"116.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346170209446162434","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 03:02:43","updateUser":"1341292276263723010","updateTime":"2021-01-05 03:02:43","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"132.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"}],"total":16,"size":10,"current":1,"orders":[],"optimizeCountSql":true,"hitCount":false,"countId":"","maxLimit":-1,"searchCount":true,"pages":2}
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
         * records : [{"id":"1343090944706203649","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2020-12-27 15:06:49","updateUser":"1123598821738675201","updateTime":"2020-12-27 15:07:08","status":1,"isDeleted":0,"commonId":"1341702497848283138","unit":"kg","price":"1.30","quantity":"100.00","freezeQuantity":-1,"availableQuantity":"100.00","company":"保定礦粉厰","version":0,"materialName":"矿粉","materialDesc":"矿粉"},{"id":"1344963574161862658","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-01 19:07:58","updateUser":"1341292276263723010","updateTime":"2021-01-01 19:07:58","status":1,"isDeleted":0,"commonId":"1341702566756503554","unit":"m³","price":"20.50","quantity":"100.00","freezeQuantity":-1,"availableQuantity":"100.00","company":"测试厂家","version":0,"materialName":"砂","materialDesc":"砂"},{"id":"1345391921987600385","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:30:05","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:30:05","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1345392724177600514","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:33:16","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:33:16","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"11.12","quantity":"1012.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试沥青厂","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1345394715712831489","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-02 23:41:11","updateUser":"1341292276263723010","updateTime":"2021-01-02 23:41:11","status":1,"isDeleted":0,"commonId":"1341703050342977537","unit":"kg","price":"32.00","quantity":"1010.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试沥青厂","version":0,"materialName":"乳化沥青","materialDesc":"乳化沥青"},{"id":"1346165552179081217","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:44:12","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:44:12","status":1,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168585835278338","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:56:16","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:56:16","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"-1.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168764307107842","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:56:58","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:56:58","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"116.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346168809685282817","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 02:57:09","updateUser":"1341292276263723010","updateTime":"2021-01-05 02:57:09","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"116.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"},{"id":"1346170209446162434","createUser":"1341292276263723010","createDept":"1123598813738675203","createTime":"2021-01-05 03:02:43","updateUser":"1341292276263723010","updateTime":"2021-01-05 03:02:43","status":0,"isDeleted":0,"commonId":"1341702423596519426","unit":"kg","price":"1.00","quantity":"115.00","freezeQuantity":"-1.00","availableQuantity":"132.00","company":"测试公司","version":0,"materialName":"沥青","materialDesc":"沥青"}]
         * total : 16
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
        public static class RecordsBean implements ISearchInfo{
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
             * materialName : 矿粉
             * materialDesc : 矿粉
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
            private String freezeQuantity;
            private String availableQuantity;
            private String company;
            private int version;
            private String materialName;
            private String materialDesc;

            public String getLeftInfo() {
                return  formatStr("价格", price) + "<\\br>" +
                        formatStr("现存存量", quantity) + "<\\br>" +
                        formatStr("可用存量", availableQuantity);
            }

            public String getRightInfo() {
                return  formatStr("单位", unit) + "<\\br>" +
                        formatStr("冻结存量", freezeQuantity) + "<\\br>" +
                        formatStr("公司", company);
            }

            public String getDesc() {
                return  formatStr("描述", materialDesc);
            }

            public String formatStr(String name, String value){
                return "<font color=\"#333333\">" + name + ": </font> <font color=\"#999999\">" + value + "</font>";
            }

            @Override
            public String getSearchInfo() {
                return materialName;
            }
        }
    }
}


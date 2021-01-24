package com.working.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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


    @NoArgsConstructor
    @Data
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
    }
}

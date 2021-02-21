package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MaterialList {

    /**
     * code : 0
     * data : [{"availableQuantity":0,"commonId":0,"company":"","createDept":0,"createTime":"","createUser":0,"freezeQuantity":0,"id":0,"isDeleted":0,"materialDesc":"","materialName":"","price":0,"quantity":0,"prange":0,"status":0,"unit":"","updateTime":"","updateUser":0,"version":0}]
     * msg :
     * success : true
     */

    private int code;
    private String msg;
    private boolean success;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * availableQuantity : 0
         * commonId : 0
         * company :
         * createDept : 0
         * createTime :
         * createUser : 0
         * freezeQuantity : 0
         * id : 0
         * isDeleted : 0
         * materialDesc :
         * materialName :
         * price : 0
         * quantity : 0
         * prange : 0
         * status : 0
         * unit :
         * updateTime :
         * updateUser : 0
         * version : 0
         */

        private String availableQuantity;
        private String commonId;
        private String company;
        private String createDept;
        private String createTime;
        private String createUser;
        private String freezeQuantity;
        private String id;
        private int isDeleted;
        private String materialDesc;
        private String materialName;
        private String price;
        private String quantity;
        private int prange;
        private int status;
        private String unit;
        private String updateTime;
        private String updateUser;
        private int version;
        private int owned;
    }
}

package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RepairContent {

    /**
     * code : 200
     * success : true
     * data : [{"damageType":13,"repairMethodId":"1","repairContentId":"1","repairContentName":"5.5cm细粒式沥青混凝土路面"}]
     * msg : 操作成功
     */

    private int code;
    private boolean success;
    private String msg;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * damageType : 13
         * repairMethodId : 1
         * repairContentId : 1
         * repairContentName : 5.5cm细粒式沥青混凝土路面
         */

        private int damageType;
        private int repairMethodId;
        private int repairContentId;
        private String repairContentName;
    }
}

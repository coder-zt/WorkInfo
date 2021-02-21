package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RepairMethod {

    /**
     * code : 0
     * data : [{"damageType":0,"repairMethodId":"","repairMethodName":""}]
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
         * damageType : 0
         * repairMethodId :
         * repairMethodName :
         */

        private int damageType;
        private int repairMethodId;
        private String repairMethodName;
    }
}

package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StatBean {

    /**
     * code : 200
     * success : true
     * data : [{"chartType":1,"count":75},{"chartType":2,"count":27},{"chartType":3,"count":17},{"chartType":4,"count":12},{"chartType":5,"count":31}]
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
         * chartType : 1
         * count : 75
         */

        private int chartType;
        private int count;
    }
}

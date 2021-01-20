package com.working.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Response {

    /**
     * code : 400
     * success : false
     * data : {}
     * msg : 该物料在中队仓库中已存在！
     */

    private int code;
    private boolean success;
    private DataBean data;
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataBean {
    }
}

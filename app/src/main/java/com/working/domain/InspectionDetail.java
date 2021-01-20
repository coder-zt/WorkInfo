package com.working.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InspectionDetail {

    /**
     * code : 200
     * success : true
     * data : {"id":"1342406065400168450","createUser":"1342396148266156034","createDept":"1123598813738675203","createTime":"2020-12-25 17:45:21","updateUser":"1342396148266156034","updateTime":"2021-01-02 19:10:24","status":1,"isDeleted":0,"title":"null_app_测试","extension":"abc巡检测试","diseaseType":13,"structureName":"null_app_测试","pileNo":"null_app_测试","defectQuality":"yanzhong ","nature":"null_app_测试","extent":"非常大的缺口","scale":"null_app_测试","picUrl":"http://114.115.215.68:9000/caster/upload/20201230/cbbc63b54d31b6b5d6b419993b455b20.jpg","maintenanceMeasures":"建议立即组织维修","userName":"花舞","avatar":""}
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
         * id : 1342406065400168450
         * createUser : 1342396148266156034
         * createDept : 1123598813738675203
         * createTime : 2020-12-25 17:45:21
         * updateUser : 1342396148266156034
         * updateTime : 2021-01-02 19:10:24
         * status : 1
         * isDeleted : 0
         * title : null_app_测试
         * extension : abc巡检测试
         * diseaseType : 13
         * structureName : null_app_测试
         * pileNo : null_app_测试
         * defectQuality : yanzhong
         * nature : null_app_测试
         * extent : 非常大的缺口
         * scale : null_app_测试
         * picUrl : http://114.115.215.68:9000/caster/upload/20201230/cbbc63b54d31b6b5d6b419993b455b20.jpg
         * maintenanceMeasures : 建议立即组织维修
         * userName : 花舞
         * avatar :
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String title;
        private String extension;
        private int diseaseType;
        private String structureName;
        private String pileNo;
        private String defectQuality;
        private String nature;
        private String extent;
        private String scale;
        private String picUrl;
        private String maintenanceMeasures;
        private String userName;
        private String avatar;
    }
}

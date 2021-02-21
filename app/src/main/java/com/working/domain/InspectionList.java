package com.working.domain;

import com.working.base.IOrderInfo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InspectionList {

    /**
     * code : 0
     * data : {"current":0,"hitCount":true,"pages":0,"records":[{"avatar":"","createDept":0,"createTime":"","createUser":0,"defectQuality":"","diseaseType":0,"endTime":"","extension":"","extent":"","id":0,"isDeleted":0,"maintenanceMeasures":"","nature":"","picUrl":"","pileNo":"","repairContent":"","repairContentId":"","repairMethod":"","repairMethodId":"","scale":"","startTime":"","status":0,"structureName":"","title":"","updateTime":"","updateUser":0,"userName":""}],"searchCount":true,"size":0,"total":0}
     * msg :
     * success : true
     */

    private int code;
    private DataBean data;
    private String msg;
    private boolean success;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * current : 0
         * hitCount : true
         * pages : 0
         * records : [{"avatar":"","createDept":0,"createTime":"","createUser":0,"defectQuality":"","diseaseType":0,"endTime":"","extension":"","extent":"","id":0,"isDeleted":0,"maintenanceMeasures":"","nature":"","picUrl":"","pileNo":"","repairContent":"","repairContentId":"","repairMethod":"","repairMethodId":"","scale":"","startTime":"","status":0,"structureName":"","title":"","updateTime":"","updateUser":0,"userName":""}]
         * searchCount : true
         * size : 0
         * total : 0
         */

        private int current;
        private boolean hitCount;
        private int pages;
        private boolean searchCount;
        private int size;
        private int total;
        private List<RecordsBean> records;

        @NoArgsConstructor
        @Data
        public static class RecordsBean implements IOrderInfo,ISearchInfo {
            /**
             * avatar :
             * createDept : 0
             * createTime :
             * createUser : 0
             * defectQuality :
             * diseaseType : 0
             * endTime :
             * extension :
             * extent :
             * id : 0
             * isDeleted : 0
             * maintenanceMeasures :
             * nature :
             * picUrl :
             * pileNo :
             * repairContent :
             * repairContentId :
             * repairMethod :
             * repairMethodId :
             * scale :
             * startTime :
             * status : 0
             * structureName :
             * title :
             * updateTime :
             * updateUser : 0
             * userName :
             */

            private String avatar;
            private String createDept;
            private String createTime;
            private String createUser;
            private String defectQuality;
            private int diseaseType;
            private String endTime;
            private String extension;
            private String extent;
            private String id;
            private int isDeleted;
            private String maintenanceMeasures;
            private String nature;
            private String picUrl;
            private String pileNo;
            private String repairContent;
            private String repairContentId;
            private String repairMethod;
            private String repairMethodId;
            private String scale;
            private String startTime;
            private int status;
            private String structureName;
            private String title;
            private String updateTime;
            private String updateUser;
            private String userName;
            public String getLeftInfo() {
                return  formatStr("性质", nature) + "<\\br>" +
                        formatStr("标度", scale) + "<\\br>" +
                        formatStr("缺损质量", defectQuality);
            }

            public String getRightInfo() {
                return  formatStr("缺损位置", pileNo) + "<\\br>" +
                        formatStr("结构名称", structureName) + "<\\br>" +
                        formatStr("程度", extent);
            }

            public String getMeasures() {
                return  formatStr("养护措施", maintenanceMeasures);
            }

            public String formatStr(String name, String value){
                return "<font color=\"#333333\">" + name + ": </font> <font color=\"#999999\">" + value + "</font>";
            }

            public String getExtensionShow() {
                return  formatStr("巡检记录描述", extension);
            }

            @Override
            public String getSearchInfo() {
                return title;
            }

            @Override
            public String getOrderInfo() {
                return updateTime;
            }

        }
    }
}

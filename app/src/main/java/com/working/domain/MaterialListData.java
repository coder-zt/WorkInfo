package com.working.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增用品列表
 */
@NoArgsConstructor
@Data
public class MaterialListData {

    /**
     * code : 200
     * success : true
     * data : [{"id":"1341702423596519426","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:09:20","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:09:20","status":1,"isDeleted":0,"materialName":"沥青","materialDesc":"沥青","unit":"kg","commonPrice":"1.00"},{"id":"1341702497848283138","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:09:37","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:09:37","status":1,"isDeleted":0,"materialName":"矿粉","materialDesc":"矿粉","unit":"kg","commonPrice":"1.00"},{"id":"1341702566756503554","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:09:54","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:09:54","status":1,"isDeleted":0,"materialName":"砂","materialDesc":"砂","unit":"m3","commonPrice":"2.00"},{"id":"1341702799955611649","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:10:49","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:10:49","status":1,"isDeleted":0,"materialName":"碎石 0-3mm","materialDesc":"碎石 0-3mm","unit":"m3","commonPrice":"1.00"},{"id":"1341702873423040514","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:11:07","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:11:07","status":1,"isDeleted":0,"materialName":"碎石 3-5mm","materialDesc":"碎石 3-5mm","unit":"m3","commonPrice":"2.00"},{"id":"1341702935750397954","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:11:22","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:11:22","status":1,"isDeleted":0,"materialName":"碎石 5-10mm","materialDesc":"碎石 5-10mm","unit":"m3","commonPrice":"1.00"},{"id":"1341702993371746306","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:11:35","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:11:35","status":1,"isDeleted":0,"materialName":"碎石 10-15mm","materialDesc":"碎石 10-15mm","unit":"m3","commonPrice":"1.00"},{"id":"1341703050342977537","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:11:49","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:11:49","status":1,"isDeleted":0,"materialName":"乳化沥青","materialDesc":"乳化沥青","unit":"kg","commonPrice":"1.00"},{"id":"1341703327578083329","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:12:55","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:13:07","status":1,"isDeleted":0,"materialName":"冷补料","materialDesc":"1t可修补8.3-8.6平方米坑槽","unit":"t","commonPrice":"1.00"},{"id":"1341703509036257282","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:13:38","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:13:38","status":1,"isDeleted":0,"materialName":"水泥","materialDesc":"水泥","unit":"kg","commonPrice":"1.00"},{"id":"1341703657644642306","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:14:14","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:14:14","status":1,"isDeleted":0,"materialName":"碎石","materialDesc":"碎石","unit":"m3","commonPrice":"1.00"},{"id":"1341703761604661249","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:14:39","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:14:39","status":1,"isDeleted":0,"materialName":"石屑","materialDesc":"石屑","unit":"m3","commonPrice":"1.00"},{"id":"1341703828340232193","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:14:54","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:14:54","status":1,"isDeleted":0,"materialName":"水","materialDesc":"水","unit":"kg","commonPrice":"1.00"},{"id":"1341703905465094146","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:15:13","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:15:13","status":1,"isDeleted":0,"materialName":"密封胶","materialDesc":"自然裂缝4500m/t，开槽3000m/t","unit":"kg","commonPrice":"1.00"},{"id":"1341704459826253826","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:17:25","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:17:25","status":1,"isDeleted":0,"materialName":"片石","materialDesc":"片石","unit":"m3","commonPrice":"1.00"},{"id":"1341704584812318721","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:17:55","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:17:55","status":1,"isDeleted":0,"materialName":"油漆","materialDesc":"1桶（18kg）可刷65m2","unit":"kg","commonPrice":"1.00"},{"id":"1341704672401969154","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:18:16","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:18:16","status":1,"isDeleted":0,"materialName":"橡胶水","materialDesc":"橡胶水","unit":"kg","commonPrice":"1.00"},{"id":"1341704731516489729","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:18:30","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:18:30","status":1,"isDeleted":0,"materialName":"80型伸缩缝","materialDesc":"80型伸缩缝","unit":"m","commonPrice":"1.00"},{"id":"1341704787166515202","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:18:43","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:18:43","status":1,"isDeleted":0,"materialName":"柱式护栏","materialDesc":"柱式护栏","unit":"个","commonPrice":"1.00"},{"id":"1341704833299664898","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:18:54","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:18:54","status":1,"isDeleted":0,"materialName":"警示桩","materialDesc":"警示桩","unit":"个","commonPrice":"100.00"},{"id":"1341704876102537218","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:19:04","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:19:04","status":1,"isDeleted":0,"materialName":"百米桩","materialDesc":"百米桩","unit":"个","commonPrice":"100.00"},{"id":"1341704917592592385","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:19:14","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:19:14","status":1,"isDeleted":0,"materialName":"里程碑","materialDesc":"里程碑","unit":"个","commonPrice":"100.00"},{"id":"1341704964963061762","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:19:25","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:19:25","status":1,"isDeleted":0,"materialName":"路缘石","materialDesc":"路缘石","unit":"个","commonPrice":"100.00"},{"id":"1341705004003643394","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:19:35","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:19:35","status":1,"isDeleted":0,"materialName":"钢管警示柱","materialDesc":"钢管警示柱","unit":"个","commonPrice":"100.00"},{"id":"1341705069006966786","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:19:50","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:20:29","status":1,"isDeleted":0,"materialName":"C级波形梁钢护栏","materialDesc":"4m 1块","unit":"块","commonPrice":"100.00"},{"id":"1341705181196210178","createUser":"1123598821738675201","createDept":"1123598813738675201","createTime":"2020-12-23 19:20:17","updateUser":"1123598821738675201","updateTime":"2020-12-23 19:20:17","status":1,"isDeleted":0,"materialName":"热融标线涂料","materialDesc":"1t可划200m2左右","unit":"kg","commonPrice":"100.00"}]
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
         * id : 1341702423596519426
         * createUser : 1123598821738675201
         * createDept : 1123598813738675201
         * createTime : 2020-12-23 19:09:20
         * updateUser : 1123598821738675201
         * updateTime : 2020-12-23 19:09:20
         * status : 1
         * isDeleted : 0
         * materialName : 沥青
         * materialDesc : 沥青
         * unit : kg
         * commonPrice : 1.00
         */

        private String id;
        private String createUser;
        private String createDept;
        private String createTime;
        private String updateUser;
        private String updateTime;
        private int status;
        private int isDeleted;
        private String materialName;
        private String materialDesc;
        private String unit;
        private String commonPrice;
        private int owned;
    }
}

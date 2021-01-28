package com.working.domain;

/**
 * 巡检记录数据的添加数据
 */
public class InspectionFormData implements ICommitData {

    /**
     * defectQuality :
     * diseaseType : 13
     * extension :
     * extent :
     * maintenanceMeasures :
     * nature : _app_测试
     * pileNo : _app_测试
     * scale : _app_测试
     * status : 0
     * structureName : _app_测试
     * title : _app_测试
     * picUrl:
     */

    private String defectQuality;
    private int diseaseType;
    private String extension;
    private String extent;
    private String maintenanceMeasures;
    private String nature;
    private String pileNo;
    private String scale;
    private int status;
    private String structureName;
    private String title;
    private String id;
    private String picUrl;

    public String getDefectQuality() {
        return defectQuality;
    }

    public void setDefectQuality(String defectQuality) {
        this.defectQuality = defectQuality;
    }

    public int getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(int diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getMaintenanceMeasures() {
        return maintenanceMeasures;
    }

    public void setMaintenanceMeasures(String maintenanceMeasures) {
        this.maintenanceMeasures = maintenanceMeasures;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPileNo() {
        return pileNo;
    }

    public void setPileNo(String pileNo) {
        this.pileNo = pileNo;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}

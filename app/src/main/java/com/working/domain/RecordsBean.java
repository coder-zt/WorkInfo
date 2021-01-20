package com.working.domain;

public class RecordsBean{
    /**
     * id : 0
     * createUser : 0
     * createDept : 0
     * createTime :
     * updateUser : 1123598821738675201
     * updateTime : 2020-12-26 00:45:30
     * status : 1
     * isDeleted : 0
     * title :
     * extension :
     * diseaseType : 0
     * structureName :
     * pileNo :
     * defectQuality :
     * nature :
     * extent :
     * scale :
     * picUrl :
     * maintenanceMeasures :
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(int diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getPileNo() {
        return pileNo;
    }

    public void setPileNo(String pileNo) {
        this.pileNo = pileNo;
    }

    public String getDefectQuality() {
        return defectQuality;
    }

    public void setDefectQuality(String defectQuality) {
        this.defectQuality = defectQuality;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMaintenanceMeasures() {
        return maintenanceMeasures;
    }

    public void setMaintenanceMeasures(String maintenanceMeasures) {
        this.maintenanceMeasures = maintenanceMeasures;
    }

    @Override
    public String toString() {
        return "extension：" + extension +
                "\ndiseaseType：" + diseaseType +
                "\nstructureName：" + structureName + 
                "\npileNo：" + pileNo + 
                "\ndefectQuality：" + defectQuality + 
                "\nnature：" + nature + 
                "\nextent：" + extent + 
                "\nscale：" + scale + 
                "\npicUrl：" + picUrl + 
                "\nmaintenanceMeasures：" + maintenanceMeasures;
    }
}
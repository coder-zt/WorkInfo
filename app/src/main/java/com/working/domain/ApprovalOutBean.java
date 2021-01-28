package com.working.domain;

public class ApprovalOutBean implements ICommitData {

    /**
     * approvalStatus : 0
     * approvalTime :
     * approvalUser : 0
     * auditOpinion :
     * createDept : 0
     * createTime :
     * createUser : 0
     * id : 0
     * isDeleted : 0
     * outStockNo :
     * picUrl :
     * status : 0
     * updateTime :
     * updateUser : 0
     */

    private int approvalStatus;
    private String approvalTime;
    private String approvalUser;
    private String auditOpinion;
    private String createDept;
    private String createTime;
    private String createUser;
    private String id;
    private int isDeleted;
    private String outStockNo;
    private String picUrl;
    private int status;
    private String updateTime;
    private String updateUser;


    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOutStockNo() {
        return outStockNo;
    }

    public void setOutStockNo(String outStockNo) {
        this.outStockNo = outStockNo;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}

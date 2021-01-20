package com.working.domain;

import com.working.interfaces.IPurchaseOrderData;

/**
 * 采购清单的审批数据
 */
public class ApprovalBean implements IPurchaseOrderData {

    /**
     * approvalStatus : 0
     * approvalTime :
     * approvalUser : 0
     * auditOpinion :
     * createDept : 0
     * createTime :
     * createUser : 0
     * id : 0
     * inspectionRecordId : 0
     * isDeleted : 0
     * purchaseName :
     * purchaseNo :
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
    private String inspectionRecordId;
    private int isDeleted;
    private String purchaseName;
    private String purchaseNo;
    private int status;
    private String updateTime;
    private int updateUser;

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public String getInspectionRecordId() {
        return inspectionRecordId;
    }

    public void setInspectionRecordId(String inspectionRecordId) {
        this.inspectionRecordId = inspectionRecordId;
    }

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

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }
}

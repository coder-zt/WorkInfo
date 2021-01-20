package com.working.domain;

import com.working.interfaces.IPurchaseOrderData;

import java.io.Serializable;
import java.util.List;

public class PurchaseCommitBean implements Serializable {

    /**
     * id : 1343054578890952706
     * inspectionRecordId : 1343054578584768514
     * purchaseItemList : [{"materialId":1,"materialName":"沥青","owned":1,"price":1.2,"productQuantity":3,"purchaseId":1343054578890952706,"remainingQuantity":30,"unit":"kg"}]
     * purchaseName : 测试采购订单
     * purchaseNo : 1120201227010
     * status : 0
     */

    private long id;
    private long inspectionRecordId;
    private String purchaseName;
    private String purchaseNo;
    private int status;
    private int approvalStatus;
    private List<PurchaseItemListBean> purchaseItemList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInspectionRecordId() {
        return inspectionRecordId;
    }

    public void setInspectionRecordId(long inspectionRecordId) {
        this.inspectionRecordId = inspectionRecordId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PurchaseItemListBean> getPurchaseItemList() {
        return purchaseItemList;
    }

    public void setPurchaseItemList(List<PurchaseItemListBean> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public static class PurchaseItemListBean{
        /**
         * materialId : 1
         * materialName : 沥青
         * owned : 1
         * price : 1.2
         * productQuantity : 3
         * purchaseId : 1343054578890952706
         * remainingQuantity : 30
         * unit : kg
         */

        private long materialId;
        private String materialName;
        private int owned;
        private double price;
        private int productQuantity;
        private long purchaseId;
        private int remainingQuantity;
        private String unit;

        public long getMaterialId() {
            return materialId;
        }

        public void setMaterialId(long materialId) {
            this.materialId = materialId;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public int getOwned() {
            return owned;
        }

        public void setOwned(int owned) {
            this.owned = owned;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
        }

        public long getPurchaseId() {
            return purchaseId;
        }

        public void setPurchaseId(long purchaseId) {
            this.purchaseId = purchaseId;
        }

        public int getRemainingQuantity() {
            return remainingQuantity;
        }

        public void setRemainingQuantity(int remainingQuantity) {
            this.remainingQuantity = remainingQuantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}

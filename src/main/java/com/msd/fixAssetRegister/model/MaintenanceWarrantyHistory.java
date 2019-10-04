package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_warranty_history")
public class MaintenanceWarrantyHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name ="WARRANTY_ID")
    private int warrantyId;


    @Column(name = "TRANSACTION_NO")
    private String  transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "WARRANTY_PERIOD")
    private String warrantyPeriod;

    @Column(name ="WARRANTY_PERIOD_FROM")
    private Date warrantyPeriodFrom;

    @Column (name = "WARRANTY_PERIOD_TO")
    private Date warrantyPeriodTo;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column (name ="USER_ID")
    private int userID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Date getWarrantyPeriodFrom() {
        return warrantyPeriodFrom;
    }

    public void setWarrantyPeriodFrom(Date warrantyPeriodFrom) {
        this.warrantyPeriodFrom = warrantyPeriodFrom;
    }

    public Date getWarrantyPeriodTo() {
        return warrantyPeriodTo;
    }

    public void setWarrantyPeriodTo(Date warrantyPeriodTo) {
        this.warrantyPeriodTo = warrantyPeriodTo;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

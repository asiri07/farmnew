package com.msd.fixAssetRegister.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "maintenance_modification_history")
public class MaintenanceModificationHistory implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;


    @Column(name = "MODIFICATION_ID")
    private int modificationId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "MODI_TYPE")
    private int modiType;

    @Column(name = "MODI_DATE")
    private Date modiDate;

    @Column(name = "MODI_WORK")
    private String modiWork;


    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    @Column(name = "MODI_COST")
    private double modiCost;

    @Column(name = "MODI_REASON")
    private String modiReason;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public int getModificationId() {
        return modificationId;
    }

    public void setModificationId(int modificationId) {
        this.modificationId = modificationId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getModiType() {
        return modiType;
    }

    public void setModiType(int modiType) {
        this.modiType = modiType;
    }

    public Date getModiDate() {
        return modiDate;
    }

    public void setModiDate(Date modiDate) {
        this.modiDate = modiDate;
    }

    public String getModiWork() {
        return modiWork;
    }

    public void setModiWork(String modiWork) {
        this.modiWork = modiWork;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public double getModiCost() {
        return modiCost;
    }

    public void setModiCost(double modiCost) {
        this.modiCost = modiCost;
    }

    public String getModiReason() {
        return modiReason;
    }

    public void setModiReason(String modiReason) {
        this.modiReason = modiReason;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}





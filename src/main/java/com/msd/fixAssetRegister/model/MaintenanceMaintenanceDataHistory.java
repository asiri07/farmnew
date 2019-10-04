package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@Table(name = "maintenance_maintenance_data_history")
public class MaintenanceMaintenanceDataHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "MAINTENANCE_DATA_ID")
    private int maintenanceDataId;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "MAINT_ENTRY_DA")
    private Date maintEntryDa;

    @Column(name = "MAINT_DA")
    private String maintDa;

    @Column(name = "MAINT_WORK")
    private String maintWork;

    @Column(name = "MAINT_METER")
    private Double maintMeter;


    @Column(name = "MAINT_MASTER")
    private String maintMaster;

    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    private String detailCurrencyType;

    @Column(name = "MAINT_COST")
    private Double maintCost;


    @Column(name = "MAINT_AC_CODE")
    private String maintAcCode;


    @Column(name = "UPDATE_USER")
    private int updateUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;


    public MaintenanceMaintenanceDataHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaintenanceDataId() {
        return maintenanceDataId;
    }

    public void setMaintenanceDataId(int maintenanceDataId) {
        this.maintenanceDataId = maintenanceDataId;
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

    public Date getMaintEntryDa() {
        return maintEntryDa;
    }

    public void setMaintEntryDa(Date maintEntryDa) {
        this.maintEntryDa = maintEntryDa;
    }

    public String getMaintDa() {
        return maintDa;
    }

    public void setMaintDa(String maintDa) {
        this.maintDa = maintDa;
    }

    public String getMaintWork() {
        return maintWork;
    }

    public void setMaintWork(String maintWork) {
        this.maintWork = maintWork;
    }

    public Double getMaintMeter() {
        return maintMeter;
    }

    public void setMaintMeter(Double maintMeter) {
        this.maintMeter = maintMeter;
    }

    public String getMaintMaster() {
        return maintMaster;
    }

    public void setMaintMaster(String maintMaster) {
        this.maintMaster = maintMaster;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public String getDetailCurrencyType() {
        return detailCurrencyType;
    }

    public void setDetailCurrencyType(String detailCurrencyType) {
        this.detailCurrencyType = detailCurrencyType;
    }

    public Double getMaintCost() {
        return maintCost;
    }

    public void setMaintCost(Double maintCost) {
        this.maintCost = maintCost;
    }

    public String getMaintAcCode() {
        return maintAcCode;
    }

    public void setMaintAcCode(String maintAcCode) {
        this.maintAcCode = maintAcCode;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
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


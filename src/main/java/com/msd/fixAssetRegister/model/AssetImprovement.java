package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="asset_improvement")
public class AssetImprovement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ASSET_IMP_ID")
    private int assetImpId;

    @Column(name="TRANSACTION_NO")
    private String transactionNo;

    @ManyToOne()
    @JoinColumn(name="ASSET_ID")
    private Asset asset;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    @Column(name="`CURRENCY_VALUE`")
    private Double currencyValue;

    @Temporal(TemporalType.DATE)
    @Column(name = "IMP_DATE")
    private Date impDate;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    public AssetImprovement() {
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public int getAssetImpId() {
        return assetImpId;
    }

    public void setAssetImpId(int assetImpId) {
        this.assetImpId = assetImpId;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getImpDate() {
        return impDate;
    }

    public void setImpDate(Date impDate) {
        this.impDate = impDate;
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

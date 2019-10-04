/*
 *     Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *     *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *        This software product contains information which is proprietary to
 *        and considered a trade secret MsSoftIT Solution .
 *        It is expressly agreed that it shall not be reproduced in whole or part,
 *        disclosed, divulged or otherwise made available to any third party directly
 *        or indirectly.  Reproduction of this product for any purpose is prohibited
 *        without written authorisation from the The MsSoftIT Solution
 *        All Rights Reserved.
 *
 *        E-Mail mssoftit@gmail.com
 *        URL : mssoftit.lk
 *        Created By : Mahendra Sri Dayarathna
 */
package com.msd.fixAssetRegister.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_service_agreement_history")
public class MaintenanceServiceAgreementsHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "AGREE_ID")
    private int AGREE_ID;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "AGREE_NUMBER")
    private String agreeNumber;

    @Column(name = "AGREE_PERIOD")
    private String agreePeriod;

    @Column(name = "AGREE_FROM")
    private Date agreeFrom;

    @Column(name = "AGREE_TO")
    private Date agreeTo;

    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    @Column(name = "AGREE_COST")
    private Double agreeCost;

    @Column(name = "AGREE_COMPANY")
    private String agreeCompany;

    @Column(name = "AGREE_SERVICE_INTERVAL")
    private String agreeServiceInterval;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name="USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAGREE_ID() {
        return AGREE_ID;
    }

    public void setAGREE_ID(int AGREE_ID) {
        this.AGREE_ID = AGREE_ID;
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

    public String getAgreeNumber() {
        return agreeNumber;
    }

    public void setAgreeNumber(String agreeNumber) {
        this.agreeNumber = agreeNumber;
    }

    public String getAgreePeriod() {
        return agreePeriod;
    }

    public void setAgreePeriod(String agreePeriod) {
        this.agreePeriod = agreePeriod;
    }

    public Date getAgreeFrom() {
        return agreeFrom;
    }

    public void setAgreeFrom(Date agreeFrom) {
        this.agreeFrom = agreeFrom;
    }

    public Date getAgreeTo() {
        return agreeTo;
    }

    public void setAgreeTo(Date agreeTo) {
        this.agreeTo = agreeTo;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public Double getAgreeCost() {
        return agreeCost;
    }

    public void setAgreeCost(Double agreeCost) {
        this.agreeCost = agreeCost;
    }

    public String getAgreeCompany() {
        return agreeCompany;
    }

    public void setAgreeCompany(String agreeCompany) {
        this.agreeCompany = agreeCompany;
    }

    public String getAgreeServiceInterval() {
        return agreeServiceInterval;
    }

    public void setAgreeServiceInterval(String agreeServiceInterval) {
        this.agreeServiceInterval = agreeServiceInterval;
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

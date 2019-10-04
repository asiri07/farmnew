/*
 *
 *      Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *       *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *          This software product contains information which is proprietary to
 *          and considered a trade secret MsSoftIT Solution .
 *          It is expressly agreed that it shall not be reproduced in whole or part,
 *          disclosed, divulged or otherwise made available to any third party directly
 *          or indirectly.  Reproduction of this product for any purpose is prohibited
 *          without written authorisation from the The MsSoftIT Solution
 *          All Rights Reserved.
 *
 *          E-Mail mssoftit@gmail.com
 *          URL : mssoftit.lk
 *          Created By : Dilusha Kumari
 *
 */


package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;


@Entity

@Table(name = "maintenance_running_data_history")
public class MaintenanceRunningDataHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "RUNNING_ID")
    private int runningId;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "DATE")
    private String date;

    @Column(name = "TIME")
    private String time;

    @Column(name = "MET_FRO_NO")
    private double metFroNo;

    @Column(name = "MET_TO_NO")
    private double metToNo;


    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    private String detailCurrencyType;
    @Column(name = "COST")
    private double cost;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Column(name="UPDATE_USER")
    private int updateUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRunningId() {
        return runningId;
    }

    public void setRunningId(int runningId) {
        this.runningId = runningId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMetFroNo() {
        return metFroNo;
    }

    public void setMetFroNo(double metFroNo) {
        this.metFroNo = metFroNo;
    }

    public double getMetToNo() {
        return metToNo;
    }

    public void setMetToNo(double metToNo) {
        this.metToNo = metToNo;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public MaintenanceRunningDataHistory() {

    }
}


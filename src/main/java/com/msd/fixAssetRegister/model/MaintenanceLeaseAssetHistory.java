package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "maintenance_lease_asset_history")
public class MaintenanceLeaseAssetHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="LEASE_ID")
    private int leaseId;

    @Column(name="TRANSACTION_NO")
    private String transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "LEASE_TYPE")
    private int leaseType;

    @Column(name = "LEASE_AGRE_NO")
    private int leaseAgreNo;

    @Column(name = "LEASE_TOT")
    private double leaseTot;

    @Column(name = "LEASE_NO_PREMIUM")
    private String leaseNoPremium;

    @Column(name = "LEASE_PREMIUM")
    private double leasePremium;

    @Column(name = "LEASE_PREMIUM_DATE")
    private String leasePremiumDate;

    @Column(name = "LEASE_PERIOD_FROM")
    private Date leasePeriodFrom;

    @Column(name = "LEASE_PERIOD_TO")
    private Date leasePremiumTo;

    @Column(name = "LEASE_COMPANY")
    private String leaseCompany;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "LEASE_ADDRESS")
    private String leaseAddress;

    @Column(name = "LEASE_TELEPHONE_NO")
    private String leaseTelephoneNo;

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

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
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

    public int getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(int leaseType) {
        this.leaseType = leaseType;
    }

    public int getLeaseAgreNo() {
        return leaseAgreNo;
    }

    public void setLeaseAgreNo(int leaseAgreNo) {
        this.leaseAgreNo = leaseAgreNo;
    }

    public double getLeaseTot() {
        return leaseTot;
    }

    public void setLeaseTot(double leaseTot) {
        this.leaseTot = leaseTot;
    }

    public String getLeaseNoPremium() {
        return leaseNoPremium;
    }

    public void setLeaseNoPremium(String leaseNoPremium) {
        this.leaseNoPremium = leaseNoPremium;
    }

    public double getLeasePremium() {
        return leasePremium;
    }

    public void setLeasePremium(double leasePremium) {
        this.leasePremium = leasePremium;
    }

    public String getLeasePremiumDate() {
        return leasePremiumDate;
    }

    public void setLeasePremiumDate(String leasePremiumDate) {
        this.leasePremiumDate = leasePremiumDate;
    }

    public Date getLeasePeriodFrom() {
        return leasePeriodFrom;
    }

    public void setLeasePeriodFrom(Date leasePeriodFrom) {
        this.leasePeriodFrom = leasePeriodFrom;
    }

    public Date getLeasePremiumTo() {
        return leasePremiumTo;
    }

    public void setLeasePremiumTo(Date leasePremiumTo) {
        this.leasePremiumTo = leasePremiumTo;
    }

    public String getLeaseCompany() {
        return leaseCompany;
    }

    public void setLeaseCompany(String leaseCompany) {
        this.leaseCompany = leaseCompany;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getLeaseAddress() {
        return leaseAddress;
    }

    public void setLeaseAddress(String leaseAddress) {
        this.leaseAddress = leaseAddress;
    }

    public String getLeaseTelephoneNo() {
        return leaseTelephoneNo;
    }

    public void setLeaseTelephoneNo(String leaseTelephoneNo) {
        this.leaseTelephoneNo = leaseTelephoneNo;
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
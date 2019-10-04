package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "maintenance_insurance_history")
public class MaintenanceInsuranceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "INSURANCE_ID")
    private int insuranceId;

    @Column(name = "TRANSATCION_NO")
    private String transactionNo;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "INSURANCE_TYPE")
    private String insuranceType;

    @Column(name = "INSURANCE_POLICY_NO")
    private String insurancePolicyNo;

    @Column(name = "INSURANCE_POLICY")
    private String insurancePolicy;

    @Column(name = "INSURANCE_PERIOD")
    private int insurancePeriod;

    @Column(name = "INSURANCE_PERIOD_FROM")
    private Date insurancePeriodFrom;

    @Column(name = "INSURANCE_PERIOD_TO")
    private Date insurancePeriodTo;

    @Column(name = "INSURANCE_VALUE")
    private double insuranceValue;


    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    @Column(name = "PREMIUM")
    private Double premium;

    @Column(name = "INSURANCE_COMPANY")
    private String insuranceCompany;

    @Column(name = "INSURANCE_AGENT")
    private String insuranceAgent;

    @Column(name = "INSURANCE_ADDRESS")
    private String insuranceAddress;

    @Column(name = "INSURANCE_TELEPHONE_NO")
    private String insuranceTelephoneNo;

    @Column(name = "CATEGORY")
    private String category;


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

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(int insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public Date getInsurancePeriodFrom() {
        return insurancePeriodFrom;
    }

    public void setInsurancePeriodFrom(Date insurancePeriodFrom) {
        this.insurancePeriodFrom = insurancePeriodFrom;
    }

    public Date getInsurancePeriodTo() {
        return insurancePeriodTo;
    }

    public void setInsurancePeriodTo(Date insurancePeriodTo) {
        this.insurancePeriodTo = insurancePeriodTo;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceAgent() {
        return insuranceAgent;
    }

    public void setInsuranceAgent(String insuranceAgent) {
        this.insuranceAgent = insuranceAgent;
    }

    public String getInsuranceAddress() {
        return insuranceAddress;
    }

    public void setInsuranceAddress(String insuranceAddress) {
        this.insuranceAddress = insuranceAddress;
    }

    public String getInsuranceTelephoneNo() {
        return insuranceTelephoneNo;
    }

    public void setInsuranceTelephoneNo(String insuranceTelephoneNo) {
        this.insuranceTelephoneNo = insuranceTelephoneNo;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
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

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}

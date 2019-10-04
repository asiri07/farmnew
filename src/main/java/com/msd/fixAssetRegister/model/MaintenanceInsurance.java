package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_insurance")
public class MaintenanceInsurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INSURANCE_ID")
    private int insuranceID;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "CURRENCY_TYPE")
    private int currencyType;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;

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

    @Column(name = "PREMIUM")
    private double premium;

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

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Transient
    private String premiumDisplay;
    @Transient
    private  String insuredValueDisplay;

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(int insuranceID) {
        this.insuranceID = insuranceID;
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

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
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

    public String getPremiumDisplay() {
        return premiumDisplay;
    }

    public void setPremiumDisplay(String premiumDisplay) {
        this.premiumDisplay = premiumDisplay;
    }

    public String getInsuredValueDisplay() {
        return insuredValueDisplay;
    }

    public void setInsuredValueDisplay(String insuredValueDisplay) {
        this.insuredValueDisplay = insuredValueDisplay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

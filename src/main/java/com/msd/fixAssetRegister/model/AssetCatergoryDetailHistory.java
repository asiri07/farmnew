package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the asset_catergory_detail database table.
 */
@Entity
@Table(name = "asset_catergory_detail_history")
public class AssetCatergoryDetailHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DCAT_ID")
    private int dcatId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Column(name = "DCAT_CODE")
    private String dcatCode;

    @Column(name = "DCAT_DES")
    private String dcatDes;

    @Column(name = "DEP_RATE_ACCOUNT")
    private Double depRateAccount;

    @Column(name = "DEP_RATE_INCOME_TAX")
    private Double depRateIncomeTax;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "SCAT_ID")
    private int scatId;

    @Column(name = "STATUS")
    private int status = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    public AssetCatergoryDetailHistory() {
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getDcatId() {
        return this.dcatId;
    }

    public void setDcatId(int dcatId) {
        this.dcatId = dcatId;
    }

    public Date getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getDcatCode() {
        return this.dcatCode;
    }

    public void setDcatCode(String dcatCode) {
        this.dcatCode = dcatCode;
    }

    public String getDcatDes() {
        return this.dcatDes;
    }

    public void setDcatDes(String dcatDes) {
        this.dcatDes = dcatDes;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScatId() {
        return scatId;
    }

    public void setScatId(int scatId) {
        this.scatId = scatId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getDepRateAccount() {
        return depRateAccount;
    }

    public void setDepRateAccount(Double depRateAccount) {
        this.depRateAccount = depRateAccount;
    }

    public Double getDepRateIncomeTax() {
        return depRateIncomeTax;
    }

    public void setDepRateIncomeTax(Double depRateIncomeTax) {
        this.depRateIncomeTax = depRateIncomeTax;
    }
}
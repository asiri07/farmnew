package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the asset_catergory_sub database table.
 */
@Entity
@Table(name = "asset_catergory_sub_history")
public class AssetCatergorySubHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "SCAT_ID")
    private int scatId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Column(name = "SCAT_CODE")
    private String scatCode;

    @Column(name = "SCAT_DES")
    private String scatDes;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "MCAT_ID")
    private int mcatId;

    @Column(name = "STATUS")
    private int status = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_USER")
    private int updateUser;

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

    public AssetCatergorySubHistory() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScatId() {
        return this.scatId;
    }

    public void setScatId(int scatId) {
        this.scatId = scatId;
    }

    public Date getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getScatCode() {
        return this.scatCode;
    }

    public void setScatCode(String scatCode) {
        this.scatCode = scatCode;
    }

    public String getScatDes() {
        return this.scatDes;
    }

    public void setScatDes(String scatDes) {
        this.scatDes = scatDes;
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

    public int getMcatId() {
        return mcatId;
    }

    public void setMcatId(int mcatId) {
        this.mcatId = mcatId;
    }
}
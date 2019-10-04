package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the asset_catergory_main database table.
 */
@Entity
@Table(name = "asset_catergory_main_history")
public class AssetCatergoryMainHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "MCAT_ID")
    private int mcatId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Column(name = "MCAT_CODE")
    private String mcatCode;

    @Column(name = "MCAT_DES")
    private String mcatDes;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "STATUS")
    private int status = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_USER")
    private int updateUser;


    public AssetCatergoryMainHistory() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AssetCatergoryMainHistory(String mcatCode) {
        this.mcatCode = mcatCode;
    }

    public int getMcatId() {
        return this.mcatId;
    }

    public void setMcatId(int mcatId) {
        this.mcatId = mcatId;
    }

    public Date getActionTime() {
        return this.actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getMcatCode() {
        return this.mcatCode;
    }

    public void setMcatCode(String mcatCode) {
        this.mcatCode = mcatCode;
    }

    public String getMcatDes() {
        return this.mcatDes;
    }

    public void setMcatDes(String mcatDes) {
        this.mcatDes = mcatDes;
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
}
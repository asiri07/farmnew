package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
@Table(name="accessory_assigning_history")
public class AccessoryAssigningHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCESSORY_ASSIGNING_ID")
    private int accessoryAssigningId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "ACCESSORY_ID")
    private int accessoryId;

    @Transient
    private String detailAccessoryId;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    public int getAccessoryAssigningId() {
        return accessoryAssigningId;
    }

    public void setAccessoryAssigningId(int accessoryAssigningId) {
        this.accessoryAssigningId = accessoryAssigningId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getDetailAccessoryId() {
        return detailAccessoryId;
    }

    public void setDetailAccessoryId(String detailAccessoryId) {
        this.detailAccessoryId = detailAccessoryId;
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
}
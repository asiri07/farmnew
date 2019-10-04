package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
@Table(name="accessory_assigning")
public class AccessoryAssigning implements Serializable {

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

    @Column(name = "QTY")
    private int qty;

    @Column(name = "ACTION_TIME")
    private Date actionTime;

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
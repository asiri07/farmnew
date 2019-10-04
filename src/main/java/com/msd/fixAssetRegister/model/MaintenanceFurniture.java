package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_furniture")
public class MaintenanceFurniture implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "FURNITURE_ID")
    private int furnitureId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "ASSET_ID_FROM")
    private int assetIdFrom;

    @Column(name = "ASSET_ID_TO")
    private int assetIdTo;

    @Column(name = "IS_WARRANTY")
    private int isWarranty;

    @Column(name = "IS_INSURANCE")
    private int isInsurnce;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;

    public int getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(int furnitureId) {
        this.furnitureId = furnitureId;
    }


    public int getAssetIdFrom() {
        return assetIdFrom;
    }

    public void setAssetIdFrom(int assetIdFrom) {
        this.assetIdFrom = assetIdFrom;
    }

    public int getAssetIdTo() {
        return assetIdTo;
    }

    public void setAssetIdTo(int assetIdTo) {
        this.assetIdTo = assetIdTo;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getIsWarranty() {
        return isWarranty;
    }

    public void setIsWarranty(int isWarranty) {
        this.isWarranty = isWarranty;
    }

    public int getIsInsurnce() {
        return isInsurnce;
    }

    public void setIsInsurnce(int isInsurnce) {
        this.isInsurnce = isInsurnce;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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


package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_furniture_history")
public class MaintenanceFurnitureHistory implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "FURNITURE_ID")
    private int furnitureId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "IS_WARRANTY")
    private int isWarranty;

    @Column(name = "IS_INSURANCE")
    private int isInsurnce;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "UPDATE_USER")
    private int updateUser;

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

    public int getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(int furnitureId) {
        this.furnitureId = furnitureId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
}


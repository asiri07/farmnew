/*
 *     Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *     *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *        This software product contains information which is proprietary to
 *        and considered a trade secret MsSoftIT Solution .
 *        It is expressly agreed that it shall not be reproduced in whole or part,
 *        disclosed, divulged or otherwise made available to any third party directly
 *        or indirectly.  Reproduction of this product for any purpose is prohibited
 *        without written authorisation from the The MsSoftIT Solution
 *        All Rights Reserved.
 *
 *        E-Mail mssoftit@gmail.com
 *        URL : mssoftit.lk
 *        Created By : Mahendra Sri Dayarathna
 */
package com.msd.fixAssetRegister.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_software_history")
public class MaintenanceSoftwareHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "SOFTWARE_ID")
    private int softwareId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SELLER_DEALER")
    private String sellerDealer;

    @Column(name = "SELLER_ADD")
    private String sellerAdd;

    @Column(name = "SOFT_OWNER_NAME")
    private String softOwnerName;

    @Column(name = "PRODUCT_KEY")
    private String productKey;

    @Column(name = "NO_USERS")
    private String noUsers;

    @Column(name = "IS_SERVICE_AGRE")
    private int isServiceAgre;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="UPDATE_USER")
    private int updatedUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getSellerDealer() {
        return sellerDealer;
    }

    public void setSellerDealer(String sellerDealer) {
        this.sellerDealer = sellerDealer;
    }

    public String getSellerAdd() {
        return sellerAdd;
    }

    public void setSellerAdd(String sellerAdd) {
        this.sellerAdd = sellerAdd;
    }

    public String getSoftOwnerName() {
        return softOwnerName;
    }

    public void setSoftOwnerName(String softOwnerName) {
        this.softOwnerName = softOwnerName;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getNoUsers() {
        return noUsers;
    }

    public void setNoUsers(String noUsers) {
        this.noUsers = noUsers;
    }

    public int getIsServiceAgre() {
        return isServiceAgre;
    }

    public void setIsServiceAgre(int isServiceAgre) {
        this.isServiceAgre = isServiceAgre;
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

    public int getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

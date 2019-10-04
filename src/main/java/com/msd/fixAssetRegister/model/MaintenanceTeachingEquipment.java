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
@Table(name = "maintenance_teaching_equipment")
public class MaintenanceTeachingEquipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEACHING_ID")
    private int teachingId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "WIDTH")
    private String width;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "IS_WARRANTY")
    private int isWarranty;

    @Column(name = "IS_INSURANCE")
    private int isInsurance;

    @Column(name = "IS_SERVICE_AGRE")
    private int isServiceAgre;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name="USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    public int getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(int teachingId) {
        this.teachingId = teachingId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getIsWarranty() {
        return isWarranty;
    }

    public void setIsWarranty(int isWarranty) {
        this.isWarranty = isWarranty;
    }

    public int getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(int isInsurance) {
        this.isInsurance = isInsurance;
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



}

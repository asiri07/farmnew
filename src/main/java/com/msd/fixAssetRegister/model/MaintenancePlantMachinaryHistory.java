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
@Table(name = "maintenance_plant_machinary_history")
public class MaintenancePlantMachinaryHistory implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "PLANT_ID")
    private int plantId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "TYPE_FUEL")
    private String typeFuel;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "COUNTRY_ORIGIN")
    private String countryOrigin;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "YEAR_MANUFACTURE")
    private int yearManufacture;

    @Column(name = "WEIDTH")
    private String weidth;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "IS_WARRANTY")
    private int isWarrenty;

    @Column(name = "IS_INSURANCE")
    private int isInsurance;

    @Column(name = "IS_SERVICE_AGRE")
    private int isServiceAgre;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "MANUFACTURE")
    private String manufacture;

    @Column(name = "BAYER")
    private String bayer;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name="TELEPHONE")
    private String telephone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="USER_ID")
    private int userId;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(String typeFuel) {
        this.typeFuel = typeFuel;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWeidth() {
        return weidth;
    }

    public void setWeidth(String weidth) {
        this.weidth = weidth;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getBayer() {
        return bayer;
    }

    public void setBayer(String bayer) {
        this.bayer = bayer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIsWarrenty() {
        return isWarrenty;
    }

    public void setIsWarrenty(int isWarrenty) {
        this.isWarrenty = isWarrenty;
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

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(int yearManufacture) {
        this.yearManufacture = yearManufacture;
    }
}

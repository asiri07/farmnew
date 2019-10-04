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
@Table(name = "maintenance_vehicle")
public class MaintenanceVehicle implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int vehicleId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "REGI_NO")
    private String regiNo;

    @Column(name = "VEHICLE_CLASS")
    private String vehicleClass;

    @Column(name = "TYPE_FUEL")
    private int typeFuel;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "COUNTRY_ORIGIN")
    private String countryOrigin;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "YEAR_MANUFACTUTE")
    private int yearManufactute;

    @Column(name = "COLOUR")
    private String colour;

    @Column(name = "CHASSIS_NO")
    private String chassisNo;

    @Column(name = "ENGINE_NO")
    private String engineNo;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CY_CAPACITY")
    private double cyCapacyty;

    @Column(name = "TAX_CLASS")
    private String taxClass;

    @Column(name = "REGI_STATUS")
    private String regiStatus;

    @Column(name = "PREVI_OWNER_NO")
    private int previOwnerNo;

    @Column(name = "SEAT_CAPACITY")
    private int seatCapacity;

    @Column(name = "TYRE_SIZE_FRONT")
    private String tyreSizeFront;

    @Column(name = "TYRE_SIZE_REAR")
    private String tyreSizeRear;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "WIDTH")
    private String width;

    @Column(name = "SELLER_NAME")
    private String sellerName;

    @Column(name = "SELLER_ADDRESS")
    private String sellerAddress;

    @Column(name = "SELLER_TELEPHONE_NO")
    private String sellerTelephoneNo;

    @Column(name = "REG_PROVINCIAL")
    private String regProvincial;

    @Column(name = "FIRST_REGISTRATION_DATE")
    private String regFirstDa;

    @Column(name = "DA_LICENCE")
    private String daLicence;

    @Column(name = "SERVICE_INTERVAL")
    private String serviceInterval;

    @Column(name = "IS_WARRANTY")
    private int isWarranty;

    @Column(name = "IS_INSURANCE")
    private int isInsurance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="USER_ID")
    private int userId;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getRegiNo() {
        return regiNo;
    }

    public void setRegiNo(String regiNo) {
        this.regiNo = regiNo;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public int getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(int typeFuel) {
        this.typeFuel = typeFuel;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
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

    public int getYearManufactute() {
        return yearManufactute;
    }

    public void setYearManufactute(int yearManufactute) {
        this.yearManufactute = yearManufactute;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDaLicence() {
        return daLicence;
    }

    public void setDaLicence(String daLicence) {
        this.daLicence = daLicence;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getCyCapacyty() {
        return cyCapacyty;
    }

    public void setCyCapacyty(double cyCapacyty) {
        this.cyCapacyty = cyCapacyty;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getRegiStatus() {
        return regiStatus;
    }

    public void setRegiStatus(String regiStatus) {
        this.regiStatus = regiStatus;
    }

    public int getPreviOwnerNo() {
        return previOwnerNo;
    }

    public void setPreviOwnerNo(int previOwnerNo) {
        this.previOwnerNo = previOwnerNo;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getTyreSizeFront() {
        return tyreSizeFront;
    }

    public void setTyreSizeFront(String tyreSizeFront) {
        this.tyreSizeFront = tyreSizeFront;
    }

    public String getTyreSizeRear() {
        return tyreSizeRear;
    }

    public void setTyreSizeRear(String tyreSizeRear) {
        this.tyreSizeRear = tyreSizeRear;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerTelephoneNo() {
        return sellerTelephoneNo;
    }

    public void setSellerTelephoneNo(String sellerTelephoneNo) {
        this.sellerTelephoneNo = sellerTelephoneNo;
    }

    public String getRegProvincial() {
        return regProvincial;
    }

    public void setRegProvincial(String regProvincial) {
        this.regProvincial = regProvincial;
    }

    public String getRegFirstDa() {
        return regFirstDa;
    }

    public void setRegFirstDa(String regFirstDa) {
        this.regFirstDa = regFirstDa;
    }

    public String getServiceInterval() {
        return serviceInterval;
    }

    public void setServiceInterval(String serviceInterval) {
        this.serviceInterval = serviceInterval;
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
}

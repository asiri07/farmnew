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
@Table(name = "maintenance_computer_history")
public class MaintenanceComputerHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPUTER_HISTORY_ID")
    private int computerHistotyId;

    @Column(name = "COMPUTER_ID")
    private int computerId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "COM_TYPE")
    private int computerType;

    @Column(name = "PROCESSOR")
    private String processor;

    @Column(name = "RAM_SIZE")
    private String ramSize;

    @Column(name = "HDD")
    private String hardDiskCapacity;

    @Column(name = "MONITOR")
    private String monitor;

    @Column(name = "SYSTEM_TYPE")
    private String systemType;

    @Column(name = "OPERATING_SYSTEM")
    private String operatingSystem;

    @Column(name = "IS_WARRANTY")
    private int isWarranty;

    @Column(name = "IS_INSURANCE")
    private int isInsurance;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "COM_COLOR")
    private String computerColor;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "BATTERY")
    private String battery;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "WIDTH")
    private String width;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "BUYER")
    private String buyer;

    @Column(name = "BUYER_ADDRESS")
    private String buyerAddress;

    @Column(name = "BUYER_TEL")
    private String buyerTel;

    @Column(name = "ADAPTER")
    private String adapter;

    @Column(name="UPDATE_USER")
    private int upDateUser;

    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="STATUS")
    private int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;


    public int getComputerHistotyId() {
        return computerHistotyId;
    }

    public void setComputerHistotyId(int computerHistotyId) {
        this.computerHistotyId = computerHistotyId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getComputerType() {
        return computerType;
    }

    public void setComputerType(int computerType) {
        this.computerType = computerType;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getHardDiskCapacity() {
        return hardDiskCapacity;
    }

    public void setHardDiskCapacity(String hardDiskCapacity) {
        this.hardDiskCapacity = hardDiskCapacity;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getComputerColor() {
        return computerColor;
    }

    public void setComputerColor(String computerColor) {
        this.computerColor = computerColor;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public int getUpDateUser() {
        return upDateUser;
    }

    public void setUpDateUser(int upDateUser) {
        this.upDateUser = upDateUser;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }


}

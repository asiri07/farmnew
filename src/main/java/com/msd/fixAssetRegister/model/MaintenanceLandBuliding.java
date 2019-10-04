/*
 *
 *      Copyright (c) 2018,  MsSoftIT Solution All Rights Reserved.
 *       *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *          This software product contains information which is proprietary to
 *          and considered a trade secret MsSoftIT Solution .
 *          It is expressly agreed that it shall not be reproduced in whole or part,
 *          disclosed, divulged or otherwise made available to any third party directly
 *          or indirectly.  Reproduction of this product for any purpose is prohibited
 *          without written authorisation from the The MsSoftIT Solution
 *          All Rights Reserved.
 *
 *          E-Mail mssoftit@gmail.com
 *          URL : mssoftit.lk
 *          Created By : Mahendra Sri Dayarathna
 *
 */
package com.msd.fixAssetRegister.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maintenance_land_building")
public class MaintenanceLandBuliding implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAND_BUILDING_ID")
    private int landBuildingID;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "BUILDING_ADDRESS1")
    private String buildingAddress1;

    @Column(name = "BUILDING_ADDRESS2")
    private String buildingAddress2;

    @Column(name = "BUILDING_ADDRESS3")
    private String buildingAddress3;

    @Column(name = "BUILDING_ADDRESS4")
    private String buildingAddress4;

    @Column(name = "DEED_TYPE")
    private int deedType;

    @Column(name = "DEED_NO")
    private String deedNo;

    @Column(name = "DEED_SIGNED_DATE")
    private Date deedSignedDate;

    @Column(name = "DEED_REGISTER_DATE")
    private Date deedRegisterDate;

    @Column(name = "DEED_VALUE")
    private double deedValue=0.00;

    @Column(name = "EXTENT")
    private String extent;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ASSESSEMENT_NO")
    private String assessmentNo;

    @Column(name = "ASSESSEMENT_VALUE")
    private String assessementValue;

    @Column(name = "BUILDING_STORIED")
    private String buildingStoried;

    @Column(name = "BUILDING_PLAN_NO")
    private String buildingPlanNo;

    @Column(name = "COC_NO")
    private String cocNo;

    @Column(name = "ATTESTER")
    private String attester;

    @Column(name = "ATTESTER_ADDRESS")
    private String attesterAddress;

    @Column(name = "SELLER")
    private String seller;

    @Column(name = "SELLER_ADDRESS")
    private String sellerAddress;

    @Column(name = "GRA_NIL_DIV_NO")
    private String graNilDivNo;

    @Column(name = "DIVISION_NO")
    private String divisionNo;

    @Column(name = "SURVEY_PLAN_NO")
    private String surveyPlanNo;

    @Column(name = "SURVEY_NAME")
    private String surveyName;

    @Column(name = "SURVEY_ADDRESS")
    private String surveyAddress;

    @Column(name = "SURVEY_TELEPHONE")
    private String surveyTelephone;

    @Column(name = "SURVEY_DATE")
    private String surveyDate;

    @Column(name = "LOT_NO")
    private String lotNo;

    @Column(name = "LAND_NAME")
    private String landName;

    @Column(name = "LAND_SITUATED")
    private String landSituated;

    @Column(name = "LAND_PROVINCIAL_AREA")
    private String landProvincalArea;

    @Column(name = "LAND_DISTRICT")
    private String landDistrict;

    @Column(name = "LAND_PROVINCE")
    private String landProvince;

    @Column(name = "IS_INSURANCE")
    private int isInsurance= 0;

    @Transient
    private String amount;
    @Transient
    private String assesmentValueDisplay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="USER_ID")
    private int userId;

    public MaintenanceLandBuliding() {
    }

    public int getLandBuildingID() {
        return landBuildingID;
    }

    public void setLandBuildingID(int landBuildingID) {
        this.landBuildingID = landBuildingID;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getBuildingAddress1() {
        return buildingAddress1;
    }

    public void setBuildingAddress1(String buildingAddress1) {
        this.buildingAddress1 = buildingAddress1;
    }

    public String getBuildingAddress2() {
        return buildingAddress2;
    }

    public void setBuildingAddress2(String buildingAddress2) {
        this.buildingAddress2 = buildingAddress2;
    }

    public String getBuildingAddress3() {
        return buildingAddress3;
    }

    public void setBuildingAddress3(String buildingAddress3) {
        this.buildingAddress3 = buildingAddress3;
    }

    public String getBuildingAddress4() {
        return buildingAddress4;
    }

    public void setBuildingAddress4(String buildingAddress4) {
        this.buildingAddress4 = buildingAddress4;
    }

    public int getDeedType() {
        return deedType;
    }

    public void setDeedType(int deedType) {
        this.deedType = deedType;
    }

    public String getDeedNo() {
        return deedNo;
    }

    public void setDeedNo(String deedNo) {
        this.deedNo = deedNo;
    }

    public Date getDeedSignedDate() {
        return deedSignedDate;
    }

    public void setDeedSignedDate(Date deedSignedDate) {
        this.deedSignedDate = deedSignedDate;
    }

    public Date getDeedRegisterDate() {
        return deedRegisterDate;
    }

    public void setDeedRegisterDate(Date deedRegisterDate) {
        this.deedRegisterDate = deedRegisterDate;
    }

    public double getDeedValue() {
        return deedValue;
    }

    public void setDeedValue(double deedValue) {
        this.deedValue = deedValue;
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAssessmentNo() {
        return assessmentNo;
    }

    public void setAssessmentNo(String assessmentNo) {
        this.assessmentNo = assessmentNo;
    }

    public String getAssessementValue() {
        return assessementValue;
    }

    public void setAssessementValue(String assessementValue) {
        this.assessementValue = assessementValue;
    }

    public String getBuildingStoried() {
        return buildingStoried;
    }

    public void setBuildingStoried(String buildingStoried) {
        this.buildingStoried = buildingStoried;
    }

    public String getBuildingPlanNo() {
        return buildingPlanNo;
    }

    public void setBuildingPlanNo(String buildingPlanNo) {
        this.buildingPlanNo = buildingPlanNo;
    }

    public String getCocNo() {
        return cocNo;
    }

    public void setCocNo(String cocNo) {
        this.cocNo = cocNo;
    }

    public String getAttester() {
        return attester;
    }

    public void setAttester(String attester) {
        this.attester = attester;
    }

    public String getAttesterAddress() {
        return attesterAddress;
    }

    public void setAttesterAddress(String attesterAddress) {
        this.attesterAddress = attesterAddress;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getGraNilDivNo() {
        return graNilDivNo;
    }

    public void setGraNilDivNo(String graNilDivNo) {
        this.graNilDivNo = graNilDivNo;
    }

    public String getDivisionNo() {
        return divisionNo;
    }

    public void setDivisionNo(String divisionNo) {
        this.divisionNo = divisionNo;
    }

    public String getSurveyPlanNo() {
        return surveyPlanNo;
    }

    public void setSurveyPlanNo(String surveyPlanNo) {
        this.surveyPlanNo = surveyPlanNo;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyAddress() {
        return surveyAddress;
    }

    public void setSurveyAddress(String surveyAddress) {
        this.surveyAddress = surveyAddress;
    }

    public String getSurveyTelephone() {
        return surveyTelephone;
    }

    public void setSurveyTelephone(String surveyTelephone) {
        this.surveyTelephone = surveyTelephone;
    }

    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getLandSituated() {
        return landSituated;
    }

    public void setLandSituated(String landSituated) {
        this.landSituated = landSituated;
    }

    public String getLandProvincalArea() {
        return landProvincalArea;
    }

    public void setLandProvincalArea(String landProvincalArea) {
        this.landProvincalArea = landProvincalArea;
    }

    public String getLandDistrict() {
        return landDistrict;
    }

    public void setLandDistrict(String landDistrict) {
        this.landDistrict = landDistrict;
    }

    public String getLandProvince() {
        return landProvince;
    }

    public void setLandProvince(String landProvince) {
        this.landProvince = landProvince;
    }

    public int isInsurance() {
        return isInsurance;
    }

    public void setInsurance(int insurance) {
        isInsurance = insurance;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAssesmentValueDisplay() {
        return assesmentValueDisplay;
    }

    public void setAssesmentValueDisplay(String assesmentValueDisplay) {
        this.assesmentValueDisplay = assesmentValueDisplay;
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
}

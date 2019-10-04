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
@Table(name = "maintenance_land_history")
public class MaintenanceLandHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAND_HISTORY_ID")
    private int landHistoryId;

    @Column(name = "LAND_ID")
    private int landId;

    @Column(name = "ASSET_ID")
    private int assetId;

    @Column(name = "DEED_TYPE")
    private int deedType;

    @Column(name = "DEED_NO")
    private String deedNo;

    @Column(name = "DEED_SIGN_DATE")
    private Date deedSignDate;

    @Column(name = "DEED_REG_DATE")
    private Date deedRegDate;

    @Column(name = "LAN_ADD1")
    private String lanAdd1;

    @Column(name = "LAN_ADD2")
    private String lanAdd2;

    @Column(name = "LAN_ADD3")
    private String lanAdd3;

    @Column(name = "LAN_ADD4")
    private String lanAdd4;

    @Column(name = "VALUE")
    private double value;

    @Column(name = "EXTENT")
    private String extent;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ATTESTER")
    private String attester;

    @Column(name = "ATTESTER_ADD")
    private String attesterAdd;

    @Column(name = "SELLER")
    private String seller;

    @Column(name = "SELL_ADD")
    private String selAdd;

    @Column(name = "GRA_NIL_DIVI_NO")
    private String graNilDiviNo;

    @Column(name = "DIVISION_NO")
    private String divisionNo;

    @Column(name = "ASSESMENT_NO")
    private String assesmentNo;

    @Column(name = "ASSESMENT_VALUE")
    private String assesmentValue;

    @Column(name = "SURVEY_PLAN_NO")
    private String surveyPlanNo;

    @Column(name = "SURVEYOR_NAME")
    private String surveyName;

    @Column(name = "SURVEYOR_ADD")
    private String surveyAdd;

    @Column(name = "SURVEYOR_TEL")
    private String surveyTel;

    @Column(name = "SURVEYOR_DATE")
    private String surveyDate;

    @Column(name = "LOT_NO")
    private String lotNo;

    @Column(name = "LAND_NAME")
    private String landName;

    @Column(name = "LAND_SITUATED")
    private String landSituated;

    @Column(name = "LAND_P_AREA")
    private String landPArea;

    @Column(name = "LAND_DISTRICT")
    private String landDistrict;

    @Column(name = "LAND_PROVINCE")
    private String landProvince;

    @Column(name = "IS_INSURANCE")
    private int isInsurance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="USER_ID")
    private int userId;

    @Column(name = "UPDATE_USER")
    private int updateUser;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "TRANSACTION_NO")
    private int transactionNo;

    public int getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        this.transactionNo = transactionNo;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getLandId() {
        return landId;
    }

    public void setLandId(int landId) {
        this.landId = landId;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getDeedSignDate() {
        return deedSignDate;
    }

    public void setDeedSignDate(Date deedSignDate) {
        this.deedSignDate = deedSignDate;
    }

    public Date getDeedRegDate() {
        return deedRegDate;
    }

    public void setDeedRegDate(Date deedRegDate) {
        this.deedRegDate = deedRegDate;
    }

    public String getLanAdd1() {
        return lanAdd1;
    }

    public void setLanAdd1(String lanAdd1) {
        this.lanAdd1 = lanAdd1;
    }

    public String getLanAdd2() {
        return lanAdd2;
    }

    public void setLanAdd2(String lanAdd2) {
        this.lanAdd2 = lanAdd2;
    }

    public String getLanAdd3() {
        return lanAdd3;
    }

    public void setLanAdd3(String lanAdd3) {
        this.lanAdd3 = lanAdd3;
    }

    public String getLanAdd4() {
        return lanAdd4;
    }

    public void setLanAdd4(String lanAdd4) {
        this.lanAdd4 = lanAdd4;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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

    public String getAttester() {
        return attester;
    }

    public void setAttester(String attester) {
        this.attester = attester;
    }

    public String getAttesterAdd() {
        return attesterAdd;
    }

    public void setAttesterAdd(String attesterAdd) {
        this.attesterAdd = attesterAdd;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSelAdd() {
        return selAdd;
    }

    public void setSelAdd(String selAdd) {
        this.selAdd = selAdd;
    }

    public String getGraNilDiviNo() {
        return graNilDiviNo;
    }

    public void setGraNilDiviNo(String graNilDiviNo) {
        this.graNilDiviNo = graNilDiviNo;
    }

    public String getDivisionNo() {
        return divisionNo;
    }

    public void setDivisionNo(String divisionNo) {
        this.divisionNo = divisionNo;
    }

    public String getAssesmentNo() {
        return assesmentNo;
    }

    public void setAssesmentNo(String assesmentNo) {
        this.assesmentNo = assesmentNo;
    }

    public String getAssesmentValue() {
        return assesmentValue;
    }

    public void setAssesmentValue(String assesmentValue) {
        this.assesmentValue = assesmentValue;
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

    public String getSurveyAdd() {
        return surveyAdd;
    }

    public void setSurveyAdd(String surveyAdd) {
        this.surveyAdd = surveyAdd;
    }

    public void setSurveyTel(String surveyTel) {
        this.surveyTel = surveyTel;
    }

    public String getSurveyTel() {
        return surveyTel;
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

    public String getLandPArea() {
        return landPArea;
    }

    public void setLandPArea(String landPArea) {
        this.landPArea = landPArea;
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

    public int getLandHistoryId() {
        return landHistoryId;
    }

    public void setLandHistoryId(int landHistoryId) {
        this.landHistoryId = landHistoryId;
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

    public int getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(int isInsurance) {
        this.isInsurance = isInsurance;
    }

}



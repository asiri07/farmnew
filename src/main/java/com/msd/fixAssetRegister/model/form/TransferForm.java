package com.msd.fixAssetRegister.model.form;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class TransferForm implements Serializable {

    private int assertId;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String fromDep;
    private String fromSec;
    private String fromLoc;
    private String fromEmpNo;
    private String toDep;
    private String toSec;
    private String toLoc;
    private String toEmpNo;
    private int userId;
    private int type;
    private String multiAssets;
    private String issuedTo;
    private String comments;
    private int isDepartmentTrasfer = 0;


    public TransferForm() {}



    public int getAssertId() {
        return assertId;
    }

    public void setAssertId(int assertId) {
        this.assertId = assertId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromDep() {
        return fromDep;
    }

    public void setFromDep(String fromDep) {
        this.fromDep = fromDep;
    }

    public String getFromSec() {
        return fromSec;
    }

    public void setFromSec(String fromSec) {
        this.fromSec = fromSec;
    }

    public String getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(String fromLoc) {
        this.fromLoc = fromLoc;
    }

    public String getFromEmpNo() {
        return fromEmpNo;
    }

    public void setFromEmpNo(String fromEmpNo) {
        this.fromEmpNo = fromEmpNo;
    }

    public String getToDep() {
        return toDep;
    }

    public void setToDep(String toDep) {
        this.toDep = toDep;
    }

    public String getToSec() {
        return toSec;
    }

    public void setToSec(String toSec) {
        this.toSec = toSec;
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc;
    }

    public String getToEmpNo() {
        return toEmpNo;
    }

    public void setToEmpNo(String toEmpNo) {
        this.toEmpNo = toEmpNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMultiAssets() {
        return multiAssets;
    }

    public void setMultiAssets(String multiAssets) {
        this.multiAssets = multiAssets;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getIsDepartmentTrasfer() {
        return isDepartmentTrasfer;
    }

    public void setIsDepartmentTrasfer(int isDepartmentTrasfer) {
        this.isDepartmentTrasfer = isDepartmentTrasfer;
    }
}

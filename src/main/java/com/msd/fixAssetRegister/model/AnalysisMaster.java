package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="anlysis_master")
public class AnalysisMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ANA_CODE_ID")
    private int anaCodeId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACTION_TIME")
    private Date actionTime;

    @Column(name="ANA_CODE")
    private String anaCode;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="USER_ID")
    private int userId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAnaCodeId() {
        return anaCodeId;
    }

    public void setAnaCodeId(int anaCodeId) {
        this.anaCodeId = anaCodeId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getAnaCode() {
        return anaCode;
    }

    public void setAnaCode(String anaCode) {
        this.anaCode = anaCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

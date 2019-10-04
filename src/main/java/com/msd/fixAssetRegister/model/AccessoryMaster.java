package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
@Table(name="accessory_master")
public class AccessoryMaster implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCER_ID")
	private int accerId;

    @Column(name="ACCER_CODE")
    private String accerCode;

	@Column(name="ACCER_NAME")
	private String accerName;

	@Column(name = "USER_ID")
    private int userId;

    @Column(name = "ACTION_TIME")
    private Date actionTime;


    public int getAccerId() {
        return accerId;
    }

    public void setAccerId(int accerId) {
        this.accerId = accerId;
    }

    public String getAccerCode() {
        return accerCode;
    }

    public void setAccerCode(String accerCode) {
        this.accerCode = accerCode;
    }

    public String getAccerName() {
        return accerName;
    }

    public void setAccerName(String accerName) {
        this.accerName = accerName;
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
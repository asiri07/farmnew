package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the physical_verification database table.
 * 
 */
@Entity
@Table(name="physical_verification")
@NamedQuery(name="PhysicalVerification.findAll", query="SELECT p FROM PhysicalVerification p")
public class PhysicalVerification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="BALANCE")
	private Double balance;

	@Transient
	private String detailBalance;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="DETAIL_CODE")
	private String detailCode;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="DETAIL_ID")
	private String detailId;

	@Column(name="LOCATION_ID")
	private int locationId;

	@Column(name="USER_ID")
	private int userId;

	public PhysicalVerification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetailCode() {
		return this.detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailBalance() {
		return detailBalance;
	}

	public void setDetailBalance(String detailBalance) {
		this.detailBalance = detailBalance;
	}
}
package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the physical_verification database table.
 *
 */
@Entity
@Table(name="asset_revaluation")
public class AssetRevaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="BALANCE")
	private Double balance;

	@Column(name="REVALUE")
	private Double revalue;

	@Transient
	private String detailBalance;

	@Transient
	private String detailrevalue;

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

	@Column(name="CURRENCY_ID")
	private String currencyId;


	public String getDetailrevalue() {
		return detailrevalue;
	}

	public void setDetailrevalue(String detailrevalue) {
		this.detailrevalue = detailrevalue;
	}

	public Double getRevalue() {
		return revalue;
	}

	public void setRevalue(Double revalue) {
		this.revalue = revalue;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getDetailBalance() {
		return detailBalance;
	}

	public void setDetailBalance(String detailBalance) {
		this.detailBalance = detailBalance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}
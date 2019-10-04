package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the disposal database table.
 * 
 */
@Entity
@Table(name = "disposal")
@NamedQuery(name="Disposal.findAll", query="SELECT d FROM Disposal d")
public class Disposal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DP_ID")
	private int dpId;

	@Column(name="TRANSACTION_NO")
	private String transactionNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DP_DATE")
	private Date dpDate;

	@Column(name="DP_DES")
	private String dpDes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DP_LUPDATE")
	private Date dpLupdate;

	@Column(name="DP_REASON")
	private String dpReason;

	@Column(name="DP_UPDATEBY")
	private String dpUpdateby;

	@Column(name="`DP_VALUE`")
	private Double dp_value;

	@Column(name = "CURRENCY_TYPE")
	private int currencyType;

	@Column(name="`CURRENCY_VALUE`")
	private Double currencyValue;

	@Column(name="USER_ID")
	private int userId;

	//bi-directional many-to-one association to Asset
	@ManyToOne()
	@JoinColumn(name="AS_ID")
	private Asset asset;

	public Disposal() {
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Double getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(Double currencyValue) {
		this.currencyValue = currencyValue;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(int currencyType) {
		this.currencyType = currencyType;
	}



	public int getDpId() {
		return this.dpId;
	}

	public void setDpId(int dpId) {
		this.dpId = dpId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Date getDpDate() {
		return this.dpDate;
	}

	public void setDpDate(Date dpDate) {
		this.dpDate = dpDate;
	}

	public String getDpDes() {
		return this.dpDes;
	}

	public void setDpDes(String dpDes) {
		this.dpDes = dpDes;
	}

	public Date getDpLupdate() {
		return this.dpLupdate;
	}

	public void setDpLupdate(Date dpLupdate) {
		this.dpLupdate = dpLupdate;
	}

	public String getDpReason() {
		return this.dpReason;
	}

	public void setDpReason(String dpReason) {
		this.dpReason = dpReason;
	}

	public String getDpUpdateby() {
		return this.dpUpdateby;
	}

	public void setDpUpdateby(String dpUpdateby) {
		this.dpUpdateby = dpUpdateby;
	}

	public Double getDp_value() {
		return this.dp_value;
	}

	public void setDp_value(Double dp_value) {
		this.dp_value = dp_value;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

}
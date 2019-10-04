package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the asset_prior_reg database table.
 * 
 */
@Entity
@Table(name="asset_prior_reg")
@NamedQuery(name="AssetPriorReg.findAll", query="SELECT a FROM AssetPriorReg a")
public class AssetPriorReg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AS_PRIOR_ID")
	private int asPriorId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="AMOUNT")
	private BigDecimal amount;

	@Column(name="AS_DES")
	private String asDes;

	@Column(name="AS_PRIOR_CODE")
	private String asPriorCode;

	@Column(name="GRN_NO")
	private String grnNo;

	@Column(name="NO_OF_UNITS")
	private BigDecimal noOfUnits;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="USER_ID")
	private int userId;

	@Column(name="VOUCHER_NO")
	private String voucherNo;

	public AssetPriorReg() {
	}

	public int getAsPriorId() {
		return this.asPriorId;
	}

	public void setAsPriorId(int asPriorId) {
		this.asPriorId = asPriorId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAsDes() {
		return this.asDes;
	}

	public void setAsDes(String asDes) {
		this.asDes = asDes;
	}

	public String getAsPriorCode() {
		return this.asPriorCode;
	}

	public void setAsPriorCode(String asPriorCode) {
		this.asPriorCode = asPriorCode;
	}

	public String getGrnNo() {
		return this.grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public BigDecimal getNoOfUnits() {
		return this.noOfUnits;
	}

	public void setNoOfUnits(BigDecimal noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

}
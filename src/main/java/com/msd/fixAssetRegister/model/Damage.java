package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the damage database table.
 * 
 */
@Entity
@Table(name="damage")
@NamedQuery(name="Damage.findAll", query="SELECT d FROM Damage d")
public class Damage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DMG_AS_ID")
	private int dmgAsId;


	@Column(name="TRANSACTION_NO")
	private String transactionNo;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Asset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DMG_ASSET_ID")
	private Asset asset;


	@Column(name="DMG_DES")
	private String dmgDes;

	@Column(name = "DMG_COST")
	private String dmgCost;

	@Column(name = "UPDATEDBY")
	private int updatedby;

	@Column(name="USER_ID")
	private int userId;

	@Column(name="ACTION_TIME")
	private Date actionTime;

	public Damage() {
	}

	public int getDmgAsId() {
		return dmgAsId;
	}

	public void setDmgAsId(int dmgAsId) {
		this.dmgAsId = dmgAsId;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getDmgDes() {
		return dmgDes;
	}

	public void setDmgDes(String dmgDes) {
		this.dmgDes = dmgDes;
	}

	public String getDmgCost() {
		return dmgCost;
	}

	public void setDmgCost(String dmgCost) {
		this.dmgCost = dmgCost;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
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
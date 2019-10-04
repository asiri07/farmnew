package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;



/**
 * The persistent class for the asset_location_details database table.
 *
 */
@Entity
@Table(name="asset_location_details")
@NamedQuery(name="AssetLocationDetail.findAll", query="SELECT a FROM AssetLocationDetail a")
public class AssetLocationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AS_DETAIL_ID")
	private int asDetailId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;


	@Column(name="USER_ID")
	private int userId;

	@Column(name="AS_ID")
	private int asId;


	@Column(name="LOC_ID")
	private int locId;

	public AssetLocationDetail() {
	}

	public int getAsDetailId() {
		return this.asDetailId;
	}

	public void setAsDetailId(int asDetailId) {
		this.asDetailId = asDetailId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
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

	public int getAsId() {
		return asId;
	}

	public void setAsId(int asId) {
		this.asId = asId;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}
}
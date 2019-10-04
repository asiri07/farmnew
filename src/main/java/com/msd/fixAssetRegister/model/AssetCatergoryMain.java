package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the asset_catergory_main database table.
 * 
 */
@Entity
@Table(name="asset_catergory_main")
@NamedQuery(name="AssetCatergoryMain.findAll", query="SELECT a FROM AssetCatergoryMain a")
public class AssetCatergoryMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MCAT_ID")
		private int mcatId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="MCAT_CODE")
	private String mcatCode;

	@Column(name="ASSET_TYPE_ID")
	private int assetTypeId;

	@Column(name="QTY")
	private int qty;

	@Column(name="MCAT_DES")
	private String mcatDes;

	@Column(name="USER_ID")
	private int userId;

	//bi-directional many-to-one association to AssetCatergorySub
	@OneToMany(mappedBy="assetCatergoryMain")
	private List<AssetCatergorySub> assetCatergorySubs;


	public AssetCatergoryMain() {
	}

	public AssetCatergoryMain(String mcatCode) {
		this.mcatCode = mcatCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public int getMcatId() {
		return this.mcatId;
	}

	public void setMcatId(int mcatId) {
		this.mcatId = mcatId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getMcatCode() {
		return this.mcatCode;
	}

	public void setMcatCode(String mcatCode) {
		this.mcatCode = mcatCode;
	}

	public int getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getMcatDes() {
		return this.mcatDes;
	}

	public void setMcatDes(String mcatDes) {
		this.mcatDes = mcatDes;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<AssetCatergorySub> getAssetCatergorySubs() {
		return this.assetCatergorySubs;
	}

	public void setAssetCatergorySubs(List<AssetCatergorySub> assetCatergorySubs) {
		this.assetCatergorySubs = assetCatergorySubs;
	}

	public AssetCatergorySub addAssetCatergorySub(AssetCatergorySub assetCatergorySub) {
		getAssetCatergorySubs().add(assetCatergorySub);
		assetCatergorySub.setAssetCatergoryMain(this);

		return assetCatergorySub;
	}

	public AssetCatergorySub removeAssetCatergorySub(AssetCatergorySub assetCatergorySub) {
		getAssetCatergorySubs().remove(assetCatergorySub);
		assetCatergorySub.setAssetCatergoryMain(null);

		return assetCatergorySub;
	}

}
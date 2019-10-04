package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the asset_catergory_sub database table.
 * 
 */
@Entity
@Table(name="asset_catergory_sub")
@NamedQuery(name="AssetCatergorySub.findAll", query="SELECT a FROM AssetCatergorySub a")
public class AssetCatergorySub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SCAT_ID")
	private int scatId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="SCAT_CODE")
	private String scatCode;

	@Column(name="SCAT_DES")
	private String scatDes;

	@Column(name="USER_ID")
	private int userId;

	//bi-directional many-to-one association to AssetCatergoryDetail
	@OneToMany(mappedBy="assetCatergorySub")
	private List<AssetCatergoryDetail> assetCatergoryDetails;

	//bi-directional many-to-one association to AssetCatergoryMain
	@ManyToOne
	@JoinColumn(name="MCAT_ID")
	private AssetCatergoryMain assetCatergoryMain;

	public AssetCatergorySub() {
	}

	public int getScatId() {
		return this.scatId;
	}

	public void setScatId(int scatId) {
		this.scatId = scatId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getScatCode() {
		return this.scatCode;
	}

	public void setScatCode(String scatCode) {
		this.scatCode = scatCode;
	}

	public String getScatDes() {
		return this.scatDes;
	}

	public void setScatDes(String scatDes) {
		this.scatDes = scatDes;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<AssetCatergoryDetail> getAssetCatergoryDetails() {
		return this.assetCatergoryDetails;
	}

	public void setAssetCatergoryDetails(List<AssetCatergoryDetail> assetCatergoryDetails) {
		this.assetCatergoryDetails = assetCatergoryDetails;
	}

	public AssetCatergoryDetail addAssetCatergoryDetail(AssetCatergoryDetail assetCatergoryDetail) {
		getAssetCatergoryDetails().add(assetCatergoryDetail);
		assetCatergoryDetail.setAssetCatergorySub(this);

		return assetCatergoryDetail;
	}

	public AssetCatergoryDetail removeAssetCatergoryDetail(AssetCatergoryDetail assetCatergoryDetail) {
		getAssetCatergoryDetails().remove(assetCatergoryDetail);
		assetCatergoryDetail.setAssetCatergorySub(null);

		return assetCatergoryDetail;
	}

	public AssetCatergoryMain getAssetCatergoryMain() {
		return this.assetCatergoryMain;
	}

	public void setAssetCatergoryMain(AssetCatergoryMain assetCatergoryMain) {
		this.assetCatergoryMain = assetCatergoryMain;
	}

}
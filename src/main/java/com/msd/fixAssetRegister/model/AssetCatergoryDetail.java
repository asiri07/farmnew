package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the asset_catergory_detail database table.
 * 
 */
@Entity
@Table(name="asset_catergory_detail")
public class AssetCatergoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DCAT_ID")
	private int dcatId;

	@Column(name="DCAT_CODE")
	private String dcatCode;

	@Column(name="DCAT_DES")
	private String dcatDes;

	@Column(name="DEP_RATE_ACCOUNT")
	private Double depRateAccount;

	@Column(name="DEP_RATE_INCOME_TAX")
	private Double depRateIncomeTax;

	@Column(name="USER_ID")
	private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="assetCatergoryDetail")
	private List<Asset> assets;

	//bi-directional many-to-one association to AssetCatergorySub
	@ManyToOne
	@JoinColumn(name="SCAT_ID")
	private AssetCatergorySub assetCatergorySub;

	public AssetCatergoryDetail() {
	}

	public int getDcatId() {
		return this.dcatId;
	}

	public void setDcatId(int dcatId) {
		this.dcatId = dcatId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getDcatCode() {
		return this.dcatCode;
	}

	public void setDcatCode(String dcatCode) {
		this.dcatCode = dcatCode;
	}

	public String getDcatDes() {
		return this.dcatDes;
	}

	public void setDcatDes(String dcatDes) {
		this.dcatDes = dcatDes;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public Asset addAsset(Asset asset) {
		getAssets().add(asset);
		asset.setAssetCatergoryDetail(this);

		return asset;
	}

	public Asset removeAsset(Asset asset) {
		getAssets().remove(asset);
		asset.setAssetCatergoryDetail(null);

		return asset;
	}

	public AssetCatergorySub getAssetCatergorySub() {
		return this.assetCatergorySub;
	}

	public void setAssetCatergorySub(AssetCatergorySub assetCatergorySub) {
		this.assetCatergorySub = assetCatergorySub;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Double getDepRateAccount() {
		return depRateAccount;
	}

	public void setDepRateAccount(Double depRateAccount) {
		this.depRateAccount = depRateAccount;
	}

	public Double getDepRateIncomeTax() {
		return depRateIncomeTax;
	}

	public void setDepRateIncomeTax(Double depRateIncomeTax) {
		this.depRateIncomeTax = depRateIncomeTax;
	}
}
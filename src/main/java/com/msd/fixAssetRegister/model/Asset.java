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
@Table(name="asset")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AS_ID")
	private int asId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="AS_CODE")
	private String asCode;

	@Column(name="AS_CODE_OLD")
	private String asCodeOld;

	@Column(name="AS_DAMAGE")
	private Boolean asDamage = false;

	@Column(name="AS_DES")
	private String asDes;

	@Column(name="AS_DISPOSED")
	private Boolean asDisposed = false;

	@Column(name="AS_ISSUENO")
	private String asIssueno;

	@Column(name="AS_TRANSFER")
	private Boolean asTransfer = false;

	@Column(name="AUTH_PERSON")
	private String authPerson;

	@Column(name="CANCEL")
	private Boolean cancel = false;

	@Column(name="CANCELEDBY")
	private int canceledby;

	@Column(name="CONFIRM")
	private Boolean confirm = false;

	@Column(name="CONFIRMEDBY")
	private int confirmedby;

//	@Column(name="DEP_RATE")
//	private double depRate;

	@Column(name="LEDGER_CODE")
	private String ledgerCode;

	@Column(name="LIFE_TIME")
	private String lifeTime;

	@Column(name="MAN_REG_NO")
	private String manRegNo;

	@Column(name="NO_OF_UNIT")
	private int noOfUnit;

	@Temporal(TemporalType.DATE)
	@Column(name="PUR_DATE")
	private Date purDate;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="MANUFACTURER")
	private String manufacturer;

	@Column(name="ORIGINAL_COST")
	private String originalCost;

	@Column(name="FUNDING_SOURCE")
	private String fundingSource;

    @Column(name="IS_LEASING")
    private int isLeasing;

	@Column(name="UNIT_PRICE")
	private double unitPrice;

	@Column(name="USER_ID")
	private int userId;

	//bi-directional many-to-one association to AssetCatergoryDetail
	@ManyToOne
	@JoinColumn(name="AS_DCAT_ID")
	private AssetCatergoryDetail assetCatergoryDetail;

    @Column(name = "CURRENCY_TYPE")
    private int currencyType;


	@Column(name="ANALYSIS_CODE")
	private String analysisCode;

	//bi-directional many-to-one association to Disposal
	@OneToMany(mappedBy="asset")
	private List<Disposal> disposals;

	//bi-directional many-to-one association to Incometax
	@OneToMany(mappedBy="asset")
	private List<Incometax> incometaxs;

	@ManyToOne
	@JoinColumn(name="LOC_ID")
	private LocationMaster locationMaster;

	//bi-directional many-to-one association to Damage
	@OneToMany(mappedBy="asset")
	private List<Damage> damages;

	//bi-directional many-to-one association to Damage
	@OneToMany(mappedBy="asset")
	private List<AssetImprovement> assetImprovements;

	//bi-directional many-to-one association to Transfer
	@OneToMany(mappedBy="asset")
	private List<Transfer> transfers;

	@Column(name="PO_DATE")
	private String poDate;

	@Column(name="PO_NO")
	private String poNo;

	@Column(name="GRN_DATE")
	private String grnDate;

	@Column(name="GRN_NO")
	private String grnNo;

	@Column(name="INVOICE_DATE")
	private String invoiceDate;

	@Column(name="ISSUE_NOTE_DATE")
	private String issueNoteDate;

	@Column(name="ISSUE_NOTE_NO")
	private String issueNoteNo;

	@Column(name="SUPPLIER_NAME")
	private String supplierName;

	@Column(name="SUPPLIERS_INVOICE_NO")
	private String suppliersInvoiceNo;

	@Column(name="TELEPHONE_NO")
	private String telephoneNo;

	@Column(name="ADDRESS")
	private String address;

    @Column(name = "TRANSACTION_NO")
	private String transactionNo;

	@Column(name="QR_PATH")
	private String qrPath;

	@Column(name="FILE_PATH")
	private String filePath;

	@Transient
	private String currencyCode;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Asset() {
	}

	public String getQrPath() {
		return qrPath;
	}

	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	}

	public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

	public int getAsId() {
		return this.asId;
	}

	public void setAsId(int asId) {
		this.asId = asId;
	}

	public Date getActionTime() {
		return this.actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAsCode() {
		return this.asCode;
	}

	public void setAsCode(String asCode) {
		this.asCode = asCode;
	}

	public String getAsCodeOld() {
		return this.asCodeOld;
	}

	public void setAsCodeOld(String asCodeOld) {
		this.asCodeOld = asCodeOld;
	}

	public Boolean getAsDamage() {
		return this.asDamage;
	}

	public void setAsDamage(Boolean asDamage) {
		this.asDamage = asDamage;
	}

	public String getAsDes() {
		return this.asDes;
	}

	public void setAsDes(String asDes) {
		this.asDes = asDes;
	}

	public Boolean getAsDisposed() {
		return this.asDisposed;
	}

	public void setAsDisposed(Boolean asDisposed) {
		this.asDisposed = asDisposed;
	}

	public String getAsIssueno() {
		return this.asIssueno;
	}

	public void setAsIssueno(String asIssueno) {
		this.asIssueno = asIssueno;
	}

	public Boolean getAsTransfer() {
		return this.asTransfer;
	}

	public void setAsTransfer(Boolean asTransfer) {
		this.asTransfer = asTransfer;
	}

	public String getAuthPerson() {
		return this.authPerson;
	}

	public void setAuthPerson(String authPerson) {
		this.authPerson = authPerson;
	}

	public Boolean getCancel() {
		return this.cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

	public int getCanceledby() {
		return this.canceledby;
	}

	public void setCanceledby(int canceledby) {
		this.canceledby = canceledby;
	}

	public Boolean getConfirm() {
		return this.confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public int getConfirmedby() {
		return this.confirmedby;
	}

	public void setConfirmedby(int confirmedby) {
		this.confirmedby = confirmedby;
	}

//	public double getDepRate() {
//		return this.depRate;
//	}
//
//	public void setDepRate(double depRate) {
//		this.depRate = depRate;
//	}

	public String getLedgerCode() {
		return this.ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getLifeTime() {
		return this.lifeTime;
	}

	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

	public String getManRegNo() {
		return this.manRegNo;
	}

	public void setManRegNo(String manRegNo) {
		this.manRegNo = manRegNo;
	}

	public int getNoOfUnit() {
		return this.noOfUnit;
	}

	public void setNoOfUnit(int noOfUnit) {
		this.noOfUnit = noOfUnit;
	}

	public Date getPurDate() {
		return this.purDate;
	}

	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(String originalCost) {
		this.originalCost = originalCost;
	}

	public String getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(String fundingSource) {
		this.fundingSource = fundingSource;
	}

	public int getIsLeasing() {
		return isLeasing;
	}

	public void setIsLeasing(int isLeasing) {
		this.isLeasing = isLeasing;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public AssetCatergoryDetail getAssetCatergoryDetail() {
		return this.assetCatergoryDetail;
	}

	public void setAssetCatergoryDetail(AssetCatergoryDetail assetCatergoryDetail) {
		this.assetCatergoryDetail = assetCatergoryDetail;
	}

	public List<Disposal> getDisposals() {
		return this.disposals;
	}

	public void setDisposals(List<Disposal> disposals) {
		this.disposals = disposals;
	}

	public Disposal addDisposal(Disposal disposal) {
		getDisposals().add(disposal);
		disposal.setAsset(this);

		return disposal;
	}

	public Disposal removeDisposal(Disposal disposal) {
		getDisposals().remove(disposal);
		disposal.setAsset(null);

		return disposal;
	}

	public List<Incometax> getIncometaxs() {
		return this.incometaxs;
	}

	public void setIncometaxs(List<Incometax> incometaxs) {
		this.incometaxs = incometaxs;
	}

	public Incometax addIncometax(Incometax incometax) {
		getIncometaxs().add(incometax);
		incometax.setAsset(this);

		return incometax;
	}

	public Incometax removeIncometax(Incometax incometax) {
		getIncometaxs().remove(incometax);
		incometax.setAsset(null);

		return incometax;
	}

	public LocationMaster getLocationMaster() {
		return locationMaster;
	}

	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}

	public List<Damage> getDamages() {
		return this.damages;
	}

	public void setDamages(List<Damage> damages) {
		this.damages = damages;
	}

	public Damage addDamage(Damage damage) {
		getDamages().add(damage);
		damage.setAsset(this);
		return damage;
	}

	public Damage removeDamage(Damage damage) {
		getDamages().remove(damage);
		damage.setAsset(null);
		return damage;
	}


	public List<Transfer> getTransfers() {
		return this.transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}

	public Transfer addTransfer(Transfer transfer) {
		getTransfers().add(transfer);
		transfer.setAsset(this);
		return transfer;
	}

	public Transfer removeTransfer(Transfer transfer) {
		getTransfers().remove(transfer);
		transfer.setAsset(null);

		return transfer;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<AssetImprovement> getAssetImprovements() {
		return assetImprovements;
	}

	public void setAssetImprovements(List<AssetImprovement> assetImprovements) {
		this.assetImprovements = assetImprovements;
	}

	public String getAnalysisCode() {
		return analysisCode;
	}

	public void setAnalysisCode(String analysisCode) {
		this.analysisCode = analysisCode;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(String grnDate) {
		this.grnDate = grnDate;
	}

	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getIssueNoteDate() {
		return issueNoteDate;
	}

	public void setIssueNoteDate(String issueNoteDate) {
		this.issueNoteDate = issueNoteDate;
	}

	public String getIssueNoteNo() {
		return issueNoteNo;
	}

	public void setIssueNoteNo(String issueNoteNo) {
		this.issueNoteNo = issueNoteNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSuppliersInvoiceNo() {
		return suppliersInvoiceNo;
	}

	public void setSuppliersInvoiceNo(String suppliersInvoiceNo) {
		this.suppliersInvoiceNo = suppliersInvoiceNo;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
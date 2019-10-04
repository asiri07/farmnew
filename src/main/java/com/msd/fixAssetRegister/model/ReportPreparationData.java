package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report_preparation_data database table.
 * 
 */
@Entity
@Table(name="report_preparation_data")
@NamedQuery(name="ReportPreparationData.findAll", query="SELECT r FROM ReportPreparationData r")
public class ReportPreparationData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="ASSET_CODE")
	private String assetCode;

	@Column(name="MAIN_CAT")
	private String mainCat;

	@Column(name="SUB_CAT")
	private String subCat;

	@Column(name="DETAIL_CAT")
	private String detailCat;

	@Column(name="COMPANY_CODE")
	private String companyCode;

	@Column(name="DEPARTMENT_CODE")
	private String departmentCode;

	@Column(name="SECTION_CODE")
	private String sectionCode;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	@Column(name="ANALYSIS_CODE")
	private String analysisCode;

	@Column(name="AUTH_PERSON")
	private String authPerson;

	@Temporal(TemporalType.DATE)
	@Column(name="DAMAGE_DATE")
	private Date damageDate;

	@Column(name="DEPRECIATION_RATE")
	private double depreciationRate;

	@Temporal(TemporalType.DATE)
	@Column(name="DISPOSAL_DATE")
	private Date disposalDate;

	@Column(name="DISPOSAL_AMOUNT")
	private double disposalAmount;

	@Column(name="DISP_VALUE_DISPOSAL")
	private double dispValueDisposal;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPROVEMENT_DATE")
	private Date improvementDate;

	@Column(name="IS_DAMAGE")
	private Boolean isDamage;

	@Column(name="IS_DISPOSAL")
	private Boolean isDisposal;

	@Column(name="IS_IMPROVEMENT")
	private Boolean isImprovement;

	@Column(name="IS_TRANSFER")
	private Boolean isTransfer;

	@Column(name="LEDGER_CODE")
	private String ledgerCode;

	@Column(name="LIFE_TIME")
	private String lifeTime;

	@Column(name="NO_OF_DAMAGE")
	private int noOfDamage;

	@Column(name="NO_OF_TRANSFERS")
	private int noOfTransfers;

	@Temporal(TemporalType.DATE)
	@Column(name="PURCHASED_DATE")
	private Date purchasedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="REGISTED_DATE")
	private Date registedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="ASSET_VALUE")
	private double assetValue;

	@Column(name = "CURRENCY_TYPE")
	private double currencyType;

	@Column(name="VALUE_AFTER_IMPROVEMENT")
	private double valueAfterImprovement;

	@Column(name="FROM_DATE_VALUE")
	private double fromDateValue;


	@Column(name="FROM_DATE_DISP_VAL")
	private double fromDateDispValue;

	@Column(name = "BF_DISP_VALUE")
	private double bfDispValue;

	@Column(name="TO_DATE_VALUE")
	private double toDateValue;

	@Column(name="TO_DATE_DISP_VAL")
	private double toDateDispValue;

	@Column(name="CURRENT_VALUE")
	private double currentValue;

	@Column(name="FROM_DATE_TAX_VALUE")
	private double fromDateTaxValue;

	@Column(name="FROM_DATE_TAX_DISP_VAL")
	private double fromDateTaxDispValue;

	@Column(name="TO_DATE_TAX_DISP_VAL")
	private double toDateTaxDispValue;

	@Column(name="TO_DATE_TAX_VALUE")
	private double toDateTaxValue;

	@Column(name="TAX_CURRNT_VALUE")
	private double taxCurrntValue;

	@Column(name="TAX_RATE")
	private double taxRate;

	@Temporal(TemporalType.DATE)
	@Column(name="TRANSFER_DATE")
	private Date transferDate;

	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;

	public ReportPreparationData() {
	}

	public double getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(double currencyType) {
		this.currencyType = currencyType;
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

	public String getAnalysisCode() {
		return this.analysisCode;
	}

	public void setAnalysisCode(String analysisCode) {
		this.analysisCode = analysisCode;
	}

	public String getAssetCode() {
		return this.assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAuthPerson() {
		return this.authPerson;
	}

	public void setAuthPerson(String authPerson) {
		this.authPerson = authPerson;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public double getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public Date getDamageDate() {
		return this.damageDate;
	}

	public void setDamageDate(Date damageDate) {
		this.damageDate = damageDate;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public double getDepreciationRate() {
		return this.depreciationRate;
	}

	public void setDepreciationRate(double depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public String getDetailCat() {
		return this.detailCat;
	}

	public void setDetailCat(String detailCat) {
		this.detailCat = detailCat;
	}

	public Date getDisposalDate() {
		return this.disposalDate;
	}

	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getImprovementDate() {
		return this.improvementDate;
	}

	public void setImprovementDate(Date improvementDate) {
		this.improvementDate = improvementDate;
	}

	public Boolean getIsDamage() {
		return this.isDamage;
	}

	public void setIsDamage(Boolean isDamage) {
		this.isDamage = isDamage;
	}

	public Boolean getIsDisposal() {
		return this.isDisposal;
	}

	public void setIsDisposal(Boolean isDisposal) {
		this.isDisposal = isDisposal;
	}

	public Boolean getIsImprovement() {
		return this.isImprovement;
	}

	public void setIsImprovement(Boolean isImprovement) {
		this.isImprovement = isImprovement;
	}

	public Boolean getIsTransfer() {
		return this.isTransfer;
	}

	public void setIsTransfer(Boolean isTransfer) {
		this.isTransfer = isTransfer;
	}

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

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getMainCat() {
		return this.mainCat;
	}

	public void setMainCat(String mainCat) {
		this.mainCat = mainCat;
	}

	public int getNoOfDamage() {
		return this.noOfDamage;
	}

	public void setNoOfDamage(int noOfDamage) {
		this.noOfDamage = noOfDamage;
	}

	public int getNoOfTransfers() {
		return this.noOfTransfers;
	}

	public void setNoOfTransfers(int noOfTransfers) {
		this.noOfTransfers = noOfTransfers;
	}

	public Date getPurchasedDate() {
		return this.purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public Date getRegistedDate() {
		return this.registedDate;
	}

	public void setRegistedDate(Date registedDate) {
		this.registedDate = registedDate;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Boolean getDamage() {
		return isDamage;
	}

	public void setDamage(Boolean damage) {
		isDamage = damage;
	}

	public Boolean getDisposal() {
		return isDisposal;
	}

	public void setDisposal(Boolean disposal) {
		isDisposal = disposal;
	}

	public Boolean getImprovement() {
		return isImprovement;
	}

	public void setImprovement(Boolean improvement) {
		isImprovement = improvement;
	}

	public Boolean getTransfer() {
		return isTransfer;
	}

	public void setTransfer(Boolean transfer) {
		isTransfer = transfer;
	}

	public double getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(double assetValue) {
		this.assetValue = assetValue;
	}

	public double getValueAfterImprovement() {
		return valueAfterImprovement;
	}

	public void setValueAfterImprovement(double valueAfterImprovement) {
		this.valueAfterImprovement = valueAfterImprovement;
	}

	public String getSubCat() {
		return this.subCat;
	}

	public void setSubCat(String subCat) {
		this.subCat = subCat;
	}

	public double getTaxCurrntValue() {
		return this.taxCurrntValue;
	}

	public void setTaxCurrntValue(double taxCurrntValue) {
		this.taxCurrntValue = taxCurrntValue;
	}

	public double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getFromDateValue() {
		return fromDateValue;
	}

	public void setFromDateValue(double fromDateValue) {
		this.fromDateValue = fromDateValue;
	}

	public double getToDateValue() {
		return toDateValue;
	}

	public void setToDateValue(double toDateValue) {
		this.toDateValue = toDateValue;
	}

	public double getFromDateTaxValue() {
		return fromDateTaxValue;
	}

	public void setFromDateTaxValue(double fromDateTaxValue) {
		this.fromDateTaxValue = fromDateTaxValue;
	}

	public double getToDateTaxValue() {
		return toDateTaxValue;
	}

	public void setToDateTaxValue(double toDateTaxValue) {
		this.toDateTaxValue = toDateTaxValue;
	}

	public double getDisposalAmount() {
		return disposalAmount;
	}

	public void setDisposalAmount(double disposalAmount) {
		this.disposalAmount = disposalAmount;
	}

	public double getDispValueDisposal() {
		return dispValueDisposal;
	}

	public void setDispValueDisposal(double dispValueDisposal) {
		this.dispValueDisposal = dispValueDisposal;
	}

	public double getFromDateDispValue() {
		return fromDateDispValue;
	}

	public void setFromDateDispValue(double fromDateDispValue) {
		this.fromDateDispValue = fromDateDispValue;
	}

	public double getToDateDispValue() {
		return toDateDispValue;
	}

	public void setToDateDispValue(double toDateDispValue) {
		this.toDateDispValue = toDateDispValue;
	}

	public double getFromDateTaxDispValue() {
		return fromDateTaxDispValue;
	}

	public void setFromDateTaxDispValue(double fromDateTaxDispValue) {
		this.fromDateTaxDispValue = fromDateTaxDispValue;
	}

	public double getToDateTaxDispValue() {
		return toDateTaxDispValue;
	}

	public void setToDateTaxDispValue(double toDateTaxDispValue) {
		this.toDateTaxDispValue = toDateTaxDispValue;
	}

	public double getBfDispValue() {
		return bfDispValue;
	}

	public void setBfDispValue(double bfDispValue) {
		this.bfDispValue = bfDispValue;
	}
}
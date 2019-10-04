package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the incometax database table.
 * 
 */
@Entity
@Table(name="incometax")
@NamedQuery(name="Incometax.findAll", query="SELECT i FROM Incometax i")
public class Incometax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INC_ID")
	private int incId;

	@Column(name="ACC_FROM_YEAR")
	private Date accFromYear;

	@Column(name="ACC_TO_YEAR")
	private Date accToYear;

	@Column(name="DEP_RATE")
	private Double depRate;

	//bi-directional many-to-one association to Asset
	@ManyToOne
	@JoinColumn(name="AS_ID")
	private Asset asset;

	public Incometax() {
	}

	public int getIncId() {
		return this.incId;
	}

	public void setIncId(int incId) {
		this.incId = incId;
	}

	public Date getAccFromYear() {
		return this.accFromYear;
	}

	public void setAccFromYear(Date accFromYear) {
		this.accFromYear = accFromYear;
	}



	public Double getDepRate() {
		return this.depRate;
	}

	public void setDepRate(Double depRate) {
		this.depRate = depRate;
	}

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Date getAccToYear() {
		return accToYear;
	}

	public void setAccToYear(Date accToYear) {
		this.accToYear = accToYear;
	}
}
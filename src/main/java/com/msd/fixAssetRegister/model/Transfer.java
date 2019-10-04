package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the transfer database table.
 * 
 */
@Entity
@Table(name = "transfer")
@NamedQuery(name="Transfer.findAll", query="SELECT t FROM Transfer t")
public class Transfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TF_ID")
	private int tfId;

	//bi-directional many-to-one association to Asset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TF_ASSET")
	private Asset asset;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TF_DATE")
	private Date tfDate;

	@Column(name = "ISSUED_TO")
	private String issuedTo;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name="TF_FROMCODE")
	private String tfFromcode;

	@Column(name = "FROM_DEP")
	private String fromDep;

	@Column(name = "FROM_SEC")
	private String fromSec;

	@Column(name = "FROM_LOC")
	private String fromLoc;

	@Column(name="TF_REGCODE")
	private String tfRegcode;

	@Column(name="TF_TOCODE")
	private String tfTocode;

	@Column(name = "TO_DEP")
	private String toDep;

	@Column(name = "TO_SEC")
	private String toSec;

	@Column(name = "TO_LOC")
	private String toLoc;

    @Column(name = "EMP_NO_FROM")
    private String empNoFrom;

    @Column(name = "EMP_NO_TO")
    private String empNoTo;

	@Column(name="TF_UPDATEBY")
	private int tfUpdateby;

	@Column(name = "TYPE")
	private int type;

	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTION_TIME")
	private Date actionTime;




	public Transfer() {
	}



	public int getTfId() {
		return this.tfId;
	}

	public void setTfId(int tfId) {
		this.tfId = tfId;
	}

	public Date getTfDate() {
		return this.tfDate;
	}

	public void setTfDate(Date tfDate) {
		this.tfDate = tfDate;
	}



	public String getTfFromcode() {
		return this.tfFromcode;
	}

	public void setTfFromcode(String tfFromcode) {
		this.tfFromcode = tfFromcode;
	}


	public String getTfRegcode() {
		return this.tfRegcode;
	}

	public void setTfRegcode(String tfRegcode) {
		this.tfRegcode = tfRegcode;
	}

	public String getTfTocode() {
		return this.tfTocode;
	}

	public void setTfTocode(String tfTocode) {
		this.tfTocode = tfTocode;
	}

	public int getTfUpdateby() {
		return this.tfUpdateby;
	}

	public void setTfUpdateby(int tfUpdateby) {
		this.tfUpdateby = tfUpdateby;
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

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIssuedTo() {
		return issuedTo;
	}

	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getFromDep() {
		return fromDep;
	}

	public void setFromDep(String fromDep) {
		this.fromDep = fromDep;
	}

	public String getFromSec() {
		return fromSec;
	}

	public void setFromSec(String fromSec) {
		this.fromSec = fromSec;
	}

	public String getFromLoc() {
		return fromLoc;
	}

	public void setFromLoc(String fromLoc) {
		this.fromLoc = fromLoc;
	}

	public String getToDep() {
		return toDep;
	}

	public void setToDep(String toDep) {
		this.toDep = toDep;
	}

	public String getToSec() {
		return toSec;
	}

	public void setToSec(String toSec) {
		this.toSec = toSec;
	}

	public String getToLoc() {
		return toLoc;
	}

	public void setToLoc(String toLoc) {
		this.toLoc = toLoc;
	}

    public String getEmpNoFrom() {
        return empNoFrom;
    }

    public void setEmpNoFrom(String empNoFrom) {
        this.empNoFrom = empNoFrom;
    }

    public String getEmpNoTo() {
        return empNoTo;
    }

    public void setEmpNoTo(String empNoTo) {
        this.empNoTo = empNoTo;
    }
}
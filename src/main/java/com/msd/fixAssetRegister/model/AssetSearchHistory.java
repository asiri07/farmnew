package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name = "asset_search_history")
public class AssetSearchHistory implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@Column(name="DATE")
	private Date date;

	@Column(name="AS_CODE")
	private String asCode;

    @Column(name = "ASATDATE")
    private Date asAtDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date actionTime;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAsCode() {
		return asCode;
	}

	public void setAsCode(String asCode) {
		this.asCode = asCode;
	}

	public Date getAsAtDate() {
		return asAtDate;
	}

	public void setAsAtDate(Date asAtDate) {
		this.asAtDate = asAtDate;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
}
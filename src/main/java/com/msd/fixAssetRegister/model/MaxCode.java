package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name = "maxcode")
public class MaxCode implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int ID;

	@Column(name="TR_TYPE")
	private String cityCode;

	@Column(name="TR_MAX_NO")
	private String description;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name = "asset_type_assing")
public class AssetTypeAssing implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@Column(name="MCAT_ID")
	private String mcatId;

	@Column(name="typeLand")
	private boolean typeLand;

	@Column(name="typeLandBuilding")
	private boolean typeLandBuilding;

	@Column(name="typeVehicle")
	private boolean typeVehicle;

	@Column(name="typeComputer")
	private boolean typeComputer;

	@Column(name="typePlantMachinary")
	private boolean typePlantMachinary;

	@Column(name="typeFurniture")
	private boolean typeFurniture;

	@Column(name="typeOfficeEquipment")
	private boolean typeOfficeEquipment;

	@Column(name="typeLabEquipment")
	private boolean typeLabEquipment;

	@Column(name="typeTeachingEquipment")
	private boolean typeTeachingEquipment;

	@Column(name="typeFixtures")
	private boolean typeFixtures;

	@Column(name="typeLibraryBooks")
	private boolean typeLibraryBooks;

	@Column(name="typeSportEquipment")
	private boolean typeSportEquipment;

	@Column(name="typeSoftware")
	private boolean typeSoftware;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTypeLand() {
		return typeLand;
	}

	public void setTypeLand(boolean typeLand) {
		this.typeLand = typeLand;
	}

	public boolean isTypeLandBuilding() {
		return typeLandBuilding;
	}

	public void setTypeLandBuilding(boolean typeLandBuilding) {
		this.typeLandBuilding = typeLandBuilding;
	}

	public boolean isTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(boolean typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public boolean isTypeComputer() {
		return typeComputer;
	}

	public void setTypeComputer(boolean typeComputer) {
		this.typeComputer = typeComputer;
	}

	public boolean isTypePlantMachinary() {
		return typePlantMachinary;
	}

	public void setTypePlantMachinary(boolean typePlantMachinary) {
		this.typePlantMachinary = typePlantMachinary;
	}

	public boolean isTypeFurniture() {
		return typeFurniture;
	}

	public void setTypeFurniture(boolean typeFurniture) {
		this.typeFurniture = typeFurniture;
	}

	public boolean isTypeOfficeEquipment() {
		return typeOfficeEquipment;
	}

	public void setTypeOfficeEquipment(boolean typeOfficeEquipment) {
		this.typeOfficeEquipment = typeOfficeEquipment;
	}

	public boolean isTypeLabEquipment() {
		return typeLabEquipment;
	}

	public void setTypeLabEquipment(boolean typeLabEquipment) {
		this.typeLabEquipment = typeLabEquipment;
	}

	public boolean isTypeTeachingEquipment() {
		return typeTeachingEquipment;
	}

	public void setTypeTeachingEquipment(boolean typeTeachingEquipment) {
		this.typeTeachingEquipment = typeTeachingEquipment;
	}

	public boolean isTypeFixtures() {
		return typeFixtures;
	}

	public void setTypeFixtures(boolean typeFixtures) {
		this.typeFixtures = typeFixtures;
	}

	public boolean isTypeLibraryBooks() {
		return typeLibraryBooks;
	}

	public void setTypeLibraryBooks(boolean typeLibraryBooks) {
		this.typeLibraryBooks = typeLibraryBooks;
	}

	public boolean isTypeSportEquipment() {
		return typeSportEquipment;
	}

	public void setTypeSportEquipment(boolean typeSportEquipment) {
		this.typeSportEquipment = typeSportEquipment;
	}

	public boolean isTypeSoftware() {
		return typeSoftware;
	}

	public void setTypeSoftware(boolean typeSoftware) {
		this.typeSoftware = typeSoftware;
	}

	public String getMcatId() {
		return mcatId;
	}

	public void setMcatId(String mcatId) {
		this.mcatId = mcatId;
	}
}
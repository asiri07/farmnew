package com.msd.fixAssetRegister.model.form;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class MainCategoryForm implements Serializable {


    private String mcatCode;
    private int mcatId;
    private int id;
    private String mcatDes;
    private int typeid;
    private boolean typeLand;
    private boolean typeLandBuilding;
    private boolean typeVehicle;
    private boolean typeComputer;
    private boolean typePlantMachinary;
    private boolean typeFurniture;
    private boolean typeOfficeEquipment;
    private boolean typeLabEquipment;
    private boolean typeTeachingEquipment;
    private boolean typeFixtures;
    private boolean typeLibraryBooks;
    private boolean typeSportEquipment;
    private boolean typeSoftware;


    public MainCategoryForm() {}


    public String getMcatCode() {
        return mcatCode;
    }

    public void setMcatCode(String mcatCode) {
        this.mcatCode = mcatCode;
    }

    public String getMcatDes() {
        return mcatDes;
    }

    public void setMcatDes(String mcatDes) {
        this.mcatDes = mcatDes;
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

    public int getMcatId() {
        return mcatId;
    }

    public void setMcatId(int mcatId) {
        this.mcatId = mcatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
}

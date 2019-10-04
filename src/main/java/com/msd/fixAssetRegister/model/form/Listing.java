package com.msd.fixAssetRegister.model.form;

public class Listing {

    private int listingId;
    private int listingType;
    private String listingName;
    private String description;

    public Listing() {
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getListingType() {
        return listingType;
    }

    public void setListingType(int listingType) {
        this.listingType = listingType;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

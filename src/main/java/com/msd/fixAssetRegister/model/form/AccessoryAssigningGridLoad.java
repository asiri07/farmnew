package com.msd.fixAssetRegister.model.form;

import java.io.Serializable;
import java.util.Date;

public class AccessoryAssigningGridLoad implements Serializable {

    private String assetCode;
    private String accessoryCode;
    private String accessoryName;
    private int accessoryId;

    public AccessoryAssigningGridLoad() {}

    public int getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(int accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAccessoryCode() {
        return accessoryCode;
    }

    public void setAccessoryCode(String accessoryCode) {
        this.accessoryCode = accessoryCode;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }
}

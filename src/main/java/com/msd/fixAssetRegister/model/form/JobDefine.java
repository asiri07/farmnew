package com.msd.fixAssetRegister.model.form;

import com.msd.fixAssetRegister.model.ASubtab;

import java.util.List;

public class JobDefine {

    private int mainTabId;
    private String mainTabName;
    private String refPage;
    private String mainCode;
    private List<ASubtab> subList;
    private boolean isActive;

    public JobDefine() {
    }

    public int getMainTabId() {
        return mainTabId;
    }

    public void setMainTabId(int mainTabId) {
        this.mainTabId = mainTabId;
    }

    public String getMainTabName() {
        return mainTabName;
    }

    public void setMainTabName(String mainTabName) {
        this.mainTabName = mainTabName;
    }

    public String getRefPage() {
        return refPage;
    }

    public void setRefPage(String refPage) {
        this.refPage = refPage;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public List<ASubtab> getSubList() {
        return subList;
    }

    public void setSubList(List<ASubtab> subList) {
        this.subList = subList;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}


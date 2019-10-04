package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the a_subtab database table.
 */
@Entity
@Table(name = "a_subtab")
@NamedQuery(name = "ASubtab.findAll", query = "SELECT a FROM ASubtab a")
public class ASubtab implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sub_Tab_No;

    private Boolean isActive;

    private int main_Tab_ID;

    private int ordertab;

    private String refPage;

    private String sub_Tab_Code;

    private String sub_Tab_Name;

    @Transient
    private List<ASubtabSub> subtabSubList;

    public ASubtab() {

    }

    public int getSub_Tab_No() {
        return this.sub_Tab_No;
    }

    public void setSub_Tab_No(int sub_Tab_No) {
        this.sub_Tab_No = sub_Tab_No;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public int getMain_Tab_ID() {
        return this.main_Tab_ID;
    }

    public void setMain_Tab_ID(int main_Tab_ID) {
        this.main_Tab_ID = main_Tab_ID;
    }

    public int getOrdertab() {
        return this.ordertab;
    }

    public void setOrdertab(int ordertab) {
        this.ordertab = ordertab;
    }

    public String getRefPage() {
        return this.refPage;
    }

    public void setRefPage(String refPage) {
        this.refPage = refPage;
    }

    public String getSub_Tab_Code() {
        return this.sub_Tab_Code;
    }

    public void setSub_Tab_Code(String sub_Tab_Code) {
        this.sub_Tab_Code = sub_Tab_Code;
    }

    public String getSub_Tab_Name() {
        return this.sub_Tab_Name;
    }

    public void setSub_Tab_Name(String sub_Tab_Name) {
        this.sub_Tab_Name = sub_Tab_Name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ASubtabSub> getSubtabSubList() {
        return subtabSubList;
    }

    public void setSubtabSubList(List<ASubtabSub> subtabSubList) {
        this.subtabSubList = subtabSubList;
    }
}
package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the a_subtab_sub database table.
 */
@Entity
@Table(name = "a_subtab_sub")
public class ASubtabSub implements Serializable {
    private static final long serialVersionUID = 1L;

    private int sub_Tab_ID;

    private Boolean isActive;

    private int main_Tab_ID;

    private int ordertab;

    private String refPage;

    private String sub_Tab_Sub_Code;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sub_Tab_Sub_ID;

    private String sub_Tab_Sub_Name;

    public ASubtabSub() {
    }


    public int getSub_Tab_ID() {
        return this.sub_Tab_ID;
    }

    public void setSub_Tab_ID(int sub_Tab_ID) {
        this.sub_Tab_ID = sub_Tab_ID;
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

    public String getSub_Tab_Sub_Code() {
        return this.sub_Tab_Sub_Code;
    }

    public void setSub_Tab_Sub_Code(String sub_Tab_Sub_Code) {
        this.sub_Tab_Sub_Code = sub_Tab_Sub_Code;
    }

    public int getSub_Tab_Sub_ID() {
        return this.sub_Tab_Sub_ID;
    }

    public void setSub_Tab_Sub_ID(int sub_Tab_Sub_ID) {
        this.sub_Tab_Sub_ID = sub_Tab_Sub_ID;
    }

    public String getSub_Tab_Sub_Name() {
        return this.sub_Tab_Sub_Name;
    }

    public void setSub_Tab_Sub_Name(String sub_Tab_Sub_Name) {
        this.sub_Tab_Sub_Name = sub_Tab_Sub_Name;
    }

}
package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the a_maintab database table.
 */
@Entity
@Table(name = "a_maintab")
@NamedQuery(name = "AMaintab.findAll", query = "SELECT a FROM AMaintab a")
public class AMaintab implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int main_Tab_ID;

    private Boolean isActive;

    private String main_Tab_Code;

    private String main_Tab_Name;

    private int order_ID;

    private String ref_Page;

    public int getMain_Tab_ID() {
        return this.main_Tab_ID;
    }

    public void setMain_Tab_ID(int main_Tab_ID) {
        this.main_Tab_ID = main_Tab_ID;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getMain_Tab_Code() {
        return this.main_Tab_Code;
    }

    public void setMain_Tab_Code(String main_Tab_Code) {
        this.main_Tab_Code = main_Tab_Code;
    }

    public String getMain_Tab_Name() {
        return this.main_Tab_Name;
    }

    public void setMain_Tab_Name(String main_Tab_Name) {
        this.main_Tab_Name = main_Tab_Name;
    }

    public int getOrder_ID() {
        return this.order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public String getRef_Page() {
        return this.ref_Page;
    }

    public void setRef_Page(String ref_Page) {
        this.ref_Page = ref_Page;
    }

}
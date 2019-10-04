package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the a_job_define database table.
 */
@Entity
@Table(name = "a_job_define")
public class AJobDefine implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int job_Id;


    private int main_Tab_Id;


    private int ref_User_Type;


    private int sub_Tab_Id;


    private int sub_Tab_Sub_Id;

    private int rer_Branch_Id;

    public AJobDefine() {
    }

    public int getJob_Id() {
        return this.job_Id;
    }

    public void setJob_Id(int job_Id) {
        this.job_Id = job_Id;
    }


    public int getMain_Tab_Id() {
        return this.main_Tab_Id;
    }

    public void setMain_Tab_Id(int main_Tab_Id) {
        this.main_Tab_Id = main_Tab_Id;
    }

    public int getRef_User_Type() {
        return this.ref_User_Type;
    }

    public void setRef_User_Type(int ref_User_Type) {
        this.ref_User_Type = ref_User_Type;
    }

    public int getSub_Tab_Id() {
        return this.sub_Tab_Id;
    }

    public void setSub_Tab_Id(int sub_Tab_Id) {
        this.sub_Tab_Id = sub_Tab_Id;
    }

    public int getSub_Tab_Sub_Id() {
        return this.sub_Tab_Sub_Id;
    }

    public void setSub_Tab_Sub_Id(int sub_Tab_Sub_Id) {
        this.sub_Tab_Sub_Id = sub_Tab_Sub_Id;
    }


    public int getRer_Branch_Id() {
        return rer_Branch_Id;
    }

    public void setRer_Branch_Id(int rer_Branch_Id) {
        this.rer_Branch_Id = rer_Branch_Id;
    }
}
package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "report_preparation_detail_balance")
public class ReportPreparationDetailBalance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "DETAIL_CODE_ID")
    private int detailCodeId;

    @Column(name = "BALANCE")
    private int balance;


    public ReportPreparationDetailBalance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetailCodeId() {
        return detailCodeId;
    }

    public void setDetailCodeId(int detailCodeId) {
        this.detailCodeId = detailCodeId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

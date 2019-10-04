package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "CURRENCY_FROM")
    private int currencyFrom;

    @Column(name = "CURRENCY_TO")
    private int currencyTo;

    @Column(name = "EXCHANGE_RATE")
    private Double exchangeRate;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

    public CurrencyRate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(int currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public int getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(int currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}

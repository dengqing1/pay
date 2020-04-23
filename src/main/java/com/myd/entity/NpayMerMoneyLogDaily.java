package com.myd.entity;

import java.util.Date;

public class NpayMerMoneyLogDaily {
    private Integer id;

    private String merId;

    private Long inMoney;

    private Long outMoney;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public Long getInMoney() {
        return inMoney;
    }

    public void setInMoney(Long inMoney) {
        this.inMoney = inMoney;
    }

    public Long getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Long outMoney) {
        this.outMoney = outMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
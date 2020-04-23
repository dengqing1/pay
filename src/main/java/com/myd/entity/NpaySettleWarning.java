package com.myd.entity;

import java.util.Date;

public class NpaySettleWarning {
    private String orderid;

    private Date createTime;

    private Integer abBalance;

    private Integer abBalanceAvailable;

    private Integer abFreezeBalance;

    private Integer balance;

    private Integer balanceAvailable;

    private Integer freezeBalance;

    private String message;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAbBalance() {
        return abBalance;
    }

    public void setAbBalance(Integer abBalance) {
        this.abBalance = abBalance;
    }

    public Integer getAbBalanceAvailable() {
        return abBalanceAvailable;
    }

    public void setAbBalanceAvailable(Integer abBalanceAvailable) {
        this.abBalanceAvailable = abBalanceAvailable;
    }

    public Integer getAbFreezeBalance() {
        return abFreezeBalance;
    }

    public void setAbFreezeBalance(Integer abFreezeBalance) {
        this.abFreezeBalance = abFreezeBalance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Integer balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }

    public Integer getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(Integer freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
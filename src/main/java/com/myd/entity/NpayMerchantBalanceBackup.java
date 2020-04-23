package com.myd.entity;

public class NpayMerchantBalanceBackup {
    private String merchantid;

    private String merchantname;

    private Integer status;

    private Integer balance;

    private Integer balanceAvailable;

    private Integer freezeBalance;

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname == null ? null : merchantname.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
package com.myd.entity;

public class NpayMerchantBalance2018 {
    private String merchantid;

    private String merchantname;

    private Integer status;

    private Long balance;

    private Long balanceAvailable;

    private Long freezeBalance;

    private Long blockBalance;

    private Integer lastId;

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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Long balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }

    public Long getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(Long freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public Long getBlockBalance() {
        return blockBalance;
    }

    public void setBlockBalance(Long blockBalance) {
        this.blockBalance = blockBalance;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }
}
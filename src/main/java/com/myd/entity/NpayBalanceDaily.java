package com.myd.entity;

import java.util.Date;

public class NpayBalanceDaily {
    private Date reportDt;

    private Date createTime;

    private String merchantid;

    private String merchantname;

    private Long balance;

    private Long balanceAvailable;

    private Long freezeBalance;

    private Long blockBalance;

    private Integer snapId;

    private Integer maxSid;

    private Integer maxResid;

    private Integer checkStatus;

    private Long settleAmount;

    private Long resettleAmount;

    private String lastOid;

    public Date getReportDt() {
        return reportDt;
    }

    public void setReportDt(Date reportDt) {
        this.reportDt = reportDt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public Integer getSnapId() {
        return snapId;
    }

    public void setSnapId(Integer snapId) {
        this.snapId = snapId;
    }

    public Integer getMaxSid() {
        return maxSid;
    }

    public void setMaxSid(Integer maxSid) {
        this.maxSid = maxSid;
    }

    public Integer getMaxResid() {
        return maxResid;
    }

    public void setMaxResid(Integer maxResid) {
        this.maxResid = maxResid;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Long settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Long getResettleAmount() {
        return resettleAmount;
    }

    public void setResettleAmount(Long resettleAmount) {
        this.resettleAmount = resettleAmount;
    }

    public String getLastOid() {
        return lastOid;
    }

    public void setLastOid(String lastOid) {
        this.lastOid = lastOid == null ? null : lastOid.trim();
    }
}
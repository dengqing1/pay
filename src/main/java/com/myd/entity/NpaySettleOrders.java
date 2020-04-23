package com.myd.entity;

import java.util.Date;

public class NpaySettleOrders {
    private Integer id;

    private String orderid;

    private Integer txnamt;

    private Date createAt;

    private Date settleAt;

    private Integer settleAmount;

    private Integer outFee;

    private String channelMerId;

    private String merId;

    private Integer inFee;

    private Integer status;

    private Date updateAt;

    private Long balance;

    private Long balanceAvailable;

    private Long freezeBalance;

    private String lastOid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(Integer txnamt) {
        this.txnamt = txnamt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getSettleAt() {
        return settleAt;
    }

    public void setSettleAt(Date settleAt) {
        this.settleAt = settleAt;
    }

    public Integer getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Integer settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Integer getOutFee() {
        return outFee;
    }

    public void setOutFee(Integer outFee) {
        this.outFee = outFee;
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public Integer getInFee() {
        return inFee;
    }

    public void setInFee(Integer inFee) {
        this.inFee = inFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
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

    public String getLastOid() {
        return lastOid;
    }

    public void setLastOid(String lastOid) {
        this.lastOid = lastOid == null ? null : lastOid.trim();
    }
}
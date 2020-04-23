package com.myd.entity;

import java.util.Date;

public class NpayReconOrder {
    private String merorderid;

    private String txntype;

    private String txnsubtype;

    private String biztype;

    private String accesstype;

    private String channelMerId;

    private Integer txnamt;

    private String currency;

    private String channelId;

    private Date createAt;

    private String filed1;

    private String filed2;

    private String settleAmount;

    private String settleCurrency;

    private Date settleDate;

    private Date txntime;

    private String paytype;

    private String orderStatus;

    private String feeType;

    private Integer outFee;

    public String getMerorderid() {
        return merorderid;
    }

    public void setMerorderid(String merorderid) {
        this.merorderid = merorderid == null ? null : merorderid.trim();
    }

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype == null ? null : txntype.trim();
    }

    public String getTxnsubtype() {
        return txnsubtype;
    }

    public void setTxnsubtype(String txnsubtype) {
        this.txnsubtype = txnsubtype == null ? null : txnsubtype.trim();
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype == null ? null : biztype.trim();
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype == null ? null : accesstype.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public Integer getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(Integer txnamt) {
        this.txnamt = txnamt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getFiled1() {
        return filed1;
    }

    public void setFiled1(String filed1) {
        this.filed1 = filed1 == null ? null : filed1.trim();
    }

    public String getFiled2() {
        return filed2;
    }

    public void setFiled2(String filed2) {
        this.filed2 = filed2 == null ? null : filed2.trim();
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount == null ? null : settleAmount.trim();
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency == null ? null : settleCurrency.trim();
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Date getTxntime() {
        return txntime;
    }

    public void setTxntime(Date txntime) {
        this.txntime = txntime;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public Integer getOutFee() {
        return outFee;
    }

    public void setOutFee(Integer outFee) {
        this.outFee = outFee;
    }
}
package com.myd.entity;

import java.util.Date;

public class NpayMerMoneyLogs {
    private Integer id;

    private String merId;

    private String orderId;

    private Integer amount;

    private Integer type;

    private Integer createTime;

    private String relatedOrderId;

    private Integer updateTime;

    private Float inFeeAmount;

    private Float outFeeAmount;

    private String outFeeType;

    private String inFeeType;

    private Float inFee;

    private Float outFee;

    private String channelMerId;

    private String channelMerAbbr;

    private String gateway;

    private Integer billingCheckStatus;

    private Date billingCheckTime;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getRelatedOrderId() {
        return relatedOrderId;
    }

    public void setRelatedOrderId(String relatedOrderId) {
        this.relatedOrderId = relatedOrderId == null ? null : relatedOrderId.trim();
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Float getInFeeAmount() {
        return inFeeAmount;
    }

    public void setInFeeAmount(Float inFeeAmount) {
        this.inFeeAmount = inFeeAmount;
    }

    public Float getOutFeeAmount() {
        return outFeeAmount;
    }

    public void setOutFeeAmount(Float outFeeAmount) {
        this.outFeeAmount = outFeeAmount;
    }

    public String getOutFeeType() {
        return outFeeType;
    }

    public void setOutFeeType(String outFeeType) {
        this.outFeeType = outFeeType == null ? null : outFeeType.trim();
    }

    public String getInFeeType() {
        return inFeeType;
    }

    public void setInFeeType(String inFeeType) {
        this.inFeeType = inFeeType == null ? null : inFeeType.trim();
    }

    public Float getInFee() {
        return inFee;
    }

    public void setInFee(Float inFee) {
        this.inFee = inFee;
    }

    public Float getOutFee() {
        return outFee;
    }

    public void setOutFee(Float outFee) {
        this.outFee = outFee;
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getChannelMerAbbr() {
        return channelMerAbbr;
    }

    public void setChannelMerAbbr(String channelMerAbbr) {
        this.channelMerAbbr = channelMerAbbr == null ? null : channelMerAbbr.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public Integer getBillingCheckStatus() {
        return billingCheckStatus;
    }

    public void setBillingCheckStatus(Integer billingCheckStatus) {
        this.billingCheckStatus = billingCheckStatus;
    }

    public Date getBillingCheckTime() {
        return billingCheckTime;
    }

    public void setBillingCheckTime(Date billingCheckTime) {
        this.billingCheckTime = billingCheckTime;
    }
}
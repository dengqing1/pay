package com.myd.entity;

import java.util.Date;

public class NpayTradeDaily {
    private Date dt;

    private String channelMerId;

    private String merchantid;

    private Long inAmount;

    private Long outAmount;

    private Long ttInFee;

    private Long ttOutFee;

    private Long inPnt;

    private Long outPnt;

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public Long getInAmount() {
        return inAmount;
    }

    public void setInAmount(Long inAmount) {
        this.inAmount = inAmount;
    }

    public Long getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(Long outAmount) {
        this.outAmount = outAmount;
    }

    public Long getTtInFee() {
        return ttInFee;
    }

    public void setTtInFee(Long ttInFee) {
        this.ttInFee = ttInFee;
    }

    public Long getTtOutFee() {
        return ttOutFee;
    }

    public void setTtOutFee(Long ttOutFee) {
        this.ttOutFee = ttOutFee;
    }

    public Long getInPnt() {
        return inPnt;
    }

    public void setInPnt(Long inPnt) {
        this.inPnt = inPnt;
    }

    public Long getOutPnt() {
        return outPnt;
    }

    public void setOutPnt(Long outPnt) {
        this.outPnt = outPnt;
    }
}
package com.myd.entity;

import java.util.Date;

public class NpaySubPayment extends NpaySubPaymentKey {
    private String merchantid;

    private String merorderid;

    private Integer txnamt;

    private String fronturl;

    private String backurl;

    private String subject;

    private String body;

    private String merresv1;

    private String userid;

    private String signature;

    private String signmethod;

    private String txntype;

    private String txnsubtype;

    private String biztype;

    private String version;

    private Date createAt;

    private String accesstype;

    private String accessmode;

    private String currency;

    private String paytype;

    private Date updateAt;

    private String txntime;

    private Integer status;

    private String gateway;

    private String customerip;

    private String bankid;

    private Byte dctype;

    private Integer notifytimes;

    private String statusdesc;

    private Byte refundtimes;

    private String refundorderid;

    private String inFeeAmount;

    private String inFee;

    private String inFeeType;

    private String outFeeType;

    private String outFee;

    private String outFeeAmount;

    private String channelMerAbbr;

    private String channelId;

    private String channelMerId;

    private String accno;

    private String customerinfo;

    private String ppflag;

    private Boolean notifysuccess;

    private Integer checkStatus;

    private String cstatus;

    private Date lastUpdate;

    private Byte t1;

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getMerorderid() {
        return merorderid;
    }

    public void setMerorderid(String merorderid) {
        this.merorderid = merorderid == null ? null : merorderid.trim();
    }

    public Integer getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(Integer txnamt) {
        this.txnamt = txnamt;
    }

    public String getFronturl() {
        return fronturl;
    }

    public void setFronturl(String fronturl) {
        this.fronturl = fronturl == null ? null : fronturl.trim();
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl == null ? null : backurl.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getMerresv1() {
        return merresv1;
    }

    public void setMerresv1(String merresv1) {
        this.merresv1 = merresv1 == null ? null : merresv1.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getSignmethod() {
        return signmethod;
    }

    public void setSignmethod(String signmethod) {
        this.signmethod = signmethod == null ? null : signmethod.trim();
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype == null ? null : accesstype.trim();
    }

    public String getAccessmode() {
        return accessmode;
    }

    public void setAccessmode(String accessmode) {
        this.accessmode = accessmode == null ? null : accessmode.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getTxntime() {
        return txntime;
    }

    public void setTxntime(String txntime) {
        this.txntime = txntime == null ? null : txntime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getCustomerip() {
        return customerip;
    }

    public void setCustomerip(String customerip) {
        this.customerip = customerip == null ? null : customerip.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public Byte getDctype() {
        return dctype;
    }

    public void setDctype(Byte dctype) {
        this.dctype = dctype;
    }

    public Integer getNotifytimes() {
        return notifytimes;
    }

    public void setNotifytimes(Integer notifytimes) {
        this.notifytimes = notifytimes;
    }

    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc == null ? null : statusdesc.trim();
    }

    public Byte getRefundtimes() {
        return refundtimes;
    }

    public void setRefundtimes(Byte refundtimes) {
        this.refundtimes = refundtimes;
    }

    public String getRefundorderid() {
        return refundorderid;
    }

    public void setRefundorderid(String refundorderid) {
        this.refundorderid = refundorderid == null ? null : refundorderid.trim();
    }

    public String getInFeeAmount() {
        return inFeeAmount;
    }

    public void setInFeeAmount(String inFeeAmount) {
        this.inFeeAmount = inFeeAmount == null ? null : inFeeAmount.trim();
    }

    public String getInFee() {
        return inFee;
    }

    public void setInFee(String inFee) {
        this.inFee = inFee == null ? null : inFee.trim();
    }

    public String getInFeeType() {
        return inFeeType;
    }

    public void setInFeeType(String inFeeType) {
        this.inFeeType = inFeeType == null ? null : inFeeType.trim();
    }

    public String getOutFeeType() {
        return outFeeType;
    }

    public void setOutFeeType(String outFeeType) {
        this.outFeeType = outFeeType == null ? null : outFeeType.trim();
    }

    public String getOutFee() {
        return outFee;
    }

    public void setOutFee(String outFee) {
        this.outFee = outFee == null ? null : outFee.trim();
    }

    public String getOutFeeAmount() {
        return outFeeAmount;
    }

    public void setOutFeeAmount(String outFeeAmount) {
        this.outFeeAmount = outFeeAmount == null ? null : outFeeAmount.trim();
    }

    public String getChannelMerAbbr() {
        return channelMerAbbr;
    }

    public void setChannelMerAbbr(String channelMerAbbr) {
        this.channelMerAbbr = channelMerAbbr == null ? null : channelMerAbbr.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public String getCustomerinfo() {
        return customerinfo;
    }

    public void setCustomerinfo(String customerinfo) {
        this.customerinfo = customerinfo == null ? null : customerinfo.trim();
    }

    public String getPpflag() {
        return ppflag;
    }

    public void setPpflag(String ppflag) {
        this.ppflag = ppflag == null ? null : ppflag.trim();
    }

    public Boolean getNotifysuccess() {
        return notifysuccess;
    }

    public void setNotifysuccess(Boolean notifysuccess) {
        this.notifysuccess = notifysuccess;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus == null ? null : cstatus.trim();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Byte getT1() {
        return t1;
    }

    public void setT1(Byte t1) {
        this.t1 = t1;
    }
}
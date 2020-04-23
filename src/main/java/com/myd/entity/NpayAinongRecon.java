package com.myd.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NpayAinongRecon {
    private Integer id;

    private String merId;

    private String merName;

    private BigDecimal txnamt;

    private BigDecimal outFee;

    private Date createTime;

    private String orderid;

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

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public BigDecimal getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(BigDecimal txnamt) {
        this.txnamt = txnamt;
    }

    public BigDecimal getOutFee() {
        return outFee;
    }

    public void setOutFee(BigDecimal outFee) {
        this.outFee = outFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }
}
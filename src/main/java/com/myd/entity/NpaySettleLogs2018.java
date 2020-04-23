package com.myd.entity;

import java.util.Date;

public class NpaySettleLogs2018 {
    private Integer id;

    private String orderid;

    private Date createTime;

    private Integer retCode;

    private String message;

    private Integer inStatus;

    private String merchantid;

    private Integer newSettleStatus;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getInStatus() {
        return inStatus;
    }

    public void setInStatus(Integer inStatus) {
        this.inStatus = inStatus;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public Integer getNewSettleStatus() {
        return newSettleStatus;
    }

    public void setNewSettleStatus(Integer newSettleStatus) {
        this.newSettleStatus = newSettleStatus;
    }
}
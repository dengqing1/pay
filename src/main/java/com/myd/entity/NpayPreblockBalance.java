package com.myd.entity;

import java.util.Date;

public class NpayPreblockBalance {
    private String merchantid;

    private Long preBlockBalance;

    private Date updateAt;

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public Long getPreBlockBalance() {
        return preBlockBalance;
    }

    public void setPreBlockBalance(Long preBlockBalance) {
        this.preBlockBalance = preBlockBalance;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
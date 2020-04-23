package com.myd.entity;

import java.util.Date;

public class NpayAdjustLog {
    private String orderid;

    private Integer originStatus;

    private Integer updatedStatus;

    private Date updateAt;

    private Date createAt;

    private String comment;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getOriginStatus() {
        return originStatus;
    }

    public void setOriginStatus(Integer originStatus) {
        this.originStatus = originStatus;
    }

    public Integer getUpdatedStatus() {
        return updatedStatus;
    }

    public void setUpdatedStatus(Integer updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}
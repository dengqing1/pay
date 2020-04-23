package com.myd.entity;

import java.util.Date;

public class NpayOrderCheck {
    private Date reportDt;

    private String merId;

    private Long channelMerId;

    private Integer orderPcnt;

    private Integer orderPamt;

    private Integer orderPfee;

    private Integer nongfuPcnt;

    private Integer nongfuPamt;

    private Integer nongfuPfee;

    private String comment;

    private Integer status;

    private Integer merberId;

    private String memberName;

    private Date commentTime;

    public Date getReportDt() {
        return reportDt;
    }

    public void setReportDt(Date reportDt) {
        this.reportDt = reportDt;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public Long getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(Long channelMerId) {
        this.channelMerId = channelMerId;
    }

    public Integer getOrderPcnt() {
        return orderPcnt;
    }

    public void setOrderPcnt(Integer orderPcnt) {
        this.orderPcnt = orderPcnt;
    }

    public Integer getOrderPamt() {
        return orderPamt;
    }

    public void setOrderPamt(Integer orderPamt) {
        this.orderPamt = orderPamt;
    }

    public Integer getOrderPfee() {
        return orderPfee;
    }

    public void setOrderPfee(Integer orderPfee) {
        this.orderPfee = orderPfee;
    }

    public Integer getNongfuPcnt() {
        return nongfuPcnt;
    }

    public void setNongfuPcnt(Integer nongfuPcnt) {
        this.nongfuPcnt = nongfuPcnt;
    }

    public Integer getNongfuPamt() {
        return nongfuPamt;
    }

    public void setNongfuPamt(Integer nongfuPamt) {
        this.nongfuPamt = nongfuPamt;
    }

    public Integer getNongfuPfee() {
        return nongfuPfee;
    }

    public void setNongfuPfee(Integer nongfuPfee) {
        this.nongfuPfee = nongfuPfee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMerberId() {
        return merberId;
    }

    public void setMerberId(Integer merberId) {
        this.merberId = merberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
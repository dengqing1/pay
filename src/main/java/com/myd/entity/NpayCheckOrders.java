package com.myd.entity;

import java.util.Date;

public class NpayCheckOrders {
    private String orderId;

    private Date orderDt;

    private Date checkDt;

    private Long channelMerId1;

    private Long channelMerId2;

    private Integer orderAmt;

    private Integer channelpayAmt;

    private Integer inFee;

    private Integer outFee;

    private Integer status;

    private String comment;

    private Integer merberId;

    private String memberName;

    private Date commentTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }

    public Date getCheckDt() {
        return checkDt;
    }

    public void setCheckDt(Date checkDt) {
        this.checkDt = checkDt;
    }

    public Long getChannelMerId1() {
        return channelMerId1;
    }

    public void setChannelMerId1(Long channelMerId1) {
        this.channelMerId1 = channelMerId1;
    }

    public Long getChannelMerId2() {
        return channelMerId2;
    }

    public void setChannelMerId2(Long channelMerId2) {
        this.channelMerId2 = channelMerId2;
    }

    public Integer getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Integer orderAmt) {
        this.orderAmt = orderAmt;
    }

    public Integer getChannelpayAmt() {
        return channelpayAmt;
    }

    public void setChannelpayAmt(Integer channelpayAmt) {
        this.channelpayAmt = channelpayAmt;
    }

    public Integer getInFee() {
        return inFee;
    }

    public void setInFee(Integer inFee) {
        this.inFee = inFee;
    }

    public Integer getOutFee() {
        return outFee;
    }

    public void setOutFee(Integer outFee) {
        this.outFee = outFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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
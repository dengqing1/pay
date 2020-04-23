package com.myd.entity;

import java.util.Date;

public class NpayReconComment {
    private Date reportDt;

    private Long channelMerId;

    private String comment;

    private Integer merberId;

    private String memberName;

    private Date commentTime;

    public Date getReportDt() {
        return reportDt;
    }

    public void setReportDt(Date reportDt) {
        this.reportDt = reportDt;
    }

    public Long getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(Long channelMerId) {
        this.channelMerId = channelMerId;
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
package com.myd.entity;

public class NpayChannelDomain {
    private Integer id;

    private String channelMerAbbr;

    private String channelMerId;

    private String channelDomain;

    private String channelMerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelMerAbbr() {
        return channelMerAbbr;
    }

    public void setChannelMerAbbr(String channelMerAbbr) {
        this.channelMerAbbr = channelMerAbbr == null ? null : channelMerAbbr.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getChannelDomain() {
        return channelDomain;
    }

    public void setChannelDomain(String channelDomain) {
        this.channelDomain = channelDomain == null ? null : channelDomain.trim();
    }

    public String getChannelMerName() {
        return channelMerName;
    }

    public void setChannelMerName(String channelMerName) {
        this.channelMerName = channelMerName == null ? null : channelMerName.trim();
    }
}
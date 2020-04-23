package com.myd.entity;

public class NpayMerFeeRates {
    private Integer id;

    private String merId;

    private String gateway;

    private String cardType;

    private String feeType;

    private Integer feeAmount;

    private Integer maxFee;

    private Integer minFee;

    private Integer crateTime;

    private Integer updateTime;
    
    private String channelAbbr;
    
    private String channelMerId;

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

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public Integer getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(Integer maxFee) {
        this.maxFee = maxFee;
    }

    public Integer getMinFee() {
        return minFee;
    }

    public void setMinFee(Integer minFee) {
        this.minFee = minFee;
    }

    public Integer getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Integer crateTime) {
        this.crateTime = crateTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
    
    

	public String getChannelAbbr() {
		return channelAbbr;
	}

	public void setChannelAbbr(String channelAbbr) {
		this.channelAbbr = channelAbbr;
	}

	public String getChannelMerId() {
		return channelMerId;
	}

	public void setChannelMerId(String channelMerId) {
		this.channelMerId = channelMerId;
	}

	@Override
	public String toString() {
		return "NpayMerFeeRates [id=" + id + ", merId=" + merId + ", gateway="
				+ gateway + ", cardType=" + cardType + ", feeType=" + feeType
				+ ", feeAmount=" + feeAmount + ", maxFee=" + maxFee
				+ ", minFee=" + minFee + ", crateTime=" + crateTime
				+ ", updateTime=" + updateTime + ", channelAbbr=" + channelAbbr
				+ ", channelMerId=" + channelMerId + "]";
	}

	
    
    
    
}
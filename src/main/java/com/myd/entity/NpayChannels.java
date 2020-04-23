package com.myd.entity;
/**
 * 渠道信息
 * @author lu xiaoqiang
 *
 * @date 2018年12月19日
 */
public class NpayChannels {
    private String channelId;//渠道号

    private String gateway;//支付网关缩写

    private String name;//渠道名称

    private String channelMerAbbr;//渠道商户名缩写

    private String channelMerId;//渠道商户号

    private String channelSecretKey;//渠道秘钥

    private String feeType;//渠道费率[percent:百分比, fix:固定金额]

    private Integer feeAmount;//扣率(‱) | 金额(元)

    private Integer status;//状态[0:关闭, 1:开启]

    private Integer createTime;//创建时间

    private Integer updateTime;//更新时间

    private String channelChannelId;

    private String proxyurl;//

    private String clearCycle;//清算模式: T1/T0/D0, 默认T1
    
    private String extra;//其他字段

    private Integer isDelete;//是否删除, 0:未删除, 1:删除

    private Integer maxAmount;//最大金额(分)
    
    private String feeRange;//费率区间
    
    private String notifyurl;//回调地址
    
    private Integer minAmount;//最小金额(分)
    
    private Integer accumulative;//累计金额
    
    

    public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getFeeRange() {
		return feeRange;
	}

	public void setFeeRange(String feeRange) {
		this.feeRange = feeRange;
	}

	public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getChannelSecretKey() {
        return channelSecretKey;
    }

    public void setChannelSecretKey(String channelSecretKey) {
        this.channelSecretKey = channelSecretKey == null ? null : channelSecretKey.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getChannelChannelId() {
        return channelChannelId;
    }

    public void setChannelChannelId(String channelChannelId) {
        this.channelChannelId = channelChannelId == null ? null : channelChannelId.trim();
    }

    public String getProxyurl() {
        return proxyurl;
    }

    public void setProxyurl(String proxyurl) {
        this.proxyurl = proxyurl == null ? null : proxyurl.trim();
    }

    public String getClearCycle() {
        return clearCycle;
    }

    public void setClearCycle(String clearCycle) {
        this.clearCycle = clearCycle == null ? null : clearCycle.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    
    

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getAccumulative() {
		return accumulative;
	}

	public void setAccumulative(Integer accumulative) {
		this.accumulative = accumulative;
	}

	@Override
	public String toString() {
		return "NpayChannels [channelId=" + channelId + ", gateway=" + gateway
				+ ", name=" + name + ", channelMerAbbr=" + channelMerAbbr
				+ ", channelMerId=" + channelMerId + ", channelSecretKey="
				+ channelSecretKey + ", feeType=" + feeType + ", feeAmount="
				+ feeAmount + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime
				+ ", channelChannelId=" + channelChannelId + ", proxyurl="
				+ proxyurl + ", clearCycle=" + clearCycle + ", extra=" + extra
				+ ", isDelete=" + isDelete + ", maxAmount=" + maxAmount
				+ ", feeRange=" + feeRange + ", notifyurl=" + notifyurl
				+ ", minAmount=" + minAmount + ", accumulative=" + accumulative
				+ "]";
	}

    
    
    
}
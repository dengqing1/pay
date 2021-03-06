package com.myd.entity;

public class NpayInRoutes {
    private Integer id;

    private String merchantid;

    private String bankid;

    private Integer gt;

    private Integer lt;

    private String channelabbr;

    private String channelmerid;
    
    private Integer accumulative;
    
    private String routesGateway;
    
    private String routesCardType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public Integer getGt() {
        return gt;
    }

    public void setGt(Integer gt) {
        this.gt = gt;
    }

    public Integer getLt() {
        return lt;
    }

    public void setLt(Integer lt) {
        this.lt = lt;
    }

    public String getChannelabbr() {
        return channelabbr;
    }

    public void setChannelabbr(String channelabbr) {
        this.channelabbr = channelabbr == null ? null : channelabbr.trim();
    }

    public String getChannelmerid() {
        return channelmerid;
    }

    public void setChannelmerid(String channelmerid) {
        this.channelmerid = channelmerid == null ? null : channelmerid.trim();
    }
    

	public Integer getAccumulative() {
		return accumulative;
	}

	public void setAccumulative(Integer accumulative) {
		this.accumulative = accumulative;
	}
	

	public String getRoutesGateway() {
		return routesGateway;
	}

	public void setRoutesGateway(String routesGateway) {
		this.routesGateway = routesGateway;
	}
	

	public String getRoutesCardType() {
		return routesCardType;
	}

	public void setRoutesCardType(String routesCardType) {
		this.routesCardType = routesCardType;
	}

	@Override
	public String toString() {
		return "NpayInRoutes [id=" + id + ", merchantid=" + merchantid
				+ ", bankid=" + bankid + ", gt=" + gt + ", lt=" + lt
				+ ", channelabbr=" + channelabbr + ", channelmerid="
				+ channelmerid + ", accumulative=" + accumulative
				+ ", routesGateway=" + routesGateway + ", routesCardType="
				+ routesCardType + "]";
	}

	

	

	

	
    
    
    
}
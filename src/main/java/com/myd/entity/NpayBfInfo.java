package com.myd.entity;

public class NpayBfInfo {
    private Integer id;

    private String merorderid;

    private String fronturl;

    private String backurl;

    private String orderid;

    private String requestid;

    private String mchtid;

    private Integer orderamount;

    private Integer ordercurrency;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerorderid() {
		return merorderid;
	}

	public void setMerorderid(String merorderid) {
		this.merorderid = merorderid;
	}

	public String getFronturl() {
		return fronturl;
	}

	public void setFronturl(String fronturl) {
		this.fronturl = fronturl;
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getMchtid() {
		return mchtid;
	}

	public void setMchtid(String mchtid) {
		this.mchtid = mchtid;
	}

	public Integer getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(Integer orderamount) {
		this.orderamount = orderamount;
	}

	public Integer getOrdercurrency() {
		return ordercurrency;
	}

	public void setOrdercurrency(Integer ordercurrency) {
		this.ordercurrency = ordercurrency;
	}

	@Override
	public String toString() {
		return "NpayBfInfo [" + (id != null ? "id=" + id + ", " : "")
				+ (merorderid != null ? "merorderid=" + merorderid + ", " : "")
				+ (fronturl != null ? "fronturl=" + fronturl + ", " : "")
				+ (backurl != null ? "backurl=" + backurl + ", " : "")
				+ (orderid != null ? "orderid=" + orderid + ", " : "")
				+ (requestid != null ? "requestid=" + requestid + ", " : "")
				+ (mchtid != null ? "mchtid=" + mchtid + ", " : "")
				+ (orderamount != null ? "orderamount=" + orderamount + ", " : "")
				+ (ordercurrency != null ? "ordercurrency=" + ordercurrency : "") + "]";
	}

    
    
    
    
}
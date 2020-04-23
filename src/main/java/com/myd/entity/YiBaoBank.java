package com.myd.entity;

public class YiBaoBank {

	private String p0_Cmd;// 业务类型  Buy
	private String p1_MerId;// 商户编号
	private String p2_Order;// 商户订单号
	private String p3_Amt;// 支付金额  元
	private String p4_Cur;// 交易币种  CNY
	private String p8_Url;// 回调地址 否
	private String hmac;// 签名数据 否
	
	public String getP0_Cmd() {
		return p0_Cmd;
	}
	public void setP0_Cmd(String p0_Cmd) {
		this.p0_Cmd = p0_Cmd;
	}
	public String getP1_MerId() {
		return p1_MerId;
	}
	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}
	public String getP2_Order() {
		return p2_Order;
	}
	public void setP2_Order(String p2_Order) {
		this.p2_Order = p2_Order;
	}
	public String getP3_Amt() {
		return p3_Amt;
	}
	public void setP3_Amt(String p3_Amt) {
		this.p3_Amt = p3_Amt;
	}
	public String getP4_Cur() {
		return p4_Cur;
	}
	public void setP4_Cur(String p4_Cur) {
		this.p4_Cur = p4_Cur;
	}
	public String getP8_Url() {
		return p8_Url;
	}
	public void setP8_Url(String p8_Url) {
		this.p8_Url = p8_Url;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	
	
}

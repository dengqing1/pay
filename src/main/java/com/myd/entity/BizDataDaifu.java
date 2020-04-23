package com.myd.entity;
/**
 * 三方代付的业务数据
 *@author xiaoqiang lu
 *
 *2018/12/28 13:19
 */
public class BizDataDaifu {
	
	private String out_order_no;	  	//商户订单号 	1001201830300230340221
	private Integer amount; 		//交易金额，单位"分" 	10000
	private String notify_url; 	 	 	//服务端回调地址 	http://api.test.net/notify
	private String subject;	 	//订单标题或商品名称 	~
	private String body;	 	//订单或商品的描述 	~
	private Integer pp_type; 	 		//银行业务对公/对私标志 	对公:0，对私:1
	private String bank_code; 	 	 	//银行编码，详见银行编码表 	~
	private String acc_no; 		 	//银行卡账号 	~
	private String acc_name; 	 	 	//银行账户名 	~
	private String opening_name; 	 	 	//开户行 	~
	private String mobile; 	 	 	//银行预留手机号 	~
	private String currency; 	 	 	//货币代码 	目前仅支持人民币 CNY
	private String purpose;	 	 	//用途 	~
	public String getOut_order_no() {
		return out_order_no;
	}
	public void setOut_order_no(String out_order_no) {
		this.out_order_no = out_order_no;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getPp_type() {
		return pp_type;
	}
	public void setPp_type(Integer pp_type) {
		this.pp_type = pp_type;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getOpening_name() {
		return opening_name;
	}
	public void setOpening_name(String opening_name) {
		this.opening_name = opening_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Override
	public String toString() {
		return "BizDataDaifu [out_order_no=" + out_order_no + ", amount=" + amount + ", notify_url=" + notify_url
				+ ", subject=" + subject + ", body=" + body + ", pp_type=" + pp_type + ", bank_code=" + bank_code
				+ ", acc_no=" + acc_no + ", acc_name=" + acc_name + ", opening_name=" + opening_name + ", mobile="
				+ mobile + ", currency=" + currency + ", purpose=" + purpose + "]";
	}

}

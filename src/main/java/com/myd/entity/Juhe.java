package com.myd.entity;



/*
 * 聚合支付参数
 */
public class Juhe {

	private String merId;		//商户号
	private String outTradeNo;		//商户订单号
	private String body;		//商品名称
	private String attach;		//附加信息
	private String notifyUrl;		//异步回调url
	private String callbackUrl;		//前台跳转地址
	private Integer totalFee;		//商户订单金额
	private String payChannel;		//支付渠道
	private String bankNumber;		//银行编码
	private String cardType;		//卡类型
	private String sign;		//签名信息
	private String nonceStr;		//签名信息
	
	private String status;    //  订单状态：needpay- 待支付；paid- 支付成功；failture- 支付失败；overtime - 订单超时；close - 订单已关闭；back - 已退款。
	
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

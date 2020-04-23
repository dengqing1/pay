package com.myd.entity;

public class KLTDuanXin {
	
	private String payerName;//20	M	认证支付付款人姓名
	private String payerTelephone;//16	M	认证支付付款人手机号
	private String payerAcctNo;//20	M	认证支付付款人卡号
	private String payerIdType="01"	;//2	M	证件类型，固定填：01 身份证
	private Long orderAmount;//10	M	金额，金额与币种有关人民币，单位是分，即10元提交时金额应为1000
	private String payerIdNo;//20	M	付款人身份证号
	private String cvv2;//3 O	信用卡安全码, 信用卡必填
	private String acctValidDate;//4	O	信用卡有效期，信用卡必填，格式：yyMM
	private String orderNo;//64O	商户订单号，保证唯一性
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerTelephone() {
		return payerTelephone;
	}
	public void setPayerTelephone(String payerTelephone) {
		this.payerTelephone = payerTelephone;
	}
	public String getPayerAcctNo() {
		return payerAcctNo;
	}
	public void setPayerAcctNo(String payerAcctNo) {
		this.payerAcctNo = payerAcctNo;
	}
	public String getPayerIdType() {
		return payerIdType;
	}
	public void setPayerIdType(String payerIdType) {
		this.payerIdType = payerIdType;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPayerIdNo() {
		return payerIdNo;
	}
	public void setPayerIdNo(String payerIdNo) {
		this.payerIdNo = payerIdNo;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getAcctValidDate() {
		return acctValidDate;
	}
	public void setAcctValidDate(String acctValidDate) {
		this.acctValidDate = acctValidDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "KLTDuanXin [" + (payerName != null ? "payerName=" + payerName + ", " : "")
				+ (payerTelephone != null ? "payerTelephone=" + payerTelephone + ", " : "")
				+ (payerAcctNo != null ? "payerAcctNo=" + payerAcctNo + ", " : "")
				+ (payerIdType != null ? "payerIdType=" + payerIdType + ", " : "")
				+ (orderAmount != null ? "orderAmount=" + orderAmount + ", " : "")
				+ (payerIdNo != null ? "payerIdNo=" + payerIdNo + ", " : "")
				+ (cvv2 != null ? "cvv2=" + cvv2 + ", " : "")
				+ (acctValidDate != null ? "acctValidDate=" + acctValidDate + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo : "") + "]";
	}
	

}

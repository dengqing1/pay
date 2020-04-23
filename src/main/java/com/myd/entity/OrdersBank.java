package com.myd.entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * 下单信息
 *@author xiaoqiang lu
 *
 *2018/12/21 17:49
 */
public class OrdersBank {

	private String signMethod;//签名方法
	private String merchantId;//商户号
	private String merOrderId;//商户订单号
	private String signature ;//签名信息
	private String txnAmt ;//交易金额
	private String frontUrl ;//前台通知地址
	private String backUrl ;//后台通知地址
	private String bankId ;//银行编号
	private Byte dcType ;//借贷类型
	private String subject ;//商品标题
	private String accNo ;//账号
	private String customerNm ;//开户姓名
	private String phoneNo ;//手机号
	private String userId ;//商户用户id
	private String customerIp ;//终端用户IP
	private String body ;//商品描述
	private String gateway ;//网关
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public Byte getDcType() {
		return dcType;
	}
	public void setDcType(Byte dcType) {
		this.dcType = dcType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerIp() {
		return customerIp;
	}
	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	@Override
	public String toString() {
		return "OrdersBank [" + (signMethod != null ? "signMethod=" + signMethod + ", " : "")
				+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
				+ (merOrderId != null ? "merOrderId=" + merOrderId + ", " : "")
				+ (signature != null ? "signature=" + signature + ", " : "")
				+ (txnAmt != null ? "txnAmt=" + txnAmt + ", " : "")
				+ (frontUrl != null ? "frontUrl=" + frontUrl + ", " : "")
				+ (backUrl != null ? "backUrl=" + backUrl + ", " : "")
				+ (bankId != null ? "bankId=" + bankId + ", " : "") + (dcType != null ? "dcType=" + dcType + ", " : "")
				+ (subject != null ? "subject=" + subject + ", " : "") + (accNo != null ? "accNo=" + accNo + ", " : "")
				+ (customerNm != null ? "customerNm=" + customerNm + ", " : "")
				+ (phoneNo != null ? "phoneNo=" + phoneNo + ", " : "")
				+ (userId != null ? "userId=" + userId + ", " : "")
				+ (customerIp != null ? "customerIp=" + customerIp + ", " : "")
				+ (body != null ? "body=" + body + ", " : "") + (gateway != null ? "gateway=" + gateway : "") + "]";
	}
	
	
	

}

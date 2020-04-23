package com.myd.entity;

/**
 * 快捷支付第一次发信息
 *@author xiaoqiang lu
 *
 *2019/01/21 14:58
 */
public class NpayKJ {
	    private String cvv2	;	//	O	信用卡安全码, 信用卡必填
	    private String acctValidDate;//		4	O	信用卡有效期，信用卡必填，格式：yyMM
		private String payerIdNo ; //M付款人身份证号
	 	private String signMethod 	;//M 	签名方法  取值：MD5
	 	private String signature ;//	M 	签名信息  签名信息，详见签名机制
	 	private String merchantId ;//	M 	商户号 由支付平台分配给商户的唯一编号
	 	private String merOrderId;// 	M 	商户订单号 商户订单号，在商户系统中唯一
	 	private String dcType ;//卡的类型0:借记卡1借贷卡
	 	private String txnAmt ;	//M 	交易金额 交易单位为分
	 	private String accNo; //	M 账号 	银行卡卡号
	 	private String customerNm; 	//M 	开户姓名 开户账号名
	 	private String 	phoneNo ;	//M 	手机号 银行开户预留手机号
	 	private String gateway; 	//M 网关	网关类型： bank
	 	private String backUrl;//后台通知地址
	 	
		private String	subject ;	//	商品标题 上送报文时需BASE64编码，参与签名计算时不需要编码
		private String	body ;	//商品描述 上送报文时需BASE64编码，参与签名计算时不需要编码
	 	
		
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
		public String getPayerIdNo() {
			return payerIdNo;
		}
		public void setPayerIdNo(String payerIdNo) {
			this.payerIdNo = payerIdNo;
		}
		public String getSignMethod() {
			return signMethod;
		}
		public void setSignMethod(String signMethod) {
			this.signMethod = signMethod;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
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
		public String getDcType() {
			return dcType;
		}
		public void setDcType(String dcType) {
			this.dcType = dcType;
		}
		public String getTxnAmt() {
			return txnAmt;
		}
		public void setTxnAmt(String txnAmt) {
			this.txnAmt = txnAmt;
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
		public String getGateway() {
			return gateway;
		}
		public void setGateway(String gateway) {
			this.gateway = gateway;
		}
		
		
		public String getBackUrl() {
			return backUrl;
		}
		public void setBackUrl(String backUrl) {
			this.backUrl = backUrl;
		}
		@Override
		public String toString() {
			return "NpayKJ [" + (cvv2 != null ? "cvv2=" + cvv2 + ", " : "")
					+ (acctValidDate != null ? "acctValidDate=" + acctValidDate + ", " : "")
					+ (payerIdNo != null ? "payerIdNo=" + payerIdNo + ", " : "")
					+ (signMethod != null ? "signMethod=" + signMethod + ", " : "")
					+ (signature != null ? "signature=" + signature + ", " : "")
					+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
					+ (merOrderId != null ? "merOrderId=" + merOrderId + ", " : "")
					+ (dcType != null ? "dcType=" + dcType + ", " : "")
					+ (txnAmt != null ? "txnAmt=" + txnAmt + ", " : "") + (accNo != null ? "accNo=" + accNo + ", " : "")
					+ (customerNm != null ? "customerNm=" + customerNm + ", " : "")
					+ (phoneNo != null ? "phoneNo=" + phoneNo + ", " : "")
					+ (gateway != null ? "gateway=" + gateway + ", " : "")
					+ (backUrl != null ? "backUrl=" + backUrl : "") + "]";
		}


		

}

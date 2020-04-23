package com.myd.entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class OrdersDaifu {
	
	private String signMethod ;	// 	签名方法 取值：MD5
	private String signature ;	// 	签名信息 签名信息，详见签名机制
	private String	version ;	// 	消息版本号 取值：1.0.0
	private String	txnType ;	// 	交易类型 取值：12
	private String	txnSubType; 	// 	交易子类型 取值：01
	private String	bizType ;	// 	产品类型 取值：000401
	private String	accessType; 	// 	接入类型 取值：0
	private String	accessMode ;	// 	接入方式 取值：01
	private String	merchantId ;	// 	商户号 由支付平台分配给商户的唯一编号
	private String	merOrderId ;	// 	商户订单号 商户订单号
	private String	accNo ;	// 	账号 银行卡卡号
	private String	customerNm ;// 身份信息	开户账号名
	private String	phoneNo; 	// 	手机号 银行开户预留手机号
	private String	ppFlag ;	//	对公对私标志取值：00：对公 01：对私
	private String	issInsName; 	// 	开户行名 如果ppFlag为00对公的时候必填
	private String	txnTime; 	// 	订单发送时间 YYYYMMDDHHMMSS举例：20171128090356
	private String	txnAmt ;	// 	交易金额 单位为分
	private String	currency; 	// 	交易币种 三位 ISO 货币代码，目前仅支持人民币 CNY
	private String	backUrl ;	//	后台通知地址 商户端接收交易异步通知的地址，在银行返回最终交易结果后支付平台通过该地址进行异步通知
	private String	payType ;	// 	支付方式 取值：0401
	private String	bankId ;	//银行编号 8位银行编码，详见数据字典
	private String	gateway ;	//	网关 daifu
	private String	subject ;	//	商品标题 上送报文时需BASE64编码，参与签名计算时不需要编码
	private String	body ;	//商品描述 上送报文时需BASE64编码，参与签名计算时不需要编码
	private String	purpose ;	// 用途	
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnSubType() {
		return txnSubType;
	}
	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getAccessMode() {
		return accessMode;
	}
	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
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
	public String getPpFlag() {
		return ppFlag;
	}
	public void setPpFlag(String ppFlag) {
		this.ppFlag = ppFlag;
	}
	public String getIssInsName() {
		return issInsName;
	}
	public void setIssInsName(String issInsName) {
		this.issInsName = issInsName;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
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
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Override
	public String toString() {
		return "OrdersDaifu [" + (signMethod != null ? "signMethod=" + signMethod + ", " : "")
				+ (signature != null ? "signature=" + signature + ", " : "")
				+ (version != null ? "version=" + version + ", " : "")
				+ (txnType != null ? "txnType=" + txnType + ", " : "")
				+ (txnSubType != null ? "txnSubType=" + txnSubType + ", " : "")
				+ (bizType != null ? "bizType=" + bizType + ", " : "")
				+ (accessType != null ? "accessType=" + accessType + ", " : "")
				+ (accessMode != null ? "accessMode=" + accessMode + ", " : "")
				+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
				+ (merOrderId != null ? "merOrderId=" + merOrderId + ", " : "")
				+ (accNo != null ? "accNo=" + accNo + ", " : "")
				+ (customerNm != null ? "customerNm=" + customerNm + ", " : "")
				+ (phoneNo != null ? "phoneNo=" + phoneNo + ", " : "")
				+ (ppFlag != null ? "ppFlag=" + ppFlag + ", " : "")
				+ (issInsName != null ? "issInsName=" + issInsName + ", " : "")
				+ (txnTime != null ? "txnTime=" + txnTime + ", " : "")
				+ (txnAmt != null ? "txnAmt=" + txnAmt + ", " : "")
				+ (currency != null ? "currency=" + currency + ", " : "")
				+ (backUrl != null ? "backUrl=" + backUrl + ", " : "")
				+ (payType != null ? "payType=" + payType + ", " : "")
				+ (bankId != null ? "bankId=" + bankId + ", " : "")
				+ (gateway != null ? "gateway=" + gateway + ", " : "")
				+ (subject != null ? "subject=" + subject + ", " : "") + (body != null ? "body=" + body + ", " : "")
				+ (purpose != null ? "purpose=" + purpose : "") + "]";
	}
	

}

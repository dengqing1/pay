package com.myd.entity;

public class DaifuAnsyDaifuReturn {
	
	private String merchantId;	//商户号	与提交订单时的商户号一致
	private String merchantOrderId;	//商户订单号	字符串，商户订单号
	private String accountName;	//收款方名称字符串，收款方名称
	private String accountNo;	//收款方账号	字符串，收款方账号
	private String accountType; //账户类型字符串，账户类型
	private Integer orderStatus;	//订单状态	0：处理中，1：成功，2：失败(此字段用于判断业务执行状态)
	private Integer amount;	//代付金额	整型数字，单位是分，即10元提交时金额应为1000
	private String bankName;	//收款方开户名	字符串，收款方开户名
	private String bankNo;	//收款方开户行	字符串，收款方开户行
	private String notifyUrl;	//回调通知地址	字符串，回调通知地址
	private String remark;		//字符串，备注
	private Integer retryTimes;	//重复次数	整型数字
	private String errorCode;	//交易错误码	交易错误码
	private String errorMsg;	//交易错误信息	交易错误信息
	private String sign;	//签名字符串	所有非空参数按下方述顺序与密钥组合，经加密后生成该值。
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRetryTimes() {
		return retryTimes;
	}
	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "DaifuAnsyDaifuReturn [" + (merchantId != null ? "merchantId=" + merchantId + ", " : "")
				+ (merchantOrderId != null ? "merchantOrderId=" + merchantOrderId + ", " : "")
				+ (accountName != null ? "accountName=" + accountName + ", " : "")
				+ (accountNo != null ? "accountNo=" + accountNo + ", " : "")
				+ (accountType != null ? "accountType=" + accountType + ", " : "")
				+ (orderStatus != null ? "orderStatus=" + orderStatus + ", " : "")
				+ (amount != null ? "amount=" + amount + ", " : "")
				+ (bankName != null ? "bankName=" + bankName + ", " : "")
				+ (bankNo != null ? "bankNo=" + bankNo + ", " : "")
				+ (notifyUrl != null ? "notifyUrl=" + notifyUrl + ", " : "")
				+ (remark != null ? "remark=" + remark + ", " : "")
				+ (retryTimes != null ? "retryTimes=" + retryTimes + ", " : "")
				+ (errorCode != null ? "errorCode=" + errorCode + ", " : "")
				+ (errorMsg != null ? "errorMsg=" + errorMsg + ", " : "") + (sign != null ? "sign=" + sign : "") + "]";
	}
	
	

}

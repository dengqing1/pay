package com.myd.entity;
/**
 * 开联通代付内容
 *@author xiaoqiang lu
 *
 *2019/01/22 15:45
 */
public class KLTDaifuContent {
	
	private String mchtOrderNo;//		50	M	商户订单号，单笔实时代付中不能重复
	private String orderDateTime;//		14	M	商户订单时间，yyyyMMddHHmmss
	private String accountNo;//		30	M	收款方账号
	private String accountName;//		200	M	收款方姓名
	private String accountType;//		15	M	收款方账户类型，固定值，可取值：	1代表个人账户	2代表企业账户
	private String bankNo;//		30	M	收款方开户行行号（电子联行号）对公需要校验，对私填写000000000000（12个0）
	private String bankName;//		200	M	收款方开户行名称（企业账户精确到支行，比如中国工商银行上海市浦东大道支行）个人账户只写银行名
	private Long amt;//		10	M	金额，正整数，单位为分。例如，票款为1280元，则表示为“128000”
	private String purpose;//		50	M	用途
	private String remark;//		200	O	备注
	private String notifyUrl;//		200	M	用于接收开联通的交易结果通知
	
	public String getMchtOrderNo() {
		return mchtOrderNo;
	}
	public void setMchtOrderNo(String mchtOrderNo) {
		this.mchtOrderNo = mchtOrderNo;
	}
	public String getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Long getAmt() {
		return amt;
	}
	public void setAmt(Long amt) {
		this.amt = amt;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark == null||"".equals(remark)?null:remark.trim();
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	@Override
	public String toString() {
		return "KLTDaifuContent [" + (mchtOrderNo != null ? "mchtOrderNo=" + mchtOrderNo + ", " : "")
				+ (orderDateTime != null ? "orderDateTime=" + orderDateTime + ", " : "")
				+ (accountNo != null ? "accountNo=" + accountNo + ", " : "")
				+ (accountName != null ? "accountName=" + accountName + ", " : "")
				+ (accountType != null ? "accountType=" + accountType + ", " : "")
				+ (bankNo != null ? "bankNo=" + bankNo + ", " : "")
				+ (bankName != null ? "bankName=" + bankName + ", " : "") + (amt != null ? "amt=" + amt + ", " : "")
				+ (purpose != null ? "purpose=" + purpose + ", " : "")
				+ (remark != null ? "remark=" + remark + ", " : "")
				+ (notifyUrl != null ? "notifyUrl=" + notifyUrl : "") + "]";
	}

	
	
	
}


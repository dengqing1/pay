package com.myd.entity;

import java.io.Serializable;

/**
 * 聚合代付   DetailData  参数   
 * @author admin
 *
 */
public class JuheDaifuDetailData {

	private String outOrderNo;//	订单号
	private String orderName;//	订单名称
	private String receiverCardNo;//	卡号
	private String receiverName;//	户名
	private String amount	;//金额
	private String receiverType;//	账户类型      个人：PERSON	企业：CORP
	private String receiverCurrency	;//币种
	private String bankName	;//开户行名称
	private String bankCode;//	开户行编码
//	private String remark;//	备注
	
	
	
	
	
	
	
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getReceiverCardNo() {
		return receiverCardNo;
	}
	public void setReceiverCardNo(String receiverCardNo) {
		this.receiverCardNo = receiverCardNo;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public String getReceiverCurrency() {
		return receiverCurrency;
	}
	public void setReceiverCurrency(String receiverCurrency) {
		this.receiverCurrency = receiverCurrency;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public String toString() {
		return "JuheDaifuDetailData [outOrderNo=" + outOrderNo + ", orderName=" + orderName + ", receiverCardNo="
				+ receiverCardNo + ", receiverName=" + receiverName + ", amount=" + amount + ", receiverType="
				+ receiverType + ", receiverCurrency=" + receiverCurrency + ", bankName=" + bankName + ", bankCode="
				+ bankCode +   "]";
	}
	
	
	
	
	
	
	
}

package com.myd.dto;


/**
 * Created by you on 2018/11/21.
 */
public class BalanceResponse extends BodyResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6076631956415437367L;
	
	private String balance; //分为单位
	
	private String merchantId; //商户平台分配的商户号
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	
}

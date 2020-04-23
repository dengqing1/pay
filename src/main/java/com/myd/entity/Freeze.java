package com.myd.entity;

public class Freeze {
	
	private Integer freezeId;
	private String freezeMerchantId;
	private String freezemerchantName;
	private String freezeBalance;
	private String freezeBlockBalance;
	private String freezeAmount;
	private String freezeNote;
	private String freezeTime;
	private String freezeState;
	public Integer getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(Integer freezeId) {
		this.freezeId = freezeId;
	}
	public String getFreezeMerchantId() {
		return freezeMerchantId;
	}
	public void setFreezeMerchantId(String freezeMerchantId) {
		this.freezeMerchantId = freezeMerchantId;
	}
	public String getFreezemerchantName() {
		return freezemerchantName;
	}
	public void setFreezemerchantName(String freezemerchantName) {
		this.freezemerchantName = freezemerchantName;
	}
	public String getFreezeBalance() {
		return freezeBalance;
	}
	public void setFreezeBalance(String freezeBalance) {
		this.freezeBalance = freezeBalance;
	}
	public String getFreezeBlockBalance() {
		return freezeBlockBalance;
	}
	public void setFreezeBlockBalance(String freezeBlockBalance) {
		this.freezeBlockBalance = freezeBlockBalance;
	}
	public String getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public String getFreezeNote() {
		return freezeNote;
	}
	public void setFreezeNote(String freezeNote) {
		this.freezeNote = freezeNote;
	}
	
	public String getFreezeTime() {
		return freezeTime;
	}
	public void setFreezeTime(String freezeTime) {
		this.freezeTime = freezeTime;
	}
	public String getFreezeState() {
		return freezeState;
	}
	public void setFreezeState(String freezeState) {
		this.freezeState = freezeState;
	}
	@Override
	public String toString() {
		return "Freeze [freezeId=" + freezeId + ", freezeMerchantId="
				+ freezeMerchantId + ", freezemerchantName="
				+ freezemerchantName + ", freezeBalance=" + freezeBalance
				+ ", freezeBlockBalance=" + freezeBlockBalance
				+ ", freezeAmount=" + freezeAmount + ", freezeNote="
				+ freezeNote + ", freezeTime=" + freezeTime + ", freezeState="
				+ freezeState + "]";
	}
	
	
	
	

}

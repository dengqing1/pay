package com.myd.entity;

public class Gather {
	
	private Integer gatherId;
	private String gatherMerchantId;
	private String gatherMerOrderId;
	private String gatherTxnAmt;
	private String gatherAccno;
	private String gatherPpflag;
	private String gatherCustomerName;
	private String gatherBankId;
	private String bankName;
	private String gatherTime;
	private String gatherUpdateTime;
	private String gatherState;
	private String txnAmts;
	private String gatherKey;
	private String gatherPhoneNo;
	
	
	public String getGatherPhoneNo() {
		return gatherPhoneNo;
	}
	public void setGatherPhoneNo(String gatherPhoneNo) {
		this.gatherPhoneNo = gatherPhoneNo;
	}
	public String getGatherKey() {
		return gatherKey;
	}
	public void setGatherKey(String gatherKey) {
		this.gatherKey = gatherKey;
	}
	public String getTxnAmts() {
		return txnAmts;
	}
	public void setTxnAmts(String txnAmts) {
		this.txnAmts = txnAmts;
	}
	public Integer getGatherId() {
		return gatherId;
	}
	public void setGatherId(Integer gatherId) {
		this.gatherId = gatherId;
	}
	public String getGatherMerchantId() {
		return gatherMerchantId;
	}
	public void setGatherMerchantId(String gatherMerchantId) {
		this.gatherMerchantId = gatherMerchantId;
	}
	public String getGatherMerOrderId() {
		return gatherMerOrderId;
	}
	public void setGatherMerOrderId(String gatherMerOrderId) {
		this.gatherMerOrderId = gatherMerOrderId;
	}
	public String getGatherTxnAmt() {
		return gatherTxnAmt;
	}
	public void setGatherTxnAmt(String gatherTxnAmt) {
		this.gatherTxnAmt = gatherTxnAmt;
	}
	public String getGatherAccno() {
		return gatherAccno;
	}
	public void setGatherAccno(String gatherAccno) {
		this.gatherAccno = gatherAccno;
	}
	public String getGatherPpflag() {
		return gatherPpflag;
	}
	public void setGatherPpflag(String gatherPpflag) {
		this.gatherPpflag = gatherPpflag;
	}
	public String getGatherCustomerName() {
		return gatherCustomerName;
	}
	public void setGatherCustomerName(String gatherCustomerName) {
		this.gatherCustomerName = gatherCustomerName;
	}
	public String getGatherBankId() {
		return gatherBankId;
	}
	public void setGatherBankId(String gatherBankId) {
		this.gatherBankId = gatherBankId;
	}
	public String getGatherTime() {
		return gatherTime;
	}
	public String getGatherUpdateTime() {
		return gatherUpdateTime;
	}
	public void setGatherUpdateTime(String gatherUpdateTime) {
		this.gatherUpdateTime = gatherUpdateTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setGatherTime(String gatherTime) {
		this.gatherTime = gatherTime;
	}
	
	public String getGatherState() {
		return gatherState;
	}
	public void setGatherState(String gatherState) {
		this.gatherState = gatherState;
	}
	@Override
	public String toString() {
		return "Gather [gatherId=" + gatherId + ", gatherMerchantId="
				+ gatherMerchantId + ", gatherMerOrderId=" + gatherMerOrderId
				+ ", gatherTxnAmt=" + gatherTxnAmt + ", gatherAccno="
				+ gatherAccno + ", gatherPpflag=" + gatherPpflag
				+ ", gatherCustomerName=" + gatherCustomerName
				+ ", gatherBankId=" + gatherBankId + ", bankName=" + bankName
				+ ", gatherTime=" + gatherTime + ", gatherState=" + gatherState
				+ "]";
	}
	
	
	

}

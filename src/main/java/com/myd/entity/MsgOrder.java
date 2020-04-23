package com.myd.entity;


//订单查询    响应数据
public class MsgOrder {

	private String success ;
	private String code;
	private String timestamp;
	private String msg;
	private String signature;
	private String merchantId;
	private String merOrderId;
	private String txnAmt;
	private String status;
	private String statusDesc;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	@Override
	public String toString() {
		return "MsgOrder [success=" + success + ", code=" + code + ", timestamp=" + timestamp + ", msg=" + msg
				+ ", signature=" + signature + ", merchantId=" + merchantId + ", merOrderId=" + merOrderId + ", txnAmt="
				+ txnAmt + ", status=" + status + ", statusDesc=" + statusDesc + "]";
	}
	
	
	
	
	
	
	
	
	
	
}

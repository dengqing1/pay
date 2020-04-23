package com.myd.dto;


/**
 * Created by you on 2018/11/21.
 */
public class StatusResponse extends BodyResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5185953398262811348L;
	
	private String merchantId; //商户订单号
	
	private String merOrderId; //商户订单号
	
	private String txnAmt;	//订单金额
	
	private String status;	//订单状态码
	
	private String statusDesc; //订单状态描述
	
	private String signature; //签名信息
	

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
	
	
	
}

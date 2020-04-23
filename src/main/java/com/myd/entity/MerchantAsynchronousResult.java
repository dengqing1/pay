package com.myd.entity;
/**
 * 异步向商户通知结果（网银）
 *@author xiaoqiang lu
 *
 *2019/01/09 13:58
 */
public class MerchantAsynchronousResult {
	 	private String signature; 	//签名信息 	必须验证签名正确性
	 	private String merchantId; 	//商户号	由支付平台分配给商户的唯一编号
	 	private String merOrderId; 	// 	商户订单号，在商户系统中唯一
	 	private String txnAmt 	;     //交易金额单位分
	 	private String respCode ;	//应答码订单状态码，参见状态码表
	 	private String respMsg ;	//应答信息订单状态描述
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
		public String getRespCode() {
			return respCode;
		}
		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}
		public String getRespMsg() {
			return respMsg;
		}
		public void setRespMsg(String respMsg) {
			this.respMsg = respMsg;
		}
		@Override
		public String toString() {
			return "MerchantBankResult [" + (signature != null ? "signature=" + signature + ", " : "")
					+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
					+ (merOrderId != null ? "merOrderId=" + merOrderId + ", " : "")
					+ (txnAmt != null ? "txnAmt=" + txnAmt + ", " : "")
					+ (respCode != null ? "respCode=" + respCode + ", " : "")
					+ (respMsg != null ? "respMsg=" + respMsg : "") + "]";
		}
	 	
	 	

}

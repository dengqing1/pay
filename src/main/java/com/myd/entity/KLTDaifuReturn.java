package com.myd.entity;

public class KLTDaifuReturn {
	private String mchtId;		//商户号
	private String signType;		//签名类型
	private String signMsg;		//签名
	private String requestId;		//请求流水
	private String orderState;		//交易状态，SUCCESS：成功，IN_PROCESS：处理中，FAIL：失败	只有在开联通系统创建了订单后该属性才有值。(此字段用于判断业务执行状态)
	private String responseCode;		//响应码，000000表示接口响应正常，其它表示失败
	private String responseMsg;		//响应信息
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	@Override
	public String toString() {
		return "KLTDaifuReturn [" + (mchtId != null ? "mchtId=" + mchtId + ", " : "")
				+ (signType != null ? "signType=" + signType + ", " : "")
				+ (signMsg != null ? "signMsg=" + signMsg + ", " : "")
				+ (requestId != null ? "requestId=" + requestId + ", " : "")
				+ (orderState != null ? "orderState=" + orderState + ", " : "")
				+ (responseCode != null ? "responseCode=" + responseCode + ", " : "")
				+ (responseMsg != null ? "responseMsg=" + responseMsg : "") + "]";
	}

	
	
	
}

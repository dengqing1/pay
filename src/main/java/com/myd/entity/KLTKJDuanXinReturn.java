package com.myd.entity;
/**
 * 快捷支付的发短信返回的数据
 *@author xiaoqiang lu
 *
 *2019/01/18 13:16
 */
public class KLTKJDuanXinReturn {
	
	private String signType;		//签名类型
	private String responseCode;		//响应码，000000表示接口响应正常，其它表示失败
	private String responseMsg;		//返回消息
	private String mchtId;		//商户号
	private String requestId;		//请求流水号，用于支付时使用
	private String orderNo;		//商户订单号，支付时使用
	private String signMsg;		//签名内容
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
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
	public String getMchtId() {
		return mchtId;
	}
	public void setMchtId(String mchtId) {
		this.mchtId = mchtId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	@Override
	public String toString() {
		return "KLTKJDuanXinReturn [" + (signType != null ? "signType=" + signType + ", " : "")
				+ (responseCode != null ? "responseCode=" + responseCode + ", " : "")
				+ (responseMsg != null ? "responseMsg=" + responseMsg + ", " : "")
				+ (mchtId != null ? "mchtId=" + mchtId + ", " : "")
				+ (requestId != null ? "requestId=" + requestId + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo + ", " : "") + (signMsg != null ? "signMsg=" + signMsg : "")
				+ "]";
	}
	

	
}

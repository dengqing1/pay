package com.myd.entity;
/**
 * 快捷支付确认支付返回的信息
 *@author xiaoqiang lu
 *
 *2019/01/21 14:10
 */
public class KLTKJConfirmReturn {
	private String signType;//签名类型，固定选择值：1，与提交订单时的签名类型保持一致
	private String responseCode;//返回码，000000：接口响应正常，（此接口为异步，支付结果等回调通知或者 查询接口）其它表示发送失败。
	private String responseMsg;	//返回信息
	private String mchtId;//商户在开联申请开户的商户号
	private String orderNo;//商户订单号，只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
	private Long orderAmount;//商户订单金额，整型数字，单位是分，即10元提交时金额应为1000
	private Integer orderCurrency;//订单金额币种类型，156代表人民币
	private String orderDatetime;//商户订单提交时间，日期格式：yyyyMMddHHmmss，例如：20121116020101
	private String orderState;		//交易状态，SUCCESS：成功，ACCEPTED：已受理，PROCESS ：处理中，FAILURE：失败，NOTRANSACTION：无此交易，UNKNOWN：未知	只有在开联通系统创建了订单后该属性才有值。(此字段用于判断业务执行状态)
	private String requestId;		//请求流水号，保证唯一性
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(Integer orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	@Override
	public String toString() {
		return "KLTKJReturn [" + (signType != null ? "signType=" + signType + ", " : "")
				+ (responseCode != null ? "responseCode=" + responseCode + ", " : "")
				+ (responseMsg != null ? "responseMsg=" + responseMsg + ", " : "")
				+ (mchtId != null ? "mchtId=" + mchtId + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (orderAmount != null ? "orderAmount=" + orderAmount + ", " : "")
				+ (orderCurrency != null ? "orderCurrency=" + orderCurrency + ", " : "")
				+ (orderDatetime != null ? "orderDatetime=" + orderDatetime + ", " : "")
				+ (orderState != null ? "orderState=" + orderState + ", " : "")
				+ (requestId != null ? "requestId=" + requestId + ", " : "")
				+ (signMsg != null ? "signMsg=" + signMsg : "") + "]";
	}
	
	

}

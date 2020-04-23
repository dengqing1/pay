package com.myd.entity;
/**
 * 聚合   异步向商户通知结果（网银）
 * @author admin
 *
 */
public class JuheResult {

	
	private String merId;		//商户号
	private String orderNo;      //平台订单号
	private String outTradeNo;		//商户订单号
	private String  status;    //订单状态：needpay- 待支付；paid- 支付成功；failture- 支付失败；overtime - 订单超时；close - 订单已关闭；back - 已退款。
	private String attach;		//用户自定义透传数据，支付通知中原样返回
	private String totalFee;		//预下单金额，单位：分
	private String body;		//商品名称
	private String payType;		//H5(WEB)，PC(pc端)
	private String payChannel;		//支付渠道 : ALIPAY（支付宝），WXPAY（微信）
	private String nonceStr;		//随机字符串，不长于 32 位
	private String sign;		//MD5签名结果，详见“安全规范”
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	@Override
	public String toString() {
		return "JuheResult [merId=" + merId + ", orderNo=" + orderNo + ", outTradeNo=" + outTradeNo + ", status="
				+ status + ", attach=" + attach + ", totalFee=" + totalFee + ", body=" + body + ", payType=" + payType
				+ ", payChannel=" + payChannel + ", nonceStr=" + nonceStr + ", sign=" + sign + "]";
	}
	
	
	
	
	
	
	
	
	
}

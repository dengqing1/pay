package com.myd.entity;
/**
 * 商户快捷支付发送短信成功后确认下单
 *@author xiaoqiang lu
 *
 *2019/01/21 19:05
 */
public class NpayKJConfirm {
	 	private String signMethod 	;//M 	签名方法  取值：MD5
	 	private String signature ;//	M 	签名信息  签名信息，详见签名机制
	 	private String merchantId ;//	M 	商户号 由支付平台分配给商户的唯一编号
	 	private String merOrderId;// 	M 	原商户订单号 商户订单号，在商户系统中唯一
	 	private String smsCode;//		10	M	短信验证码
		public String getSignMethod() {
			return signMethod;
		}
		public void setSignMethod(String signMethod) {
			this.signMethod = signMethod;
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
		public String getSmsCode() {
			return smsCode;
		}
		public void setSmsCode(String smsCode) {
			this.smsCode = smsCode;
		}
		@Override
		public String toString() {
			return "NpayKJConfirm [" + (signMethod != null ? "signMethod=" + signMethod + ", " : "")
					+ (signature != null ? "signature=" + signature + ", " : "")
					+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
					+ (merOrderId != null ? "merOrderId=" + merOrderId + ", " : "")
					+ (smsCode != null ? "smsCode=" + smsCode : "") + "]";
		}
	 	
	 	
	

}

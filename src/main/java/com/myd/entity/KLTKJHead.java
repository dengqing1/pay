package com.myd.entity;

public class KLTKJHead {
		//报文头（head）
		private String version="18";//10O	版本号，固定值18
		private String merchantId;//15	M	商户号
		private String signType="1";//1 M	签名类型，0：数字证书 1：md5
		private String sign;//M	签名信息
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		public String getSignType() {
			return signType;
		}
		public void setSignType(String signType) {
			this.signType = signType;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		@Override
		public String toString() {
			return "KLTKJHead [" + (version != null ? "version=" + version + ", " : "")
					+ (merchantId != null ? "merchantId=" + merchantId + ", " : "")
					+ (signType != null ? "signType=" + signType + ", " : "") + (sign != null ? "sign=" + sign : "")
					+ "]";
		}
		

}

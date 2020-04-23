package com.myd.entity;
/**
 * 三方的请求参数
 *@author xiaoqiang lu
 *
 *2018/12/26 18:48
 */
public class ReqPars {
	private String version;			//接口版本号	固定值：1.1
	private String merch_id;			//平台分配给商户的商户号	例：PAY10100090000033
	private String biz_code	;		//业务编码	见 biz_code 业务编码表
	private String sign	;		//请求参数的签名(Base64)	签名算法：RSA: SHA1withRSA
	private String timestamp;			//发送请求的时间，格式"yyyyMMddHHmmss"	20181112135452
	private String biz_data;		//业务参数集合（JSON格式）的base64数据	~
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMerch_id() {
		return merch_id;
	}
	public void setMerch_id(String merch_id) {
		this.merch_id = merch_id;
	}
	public String getBiz_code() {
		return biz_code;
	}
	public void setBiz_code(String biz_code) {
		this.biz_code = biz_code;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBiz_data() {
		return biz_data;
	}
	public void setBiz_data(String biz_data) {
		this.biz_data = biz_data;
	}
	@Override
	public String toString() {
		return "ReqPars [version=" + version + ", merch_id=" + merch_id + ", biz_code=" + biz_code + ", sign=" + sign
				+ ", timestamp=" + timestamp + ", biz_data=" + biz_data + "]";
	}
	
	
	
	
	
}

package com.myd.entity;
/**
 * 来联通支付异步返回的数据（快捷，网关）
 *@author xiaoqiang lu
 *
 *2019/01/22 13:52
 */
public class KLTAnsyPayReturn {
	private String merchantId;	//商户号	数字串，与提交订单时的商户号保持一致
	private String version;	//网关返回支付结果接口版本	固定选择值：v1.0 
	private String language;	//网，页显示语言种类	1代表简体中文、2代表繁体中文、3代表英文
	private Integer signType;	//签名类型	固定选择值：0、1，与提交订单时的签名类型保持一致
	private String payType	;//支付方式	字符串，返回用户在实际支付时所使用的支付方式
	private String issuerId;	//发卡方机构代码	字符串，返回用户在实际支付时所使用的发卡方机构代码
	private String mchtOrderId	;//开联订单号	字符串，开联订单号
	private String orderNo;	//商户订单号	字符串，与提交订单时的商户订单号保持一致
	private String orderDatetime;	//商户订单提交时间	与提交订单时的商户订单提交时间保持一致
	private Integer orderAmount;	//商户订单金额	整型数字，金额与币种有关	如果是人民币，则单位是分，即10元提交时金额应为1000	如果是美元，单位是美分，即10美元提交时金额为1000
	private String payDatetime;	//支付完成时间	日期格式：yyyyMMDDhhmmss，例如：20121116020101
	private String ext1;	//扩展字段1	字符串，与提交订单时的扩展字段1保持一致
	private String ext2	;//扩展字段2	字符串，与提交订单时的扩展字段2保持一致
	private String errorCode;	//交易错误码	交易错误码
	private String errorMsg	;//交易错误信息	交易错误信息
	private Integer payResult;	//处理结果	0：处理中 1：支付成功 2：失败	商户可以通过查询接口查询订单状态。(此字段用于判断业务执行状态)
	private String sign;	//签名字符串	所有非空参数按下方述顺序与密钥组合，经加密后生成该值。
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId== null||"".equals(merchantId)?null:merchantId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version == null||"".equals(version)?null :version;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language == null||"".equals(language)?null:language;
	}
	public Integer getSignType() {
		return signType;
	}
	public void setSignType(Integer signType) {
		this.signType = signType == null||"".equals(signType)?null:payResult;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType == null ||"".equals(payType)?null:payType;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId ==null ||"".equals(issuerId)?null:issuerId;
	}
	public String getMchtOrderId() {
		return mchtOrderId;
	}
	public void setMchtOrderId(String mchtOrderId) {
		this.mchtOrderId = mchtOrderId == null||"".equals(mchtOrderId)?null:mchtOrderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null||"".equals(orderNo)?null:orderNo;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime == null||"".equals(orderDatetime)?null :orderDatetime;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount == null ||"".equals(orderAmount)?null:orderAmount;
	}
	public String getPayDatetime() {
		return payDatetime;
	}
	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime == null ||"".equals(payDatetime)?null:payDatetime;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ||"".equals(ext1)? null:ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2 == null||"".equals(ext2)?null:ext2;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode == null ||"".equals(errorCode)?null :errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg == null ||"".equals(errorMsg)?null:errorMsg;
	}
	public Integer getPayResult() {
		return payResult;
	}
	public void setPayResult(Integer payResult) {
		this.payResult = payResult == null ||"".equals(payResult)?null:payResult;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign == null ||"".equals(sign) ? null :sign;
	}
	@Override
	public String toString() {
		return "KLTAnsyPayReturn [" + (merchantId != null ? "merchantId=" + merchantId + ", " : "")
				+ (version != null ? "version=" + version + ", " : "")
				+ (language != null ? "language=" + language + ", " : "")
				+ (signType != null ? "signType=" + signType + ", " : "")
				+ (payType != null ? "payType=" + payType + ", " : "")
				+ (issuerId != null ? "issuerId=" + issuerId + ", " : "")
				+ (mchtOrderId != null ? "mchtOrderId=" + mchtOrderId + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (orderDatetime != null ? "orderDatetime=" + orderDatetime + ", " : "")
				+ (orderAmount != null ? "orderAmount=" + orderAmount + ", " : "")
				+ (payDatetime != null ? "payDatetime=" + payDatetime + ", " : "")
				+ (ext1 != null ? "ext1=" + ext1 + ", " : "") + (ext2 != null ? "ext2=" + ext2 + ", " : "")
				+ (errorCode != null ? "errorCode=" + errorCode + ", " : "")
				+ (errorMsg != null ? "errorMsg=" + errorMsg + ", " : "")
				+ (payResult != null ? "payResult=" + payResult + ", " : "") + (sign != null ? "sign=" + sign : "")
				+ "]";
	}
	
	
	
	

}

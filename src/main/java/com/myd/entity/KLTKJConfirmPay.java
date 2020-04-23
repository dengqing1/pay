package com.myd.entity;
/**
 * 快捷支付确认支付
 *@author xiaoqiang lu
 *
 *2019/01/18 11:37
 */
public class KLTKJConfirmPay {
	
	private String originalRequestId;		//32	M	原请求流水号
	private String smsCode;		//10	M	短信验证码
	private String acctValidDate;		//4	O	信用卡有效期，信用卡必填，格式：yyMM
	private String orderNo;		//50	M	原发短信商户订单号 
	private Long orderAmount;		//10	M	商户订单金额,整型数字，金额与币种有关则单位是分，即10元提交时金额应为1000
	private Integer orderCurrency;		//15	M	币种类型：156代表人民币、840代表美元、344代表港币目前只支持人民币
	private String orderDatetime;		//30	M	商户下单时间,日期格式：yyyyMMddHHmmss，例如：20121116020101
	private Integer orderExpireDatetime;			//M	订单过期时间 单位分钟 整数
	private String pickupUrl;		//256	O	客户的取货地址	需http://格式的完整路径，不能加?id=123这类自定义参数
	private String cvv2;		//3	O	信用卡安全码，信用卡必填
	private String receiveUrl;		//256	M	交易结果后台通知地址	需http://格式的完整路径，不能加?id=123这类自定义参数
	private String productName;		//60	M	商品名称,英文或中文字符串，请勿首尾有空格字符
	private Integer productNum;		//10	O	商品数量
	private String productId;		//50	O	商品代码
	private String productDesc;		//200	O	商品描述
	private Long productPrice;		//10	O	商品价格
	private String ext1;			//40 O	拓展字段1
	private String ext2;			//40 O	拓展字段2
	private String split;			//2 C	订单分账标识，0-非分账订单 1-分账订单 默认为0
	public String getOriginalRequestId() {
		return originalRequestId;
	}
	public void setOriginalRequestId(String originalRequestId) {
		this.originalRequestId = originalRequestId == null || "".equals(originalRequestId) ? null : originalRequestId.trim();
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode == null ||"".equals(smsCode) ? null : smsCode.trim();
	}
	public String getAcctValidDate() {
		return acctValidDate;
	}
	public void setAcctValidDate(String acctValidDate) {
		this.acctValidDate = acctValidDate == null ||"".equals(acctValidDate) ? null:acctValidDate.trim();
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null || "".equals(orderNo) ? null :orderNo.trim();
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount == null || "".equals(orderAmount) ? null :orderAmount;
	}
	public Integer getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(Integer orderCurrency) {
		this.orderCurrency = orderCurrency  == null || "".equals(orderCurrency) ? null :orderCurrency;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime == null ||"".equals(orderDatetime)?null :orderDatetime.trim();
	}
	public Integer getOrderExpireDatetime() {
		return orderExpireDatetime;
	}
	public void setOrderExpireDatetime(Integer orderExpireDatetime) {
		this.orderExpireDatetime = orderExpireDatetime == null || "".equals(orderExpireDatetime) ? null : orderExpireDatetime;
	}
	public String getPickupUrl() {
		return pickupUrl;
	}
	public void setPickupUrl(String pickupUrl) {
		this.pickupUrl = pickupUrl == null ||"".equals(pickupUrl) ? null :pickupUrl.trim() ;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2 == null ||"".equals(cvv2) ? null :cvv2.trim();
	}
	public String getReceiveUrl() {
		return receiveUrl;
	}
	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl == null || "".equals(receiveUrl) ? null :receiveUrl.trim();
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName == null || "".equals(productName) ?null :productName.trim();
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum == null || "".equals(productNum) ? null :productNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId == null ||"".equals(productId)?null:productId.trim();
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc == null || "".equals(productDesc)? null:productDesc.trim();
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice == null || "".equals(productPrice) ? null:productPrice;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ||"".equals(ext1) ? null:ext1.trim();
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ||"".equals(ext2) ? null:ext2.trim();
	}
	public String getSplit() {
		return split;
	}
	public void setSplit(String split) {
		this.split = split == null || "".equals(split) ? null :split.trim();
	}
	@Override
	public String toString() {
		return "KLTKJConformPay [" + (originalRequestId != null ? "originalRequestId=" + originalRequestId + ", " : "")
				+ (smsCode != null ? "smsCode=" + smsCode + ", " : "")
				+ (acctValidDate != null ? "acctValidDate=" + acctValidDate + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (orderAmount != null ? "orderAmount=" + orderAmount + ", " : "")
				+ (orderCurrency != null ? "orderCurrency=" + orderCurrency + ", " : "")
				+ (orderDatetime != null ? "orderDatetime=" + orderDatetime + ", " : "")
				+ (orderExpireDatetime != null ? "orderExpireDatetime=" + orderExpireDatetime + ", " : "")
				+ (pickupUrl != null ? "pickupUrl=" + pickupUrl + ", " : "")
				+ (cvv2 != null ? "cvv2=" + cvv2 + ", " : "")
				+ (receiveUrl != null ? "receiveUrl=" + receiveUrl + ", " : "")
				+ (productName != null ? "productName=" + productName + ", " : "")
				+ (productNum != null ? "productNum=" + productNum + ", " : "")
				+ (productId != null ? "productId=" + productId + ", " : "")
				+ (productDesc != null ? "productDesc=" + productDesc + ", " : "")
				+ (productPrice != null ? "productPrice=" + productPrice + ", " : "")
				+ (ext1 != null ? "ext1=" + ext1 + ", " : "") + (ext2 != null ? "ext2=" + ext2 + ", " : "")
				+ (split != null ? "split=" + split : "") + "]";
	}
}

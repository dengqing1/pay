package com.myd.entity;

public class KLTGatewayContent {
	private String pickupUrl;		//256	M	支付成功后跳转的地址，建议填需http://格式的完整路径
	
	private String receiveUrl;		//256	M	交易结果后台通知地址，必填需http://格式的完整路径
	
	private String payerName;		//30	O	付款人姓名
	
	private String payerEmail;		//50	O	付款人邮件联系方式
	
	private String payerTelPhone;		//16	O	付款人电话联系方式
	
	private String payerAcctNo;		//128	O	微信公众号支付	微信小程序支付	微信APP支付	openid填入此字段
	
	private String orderNo;	//64	M	商户订单号，只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
	
	private Long orderAmount;		//10	M	金额，金额与币种有关	如果是人民币，则单位是分，即10元提交时金额应为1000	如果是美元，单位是美分，即10美元提交时金额为1000
	
	private Integer orderCurrency=156;		//3	M	币种类型：156代表人民币、840代表美元、344代表港币	目前只支持人民币
	
	private String orderDateTime;		//14	M	商户订单时间，日期格式：yyyyMMDDhhmmss，例如：20121116020101
	
	private Integer orderExpireDatetime;  //14	O	订单过期时间，单位为分钟。最大值为9999分钟。	如填写则以商户上送时间为准，如不填写或填0或填非法值，则服务端默认该订单9999分钟后过期。
	
	private String productName;		//60	M	商品名称，英文或中文字符串，请勿首尾有空格字符
	
	private Long productPrice;		//20	O	商品价格，英文或中文字符串
	
	private Integer productNum;		//8	O	商品数量，英文或中文字符串
	
	private String productId;		//20	O	商品代码，字母、数字或- 、_ 的组合；用于使用产品数据中心的产品数据，或用于市场活动的优惠
	
	private String productDesc;		//400	O	商品描述，英文或中文字符串
	
	private String ext1;		//40	O	扩展字段1
	
	private String ext2;		//40	O	扩展字段2
	
	private String termId;			//O	终端类型，app支付
	
	private Integer payType;		//2	M	用户在支付时可以使用的支付方式，固定选择值：	1-个人网银支付	4-企业网银支付	20-微信扫码支付(c扫B)	36-微信公众号支付	60-微信小程序支付	43-微信app支付47-银联二维码	31-银联在线支付
	
	private String  issuerId;//8	C	机构代码，payType=1或者4时必传，（测试环境：00000000）需要直连支付时填写。	参考附录支付机构代码
	
	private String split; //2	C	订单分账标识，0-非分账订单 1-分账订单 默认为0

	public String getPickupUrl() {
		return pickupUrl;
	}

	public void setPickupUrl(String pickupUrl) {
		this.pickupUrl = pickupUrl == null || "".equals(pickupUrl) ? null :pickupUrl.trim();
	}

	public String getReceiveUrl() {
		return receiveUrl;
	}

	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl == null || "".equals(receiveUrl) ? null :receiveUrl.trim();
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName == null || "".equals(payerName) ? null : payerName.trim();
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail == null || "".equals(payerEmail) ? null :payerEmail.trim();
	}

	public String getPayerTelPhone() {
		return payerTelPhone;
	}

	public void setPayerTelPhone(String payerTelPhone) {
		this.payerTelPhone = payerTelPhone ==null || "".equals(payerTelPhone) ?null :payerTelPhone.trim();
	}

	public String getPayerAcctNo() {
		return payerAcctNo;
	}

	public void setPayerAcctNo(String payerAcctNo) {
		this.payerAcctNo = payerAcctNo ==null || "".equals(payerAcctNo)? null :payerAcctNo.trim();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null || "".equals(orderNo) ? null : orderNo.trim();
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
		this.orderCurrency = orderCurrency == null || "".equals(orderCurrency) ? null :orderCurrency;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime == null || "".equals(orderDateTime) ? null :orderDateTime.trim();
	}

	public Integer getOrderExpireDatetime() {
		return orderExpireDatetime;
	}

	public void setOrderExpireDatetime(Integer orderExpireDatetime) {
		this.orderExpireDatetime = orderExpireDatetime == null || "".equals(orderExpireDatetime) ? null :orderExpireDatetime  ;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null || "".equals(productName) ? null :productName.trim();
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice ==null || "".equals(productPrice) ? null : productPrice;
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
		this.productId = productId == null || "".equals(productId) ? null :productId.trim();
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc == null || "".equals(productDesc) ? null :productDesc.trim();
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1 == null || "".equals(ext1) ? null :ext1.trim();
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2 == null || "".equals(ext2) ? null :ext2.trim();
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId == null||"".equals(termId) ?null:termId.trim();
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType == null || "".equals(payType)?null :payType;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId == null || "".equals(issuerId)?null:issuerId.trim();
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split == null || "".equals(split) ? null :split.trim();
	}

	@Override
	public String toString() {
		return "KLTGatewayContent [" + (pickupUrl != null ? "pickupUrl=" + pickupUrl + ", " : "")
				+ (receiveUrl != null ? "receiveUrl=" + receiveUrl + ", " : "")
				+ (payerName != null ? "payerName=" + payerName + ", " : "")
				+ (payerEmail != null ? "payerEmail=" + payerEmail + ", " : "")
				+ (payerTelPhone != null ? "payerTelPhone=" + payerTelPhone + ", " : "")
				+ (payerAcctNo != null ? "payerAcctNo=" + payerAcctNo + ", " : "")
				+ (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (orderAmount != null ? "orderAmount=" + orderAmount + ", " : "")
				+ (orderCurrency != null ? "orderCurrency=" + orderCurrency + ", " : "")
				+ (orderDateTime != null ? "orderDateTime=" + orderDateTime + ", " : "")
				+ (orderExpireDatetime != null ? "orderExpireDatetime=" + orderExpireDatetime + ", " : "")
				+ (productName != null ? "productName=" + productName + ", " : "")
				+ (productPrice != null ? "productPrice=" + productPrice + ", " : "")
				+ (productNum != null ? "productNum=" + productNum + ", " : "")
				+ (productId != null ? "productId=" + productId + ", " : "")
				+ (productDesc != null ? "productDesc=" + productDesc + ", " : "")
				+ (ext1 != null ? "ext1=" + ext1 + ", " : "") + (ext2 != null ? "ext2=" + ext2 + ", " : "")
				+ (termId != null ? "termId=" + termId + ", " : "")
				+ (payType != null ? "payType=" + payType + ", " : "")
				+ (issuerId != null ? "issuerId=" + issuerId + ", " : "") + (split != null ? "split=" + split : "")
				+ "]";
	}

	
}

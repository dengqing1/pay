<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网关支付</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div style="color:red">
网关支付
</div>
<br><br>
<form action="gatewayConfirm" method="post">
M支付成功后跳转的地址：<input name="pickupUrl"  type="text"><br><br>
M交易结果后台通知地址：<input name="receiveUrl"  type="text"><br><br>
O付款人姓名：<input name="payerName"  type="text"><br><br>
O付款人邮件联系方式：<input name="payerEmail"  type="text"><br><br>
O付款人电话联系方式：<input name="payerTelPhone"  type="text"><br><br>
微信公众号支付：<input name="payerAcctNo"  type="text"><br><br>
M商户订单号：<input name="orderNo"  type="text" value="${orderid}"><br><br>
M金额：<input name="orderAmount"  type="text"><br><br>
M币种类型：<input name="orderCurrency"  type="text" value="156"><br><br>
M商户订单时间：<input name="orderDateTime"  type="text" value="${time}"><br><br>
O订单过期时间：<input name="orderExpireDatetime"  type="text" value="9999"><br><br>
M商品名称：<input name="productName"  type="text"><br><br>
O商品价格：<input name="productPrice"  type="text"><br><br>
O商品数量：<input name="productNum"  type="text"><br><br>
O商品代码：<input name="productId"  type="text"><br><br>
O商品描述：<input name="productDesc"  type="text"><br><br>
O扩展字段1：<input name="ext1"  type="text"><br><br>
O扩展字段2：<input name="ext2"  type="text"><br><br>
O终端类型：<input name="termId"  type="text"><br><br>
M用户在支付时可以使用的支付方式，固定选择值：	1-个人网银支付	4-企业网银支付	20-微信扫码支付(c扫B)	36-微信公众号支付	60-微信小程序支付	43-微信app支付47-银联二维码	31-银联在线支付：<input name="payType"  type="text"><br><br>
C机构代码，payType=1或者4时必传：<input name="issuerId"  type="text"><br><br>
C订单分账标识：<input name="split"  type="text"><br><br>


<input type="submit" value="提交">
</form>
</body>
</html>

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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>确认下单</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>

<div style="color:red">
确认下单
</div>
<br><br>

<c:choose>
	<c:when test="${not empty stau }">
		<form action="confirmPay" method="post">
		(M)原请求流水号：<input name="originalRequestId"  type="text" value="${duanxin.requestId}"><br><br>
		(M)短信验证码：<input name="smsCode"  type="text" value=""><br><br>
		(O)信用卡有效期，信用卡必填，格式：yyMM：<input name="acctValidDate"  type="text" ><br><br>
		(M)原发短信商户订单号：<input name="orderNo"  type="text" value="${duanxin.orderNo}"><br><br>
		(M)商户订单金额：<input name="orderAmount"  type="text" value="100"><br><br>
		(M)币种类型：<input name="orderCurrency"  type="text" value="156"><br><br>
		(M)商户下单时间：<input name="orderDatetime"  type="text" value="${time}"><br><br>
		(M)订单过期时间 单位分钟：<input name="orderExpireDatetime"  type="text" value="9999"><br><br>
		(O)客户的取货地址：<input name="pickupUrl"  type="text" ><br><br>
		(O)信用卡安全码，信用卡必填：<input name="cvv2"  type="text" ><br><br>
		(M)交易结果后台通知地址：<input name="receiveUrl"  type="text" value="http://47.75.179.162:85/klt/KltKuaijieCallBack/backend"><br><br>
		(M)商品名称：<input name="productName"  type="text" value="名称"><br><br>
		(O)商品数量：<input name="productNum"  type="text" ><br><br>
		(O)商品代码：<input name="productId"  type="text" ><br><br>
		(O)商品描述：<input name="productDesc"  type="text" ><br><br>
		(O)商品价格：<input name="productPrice"  type="text" ><br><br>
		(O)拓展字段1：<input name="ext1"  type="text"><br><br>
		(O)拓展字段2：<input name="ext2"  type="text"><br><br>
		(c)订单分账标识，0-非分账订单 1-分账订单 默认为0：<input name="split"  type="text"><br><br>
		<input type="submit" value="提交">
		</form>
	
	</c:when>
	<c:otherwise>
			${msg}
	</c:otherwise>

</c:choose>
</body>
</html>

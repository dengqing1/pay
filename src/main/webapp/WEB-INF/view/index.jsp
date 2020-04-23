<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="http://epay.novoxpay.com/order/status" method="post">
加密方式：<input name="signMethod" value="${requestScope.signMethod}" type="text"><br><br>
商户号：<input name="merchantId" value="${requestScope.merchantId}" type="text"><br><br>
订单号：<input name="merOrderId" value="${requestScope.merOrderId}" type="text"><br><br>
签名信息：<input name="signature" value="${requestScope.signature}" type="text">
<input type="submit" value="提交">
</form>




</body>
</html>
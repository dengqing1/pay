<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>短信发送</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div style="color:red">
短信发送
</div>
<br><br>
<form action="sendDuanxin" method="post">
认证支付付款人姓名：<input name="payerName"  type="text" value="全渠道"><br><br>
认证支付付款人手机号：<input name="payerTelephone"  type="text" value="13552535506"><br><br>
认证支付付款人卡号：<input name="payerAcctNo"  type="text" value="6216261000000000018"><br><br>
金额：<input name="orderAmount"  type="text" value="100"><br><br>
身份证号：<input name="payerIdNo"  type="text" value="341126197709218366"><br><br>
订单号：<input name="orderNo"  type="text" value="${time}"><br><br>
<input type="submit" value="提交">
</form>
</body>
</html>
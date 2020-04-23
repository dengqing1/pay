<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>确认支付</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div style="color:red">
确认支付
</div>
<br><br>
<form action="comfirmSubmit" method="post">
商户号：<input name="merchantId"  type="text" value="600000000000002" ><br><br>
原商户订单号：<input name="merOrderId"  type="text" ><br><br>
短信验证码：<input name="smsCode"  type="text"  value="111111"><br><br>
秘钥：<input name="key"  type="text" value="682807c82e2716452bd069aaf72d48dc" ><br><br>
<input type="submit" value="提交">
</form>
</body>
</html>
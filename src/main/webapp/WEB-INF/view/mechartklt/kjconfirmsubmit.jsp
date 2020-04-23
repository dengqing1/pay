
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<form action="../kjpayConfirm" method="post"  id="myform">
<c:if test="${not empty map['signMethod'] }">
<input name="signMethod" value="${map['signMethod']}" type="text"><br><br>
</c:if>
<c:if test="${not empty map['signature'] }">
<input name="signature" value="${map['signature']}" type="text"><br><br>
</c:if>
<c:if test="${not empty map['merchantId'] }">
<input name="merchantId" value="${map['merchantId']}" type="text"><br><br>
</c:if>
<c:if test="${not empty map['merOrderId'] }">
<input name="merOrderId" value="${map['merOrderId']}" type="text"><br><br>
</c:if>
<c:if test="${not empty map['smsCode'] }">
<input name="smsCode" value="${map['smsCode']}" type="text"><br><br>
</c:if>
</form>
</body>
</html>
<script>
$("#myform").submit();
</script>

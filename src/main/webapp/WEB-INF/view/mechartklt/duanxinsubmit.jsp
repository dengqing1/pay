
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<form action="../pay" method="post"  id="myform">

<c:if test="${not empty map['cvv2'] }">
<input name="cvv2" value="${map['cvv2']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['acctValidDate'] }">
<input name="acctValidDate" value="${map['acctValidDate']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['payerIdNo'] }">
<input name="payerIdNo" value="${map['payerIdNo']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['signMethod'] }">
<input name="signMethod" value="${map['signMethod']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['signature'] }">
<input name="signature" value="${map['signature'] }" type="text"><br><br>
</c:if>

<c:if test="${not empty map['merchantId'] }">
<input name="merchantId" value="${map['merchantId']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['merOrderId'] }">
订单号：<input name="merOrderId" value="${map['merOrderId']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['dcType'] }">
<input name="dcType" value="${map['dcType']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['txnAmt'] }">
<input name="txnAmt" value="${map['txnAmt']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['accNo'] }">
<input name="accNo" value="${map['accNo']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['customerNm'] }">
<input name="customerNm" value="${map['customerNm']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['phoneNo'] }">
<input name="phoneNo" value="${map['phoneNo']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['gateway'] }">
<input name="gateway" value="${map['gateway']}" type="text"><br><br>
</c:if>
<c:if test="${not empty map['backUrl'] }">
<input name="backUrl" value="${map['backUrl']}" type="text"><br><br>
</c:if>
</form>
</body>
</html>
<script>
$("#myform").submit();
</script>


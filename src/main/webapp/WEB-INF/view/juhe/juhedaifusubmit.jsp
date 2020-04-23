
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div style="color:red">
交易类型:  代付
</div>
<br><br>
<form action="../pay" method="post" id="myform">

<c:if test="${not empty map['merchantId'] }">
<input name="merchantId"  type="text" value="${map['merchantId']}"><br><br>
</c:if>

<c:if test="${not empty map['merOrderId'] }">
<input name="merOrderId"  type="text" value="${map['merOrderId']}"><br><br>
</c:if>

<c:if test="${not empty map['txnAmt'] }">
<input name="txnAmt"  type="text" value="${map['txnAmt']}"> <br><br>
</c:if>

<c:if test="${not empty map['backUrl'] }">
<input name="backUrl"  type="text" value="${map['backUrl']}"><br><br>
</c:if>

<c:if test="${not empty map['bankId'] }">
<input name="bankId"  type="text" value="${map['bankId']}"><br><br>
</c:if>

<c:if test="${not empty map['purpose'] }">
<input name="purpose"  type="text" value="${map['purpose']}"><br><br>
</c:if>

<c:if test="${not empty map['subject'] }">
<input name="subject"  type="text" value="${map['subject']}"><br><br>
</c:if>

<c:if test="${not empty map['accNo']}">
<input name="accNo"  type="text" value="${map['accNo']}"><br><br>
</c:if>

<c:if test="${not empty map['customerNm'] }">
<input name="customerNm"  type="text" value="${map['customerNm']}"><br><br>
</c:if>

<c:if test="${not empty map['phoneNo'] }">
<input name="phoneNo"  type="text" value="${map['phoneNo']}"><br><br>
</c:if>

<c:if test="${not empty map['body'] }">
<input name="body"  type="text" value="${map['body']}"><br><br>
</c:if>

<c:if test="${not empty map['gateway'] }">
<input name="gateway"  type="text" value="${map['gateway']}"><br><br>
</c:if>

<c:if test="${not empty map['version'] }">
<input name="version"  type="text" value="${map['version']}"><br><br>
</c:if>

<c:if test="${not empty map['txnType'] }">
<input name="txnType"  type="text" value="${map['txnType']}"><br><br>
</c:if>

<c:if test="${not empty map['txnSubType'] }">
<input name="txnSubType"  type="text" value="${map['txnSubType']}"><br><br>
</c:if>

<c:if test="${not empty map['bizType'] }">
<input name="bizType"  type="text" value="${map['bizType']}"><br><br>
</c:if>

<c:if test="${not empty map['accessType'] }">
<input name="accessType"  type="text" value="${map['accessType']}"><br><br>
</c:if>

<c:if test="${not empty map['purpose'] }">

</c:if>
<input name="accessMode"  type="text" value="${map['accessMode']}"><br><br>
<c:if test="${not empty map['ppFlag'] }">
<input name="ppFlag"  type="text" value="${map['ppFlag']}"><br><br>
</c:if>

<c:if test="${not empty map['issInsName'] }">
<input name="issInsName"  type="text" value="${map['issInsName']}"><br><br>
</c:if>

<c:if test="${not empty map['txnTime'] }">
<input name="txnTime"  type="text" value="${map['txnTime']}"><br><br>
</c:if>

<c:if test="${not empty map['currency'] }">
<input name="currency"  type="text" value="${map['currency']}"><br><br>
</c:if>

<c:if test="${not empty map['payType'] }">
<input name="payType" type="text" value="${map['payType']}"><br><br>
</c:if>

<c:if test="${not empty map['signMethod'] }">
<input name="signMethod" value="${map['signMethod']}" type="text"><br><br>
</c:if>

<c:if test="${not empty map['signature'] }">
<input name="signature" value="${map['signature']}" type="text"><br><br>
</c:if>

</form>
</body>
</html>
<script>
$("#myform").submit();
</script>

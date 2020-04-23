<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<style>
input{
width:300px;
}

</style>
<body>
<!-- <div style="color:red">
交易类型: juhe  bank
</div> -->

<form id="tradeForm" action="http://otc.globalokpaytech.com/pay/toConfirmIn" method = "post">
	  <input type = "hidden" name="pickupUrl" value="${juheOrder.pickupUrl }"/>
	  <input type = "hidden" name="receiveUrl" value="${juheOrder.receiveUrl }"/>
	  <input type = "hidden" name="signType" value="${juheOrder.signType }"/>
	  <input type = "hidden" name="orderNo" value="${juheOrder.orderNo }"/>
	  <input type = "hidden" name="orderAmount" value="${juheOrder.orderAmount }"/>
	  <input type = "hidden" name="orderCurrency" value="${juheOrder.orderCurrency }"/>
	  <input type = "hidden" name="customerId" value="${juheOrder.customerId }"/>
	  <input type = "hidden" name="crmNo" value="${juheOrder.crmNo }"/>
	  <input type = "hidden" name="sign" value="${juheOrder.sign }"/>
<!-- 	  <input type="submit" value="测试提交2111"> -->
</form>
<script>
$("#tradeForm").submit();
</script>
</body>
</html>



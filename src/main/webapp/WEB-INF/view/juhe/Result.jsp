<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<
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
<script>
//$("#tradeForm").submit();
</script>
<body>
<div style="color:red">
交易类型: juhe  bank   回调  500
</div>

<!-- <form id="tradeForm" action="http://47.52.208.15:8080/EAmall/controller/payReturn" method = "post"> -->
<form id="tradeForm" action="../juheBanks10" method = "post">

merId   商户号    <input type = "text" name="memberid" value="10088"/>  <br><br>
orderNo	平台订单号			<input type = "text" name="orderid" value="20190307161451827486"/>  <br><br>
outTradeNo	商户订单号	  <input type = "text" name="amount" value="500"/><br><br>
status	订单状态    		<input type = "text" name="transaction_id" value="1550569671234"/><br><br>
totalFee	订单金额	<input type = "text" name="datetime" value="2019-03-07 14:28:25"/><br><br>
body	商品名称	  <input type = "text" name="returncode" value="00"/><br><br>
payType	交易类型	<input type = "text" name="attach" value="H5"/><br><br>
sign	签名			<input type = "text" name="sign" value="54F02E6C6078D1914D6445305A02394C"/><br><br>

	 
	  
	  <input type="submit" value="测试回调提交">
</form>

</body>
</html>



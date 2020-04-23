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
交易类型: juhe  bank   回调
</div>

${juheOrder}
<!-- <form id="tradeForm" action="http://47.52.208.15:8080/EAmall/controller/payReturn" method = "post"> -->
<form id="tradeForm" action="../juheBanks" method = "post">

merId   商户号    <input type = "text" name="merId" value="100660002"/>  <br><br>
orderNo	平台订单号			<input type = "text" name="orderNo" value="1550569671234"/>  <br><br>
outTradeNo	商户订单号	  <input type = "text" name="outTradeNo" value="2019022217212932506114"/><br><br>
status	订单状态    		<input type = "text" name="status" value="paid"/><br><br>
totalFee	订单金额	<input type = "text" name="totalFee" value="100"/><br><br>
body	商品名称	  <input type = "text" name="body" value="描述"/><br><br>
payType	交易类型	<input type = "text" name="payType" value="H5"/><br><br>
payChannel	支付渠道	<input type = "text" name="payChannel" value="WXPAY"/><br><br>
nonceStr	随机字符串	 <input type = "text" name="nonceStr" value="8465456464"/><br><br>
sign	签名			<input type = "text" name="sign" value="54F02E6C6078D1914D6445305A02394C"/><br><br>

	 
	  
	  <input type="submit" value="测试回调提交">
</form>

</body>
</html>



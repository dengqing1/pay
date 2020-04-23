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
交易类型: juhe  代付   模拟
</div>

${juheDaifu }
<form id="tradeForm" action="http://pay.100off.cn/substitute/batchWithdraw" method = "post">
	  <input type = "text" name="merId" value="${juheDaifu.merId }"/>
	  <input type = "text" name="outBatchNo" value="${juheDaifu.outBatchNo }"/>
	  <input type = "text" name="totalNum" value="${juheDaifu.totalNum }"/>
	  <input type = "text" name="totalAmount" value="${juheDaifu.totalAmount }"/>
	  <input type = "text" name="detailData" value="${juheDaifu.detailData }"/>
	  <input type = "text" name="nonceStr" value="${juheDaifu.nonceStr }"/>
	  <input type = "text" name="sign" value="${juheDaifu.sign }"/>
	  <input type="submit" value="代付测试提交2111">
</form>

</body>
</html>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div style="color:red">
交易类型:   bank
</div>
<br><br>
<form action="testBank" method="post">
商户号:<input name="merchantId"  type="text" value="600000000000002"><br><br>
商户订单号:<input name="merOrderId" type="text"><br><br>
交易金额:<input name="txnAmt"  type="text" value="100"><br><br>
前台通知地址:<input name="frontUrl"  type="text" value="http://47.75.179.162:85/klt/KltKuaijieCallBack/backend"><br><br>
后台通知地址:<input name="backUrl"  type="text" value="http://47.75.179.162:8080/fengmai/mechrtklt/callback"><br><br>
银行编号:<input name="bankId"  type="text" value="04100000"><br><br>
借贷类型:<input name="dcType" type="text" value="0"><br><br>
商品标题:<input name="subject"  type="text" value="标题"><br><br>
账号:<input name="accNo"  type="text" value="6216261000000000018"><br><br>
开户姓名:<input name="customerNm"  type="text" value="全渠道"><br><br>
手机号:<input name="phoneNo"  type="text" value="13552535506"><br><br>
商户用户id:<input name="userId"  type="text" value="12455"><br><br>
终端用户IP:<input name="customerIp"  type="text" value="127.0.0.1"><br><br>
商品描述:<input name="body"  type="text" value="描述"><br><br>
网关:<input name="gateway"  type="text" value="bank"><br><br><br><br>
秘钥:<input name="key"  type="text" value="682807c82e2716452bd069aaf72d48dc"><br><br>
<input type="submit" value="提交">
</form>
</body>
</html>


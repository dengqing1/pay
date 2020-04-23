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
<form action="sendduanxin" method="post">
认证支付付款人姓名：<input name="customerNm"  type="text" value="全渠道"><br><br>
认证支付付款人手机号：<input name="phoneNo"  type="text" value="13552535506"><br><br>
认证支付付款人卡号：<input name="accNo"  type="text" value="6216261000000000018"><br><br>
金额：<input name="txnAmt"  type="text" value="100"><br><br>
身份证号：<input name="payerIdNo"  type="text" value="341126197709218366"><br><br>
订单号：<input name="merOrderId"  type="text" value="${time}"><br><br>
卡的类型0:借记卡1借贷卡：<input name="dcType"  type="text" value="0"><br><br>
商户号：<input name="merchantId"  type="text" value="600000000000002" ><br><br>
信用卡安全码：(信用卡的时候必填)<input name="cvv2"  type="text" ><br><br>
信用卡有效期：(信用卡的时候必填)<input name="acctValidDate"  type="text" ><br><br>
网关：<input name="gateway"  type="text" value="kuaijie" ><br><br><br><br>
后台通知地址:<input name="backUrl"  type="text" value="http://47.75.179.162:8080/fengmai/mechrtklt/callback"><br><br>
秘钥：<input name="key"  type="text" value="682807c82e2716452bd069aaf72d48dc" ><br><br>
<input type="submit" value="提交">
</form>
</body>
</html>

	 	
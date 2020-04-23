<%--
  Created by IntelliJ IDEA.
  User: you
  Date: 2018/11/20
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div align="center" style="margin-top: 200px;">
    <a href="/fengmai/toLogin">1.登录入口</a><br><br>
    <a href="/toAddMerchant">2.商户录入</a><br><br>
    <a href="/getMerchant">3.商户信息查询</a><br><br>
    <a href="/toMerchantAccount">4.商户账户余额查询</a><br><br>
    <a href="/toMerchantAccount">5.入金汇总</a><br><br>
    <a href="/toMerchantAccount">6.代付汇总</a><br><br>
    <a href="/toPay">7.支付查询</a><br><br>
        <a href="/toMerchantOpen">8.商户开通</a><br><br>
    <a href="/toChannelConfiguration">9.通道配置</a><br><br>
</div>
</body>
</html>

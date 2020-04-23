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
<h2 align="center">支付信息</h2>
<table border="1" align="center">
    <thead>
    <tr>
        <th>订单日期</th>
        <th>平台订单号</th>
        <th>商户号</th>
        <th>商户名称</th>
        <th>商户订单号</th>
        <th>订单金额</th>
        <th>手续费</th>
        <th>产品类型</th>
        <th>银行</th>
        <th>通道简称</th>
        <th>通道名</th>
        <th>状态</th>
        <th>通道状态码</th>
        <th>状态详情</th>
    </tr>
    </thead>
    <tbody id="tableContent">
    </tbody>
</table>
</body>
</html>
<script>
    //展示列表
    $.ajax({
        url: "/getPayInformation",
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#tableContent").append("<tr><td>" + data[i].order_date + "</td><td>" + data[i].platform_order_id + "</td><td>" + data[i].merchant_id + "</td><td>" + data[i].merchant_name + "</td><td>" + data[i].merchant_order_id + "</td><td>" + data[i].order_money + "</td><td>" + data[i].poundage + "</td><td>" + data[i].product_type + "</td><td>" + data[i].bank_name + "</td><td>" + data[i].channel_abbreviation + "</td><td>" + data[i].channel_name + "</td><td>" + data[i].order_status + "</td><td>" + data[i].channel_status_code + "</td><td>" + data[i].status_details + "</td></tr>");
            }
        }
    })
</script>
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
<h2 align="center">商户账户信息</h2>
<table border="1" align="center">
    <thead>
    <tr>
        <th>商户号</th>
        <th>商户名称</th>
        <th>产品</th>
        <th>账户余额</th>
        <th>冻结资金</th>
        <th>在途金额</th>
        <th>当日入金</th>
        <th>当日出金</th>
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
        url: "/getMerchantAccount",
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $("#tableContent").append("<tr><td>" + data[i].merchant_id + "</td><td>" + data[i].merchant_name + "</td><td>" + data[i].product + "</td><td>" + data[i].merchant_account_money + "</td><td>" + data[i].freeze_funds + "</td><td>" + data[i].on_the_amount + "</td><td>" + data[i].day_into_golden + "</td><td>" + data[i].day_out_golden + "</td></tr>");
            }
        }
    })
</script>
<%--
  Created by IntelliJ IDEA.
  User: you
  Date: 2018/11/20
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<h2>商户信息</h2>
<table border="1" align="left">
    <thead>
    <tr>
        <th><a href="#" class="del">删除</a></th>
        <th>商户号</th>
        <th>商户名称</th>
        <th>产品</th>
        <th>信息更新时间</th>
        <th>风险等级</th>
        <th>信息审核状态</th>
        <th>审核时间</th>
        <th>开通状态</th>
        <th>开通时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tableContent">
    <c:forEach items="${pageInfo.list}" var="merchant">
        <tr>
            <td>
                <input type="checkbox" class="delchecked" value="${merchant.merchant_id}">
            </td>
            <td>${merchant.merchant_id}</td>
            <td>${merchant.merchant_name}</td>
            <td>${merchant.product}</td>
            <td>${merchant.information_update_time}</td>
            <td>${merchant.risk_level}</td>
            <td>${merchant.information_audit_status}</td>
            <td>${merchant.audit_time}</td>
            <td>${merchant.opening_status}</td>
            <td>${merchant.opening_time}</td>
            <td>
                <a href="#" class="updateMerchant" value="${merchant.merchant_id}">修改</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="page" style="position: relative;top: 100px;right: 600px;">
    <a href="getMerchant?pageNo=${pageInfo.firstPage}">首页</a>
    <c:if test="${pageInfo.hasPreviousPage }">
        <a href="getMerchant?pageNo=${pageInfo.pageNum-1}">上一页</a>
    </c:if>
    <c:if test="${pageInfo.hasNextPage }">
        <a href="getMerchant?pageNo=${pageInfo.pageNum+1}">下一页</a>
    </c:if>
    <a href="getMerchant?pageNo=${pageInfo.lastPage}">尾页</a>
    <span>当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
        页,总 ${pageInfo.total } 条记录</div>
</span>
</div>
<table id="table" align="right" style="display: none;">
    <tr>
        <td>商户号</td>
        <td><input type="text" name="merchant_id" id="merchant_id" value="${merchant_id}" disabled="false"></td>
    </tr>
    <tr>
        <td>商户名称</td>
        <td><input type="text" name="merchant_name" value="" id="merchant_name"></td>
    </tr>
    <tr>
        <td>产品</td>
        <td><input type="text" name="product" value="" id="product"></td>
    </tr>
    <tr>
        <td>信息更新时间</td>
        <td><input type="text" name="information_update_time" value="" id="information_update_time"></td>
    </tr>
    <tr>
        <td>风险等级</td>
        <%--td><input type="text" name="risk_level" value="" id="risk_level"></td>--%>
        <td>
            <input type="radio" name="risk_level" value="高">高
            <input type="radio" name="risk_level" value="中">中
            <input type="radio" name="risk_level" value="低">低
        </td>
    </tr>
    <tr>
        <td>信息审核状态</td>
        <%--<td><input type="text" name="information_audit_status" value="" id="information_audit_status"></td>--%>
        <td>
            <input type="radio" name="information_audit_status" value="待审核">待审核
            <input type="radio" name="information_audit_status" value="审核通过">审核通过
        </td>
    </tr>
    <tr>
        <td>审核时间</td>
        <td><input type="text" name="audit_time" value="" id="audit_time"></td>
    </tr>
    <tr>
        <td>开通状态</td>
        <%--<td><input type="text" name="opening_status" value="" id="opening_status"></td>--%>
        <td>
            <input type="radio" name="opening_status" value="未开通">未开通
            <input type="radio" name="opening_status" value="已开通">已开通
        </td>
    </tr>
    <tr>
        <td>开通时间</td>
        <td><input type="text" name="opening_time" value="" id="opening_time"></td>
    </tr>
    <tr>
        <td>
            <a href="#" class="btn">提交</a>
        </td>
    </tr>
</table>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<h5>入金汇总：</h5>
<table border="1">
    <thead>
    <tr>
        <th>商户号</th>
        <th>商户名称</th>
        <th>产品类型</th>
        <th>交易金额</th>
        <th>手续费</th>
        <th>笔数</th>
    </tr>
    </thead>
    <tbody id="intoSum">
    </tbody>
</table>
<br>
<h5>代付汇总：</h5>
<table border="1">
    <thead>
    <tr>
        <th>商户号</th>
        <th>商户名称</th>
        <th>渠道类型</th>
        <th>交易金额</th>
        <th>手续费</th>
        <th>笔数</th>
    </tr>
    </thead>
    <tbody id="paidSum">
    </tbody>
</table>

</body>
</html>
<script>

    //汇总查询
    $.ajax({
        url: "/getMerchantSum",
        type: "get",
        dataType: "json",
        success: function (data) {
            //入金汇总
            var intoSum = data.intoSum;
            //代付汇总
            var paidSum = data.paidSum;
            for (var i = 0; i < intoSum.length; i++) {
                $("#intoSum").append("<tr><td>" + intoSum[i].merchant_id + "</td><td>" + intoSum[i].merchant_name + "</td><td>" + intoSum[i].product_type + "</td><td>" + intoSum[i].transaction_amount + "</td><td>" + intoSum[i].poundage + "</td><td>" + intoSum[i].number + "</td></tr>");
            }
            for (var i = 0; i < paidSum.length; i++) {
                $("#paidSum").append("<tr><td>" + paidSum[i].merchant_id + "</td><td>" + paidSum[i].merchant_name + "</td><td>" + paidSum[i].qdchannel_type + "</td><td>" + paidSum[i].transaction_amount + "</td><td>" + paidSum[i].poundage + "</td><td>" + paidSum[i].number + "</td></tr>");
            }
        }
    })


    //删除选择的商户
    var merchantId = "";
    var merchantIds = ""
    $(".del").click(function () {
        $(".delchecked:checked").map(function () {
            merchantId += "," + $(this).val();
        });
        //merchantIds被选中的id
        merchantIds = merchantId.substring(1);
        if (null == merchantIds || undefined == merchantIds || '' == merchantIds) {
            alert("请先选择要删除的商户！")
        } else {
            $.post("/delMerchant", {"merchantIds": merchantIds}, function (data) {
                if (data) {
                    alert("删除成功!");
                    location.reload();
                } else {
                    alert("删除失败!");
                }
            }, "json");
        }
    })
    //修改商户信息回显
    $(".updateMerchant").click(function () {
        var temp = $("#table").is(":hidden");//是否隐藏
        if (temp) {
            $("#table").show();
        } else {
            alert("请先提交修改内容！")
            return false;
        }
        merchantId = $(this).attr("value");
        $.get("/getUpdateMerchantEcho", {"merchantId": merchantId}, function (data) {
            $("#merchant_id").val(data.merchant_id);
            $("#merchant_name").val(data.merchant_name);
            $("#product").val(data.product);
            $("#information_update_time").val(data.information_update_time);
            $("input[name=risk_level][value=" + data.risk_level + "]").attr("checked", true);
            $("input[name=information_audit_status][value=" + data.information_audit_status + "]").attr("checked", true);
            $("#audit_time").val(data.audit_time);
            $("input[name=opening_status][value=" + data.opening_status + "]").attr("checked", true);
            $("#opening_time").val(data.opening_time);
        }, "json");
    })
    //执行修改商户信息
    $("body").on("click", ".btn", function () {
        $.post("/updateMerchantInformation", {
            "merchant_id": $("#merchant_id").val(),
            "merchant_name": $("#merchant_name").val(),
            "product": $("#product").val(),
            "information_update_time": $("#information_update_time").val(),
            "risk_level": $("input[name='risk_level']:checked").val(),
            "information_audit_status": $("input[name='information_audit_status']:checked").val(),
            "audit_time": $("#audit_time").val(),
            "opening_status": $("input[name='opening_status']:checked").val(),
            "opening_time": $("#opening_time").val()
        }, function (data) {
            if (data) {
                alert("修改成功！");
                location.href = "/getMerchant"
            } else {
                alert("修改失败！");
            }
        }, "json");
    })
</script>
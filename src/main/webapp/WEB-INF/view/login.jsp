<%--
  Created by IntelliJ IDEA.
  User: you
  Date: 2018/11/15
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.1.min.js"></script>

</head>

<body>
帐号<input type="text" name="name" id="name">密码<input type="password" name="pwd" id="pwd">
<button id="btn">登录</button>
</body>
</html>
<script>
    $("#btn").click(function () {

        $.ajax({
            url: "/fengmai/login",
            data: {
                'name': $("#name").val(),
                'pwd': $("#pwd").val()
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data) {
                    location.href = "toList";
                } else {
                    alert("用户名或密码错误请重新输入!");
                    location.reload();
                }
            }
        })
    })
</script>
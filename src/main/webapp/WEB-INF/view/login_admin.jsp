

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="../merchant/css/normalize.css" rel="stylesheet">

	<link href="../merchant/css/login.css" rel="stylesheet">
	
	
	
	<script src="../merchant/js/jquery-1.8.3.min.js"></script>

	</head>
<body>
<div class="main">
	<div class="header flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">NovoxPay</label>
			<label class="beta-label">BETA</label>
		</div>
		<div class="header-right">
		</div>
	</div>
	<div class="main-login">
		<form class="form-horizontal" action="/toLogin.do" method="post" id="form_login">
		<div class="main-bg"></div>
		<div class="main-content flex-v">
			<label class="user-login-label">
				用户登录
				<em style="font-size: 14px;color: red;" id="error_tips"></em>
			</label>
			<div class="input-container flex">
				<input id="merchantid" name="email" class="input-content" placeholder="邮箱" />
				<span id="mer_id_tip" class="input-icon input-icon-user"></span>
			</div>
			<br><br><br>
			<div class="input-container flex">
				<input id="password" type="password" name="password" class="input-content" placeholder="密码" />
				<span class="input-icon input-icon-pwd"></span>
			</div>
<br><br>
			<a id="login" class="login-btn">登录</a>
		</div>
		</form>
	</div>
</div>
	<script>
	
	$("#login").click(function(){debugger;
		  $.ajax({
	         url: "../manager/login_admin.do",
	         type: "POST",
	         async: false,
	         data: $("#form_login").serialize(), //表单数据序列化, 可以对form表单进行序列化，从而将form表单中的所有参数传递到服务端。
	         success: function (data) {
	             //json字符串转为json对象
	             if (data == 1) {
	                 window.location.href = "../manager/admin.do";
	             } else {
	                 alert("账户名或密码错误！");
	             }
	         },
	         error: function (e) {
	             alert("请求出错！");
	         }
	     }); 
		
		
	});
	
	</script>
</body>
</html>

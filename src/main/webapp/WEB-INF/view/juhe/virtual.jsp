<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎 - 支付平台 </title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link href="../merchant/css/normalize.css" rel="stylesheet">

	<link href="../merchant/css/login.css" rel="stylesheet">
	
	<script src="../merchant/js/jquery-1.8.3.min.js"></script>
	
	
</head>
<body>



<div class="main">
	<!-- <div class="header flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">NovoxPay</label>
			<label class="beta-label">BETA</label>
		</div>
		<div class="header-right">
		</div>
	</div> -->
	<div class="main-login">
		<form class="form-horizontal" action="../juhe/juhetest" method="post" id="form_login">
		<div class="main-bg"></div>
		<div class="main-content flex-v">
			<label class="user-login-label">
				VI付费链接
				<em style="font-size: 14px;color: red;" id="error_tips"></em>
			</label>
			<div class="input-container flex">
				<input id="password" type="text" name="txnAmt" class="input-content" placeholder="充值金额(元)" />
			</div>
<br><br>
			<input type="submit"  value="确认" id="login" class="login-btn">
		</div>
		</form>
	</div>
</div>










</body>
</html>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- <link type="image/x-icon" rel="shortcut icon" href="./images/favicon.ico"> -->
	<link href="/version2/css/normalize.css" rel="stylesheet">
	<link href="/version2/css/login.css" rel="stylesheet">
	<script src="/js/libs/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="main">
	<div class="header flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">YourSite</label>
			<label class="beta-label">BETA</label>
		</div>
		<div class="header-right">
			<a href="javascript:void(0);" class="language-title">中文</a>
			<span class="border-right"></span>
			<a href="javascript:void(0);" class="language-title">繁体</a>
			<span class="border-right"></span>
			<a href="javascript:void(0);" class="language-title">English</a>
		</div>
	</div>
	<div class="main-login">
		<form class="form-horizontal" action="/index/login" method="post" id="form_login">
		<div class="main-bg"></div>
		<div class="main-content flex-v">
			<label class="user-login-label">
				用户登录
				<em style="font-size: 14px;color: red;" id="error_tips"></em>
			</label>
			<div class="input-container flex">
				<input id="mer_id" name="mer_id" class="input-content" placeholder="商户号" />
				<span id="mer_id_tip" class="input-icon input-icon-user"></span>
			</div>
			<div class="input-container flex">
				<input id="username" name="username" class="input-content" placeholder="邮箱账号" />
				<span id="email_tip" class="input-icon input-icon-email"></span>
			</div>
			<div class="input-container flex">
				<input id="password" type="password" name="password" class="input-content" placeholder="密码" />
				<span class="input-icon input-icon-pwd"></span>
			</div>
            <div class="radio-container flex">
                <span>验证方式：</span>
                <input id="captcha_email" type="radio" name="captcha_type" value="0" checked />&nbsp;邮箱验证
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input id="captcha_phone" type="radio" name="captcha_type" value="1" />&nbsp;手机验证
            </div>
			<div id="email-captcha-div" class="message-container flex message-show">
				<input id="emailMessage" name="email_captcha" class="input-content-message" placeholder="邮箱验证码" />
				<a id="send_email_captcha" href="javascript:void(0);" class="message-send">发送验证码</a>
			</div>
            <div id="phone-captcha-div" class="message-container flex message-hide">
                <input id="phoneMessage" name="phone_captcha" class="input-content-message" placeholder="手机验证码" />
                <a id="send_phone_captcha" href="javascript:void(0);" class="message-send">发送验证码</a>
            </div>
			<a id="loginBtn" href="javascript:void(0);" class="login-btn">登录</a>
		</div>
		</form>
	</div>
</div>
<script src="/version2/js/login.js" ></script>
</body>
</html>
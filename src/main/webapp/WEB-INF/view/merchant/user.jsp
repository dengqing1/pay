<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎 - 支付平台 - ACE 3</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="./merchant/css/normalize.css" rel="stylesheet">
	<link href="./merchant/css/header.css" rel="stylesheet">
	<link href="./merchant/css/page.css" rel="stylesheet">
	<link href="./merchant/css/liMarquee.css" rel="stylesheet">
	<link href="./merchant/css/currentAccount.css" rel="stylesheet">
	<link href="./merchant/css/welcome.css?20190108" rel="stylesheet">
	<link href="./merchant/css/pwdModified.css?20190109" rel="stylesheet">
	
	<link href="./merchant/css/font-awesome.min.css" rel="stylesheet">
	<link href="./merchant/css/daterangepicker.min.css" rel="stylesheet">
	
	
	<script src="./merchant/js/jquery-1.8.3.min.js"></script>
	<script src="./merchant/js/moment.min.js"></script>
	<script src="./merchant/js/ckeditor.js"></script>
	<script src="./merchant/js/pwdModified.js?v=20190109"></script>
	<script src="./merchant/js/logout.js"></script>	
	</head>
<body>
<div class="top-header">
	<div class="header-container flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">丰迈</label>
<!-- 			<label class="beta-label">BETA</label> -->
		</div>
		<div class="header-right flex flex-align-center">
			<p class="header-right-welcome">欢迎&nbsp;${user.name}</p>
			<a class="header-right-logout" href="javascript:void(0);">退出</a>
		</div>
	</div>
</div>
<div class="main">
    <div class="flex">
		<div class="main-logo">
			<label class="header-title">支付平台</label>
			<label class="header-english">PAYMENT PLATFORM</label>
		</div>
		<div class="main-logo">
		</div>
		            </div>
	<div class="flex-v">
        <label class="separate-line"></label>
	</div>
	<div class="main-content flex"> 		<div class="main-left" style="height: 594px;">

<!--   		  <div class="first-header flex flex-align-center"> -->
<!--                <div class="first-header-dot"></div> -->
<!--                <a class="first-header-text" href="/index/channel?name=merchant">系统管理</a> -->
<!--            </div> -->
           
         	<div class="first-header flex flex-align-center">
	            <div class="first-header-dot"></div>
	            <a class="first-header-text" href="javascript:void(0);">欢迎</a>
        	</div>
        	
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./merchant.html">欢迎</a>
          </div>
          
         <div class="second-header flex flex-align-center second-header-select">
	          <div class="second-header-dot"></div>
	          <a class="second-header-text" href="./user.html">修改密码</a>
         </div>
              <div class="first-header flex flex-align-center">
         <div class="first-header-dot"></div>
       		<a class="first-header-text" href="javascript:void(0);">账务</a>
    	 </div>
    	 
    	 
         <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               	<a class="second-header-text" href="./moneyHistory.html">钱流水</a>
      	 </div>
      	 
      	 
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./orderHistory.html">交易查询</a>
          </div>
          
          
          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./withdrawHistory.html">账务查询</a>
          </div>

          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./balanceLogs.html">调账查询</a>
          </div>          
          
           <div class="second-header flex flex-align-center ">
               
          <div class="first-header-dot"></div>
            <a class="first-header-text" href="javascript:void(0);">代付</a>
       	  </div>
       	  
       	  
           <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifu.html">批量代付</a>
           </div>
           
			<div class="second-header flex flex-align-center  ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifuStatement.html">账目查询</a>
            </div> 
        </div>

<div class="main-right">
	<div class="welcome-container flex flex-align-center">
		<p class="right-title position-welcome">欢迎您：</p>
		<p class="account-name">${obj.merShortName}</p>
		<p class="account-name">${obj.merId}</p>
	</div>
	<form id="form" class="form-horizontal" method="post" action="">
		<input type="hidden" name="task" value="update_password"/>
		<div class="input-container input-container-originPwd flex flex-align-center">
			<p class="right-title position-title">原密码：</p>
			<div class="right-input-container">
				<input type="password" name="old_password" class="right-input" />
			</div>
			<p class="position-title" id="old_password_tip" style="width: 210px;color:red;font-size: 14px; opacity: 0.85; text-align:left; margin-left: 10px;"></p>
		</div>
		<div class="input-container flex flex-align-center">
			<p class="right-title position-title">新密码：</p>
			<div class="right-input-container">
				<input type="password" name="new_password" class="right-input" />
			</div>
			<p class="position-title" id="new_password_tip" style="width: 210px;color:red;font-size: 14px; opacity: 0.85; text-align:left; margin-left: 10px;"></p>
		</div>
		<div class="input-container flex flex-align-center">
			<p class="right-title position-title">确认新密码：</p>
			<div class="right-input-container">
				<input type="password" name="re_password" class="right-input" />
			</div>
			<p class="position-title" id="re_password_tip" style="width: 210px;color:red;font-size: 14px; opacity: 0.85; text-align:left; margin-left: 10px;"></p>
		</div>
		<div class="input-container flex flex-align-center">
			<p class="right-title position-title">邮箱验证码：</p>
			<div class="right-input-container">
				<input name="emailCode" type="text" class="right-input right-input-email" />
			</div>
			<div class="send-message-container"><a class="right-title sendMessageBtn" href="javascript:void(0);" id="send_captcha"
			   data="${user.email}" data-tip_email="${user.email}">发送验证码</a></div>
			<p class="position-title" id="sendEmain_tip" style="width: 210px;color:red;font-size: 14px; opacity: 0.85; text-align:left; margin-left: 10px;"></p>
		</div>
		<p class="ace-state-help" id="send_tip"></p>
		<a class="submit-btn" href="javascript:void(0);"  
			style="background:url(./merchant/image/submit_bg.png) no-repeat 100% 100%;"
		>提交</a>
	</form>
</div>


    </div>
</div>
</body>
</html>
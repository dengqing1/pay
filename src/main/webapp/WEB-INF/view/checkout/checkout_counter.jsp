<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<title>收银台</title>
	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<style>
		*{
			margin:0;padding:0;
		}
		div{
			border:1px solid #c0c0c0;

		}

		#wb{
			background-color:#fff;
			width:80%;
			height:85%;
			position:absolute;
			top:80px;
			left:10%;
			border: none;

		}
		.yh{
			position:relative;
			left:40px;
			margin-left:40px;
			margin-right:140px;
			margin-top:3px;
			height:34px;
			width:180px;
			align-items:center;
			justify-content:center;
		}
		.dx{
			position:relative;
			top:31px;
			left: 60px;
		}
		#nb{
			width:98%;
			height:100%;
			margin-left:1%;
			border:none ;
			margin-bottom: 15px;
		}
		.nba{

			margin-left:14%;
			padding:20px;
			height:30px;
			width:250px;
			border: none;
			font-size:10px;
		}
		.nb-top{
			/*padding:10px;*/
			border: none;
			padding-left:10%;
		}
		.nav{
			background: #ffffff;
		}
		#bt{
			background-color:#fff;
			width:80%;
			height:80px;
			position:absolute;
			top:10px;
			left:10%;
			font-family:"Î¢ÈíÑÅºÚ";
			font-weight:bold;
			background-color:#F4F4F4;
			border: none;
		}
		.btn-top{
			position: relative;
		}
		.btn-default {
			background-color: #5C9BD4;
			width: 200px;
			height: 35px;
			position: absolute;
			top:550px;
			left: 42%;
			display: inline-block;
			padding: .3em .5em;
			border-radius: .3em;
			text-align: center;
			color: white;
			font-weight: bold;
		}

		.btn-default:active{
			box-shadow: .05em .1em .2em rgba(0,0,0,.6) inset;
			background: #bbb;
		}
		#cz{
			/*background-color:;*/
			width:150px;
			height:40px;
			position:absolute;
			top:480px;
			left:800px;
		}
		#yd{
			position:absolute;
			top:30px;
			left:650px;
		}
		#rm{
			position:absolute;
			top:25px;
			left:180px;
			border: none;
		}

		#dd{
			height: 100%;
			background-color:#c0c0c0;

		}

		img{
			max-width:100%;
		}
		body{
			font:normal 100% Arial, Helvetica, sans-serif;
		}
		h1{
			font-size:1.5em;
		}
		small{
			font-size:0.875em;
		}
		.main{
			float:right;
			width:70%;
		}
		.leftBar{
			float:left;
			width:25%;
		}
		table
		{
			table-layout:fixed;
		}
		td{
			white-space:nowrap;
			width:20px;
		}

	</style>
	<script>
		$(function(){
			$("apiCode").click(function(){
				var var1=$(".aa").val();
				alert(var1);
			});

			$("input[type='radio']").click(function(){
				var $btn = $(this);
				$("input[name='apiCode']").val($btn.attr("apiCode"));
				$("input[name='defaultBank']").val($btn.attr("apiCode"));

			});


		});
		function applay(){
			var $btns = $("input[type='radio']");
			var ok = false;
			for(var i = 0 ; i < $btns.length ; i++){
				if($btns[i].checked){
					ok = true;
					break;
				}
			}
			if(ok){
				$("#form").submit();
			}else{
				alert("清选择银行");
			}
		}
	</script>
</head>
<body  id="dd">
<form action="../../pay/AliGateWay.html" method="post" id="form">
	<input type="hidden" name="defaultBank" value=""/>
	<input type="hidden" name="apiCode" value=""/>
	<input type="hidden" name="OrederId" value="re_20190411_135151310_1020254631"/>
	<div id="bt">
		<div class="bt-top">
			<div id="rm"><span > <font color="#999">订单编号:</font> <font color="crimson" size="4px">re_20190411_135151310_1020254631</font></span> <span color="#999"><font color="#999">订单金额:</font>  <font color="crimson" size="4px">500</font><font color="#999">元</font> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <font color="#999">应付金额：</font><font color="crimson" size="4px">499.98</font><font color="#999">元</font></span></div>

		</div>

	</div>
	<div id="wb">
		<div id="nb">
			<div class="nba"><span><font color="#999" size="5px">请选择支付方式:</font> </font></span></div>
			<div class="nb-top">
				<!--<div class="nba"><span><font color="#999" size="4.5px" th:text="${Amount}">请选择支付方式:</font> </font></span></div>-->
				<table width="80%" height="60%" >
					<tr>
						<td><input type="radio" name="instId" value="BJBANK"class="dx" apiCode="bjbanknucc103"/><div class="yh" ><img src="../img/beijing1.png"/></div></td>
						<td><input type="radio" name="instId" value="BJRCB"class="dx" apiCode="bjrcbnucc103"/><div class="yh"><img src="../img/beinong1.png"/></div></td>
						<td><input type="radio" name="instId" value="CSRCB"class="dx" apiCode="csrcbnucc103"/><div class="yh"><img src="../img/chang1.png"/></div></td>
					</tr>
					<tr>
						<td><input type="radio" name="instId" value="FDB"class="dx" apicode="fdbnucc103"/><div class="yh"><img src="../img/fu1.png"/></div></td>
						<td><input type="radio" name="instId" value="ICBC"class="dx"apiCode="icbc105"/><div class="yh"><img src="../img/gongshang1.png"/></div></td>
						<td><input type="radio" name="instId" value="CEB"class="dx"apiCode="cebnucc103"/><div class="yh"><img src="../img/guangda1.png"/></div></td>

					</tr>
					<tr>
						<td><input type="radio" name="instId" value="HZCB"class="dx"apiCode="hzcbnucc103"/><div class="yh"><img src="../img/hang1.png"/></div></td>
						<td><input type="radio" name="instId" value="HXBANK"class="dx"apiCode="hxbanknucc103"/><div class="yh"><img src="../img/huaxia1.png"/></div></td>
						<td><input type="radio" name="instId" value="CCB"class="dx"apiCode="ccb103"/><div class="yh"><img src="../img/jianshe1.png"/></div></td>

					</tr>
					<tr>
						<td><input type="radio" name="instId" value="GDB"class="dx"apiCode="gdbnucc103"/><div class="yh"><img src="../img/guangfa1.png"/></div></td>
						<td><input type="radio" name="instId" value="NJCB"class="dx"apiCode="njcbnucc103"/><div class="yh"><img src="../img/nan1.png"/></div></td>
						<td><input type="radio" name="instId" value="NBBANK"class="dx"apiCode="nbbanknucc103"/><div class="yh"><img src="../img/ning1.png"/></div></td>

					</tr>
					<tr>
						<td><input type="radio" name="instId" value="SPABANK"class="dx"apiCode="spabanknucc103"/><div class="yh"><img src="../img/pingan1.png"/></div></td>
						<td><input type="radio" name="instId" value="COMM"class="dx"apiCode="commnucc103"/><div class="yh"><img src="../img/jiaotong1.png"/></div></td>
						<td><input type="radio" name="instId" value="SHBANK"class="dx"apiCode="shbanknucc103"/><div class="yh"><img src="../img/shanghai1.png"/></div></td>

					</tr>
					<tr>
						<td><input type="radio" name="instId" value="WJRCB"class="dx"apiCode="wjrcbnucc103"/><div class="yh"><img src="../img/wu1.png"/></div></td>
						<td><input type="radio" name="instId" value="CIB"class="dx"apiCode="cib102"/><div class="yh"><img src="../img/yingye1.png"/></div></td>
						<td><input type="radio" name="instId" value="PSBC"class="dx"apiCode="psbcnucc103"/><div class="yh"><img src="../img/youzheng1.png"/></div></td>

					</tr>
					<tr>
						<td><input type="radio" name="instId" value="BOC"class="dx"apiCode="boc102"/><div class="yh"><img src="../img/zhongguo1.png"/></div></td>
						<td><input type="radio" name="instId" value="CMB"class="dx"apiCode="cmb103"/><div class="yh"><img src="../img/zhaoshang1.png"/></div></td>
					</tr>
				</table>
			</div>
		</div>
		
	</div>
	<div class="btn-top">
		<div class="btn-group btn-group-justified" role="group" aria-label="...">
			<div class="btn-group" role="group">
				<button type="button" onclick="applay()" class="btn-default">确认支付</button>
			</div>
		</div>
	</div>
</form>
</body>
</html>

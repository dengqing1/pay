<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script> -->

<!--  <script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script> -->
<script type="text/javascript" src="http://static.runoob.com/assets/qrcode/qrcode.min.js"></script>

<script type="text/javascript" src="../js/jquery/jquery-3.1.1.js"></script> 
<!-- <script type="text/javascript" src="jquery.qrcode.min.js"></script>  -->


<script type="text/javascript">
 	/* var app = angular.module("weixinpay",[]);
 	app.controller("payController",function($scope,$http){
 		//生成二维码
 		$scope.createNative = function(){
 			$http.get("NativeServlet").success(function(response){
 				if(response.return_code =="SUCCESS"){
					var ewm = qrcode("10","H");//参数1: 二维码大小 2-10  参数2:容错级别
 					ewm.addData(response.code_url);
  					ewm.make();
  					document.getElementById("qr").innerHTML=ewm.createImgTag();
  					//显示订单号
  					$scope.out_trade_no  = response.out_trade_no;
  					//查询订单状态
  					//checkPayStatus($scope.out_trade_no);
  				}else{
  					alert("请求失败！！！");
 				}
  			});
 		}
		
  		checkPayStatus = function(out_trade_no){
  			$http.get("PayStatusCheckServlet?out_trade_no="+out_trade_no).success(function(response){
  				if(response.trade_state=="SUCCESS"){
  					location.href="success.html"
  				}else{
 					alert("交易出错!!!");
  				}
  			});
  		}
  	}); */
 </script> 
<script>
    window.onload =function(){
    	
    	
    	$.ajax({
            url: "NativeServlet.do",
            data: null,
            type: "post",
            dataType: "json",
            success: function (response) {
            	if(response.return_code =="SUCCESS"){
					 var qrcode = new QRCode(document.getElementById("qrcode"), {
				            width : 96,//设置宽高
				            height : 96
				        });
				        qrcode.makeCode("http://www.baidu.com");
				       // qrcode.makeCode(response.code_url);
				        document.getElementById("send").onclick =function(){
				            qrcode.makeCode(document.getElementById("getval").value);
				        }
				        
				        document.getElementById("out_trade_no").innerHTML=response.out_trade_no;
	 					//查询订单状态
	 					checkPayStatus(response.out_trade_no);
				        
				}else{
					alert("请求失败！！！");
				}
            }
        })
    	
    	
    	
    
    	 checkPayStatus = function(out_trade_no){
 			
 				
 				$.ajax({
 		            url: "PayStatusCheckServlet.do",
 		            data: {"out_trade_no" : out_trade_no},
 		            type: "post",
 		            dataType: "json",
 		            success: function (response) {
 				
 				
		 				if(response.trade_state=="SUCCESS"){
		 					alert("交易");
		 					//location.href="success.html"
		 				}else{
							alert("交易出错!!!");
		 				}
 		            }
 				});
 		}
    
    }
    
    
 

</script>

</head>
<body ng-app="weixinpay" ng-controller="payController" ng-init="createNative()">
	请扫码支付<br>
	<div id="qr"></div>
	订单号：<label id="out_trade_no"></label>
	
	
	
	<div id="qrcode">
</div>

<!-- <input type="text" id="getval"/> -->
] <button id="send">点击更换验证码</button>
	
	
	
	
</body>
</html>


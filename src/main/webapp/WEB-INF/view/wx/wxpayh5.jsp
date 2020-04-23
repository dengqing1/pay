<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
 <!DOCTYPE html>  
<html>  
    <head>  
    <script src="js/jquery/jquery-3.1.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>微信网页支付</title>  
        
        <script type="text/javascript">  
       /*  function pay() {
        	alert("h5");
            if (navigator.userAgent.indexOf("MicroMessenger") != -1) {
                pay_weixin();//微信浏览器支付
            } else {
                pay_other();//其他浏览器H5支付
            }
        }
         
        function pay_other() {
            var url = "/h5pay/orders_other.do"
            $.get(url,function(result) {
                location.href = result.mweb_url;
            });
        }
        function pay_weixin() {
        	//公众号支付方法
        } */

        </script>  
    </head>  
    <body>  
          <!--  <input type="button" value="h5支付" onclick="pay">-->
          <form action="https://wxpay.wxutil.com/mch/pay/h5.v2.php" id="xx"></form>
    </body>  
</html>
 <script type="text/javascript"> 
 
 $("#xx").submit();
 </script>
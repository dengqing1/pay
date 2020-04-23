<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

/*
 * 点击支付
 */
function patMoney(par){debugger;
	//var bId = $(par).parent().find("input").val();
	var bId="123456";
		window.location.href =  "../wx/weixinh5.do?bId="+bId; 
		
};


</script>

</head>
<body>
<hr>
 <button onclick="patMoney(this)" >点击支付</button>
 
 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/jquery/jquery-3.1.1.js"></script> 



<script type="text/javascript">

var t1 = 0;
/*
 * 点击支付
 */
function patMoney(par){
		var bId = $(par).parent().find("input").val();
	top.layer.open({
		area : [ '300px', '300px' ],
		type : 2,
		closeBtn : false,
		title : false,
		shift : 2,
		shadeClose : true,
		content : 'page/weixin/qrcode?paramMap.bId=' + bId,
		end : function (){
			window.clearInterval(t1);
		}
	});
	// 支付进行时，会一直轮询
  	t1 = window.setInterval("modifyStatus('"+ bId +"')",3000);
};

function modifyStatus(bId){
	var url = 'page/weixin/haPay';
	//轮询是否已经付费
	$.ajax({
		type : 'post',
		url : url,
		data : {"paramMap.bId" : bId},
		dataType: "json",
		cache : false,
		async : true,
		success : function(res) {
			// 我的数据返回的是状态1,不明看controller
			// 当该状态为1时，关闭模态框，刷新网页
			if(res.result == "1"){
				top.layer.closeAll();
				window.clearInterval(t1);
				location.reload();
			}
		},
		error : function() {
			layer.msg("执行错误！", 8);
		}
	});
}



</script>

</head>
<body>


 <button onclick="patMoney(1)" >点击</button>
	
	
	
	
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css" />
<title>冻结资金</title>
</head>
<body>
	<form class="layui-form" style="width: 40%;padding-top: 20px;">
		<div class="layui-form-item">
		    <label class="layui-form-label">商户号</label>
		    <div class="layui-input-block">
		      <input type="text" name="merchantid" required id="input_merId" lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">商户名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="merchantname" readonly="readonly" id="input_merchantName" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">账户余额(元)</label>
		    <div class="layui-input-block">
		      <input type="text" name="merchantname" readonly="readonly" id="input_available" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">已冻结资金(元)</label>
		    <div class="layui-input-block">
		      <input type="text" name="blockBalance" readonly="readonly" id="input_balance" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">冻结金额(元) </label>
		    <div class="layui-input-block">
		      <input type="text" name="dongjie" id="input_dongjie" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 
		  <div class="layui-form-item">
		    <label class="layui-form-label">密码</label>
		    <div class="layui-input-block">
		      <input type="text" name="mima" id="input_mima" required  lay-verify="required" autocomplete="off" class="layui-input">
		    </div>
		 </div>
		 
		 <!-- <div class="layui-form-item">
		    <label class="layui-form-label">备注</label>
		    <div class="layui-input-block">
		      <input type="text" required readonly="readonly"  autocomplete="off" class="layui-input">
		    </div>
		 </div> -->
		 <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formDemo" id="button">提交</button>
		    </div>
		 </div>
	</form>
	<script src="../static/admin/js/bootbox.js"></script>
	<script type="text/javascript">
		//显示内容
		$("#input_merId").keyup(function(){
			var id = $("#input_merId").val();
		//	if(id == "600000000000002"){
				$.ajax({
	   		        url: "selectByContent.do",
	   		        type: "POST",
	   		        data:{merchantid:id},
	   		        dataType: "json",
	   		        success: function (result) {
	   		        	$("#input_merchantName").val(result.object.merchantname);
	   		        	$("#input_available").val((result.object.balanceAvailable/100).toFixed(2));
	   		        	$("#input_balance").val((result.object.blockBalance/100).toFixed(2));
	   		        }
	   		    })
		/*	}else{
				$("#input_merchantName").val("");
		        $("#input_available").val("");
		        $("#input_balance").val("");
			}*/
		 });
	
		
		layui.use(['form'], function() {
			var form = layui.form();
			form.render();
			//监听提交
			form.on('submit(formDemo)', function(data) {
		   			var id =  $("#input_merId").val();
		   			var name =  $("#input_merchantName").val();
		   			//账户余额
		   			var available = $("#input_available").val();  
		   		    //已冻结资金
		   			var ff = $("#input_balance").val();
		   		    //冻结金额
		   		    var dongjie = $("#input_dongjie").val();
		   		     //已冻结资金>账户余额
		   			if(parseFloat(dongjie) > parseFloat(available)){
		   				alert("余额不足");
		   			}else{
		   				
		   				//加一层密码
		   				if($("#input_mima").val() != "654321"){
		   					alert("密码不正确！");
		   					return ;
		   				}else{
				   			$.ajax({
				   		        url: "saveFreeze.do",
				   		        type: "POST",
				   		        data:{balance:available,ydjzj:ff,dongjie:dongjie,id:id,name:name},
				   		        dataType: "json",
				   		        success: function (result) {
				   		            alert("保存成功!");
				   		            location.reload();
				   		        }
				   		    })
		   				}
		   			}
			});
		});
		
		
	</script>
</body>
</html>
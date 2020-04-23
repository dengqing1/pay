<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>调帐</title>
<style>
th{
	text-align:center
}
.addtiaozang{
	text-align:right;
}
</style>
</head>
<body>
	<!--用户添加的模态框 -->
	<div class="modal fade" id="oplogsAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="oplogsAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">商户号</label>
			    <div class="col-sm-10">
			      <input type="text" name="merId" class="form-control" id="merId_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">金额(单位:元)</label>
			    <div class="col-sm-10">
			      <input type="text" name="txnamt" class="form-control" id="txnamt_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			   <div class="form-group">
			    <label class="col-sm-2 control-label">渠道简称:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="channelabbr" id="channelAbbr_add_input">
			      		<option>--请选择--</option>
			      		<c:forEach items="${npayChannelslist}" var="item" varStatus="status1">
								<option value="${item.channelMerAbbr}" <c:if test="${bank==item.channelMerAbbr}">selected="true"</c:if>>${item.channelMerAbbr}</option>
						</c:forEach>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道商户号:</label>
			    <div class="col-sm-10" id="channelMerId_div">
			    	<select class="form-control" name="channelmerid" id="channelMerId_add_input" >
						<option>所有</option>
					</select>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			  
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">备注</label>
			    <div class="col-sm-10">
			      <input type="text" name="comment" class="form-control" id="comment_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="oplogs_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>调帐列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="col-md-11 addtiaozang">
			<button type="button" id="oplogs_add_btn" class="btn btn-primary">添加</button>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>商户号</th>
		               <th>商户名称</th>
		               <th>金额(单位:元)</th>
		               <th>时间</th>
		               <th>备注</th>
		             </tr>
					<c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.mer_id}</td>
		                     <td align="center">${l.mer_short_name}</td>
		                     <td align="center">${l.txnAmt}</td>
		                     <td align="center">${l.create_at}</td>
		                     <td align="center">${l.comment}</td>
		                  </tr>
		             </c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
	  	//弹出框
		$("#oplogs_add_btn").click(function(){
			$("#oplogsAddModal").modal({
				backdrop:"static"
			});
		});
	  	
	  	
	  	
		//修改
		$("#channelAbbr_add_input").change(function(){
			var channelAbbr = $("#channelAbbr_add_input").val();
			 $.ajax({
	   		        url: "../routes/routesEdit.do",
	   		        type: "GET",
	   		        data:{channelAbbr:channelAbbr},
	   		        dataType: "json",
	   		        success: function (result) {
			   		    	 var arr = eval(result.object);
			                //遍历返回的json数据加载到select标签;
			                var str="";
			                $.each(arr, function(key,value) {
			                	str += "<option value= "+ value.channelMerId +">"+ value.channelMerId +"</option>";
			                });
			                document.getElementById("channelMerId_add_input").innerHTML = str;
	   		        }
	   		    })
		});
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	debugger;
		var str='{"total_count": 1,"total_amount": 2}';      //这是一个json字符串''
		var ob=JSON.parse(str) ;  //返回一个新对象
		console.log(ob.total_count);
	  	
  		$("#oplogs_save_btn").click(function(){
  			var comment = $("#comment_add_input").val();
  			if(comment!=null&&comment!=""){
		  		$.ajax({
	   		        url: "saveOplogs.do",
	   		        type: "POST",
	   		        data:$("#oplogsAddModal_form").serialize(),
	   		        dataType: "json",
	   		        success: function (result) {
	   		            alert("保存成功!");
	   		            location.reload();
	   		        }
	   		    })
  			}else{
  				alert("备注不能为空!");
  			}
	  	});
	  	
    </script>
</body>
</html>
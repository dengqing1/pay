<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<script src="../new/laydate/laydate.js"></script>
<link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>公告列表</title>
<style>
th{
	text-align:center
}
.label{
	color: #337ab7
}
a.label:hover,a.label:focus {
	color: #337ab7
}
.states{
	line-height:42px;
}
#userBithday_add_input{
	margin:0 4px 0 10px;
}
.addgonggao{
	text-align:right;
}
</style>
</head>
<body>
	<!--用户添加的模态框 -->
	<div class="modal fade" id="noticeAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加公告</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="noticeAddModal_form">
			 <div class="form-group">
			    <label class="col-sm-2 control-label">公告标题</label>
			    <div class="col-sm-10">
			      <input type="text" name="title" class="form-control" id="title_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			 <div class="form-group">
			    <label class="col-sm-2 control-label">公告内容</label>
			    <div class="col-sm-10">
			      <input type="text" name="content" class="form-control" id="content_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">开始时间</label>
			    <div class="col-sm-10">
			      <input type="text" name="startTime" readonly="readonly" class="form-control" id="startTime_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">结束时间</label>
			    <div class="col-sm-10">
			      <input type="text" name="endTime" readonly="readonly" class="form-control" id="endTime_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">状态</label>
			    <div class="col-sm-4 states">
			      <input type="radio" name="status" value="0" title="关闭"/>关闭
			      <input type="radio" name="status" value="1" title="开启"/>开启
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="notice_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>


	<!--用户修改的模态框 -->
	<div class="modal fade" id="noticeUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">编辑公告</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="noticeUpdateModal_form">
	         <input type="hidden" name="id" id="id_Update_input"/>
			 <div class="form-group">
			    <label class="col-sm-2 control-label">公告标题</label>
			    <div class="col-sm-10">
			      <input type="text" name="title" class="form-control" id="title_Update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			 <div class="form-group">
			    <label class="col-sm-2 control-label">公告内容</label>
			    <div class="col-sm-10">
			      <input type="text" name="content" class="form-control" id="content_Update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">开始时间</label>
			    <div class="col-sm-10">
			      <input type="text" name="startTime" readonly="readonly" class="form-control" id="startTime_Update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">结束时间</label>
			    <div class="col-sm-10">
			      <input type="text" name="endTime" readonly="readonly" class="form-control" id="endTime_Update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">状态</label>
			    <div class="col-sm-4 states">
			      <input type="radio" name="status" id="state_gb" value="0" title="关闭"/>关闭
			      <input type="radio" name="status" id="state_kq" value="1" title="开启"/>开启
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="notice_update_btn">修改</button>
	      </div>
	    </div>
	  </div>
	</div>


	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>系统公告</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-11 addgonggao">
				<button class="btn btn-primary" id="export">新增公告</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>ID</th>
		               <th>公告标题</th>
		               <th>开始时间</th>
		               <th>结束时间</th>
		               <th>状态</th>
		               <th>更新时间</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.id}</td>
		                     <td align="center">${l.title}</td>
		                     <td align="center">${l.start_time}</td>
		                     <td align="center">${l.end_time}</td>
		                     <c:if test="${l.status==0}">
		                     	<td align="center">关闭</td>
		                     </c:if>
		                     <c:if test="${l.status==1}">
		                     	<td align="center">开启</td>
		                     </c:if>
		                     <td align="center">${l.updated_at}</td>
		                     <td width="10%" align="center">
		                        <a onclick="updateBut(${l.id})">修改</a>
		                      </td>
		                   </tr>
		             </c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
	  	//添加弹出框
		$("#export").click(function(){
			$("#noticeAddModal").modal({
				backdrop:"static"
			});
		});
	  	
	    function updateBut(id){
	    	$("#noticeUpdateModal").modal({
				backdrop:"static"
			});
	    	$.ajax({
   		        url: "findById.do",
   		        type: "POST",
   		        data:{id:id},
   		        dataType: "json",
   		        success: function (result) {
   		        	$("#id_Update_input").val(result.object.id);
   		        	$("#title_Update_input").val(result.object.title);
   		        	$("#content_Update_input").val(result.object.content);
   		        	$("#startTime_Update_input").val(result.object.start_time);
   		        	$("#endTime_Update_input").val(result.object.end_time);
   		        	if(result.object.status==0){
   		        		$("#state_gb").attr("checked",'checked');
   		        	}else{
   		        		$("#state_kq").attr("checked",'checked');
   		        	}
   		        }
   		    })
		}
	  	
		//编辑弹出框
	  	$("#update_but").click(function(){
	  		$("#noticeUpdateModal").modal({
				backdrop:"static"
			});
	  	});
		
	  	//保存
	  	$("#notice_save_btn").click(function(){
	  		$.ajax({
   		        url: "saveNotice.do",
   		        type: "POST",
   		        data:$("#noticeAddModal_form").serialize(),
   		        dataType: "json",
   		        success: function (result) {
   		            alert("保存成功!");
   		            location.reload();
   		        }
   		    })
	  	});
	  	
	  	//修改
	  	$("#notice_update_btn").click(function(){
	  		$.ajax({
   		        url: "saveNotice.do",
   		        type: "POST",
   		        data:$("#noticeUpdateModal_form").serialize(),
   		        dataType: "json",
   		        success: function (result) {
   		            alert("保存成功!");
   		            location.reload();
   		        }
   		    })
	  	});
	  	
	  	laydate.render({
		  elem: '#startTime_add_input'
		});
	  	
	  	laydate.render({
		  elem: '#endTime_add_input'
		});
	  	
	  	laydate.render({
		  elem: '#startTime_Update_input'
		});
		 	
		laydate.render({
		  elem: '#endTime_Update_input'
		});
	    
    </script>
</body>
</html>
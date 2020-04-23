<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>账户列表</title>
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
</style>
</head>
<body>
	<!--用户添加的模态框 -->
	<div class="modal fade" id="userAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加新账号</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="userAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">商户号</label>
			    <div class="col-sm-10">
			      <input type="text" name="merId" value="${user.merId}" class="form-control" id="userName_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text" name="name" value="${user.name}" class="form-control" id="userName_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">账号</label>
			    <div class="col-sm-10">
			      <input type="text" name="email" value="${user.email}" class="form-control" id="userBithday_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			      <input type="text" name="password" class="form-control" id="userPhone_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <input type="hidden" name="role" value="3" />
			  <input type="hidden" name="locked" value="0" />
			  <div class="form-group">
			    <label class="col-sm-2 control-label">手机</label>
			    <div class="col-sm-10">
			      <input type="text" name="phone" value="${user.phone}" class="form-control" id="userMoney_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="user_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>账户列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="user_form">
					<div class="col-md-4">
						<input type="text" name="merId" value="${parameter}" id="input_parameter" class="form-control" placeholder="请输入商户号/姓名/账号">
					</div>
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-3">
						<button type="button" id="search" class="btn btn-primary">查询</button>
					</div>
				</form>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary" id="user_add_btn">添加</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th width="10%">商户号</th>
		               <th>用户名</th>
		               <th>账号</th>
		               <th>用户手机号</th>
		               <th>最后更新时间</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.mer_id}</td>
		                     <td align="center">${l.name}</td>
		                     <td align="center">${l.email}</td>
		                     <td align="center">${l.phone}</td>
		                     <td align="center">${l.addTime}</td>
		                     <td width="10%" align="center">
		                     	<!-- <a href="">修改权限</a> -->
		                        <a href="editUser.do?id=${l.id}">修改</a>
		                        <a onclick="deletedd(${l.id})">删除</a>
		                      </td>
		                   </tr>
		             </c:forEach>
				</table>
		<!-- 分页 -->
		<div class="ui circular labels" style="text-align:center">
		    <a class="ui label">当前第 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</a>
		    <a class="ui label" hryf="">首页</a>
		    <c:if test="${pageInfo.hasPreviousPage}">
		    	<a class="ui label" onclick="pageClick(${pageInfo.pageNum-1 })">上一页</a>
		    </c:if>
		    
		    <c:if test="${pageInfo.hasNextPage}">
		    	<a class="ui label" onclick="pageClick(${pageInfo.pageNum+1 })">下一页</a>
		    </c:if>
		    <a class="ui label" onclick="pageClick(${pageInfo.pages})">末页</a>
		</div>
		<!-- 分页 end-->
			</div>
		</div>
	</div>
    <script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
    		//查询
    		$("#search").click(function(){
    			var parameter = $("#input_parameter").val();
    			location.href="findAllByUser.do?parameter="+parameter
    		});
    		
    		
    		//弹出框
    		$("#user_add_btn").click(function(){
    			$("#userAddModal").modal({
    				backdrop:"static"
    			});
    		});
    		
    		//新增
    		$("#user_save_btn").click(function(){
    			$.ajax({
	   		        url: "../merchants/saveMerUser.do",
	   		        type: "POST",
	   		        data:$("#userAddModal_form").serialize(),
	   		        dataType: "json",
	   		        success: function (result) {
	   		            alert("保存成功!");
	   		            location.reload();
	   		        }
	   		    })
    		});
    		
    		//删除
      		function deletedd(id){
      			$.ajax({
	   		        url: "delete.do",
	   		        type: "POST",
	   		        data:{id:id},
	   		        dataType: "json",
	   		        success: function (result) {
	   		            alert("删除成功!");
	   		            location.reload();
	   		        }
	   		    })
      		}
    		
    </script>
</body>
</html>
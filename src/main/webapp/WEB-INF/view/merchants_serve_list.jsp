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
<title>商户开通服务</title>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>商户开通服务</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="serve_form">
					<div class="col-md-4">
						<input type="text" name="merId" id="input_merId" class="form-control" placeholder="请输入商户号">
					</div>
					<div class="col-md-4">
						<input type="text" name="merShortName" id="input_merShortName" class="form-control" placeholder="请输入商户名称">
					</div>
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-3">
						<button type="button" id="search" class="btn btn-primary">查询</button>
					</div>
				</form>
			</div>
			<!-- <div class="col-md-4">
				<button class="btn btn-primary" id="export">导出所有商户</button>
			</div> -->
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th width="10%">商户号</th>
		               <th>商户名称</th>
		               <th>代付-渠道商户名缩写</th>
		               <th>审核状态</th>
		               <th>审核时间</th>
		               <th>开通状态</th>
		               <th>开通时间</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.mer_id}</td>
		                     <td align="center">${l.mer_short_name}</td>
		                     <td align="center">${l.mer_short_name}</td>
		                     <td align="center">审核通过</td>
		                     <td align="center">${l.mer_info_check_time}</td>
		                     <td align="center">已开通</td>
		                     <td align="center">${l.mer_open_time}</td>
		                     <td width="10%" align="center">
		                        <a href="editService.do?merId=${l.mer_id}">修改</a>
		                        <a onclick="deletedd(${l.mer_id})">删除</a>
		                      </td>
		                   </tr>
		             </c:forEach>
				</table>
		<!-- 分页 -->
		<div class="ui circular labels" style="text-align:center">
		    <a class="ui label">当前第 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</a>
		    <a class="ui label" onclick="pageClick(1)" >首页</a>
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
    			var merId = $("#input_merId").val();
    			var merShortName = $("#input_merShortName").val();
    			location.href="findAll.do?merId="+merId+'&merShortName='+merShortName
    		});
    		
    		//导出
    		$("#export").click(function(){
    			 location.href="../merchants/exportExcle.do";
    		});
    		
    		//分页函数
    		function pageClick(page){
    			$("#pa").val(page);
    			$("#serve_form").submit();
    		}
    		
    		//删除
      		function deletedd(merId){
      			$.ajax({
	   		        url: "delete.do",
	   		        type: "POST",
	   		        data:{merId:merId},
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
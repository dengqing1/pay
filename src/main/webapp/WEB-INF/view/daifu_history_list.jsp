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
<title>路由历史列表</title>
<style>
th{
	text-align:center
}
</style>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>代付路由历史操作记录</h1>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>ID</th>
		               <th>备注名</th>
		               <th>备份时间</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.id}</td>
		                     <td align="center">${l.name}</td>
		                     <td align="center">${l.updated_at}</td>
		                     <td width="10%" align="center">
		                        <a href="">回滚</a>
		                        <a onclick="deletedd(${l.id})">删除</a>
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
  		//删除
		function deletedd(id){
			$.ajax({
		        url: "deletehistory.do",
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
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
<title>异常订单列表</title>
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
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>订单状态查询</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="order_form">
					<div class="col-md-4">
						<input type="text" name="orderId" id="input_orderId" class="form-control" placeholder="请输入订单号">
					</div>
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-3">
						<button type="button" id="search" class="btn btn-primary">查询</button>
					</div>
				</form>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>创建日期</th>
		               <th>商户号</th>
		               <th>商户名称</th>
		               <th>渠道名称</th>
		               <th>订单金额</th>
		               <th>更新时间</th>
		               <th>状态编码</th>
		               <th>对账状态</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.create_at}</td>
		                     <td align="center">${l.merchantId}</td>
		                     <td align="center">${l.mer_name}</td>
		                     <td align="center">${l.channel_mer_abbr}</td>
		                     <td align="center">${l.txnAmt}</td>
		                     <td align="center">${l.update_at}</td>
		                     <td align="center">${l.status}</td>
		                     <td align="center">${l.statusDesc}</td>
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
			var orderId = $("#input_orderId").val();
			location.href="findAll.do?orderId="+orderId
		});
  		
		//分页函数
  		function pageClick(page){
  			$("#pa").val(page);
  			$("#order_form").submit();
  		}
    </script>
	

</body>
</html>
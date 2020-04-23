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
<title>通道账户余额</title>
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
				<h1>通道账户余额</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="merAccount_form">
					<div class="col-md-4">
						<input type="text" name="merId" value="${merId}" id="input_merId" class="form-control" placeholder="请输入渠道号">
					</div>
					<div class="col-md-4">
						<input type="text" name="merShortName" value="${merShortName}" id="input_merShortName" class="form-control" placeholder="请输入渠道名称">
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
		               <th width="10%">渠道号</th>
		               <th>渠道名称</th>
		               <th>当日入金</th>
		               <th>当日出金</th>
<!-- 		               <th>下游手续费</th> -->
<!-- 		               <th>待支付金额</th> -->
<!-- 		               <th>失败金额</th> -->
<!-- 		               <th>上游手续费</th> -->
<!-- 		               <th>实际金额</th> -->
		               <th>入金总额</th>
		               <th>出金总额</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.channel_mer_id}</td>
		                     <td align="center">${l.name}</td>

		                     <!-- 当日入金 -->
		                     <c:if test="${l.rujin==null}">
		                     	 <td align="center">0</td>
		                     </c:if>
		                     <c:if test="${l.rujin!=null}">
		                     	 <td align="center">${l.rujin}</td>
		                     </c:if>
		                     <!-- 当日出金 -->
		                     <c:if test="${l.chujin==null}">
		                     	 <td align="center">0</td>
		                     </c:if>
		                     <c:if test="${l.chujin!=null}">
		                     	 <td align="center">${l.chujin}</td>
		                     </c:if>
		                 
		                     <!-- 入金总额 -->
		                      <c:if test="${l.rujinsum==null}">
		                     	 <td align="center">0</td>
		                     </c:if>
		                     <c:if test="${l.rujinsum!=null}">
		                     	 <td align="center">${l.rujinsum}</td>
		                     </c:if>
		                     <!-- 出金总额 -->
		                      <c:if test="${l.chujinsum==null}">
		                     	 <td align="center">0</td>
		                     </c:if>
		                     <c:if test="${l.chujinsum!=null}">
		                     	 <td align="center">${l.chujinsum}</td>
		                     </c:if>
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
	<script type="text/javascript">
	//分页函数
		function pageClick(page){
			$("#pa").val(page);
			$("#merAccount_form").submit();
		}
	</script>
			
</body>
</html>
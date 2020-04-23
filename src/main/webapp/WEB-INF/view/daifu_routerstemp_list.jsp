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
<title>上传EXCEL</title>
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
.adddaifuluyou{
	text-align:right;
}
</style>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>代付路由上传</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<!-- <div class="col-md-11 adddaifuluyou">
			<button type="button" id="oplogs_add_btn" class="btn btn-primary">发布到正式环境</button>
		</div> -->
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>ID</th>
		               <th>商户ID</th>
		               <th>商户名</th>
		               <th>银行ID</th>
		               <th>银行名称</th>
		               <th>最小值</th>
		               <th>最大值</th>
		               <th>渠道代码</th>
		               <th>商户代码</th>
		             </tr>
					<c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.id}</td>
		                     <td align="center">${l.merchantId}</td>
		                     <td align="center">${l.mer_short_name}</td>
		                     <td align="center">${l.bankId}</td>
		                     <td align="center">所有银行</td>
		                     <td align="center">${l.min}</td>
		                     <td align="center">${l.max}</td>
		                     <td align="center">${l.channelAbbr}</td>
		                     <td align="center">${l.channelMerId}</td>
		                     <td align="center">${l.name}</td>
		                  </tr>
		             </c:forEach>
				</table>
				<!-- 分页 -->
				<div class="ui circular labels" style="text-align:center">
				    <a class="ui label">当前第 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</a>
				    <a class="ui label" href="../daifutemp/templist.do?page=1">首页</a>
				    <c:if test="${pageInfo.hasPreviousPage}">
				    	<a class="ui label" href="../daifutemp/templist.do?page=${pageInfo.pageNum-1}">上一页</a>
				    </c:if>
				    
				    <c:if test="${pageInfo.hasNextPage}">
				    	<a class="ui label" href="../daifutemp/templist.do?page=${pageInfo.pageNum+1}">下一页</a>
				    </c:if>
				    <a class="ui label" href="../daifutemp/templist.do?page=${pageInfo.pages}" >末页</a>
				</div>
				<!-- 分页 end-->
			</div>
		</div>
	</div>
	<script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
    </script>
</body>
</html>
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
<title>资金冻结查询</title>
<style>
th{
	text-align:center
}
#order_form> span{
	display:inline-block;
	padding-left:10px;
}
</style>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>冻结列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="mer_form">
					<div class="col-md-4">
						<input type="text" name="merId" id="input_merId" class="form-control" placeholder="请输入操作人">
					</div>
					<div class="col-md-4">
						<select class="form-control">
							<option>冻结</option>
							<option>解冻</option>
						</select>
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
		               <th>id</th>
		               <th>操作人</th>
		               <th>被操作帐号</th>
		               <th>金额(元)</th>
		               <th>备注</th>
		               <th>操作时间</th>
		             </tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
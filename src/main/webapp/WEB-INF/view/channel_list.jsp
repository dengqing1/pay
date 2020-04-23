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
<title>通道录入</title>
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
				<h1>通道录入</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="channel_form">
					<div class="col-md-4">
						<select name="gateway" id="input_merId" class="form-control">
							<option value="">所有</option>
							<option value="bank" <c:if test="${gateway=='bank'}">selected="true"</c:if>>网银</option>
							<option value="kuaijie" <c:if test="${gateway=='kuaijie'}">selected="true"</c:if>>快捷</option>
						</select>
					</div>
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-4">
						<select name="channelMerAbbr" id="input_merShortName" class="form-control">
							<option value="">所有</option>
							<option value="tf56" <c:if test="${channelMerAbbr=='tf56'}">selected="true"</c:if>>tf56</option>
							<option value="klt" <c:if test="${channelMerAbbr=='klt'}">selected="true"</c:if>>klt</option>
							<option value="yf" <c:if test="${channelMerAbbr=='yf'}">selected="true"</c:if>>yf</option>
						</select>
					</div>
					<div class="col-md-3">
						<button type="button" id="search" class="btn btn-primary">查询</button>
					</div>
				</form>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary" id="channel_add_btn">添加</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th width="10%">ID</th>
		               <th>支付网关</th>
		               <th>名字</th>
		               <th>渠道商户名缩写</th>
		               <th>渠道商户号</th>
		               <th>代理地址</th>
		               <th>费率类型</th>
		               <th>费率</th>
		               <th>最大入金金额</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.channel_id}</td>
		                     <td align="center">${l.gateway}</td>
		                     <td align="center">${l.name}</td>
		                     <td align="center">${l.channel_mer_abbr}</td>
		                     <td align="center">${l.channel_mer_id}</td>
		                     <td align="center">${l.proxyurl}</td>
		                     <td align="center">${l.fee_type}</td>
		                     <td align="center">${l.fee_amount}</td>
		                     <td align="center">${l.max_amount}</td>
		                     <td width="10%" align="center">
		                        <a href="edit.do?channelId=${l.channel_id}">修改</a>
		                        <a onclick="deleteChannel(${l.channel_id})">删除</a>
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
          			var gateway = $("#input_merId").val();
          			var channelMerAbbr = $("#input_merShortName").val();
          			location.href="findChannelAll.do?gateway="+gateway+'&channelMerAbbr='+channelMerAbbr
          		});
          		
          		//分页函数
          		function pageClick(page){
          			$("#pa").val(page);
          			$("#channel_form").submit();
          		}
          		
          		$("#channel_add_btn").click(function(){
          			location.href="edit.do"
          		});
          		
          	    //删除
          		function deleteChannel(channelId){
          			$.ajax({
    	   		        url: "deleteChannel.do",
    	   		        type: "POST",
    	   		        data:{channelId:channelId},
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
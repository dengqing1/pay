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
<title>商户信息查询</title>
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
				<h1>商户列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="mer_form">
					<div class="col-md-4">
						<input type="text" name="merId" value="${merId}" id="input_merId" class="form-control" placeholder="请输入商户号">
					</div>
					<div class="col-md-4">
						<input type="text" name="merShortName" value="${merShortName}" id="input_merShortName" class="form-control" placeholder="请输入商户名称">
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
		               <th>商户号</th>
		               <th>商户名称</th>
		               <th>产品</th>
		               <th>信息更新时间</th>
		               <th>风险等级</th>
		               <th>信息审核状态</th>
		               <th>审核时间</th>
		               <th>开通状态</th>
		               <th>开通时间</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.mer_id}</td>
		                     <td align="center">${l.mer_short_name}</td>
		                     <td align="center">${l.open_product_ids}</td>
		                     <td align="center">${l.update_time}</td>
		                     <c:if test="${l.mer_risk_level == 1}">
		                     	<td align="center">高</td>
		                     </c:if>
		                     <c:if test="${l.mer_risk_level == 2}">
		                     	<td align="center">中</td>
		                     </c:if>
		                     <c:if test="${l.mer_risk_level == 3}">
		                     	<td align="center">低</td>
		                     </c:if>
		                     <c:if test="${l.mer_check_status == 1}">
		                     	<td align="center">审核通过</td>
		                     </c:if>
		                     <td align="center">${l.check_time}</td>
		                     <c:if test="${l.mer_open_status == 1}">
		                     	<td align="center">已开通</td>
		                     </c:if>
		                     <c:if test="${l.mer_open_status == 0}">
		                     	<td align="center">未开通</td>
		                     </c:if>
		                     <td align="center">${l.open_time}</td>
		                     <td width="10%" align="center">
		                     	<a  href="javascript:void(0);"  onclick="show(${l.mer_id})">查看key</a>
		                        <a href="edit.do?merId=${l.mer_id}">修改</a>
		                        <a href="javascript:void(0);"  onclick="deletedd(${l.mer_id})">删除</a>
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
	
	
	
	<!--密码审核 -->
	<div class="modal fade" id="oplogsAddModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">审核</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="oplogsAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">请输入密码</label>
			    <div class="col-sm-10">
			      <input type="password" name="pwd" class="form-control" id="pwd">
			       <input type="hidden" name="id" class="form-control" id="id">
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
          			 location.href="export.do";
          		});
          		
          		//分页函数
          		function pageClick(page){
          			$("#pa").val(page);
          			
          			$("#mer_form").submit();
          		}
          		
          		//删除
          		function deletedd(merId){
          			if(window.confirm('你确定要删除吗？')){
	          			$.ajax({
	    	   		        url: "../merchants/deleteNpayMerInfo.do",
	    	   		        type: "POST",
	    	   		        data:{merId:merId},
	    	   		        dataType: "json",
	    	   		        success: function (result) {
	    	   		            alert("删除成功!");
	    	   		            location.reload();
	    	   		        }
	    	   		    })
          			}
          		}
          		
          		
          		
          	  //审核通过弹出框
        		function  show(id){
        	  		$("#id").val(id);
        			$("#oplogsAddModal").modal({
        				backdrop:"static"
        			}); 
        			
        			
        		/* 	$.ajax({
    	   		        url: "../merchants/details.do",
    	   		        type: "POST",
    	   		        data:{"merId":id},
    	   		        dataType: "json",
    	   		    	async : true,
    	   		        success: function (result) {
    	   		            alert(result.msg);
    	   		      		//location.replace(location);
    	   		        }, error:function(result){
    	   		        	alert(result.msg);
    	   		        }
    	   		    })
        			
        			 */
        			
        			
        	 	}  
          		
          		
        		$("#oplogs_save_btn").click(function(){
	          			if($("#pwd").val() =="654321"){
	          				$.ajax({
		    	   		        url: "../merchants/details.do",
		    	   		        type: "POST",
		    	   		        data:{"merId":$("#id").val()},
		    	   		        dataType: "json",
		    	   		    	success: function (result) {
		    	   		            alert(result.msg);
		    	   		      		location.replace(location);
		    	   		        }, error:function(result){
		    	   		        	alert(result.msg);
		    	   		        	location.replace(location);
		    	   		        }
		    	   		    })
	
	        			}else{
	        	            alert("密码不正确！");
	        	          	location.replace(location);
	        	        }
          		});
          		
          </script>
</body>
</html>
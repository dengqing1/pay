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
<title>商户对账查询</title>
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
				<h1>商户对账列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="merAccount_form" action="../merchants/statementList.do">
					
					<div class="col-md-3">
						<span>日期</span>
						 <input class="form-control" readonly="readonly" value="${date}" type="text" style="width:250px;display:inline-block;" name="date" id="date" placeholder="请选择时间"/>
					</div>
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-3">
						<span></span>
						<button type="button" id="search" class="btn btn-primary">查询</button>
					</div>
					
			       	<div class="col-md-3">
						<h4 class="btn btn-primary" id="myModalLabel">添加</h4>
					</div>
				</form>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th width="10%">id</th>
		               <th width="10%">日期</th>
		               <th width="10%">商户号</th>
		               <th>当日入金</th>
		               <th>待支付金额</th>
		               <th>失败金额</th>
		               <th>当日出金</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr bgcolor="#FFFFFF" >
		                     <td align="center">${l.staId}</td>
		                     <td align="center">${l.date}</td>
		                     <td align="center">${l.staMerchantId}</td>
		                     <td align="center">${l.golden}</td>
		                     <td align="center">${l.unpaid}</td>
		                     <td align="center">${l.nothing}</td>
		                     <td align="center">${l.withdraw}</td>
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




<!--用户添加的模态框 -->
	<div class="modal fade" id="oplogsAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" id="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加对账</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="oplogsAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">请选择时间</label>
			    <div class="col-sm-4">
			        <input class="form-control" readonly="readonly" type="text" style="width:250px;display:inline-block;" name="date" id="input_merId"/>
<!-- 			        <input  type="text" style="width:250px;display:inline-block;" name="date" id="date"/> -->
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" id="close1" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="oplogs_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>





		<script src="../new/laydate/laydate.js"></script>
		  <script src="../static/admin/js/bootbox.js"></script>
          <script type="text/javascript">
          
          
          
  	  	//弹出框
  		$("#myModalLabel").click(function(){
  	  		laydate.render({
  				elem: '#input_merId',
  				format: 'yyyy-MM-dd', //日期格式
  				fixed: true, //是否固定在可视区域
  	            type: 'date'
  			});
  			$("#oplogsAddModal").modal({
  				backdrop:"static"
  			});
  		});
          
  		laydate.render({
			elem: '#date',
			format: 'yyyy-MM-dd', //日期格式
            type: 'date'
		}); 
          
  		
          		//查询
          		$("#search").click(function(){
          		//	var date = $("#date").val();
          		//	location.href="findAllByAccount.do?date="+date+'&merShortName='+merShortName
          				$("#pa").val(1);
          			$("#merAccount_form").submit();
          		});
          		
          		//分页函数
         		function pageClick(page){
         			$("#pa").val(page);
         			
         			$("#merAccount_form").submit();
         		}
          		
          		
          		
          		
          		
          		$("#oplogs_save_btn").click(function(){
          			var date = $("#input_merId").val();
          			$.ajax({
    	   		        url: "statementAdd.do",
    	   		        type: "POST",
    	   		        data:{"date":date},
    	   		        dataType: "json",
    	   		        success: function (result) {
    	   		            if(result.code == 100){
    	   		            	alert("添加成功");
    	   		            	location.replace(location);
    	   		            }else{
    	   		            	alert(result.msg);
    	   		            	location.replace(location);
    	   		            }
    	   		        }
    	   		    })
          			
          			
          			
          			
          			
          			//location.href="statementAdd.do?date="+date;
          		});
          		
          		
          		
          		
          		
          		
          </script>
</body>
</html>
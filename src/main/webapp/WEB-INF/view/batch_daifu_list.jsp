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
<title>列表</title>
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
				<h1>批量代付列表</h1>
			</div>
		</div>
		
		<!-- 按钮 -->
		<div class="row">
			<br>
			<div class="col-md-8">
				<form id="merAccount_form" action="../daifu/daifulist.do" method="post">
					<div class="col-md-3">
						<span>商户号:</span>
						<input type="text" name="gatherMerchantId" value="${gatherMerchantId}" id="input_merId" class="form-control" placeholder="请输入商户号">
					</div>
					<div class="col-md-3">
						<span>商户订单号:</span>
						<input type="text" name="gatherMerOrderId" value="${gatherMerOrderId}" id="input_merShortName" class="form-control" placeholder="请输入商户订单号">
					</div>
					<div class="col-md-2 mt15">
						<span>审核状态:</span>
						<select class="form-control" name="gatherState" id="select_cplx">
							<option value="">所有</option>
							<option value="1" <c:if test="${gatherState=='1'}">selected="true"</c:if>>待审核</option>
							<option value="2" <c:if test="${gatherState=='2'}">selected="true"</c:if>>已审核</option>
							<option value="3" <c:if test="${gatherState=='3'}">selected="true"</c:if>>审核不通过</option>
						</select>
					</div>
					
					<input id="pa" type="hidden" name="page"  />
					<div class="col-md-3">
						<input type="submit" class="btn btn-primary" value="查询">
					</div>
				</form>
			</div>
		</div>
		
		
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5>
					<tr>
		               <th>商户号</th>
		               <th>商户名</th>
		               <th>商户订单号</th>
		               <th>银行卡号</th>
		               <th>姓名</th>
		               <th>手机号</th>
		               <th>银行</th>
		               <th>金额(CNY)</th>
		               <th>状态</th>
		               <th>创建时间</th>
		               <th>审核时间</th>
		               <th>来源</th>
		               <th>操作</th>
		             </tr>
		             <c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.gatherMerchantId}</td>
		                     <td align="center">${l.msName}</td>
		                     <td align="center">${l.gatherMerOrderId}</td>
		                      <td align="center">${l.gatherAccno}</td>
		                      <td align="center">${l.gatherCustomerName}</td>
		                      <td align="center">${l.gatherPhoneNo}</td>
		                      <td align="center">${l.bankName}</td>
		                     
		                     
		                     <td align="center">${l.gatherTxnAmt}</td>
		                     <c:if test="${l.gatherState==1}">
		                     	<td align="center">待审核</td>
		                     </c:if>
		                     <c:if test="${l.gatherState==2}">
		                     	<td align="center">审核通过</td>
		                     </c:if>
		                     <c:if test="${l.gatherState==3}">
		                     	<td align="center">审核不通过</td>
		                     </c:if>
		                     <td align="center">${l.gatherTime}</td>
		                     <td align="center">${l.gatherUpdateTime}</td>
		                     <td align="center">${l.gatherKey}</td>
		                     <c:if test="${l.gatherState==1}">
			                   <td width="10%" align="center">
<!--              					<a onclick="diag(${l.gatherId})" id="oplogs_add_btn">审核通过</a> -->
			                        <a onclick="show(${l.gatherId})">审核通过</a>
			                        <a onclick="updateShbtg(${l.gatherId})">审核不通过</a>
			                    </td>
		                     </c:if>
		                     <c:if test="${l.gatherState==2}">
			                     <td width="10%" align="center">
			                        <span>已审核</span>
<!-- 			                        <a onclick="order(${l.gatherId})">添加订单</a> -->
			                     </td>
		                     </c:if>
		                     <c:if test="${l.gatherState==3}">
			                     <td width="10%" align="center">
			                        <span>审核不通过</span>
			                      </td>
		                     </c:if> 
		                   </tr>
		             </c:forEach>
				</table>
				
<!-- 				 <a onclick="diag(1)">审核通过</a> -->
				
				<!-- 分页 -->
				<div class="ui circular labels" style="text-align:center">
				    <a class="ui label">当前第 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</a>
				    <a class="ui label"  onclick="pageClick(1)">首页</a>
				    <c:if test="${pageInfo.hasPreviousPage}">
				    	<a class="ui label"  onclick="pageClick(${pageInfo.pageNum-1})">上一页</a>
				    </c:if>
				    
				    <c:if test="${pageInfo.hasNextPage}">
				    	<a class="ui label"  onclick="pageClick(${pageInfo.pageNum+1})">下一页</a>
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
	        <button type="button" class="close" id="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">审核</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="oplogsAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">请输入密码</label>
			    <div class="col-sm-10">
			      <input type="password" name="merId" class="form-control" id="pwd">
			       <input type="hidden" name="id" class="form-control" id="id">
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" id="close1">关闭</button>
	        <button type="button" class="btn btn-primary" id="oplogs_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
    	
    
  //审核通过弹出框
		function  show(id){
	  		$("#id").val(id);
			$("#oplogsAddModal").modal({
				backdrop:"static"
			});
	 	}    
  //	点击保存
		$("#oplogs_save_btn").click(function(){
			if($("#pwd").val() =="654321"){
				updateShtg($("#id").val());
			}else{
	            alert("密码不正确！");
	            location.reload();
	        }
		});
 //点击关闭时  刷新页面
 		$("#close").click(function(){
	            location.reload();
		});
 		$("#close1").click(function(){
            location.reload();
		});
 
	    
  		//添加订单
        function addOrder(id){
        	$.ajax({
		        url: "../daifu/addOrderDaifu.do",
		        type: "POST",
		        data:{id:id},
		        async: false,
		        dataType: "json",
		        success: function (result) {
		        	if(result == "200"){
		        		alert("添加成功!");
			            location.reload();
		        	}else if(result == "1"){
		        		alert("该订单号已存在");
		        	}else if(result == "2"){
		        		alert("没有可用的渠道");
		        	}else if(result == "3"){
		        		alert("余额不足!");
		        	}else{
		        		alert("订单表参数不正确！");
		        		
		        	}
		        	
		            
		        },
		        error:function(jqXHR){
		            alert("发生错误："+ jqXHR.status);
		         }
		    })
        }
  		
    
    	//审核通过
        function updateShtg(id){
        	$.ajax({
		        url: "../daifu/updateShtg.do",
		        type: "POST",
		        data:{id:id},
		        dataType: "json",
		        success: function (result) {
		        	if(result.code == 100){
		        		 alert("审核成功!");
		        		 $("#merAccount_form").submit();
		        		// location.replace(location);
		        	}else{
		        		alert("审核失败!"+result.msg);
		        		$("#merAccount_form").submit();
		        		//location.replace(location);
		        	}
		           
		        }
		    })
        }
    	
    	//审核不通过
    	function updateShbtg(id){
        	$.ajax({
		        url: "../daifu/updateShbtg.do",
		        type: "POST",
		        data:{id:id},
		        dataType: "json",
		        success: function (result) {
		            alert("审核不通过!");
		            location.reload();
		        }
		    })
        }
    </script>
    
    
    	<script type="text/javascript">
	//分页函数
		function pageClick(page){
			$("#pa").val(page);
			$("#merAccount_form").submit();
		}
	</script>
    
</body>
</html>
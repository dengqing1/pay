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
<title>入金路由</title>
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
.addrujin{
	text-align:right;
}
</style>
</head>
<body>
	<!--用户添加的模态框 -->
	<div class="modal fade" id="routesAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加路由</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="routesAddModal_form">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">商户ID:</label>
			    <div class="col-sm-10">
			      <input type="text" name="merchantid" class="form-control" id="merchantId_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">商户名:</label>
			    <div class="col-sm-10">
			      <input type="text" name="merShortName" class="form-control" id="merShortName_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">银行名称:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="bankName" id="bankName_add_input" >
						<option value="">所有</option>
					</select>	
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">最小值:</label>
			    <div class="col-sm-10">
			      <input type="text" name="gt" class="form-control" id="lt_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">最大值:</label>
			    <div class="col-sm-10">
			      <input type="text" name="lt" class="form-control" id="gt_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">累计金额:</label>
			    <div class="col-sm-10">
			      <input type="text" name="accumulative" class="form-control" id="accumulative_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道简称:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="channelabbr" id="channelAbbr_add_input">
			      		<option>--请选择--</option>
			      		<c:forEach items="${npayChannelslist}" var="item" varStatus="status1">
								<option value="${item.channelMerAbbr}" <c:if test="${bank==item.channelMerAbbr}">selected="true"</c:if>>${item.channelMerAbbr}</option>
						</c:forEach>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道商户号:</label>
			    <div class="col-sm-10" id="channelMerId_div">
			    	<select class="form-control" name="channelmerid" id="channelMerId_add_input" >
						<option>所有</option>
					</select>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道名:</label>
			    <div class="col-sm-10">
			      <input type="text" name="name" class="form-control" id="name_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">网关:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="routesGateway" id="gateway">
			      		<option>--请选择--</option>
			      		<c:forEach items="${gatewayList}" var="item" varStatus="status1">
								<option value="${item.gateway}" <c:if test="${bank==item.gateway}">selected="true"</c:if>>${item.gateway}</option>
						</c:forEach>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">银行卡类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="routesCardType">
			      		<option>--请选择--</option>
			      		<option value="debit">借记卡</option>
				        <option value="credit">信用卡</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			 <!--  <div class="form-group">
			    <label class="col-sm-2 control-label">支付网关:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="gateway" id="gateway_add_input">
			      		<option>--请选择--</option>
			      		<option value="bank">网银</option>
				        <option value="kuaijie">快捷</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">银行卡类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="cardType" id="cardTyper_add_input">
			      		<option>--请选择--</option>
			      		<option value="debit">借记卡</option>
				        <option value="credit">信用卡</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div> -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">费率类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="feeType" id="feeType_add_input">
			      		<option>--请选择--</option>
			      		<option value="percent">百分比</option>
				        <option value="fix">固定金额</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">费率:</label>
			    <div class="col-sm-10">
			      <input type="text" name="feeAmount" class="form-control" id="name_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="routes_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!--用户编辑的模态框 -->
	<div class="modal fade" id="routeseditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改路由</h4>
	      </div>
	      <div class="modal-body">
	        <form class="form-horizontal" id="routeseditModal_form">
	          <input type="hidden" name="id" id="id_update_input"/>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">商户ID:</label>
			    <div class="col-sm-10">
			      <input type="text" name="merchantid" class="form-control" readonly="readonly" id="merchantId_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			 
			      <input type="hidden" name="fid" class="form-control" id="fid">
			  
			  
			  
			  <!-- <div class="form-group">
			    <label class="col-sm-2 control-label">商户名:</label>
			    <div class="col-sm-10">
			      <input type="text" name="merShortName" class="form-control" id="merShortName_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div> -->
			  <!-- <div class="form-group">
			    <label class="col-sm-2 control-label">银行ID:</label>
			    <div class="col-sm-10">
			      <input type="text" name="bankId" class="form-control" id="bankId_add_input">
			      <span class="help-block"></span>
			    </div>
			  </div> -->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">银行名称:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="bankName" id="bankName_update_input" >
						<option value="">所有</option>
						<%-- <c:forEach items="${Banklist}" var="item" varStatus="status1">
								<option value="${item.bankName}" <c:if test="${bank==item.bankName}">selected="true"</c:if>>${item.bankName}</option>
						</c:forEach> --%>
					</select>	
			      <!-- <input type="text" name="bankName" class="form-control" id="bankName_add_input">
			      <span class="help-block"></span> -->
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">最小值:</label>
			    <div class="col-sm-10">
			      <input type="text" name="gt" class="form-control" id="lt_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">最大值:</label>
			    <div class="col-sm-10">
			      <input type="text" name="lt" class="form-control" id="gt_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">累计金额:</label>
			    <div class="col-sm-10">
			      <input type="text" name="accumulative" class="form-control" id="accumulative_edit_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道简称:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="channelabbr" id="channelAbbr_update_input">
			      		<option>--请选择--</option>
			      		<c:forEach items="${npayChannelslist}" var="item" varStatus="status1">
								<option value="${item.channelMerAbbr}" <c:if test="${bank==item.channelMerAbbr}">selected="true"</c:if>>${item.channelMerAbbr}</option>
						</c:forEach>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">渠道商户号:</label>
			    <div class="col-sm-10" id="channelMerId_div">
			    	<select class="form-control" name="channelmerid" id="channelMerId_update_input" >
						<option>所有</option>
					</select>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  <div class="form-group">
			    <label class="col-sm-2 control-label">网关:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="routesGateway" id="routesGateway_update_input">
			      		<option>--请选择--</option>
			      		<c:forEach items="${gatewayList}" var="item" varStatus="status1">
								<option value="${item.gateway}" <c:if test="${bank==item.gateway}">selected="true"</c:if>>${item.gateway}</option>
						</c:forEach>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">路由银行卡类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="routesCardType" id="routesCardType_update_input">
			      		<option>--请选择--</option>
			      		<option value="debit">借记卡</option>
				        <option value="credit">信用卡</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			  
			  <!-- <div class="form-group">
			    <label class="col-sm-2 control-label">渠道名:</label>
			    <div class="col-sm-10">
			      <input type="text" name="name" class="form-control" id="name_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div> 
			  <div class="form-group">
			    <label class="col-sm-2 control-label">支付网关:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="gateway" id="gateway_update_input">
			      		<option>--请选择--</option>
			      		<option value="bank">网银</option>
				        <option value="kuaijie">快捷</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">银行卡类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="cardType" id="cardType_update_input">
			      		<option>--请选择--</option>
			      		<option value="debit">借记卡</option>
				        <option value="credit">信用卡</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>-->
			  <div class="form-group">
			    <label class="col-sm-2 control-label">费率类型:</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="feeType" id="feeType_update_input">
			      		<option>--请选择--</option>
			      		<option value="percent">百分比</option>
				        <option value="fix">固定金额</option>
			      </select>	
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">费率:</label>
			    <div class="col-sm-10">
			      <input type="text" name="feeAmount" class="form-control" id="feeAmount_update_input">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  
			  
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="routes_update_btn">跟新</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 搭建显示页面 -->
	<div class="container" style="width: 100%;">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>路由列表</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<form id="mer_form">
			<input id="pa" type="hidden" name="page"  />
			<div class="col-md-11 addrujin">
				<button type="button" id="routes_add_btn" class="btn btn-primary">新增路由</button>
			</div>
		</form>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
					<tr>
		               <th>ID</th>
		               <th>商户ID</th>
		               <th>网关</th>
		               <th>银行ID</th>
		               <th>银行名称</th>
		               <th>最小值</th>
		               <th>最大值</th>
		               <th>渠道简称</th>
		               <th>渠道商户号</th>
		               <!-- <th>渠道名</th> -->
		               <th>操作</th>
		             </tr>
					<c:forEach items="${list}" var="l" varStatus="state">
		                <tr>
		                     <td align="center">${l.id}</td>
		                     <td align="center">${l.merchantId}</td>
		                     <td align="center">${l.gateway}</td>
		                     <td align="center">${l.bankId}</td>
		                     <td align="center">所有银行</td>
		                     <td align="center">${l.min}</td>
		                     <td align="center">${l.max}</td>
		                     <td align="center">${l.channelAbbr}</td>
		                     <td align="center">${l.channelMerId}</td>
		                     <%-- <td align="center">${l.name}</td> --%>
		                     <td width="10%" align="center">
		                        <a onclick="updateRouter(${l.merchantId},'${l.channelAbbr}','${l.channelMerId}','${l.routes_gateway}','${l.routes_card_type}')">修改</a>
		                      </td>
		                  </tr>
		             </c:forEach>
				</table>
				<!-- 分页 -->
				<div class="ui circular labels" style="text-align:center">
				    <a class="ui label">当前第 ${pageInfo.pageNum }页,总${pageInfo.pages }页,总 ${pageInfo.total } 条记录</a>
				    <a class="ui label" href="../routes/routeslist.do?page=1">首页</a>
				    <c:if test="${pageInfo.hasPreviousPage}">
				    	<a class="ui label" href="../routes/routeslist.do?page=${pageInfo.pageNum-1}">上一页</a>
				    </c:if>
				    
				    <c:if test="${pageInfo.hasNextPage}">
				    	<a class="ui label" href="../routes/routeslist.do?page=${pageInfo.pageNum+1}">下一页</a>
				    </c:if>
				    <a class="ui label" href="../routes/routeslist.do?page=${pageInfo.pages}" >末页</a>
				</div>
				<!-- 分页 end-->
			</div>
		</div>
	</div>
	<script src="../static/admin/js/bootbox.js"></script>
    <script type="text/javascript">
    
		//新增弹出框
		$("#routes_add_btn").click(function(){
			$("#routesAddModal").modal({
				backdrop:"static"
			});
		});
		
		//修改
		$("#channelAbbr_update_input").change(function(){
			var channelAbbr = $("#channelAbbr_update_input").val();
			 $.ajax({
	   		        url: "../routes/routesEdit.do",
	   		        type: "GET",
	   		        data:{channelAbbr:channelAbbr},
	   		        dataType: "json",
	   		        success: function (result) {
			   		    	 var arr = eval(result.object);
			                //遍历返回的json数据加载到select标签;
			                var str="";
			                $.each(arr, function(key,value) {
			                	str += "<option value= "+ value.channelMerId +">"+ value.channelMerId +"</option>";
			                });
			                document.getElementById("channelMerId_update_input").innerHTML = str;
	   		        }
	   		    })
		});
		
		
		//修改
		$("#channelAbbr_add_input").change(function(){
			var channelAbbr = $("#channelAbbr_add_input").val();
			 $.ajax({
	   		        url: "../routes/routesEdit.do",
	   		        type: "GET",
	   		        data:{channelAbbr:channelAbbr},
	   		        dataType: "json",
	   		        success: function (result) {
			   		    	 var arr = eval(result.object);
			                //遍历返回的json数据加载到select标签;
			                var str="";
			                $.each(arr, function(key,value) {
			                	str += "<option value= "+ value.channelMerId +">"+ value.channelMerId +"</option>";
			                });
			                document.getElementById("channelMerId_add_input").innerHTML = str;
	   		        }
	   		    })
		});
		
		//保存
		$("#routes_save_btn").click(function(){
			 $.ajax({
   		        url: "../history/saveRoutes.do",
   		        type: "POST",
   		        data:$("#routesAddModal_form").serialize(),
   		        dataType: "json",
   		        success: function (result) {
   		        	if(result.code == 100){
	   		            alert("保存成功!");
	   		            location.reload();
   		        	}else{
   		        		alert("该商户已配置路由");
   		        	}
   		        }
   		    })
		});
		
		//修改
		$("#routes_update_btn").click(function(){
			 $.ajax({
   		        url: "../history/updateRoutes.do",
   		        type: "POST",
   		        data:$("#routeseditModal_form").serialize(),
   		        dataType: "json",
   		        success: function (result) {
   		        	if(result.code == 100){
	   		            alert("保存成功!");
	   		            location.reload();
   		        	}else{
   		        		alert("该商户已配置路由");
   		        	}
   		        }
   		    })
		});
		
		
		//修改弹出框
		function updateRouter(merchantId,channelAbbr,channelMerId,routes_gateway,routes_card_type){
			$.ajax({
				url:"../routes/updateRouter.do?merchantId="+merchantId+"&channelAbbr="+channelAbbr+"&channelMerId="+channelMerId+
						"&routesGateway="+routes_gateway+"&routesCardType="+routes_card_type,
				type:"GET",
				success:function(result){
					var id = result.object.channelMerId;
					$("#merchantId_update_input").val(result.object.merchantId);
					$("#lt_update_input").val(result.object.min);
					$("#gt_update_input").val(result.object.max);
					$("#channelAbbr_update_input").val(result.object.channelAbbr);
					str = "<option value= "+ result.object.channelMerId +">"+ result.object.channelMerId +"</option>";
					document.getElementById("channelMerId_update_input").innerHTML = str;
					$("#id_update_input").val(result.object.id);
					$("#gateway_update_input").val(result.object.gateway);
					$("#cardType_update_input").val(result.object.card_type);
					$("#feeType_update_input").val(result.object.fee_type);
					$("#feeAmount_update_input").val(result.object.fee_amount);
					$("#routesGateway_update_input").val(result.object.routes_gateway);
					$("#routesCardType_update_input").val(result.object.routes_card_type);
					$("#accumulative_edit_input").val(result.object.accumulative);
					$("#fid").val(result.object.fid);
					
					
				}
			});
			$("#routeseditModal").modal({
				backdrop:"static"
			});
		}
		
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="../js/jquery/jquery-3.1.1.min.js"></script>
<link href="../static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="../static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../new/laydate/laydate.js"></script>
<link href="../static/admin/css/reset.css" rel="stylesheet">
<title>交易查询</title>
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
#mer_form>div{
	margin-top:15px;
}
#order_form> span{
	display:inline-block;
	padding-left:10px;
}
.mt15{
	margin-top:15px;
}
</style>
</head>
<body>
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
			<div class="col-md-12">
				<form id="order_form" class="clearfix">
				    
					<div class="col-md-4">
						<span>商户号:</span>
						<input type="text" name="merchantId" value="${merchantId}" id="merchantId" class="form-control col-md-10" placeholder="请输入商户号">
					</div>
					<div class="col-md-4">
						<span>平台订单号:</span>
						<input type="text" name="orderId" id="orderId" value="${orderId}" class="form-control" placeholder="请输入平台订单号">
					</div>
					<input id="pa" type="hidden" name="page" value="1"/>
					<div class="col-md-4">
						<span>渠道类型:</span>
						<select class="form-control" id="selec_abbr">
				      		<option value="">所有</option>
				      		<c:forEach items="${npayChannelslist}" var="item" varStatus="status1">
									<option value="${item.channelMerAbbr}" <c:if test="${abbr == item.channelMerAbbr}">selected="true"</c:if>>${item.channelMerAbbr}</option>
							</c:forEach>
				      </select>	
					</div>
					<div class="col-md-4 mt15">
						<span style="display:block">交易日期:</span>
		                    <input class="form-control" readonly="readonly" type="text" value="${createStateAt}" style="width:250px;display:inline-block;" name="input_merId" id="input_merId"/>
		                    <span style="font-weight:bolder;">-</span>
		                    <input class="form-control" readonly="readonly" type="text" value="${createEndAt}" style="width:250px;display:inline-block;" name="input_endmerId" id="input_endmerId"/>
					</div>
					<div class="col-md-4 mt15">
						<span>产品类型:</span>
						<select class="form-control" id="select_cplx">
							<option value="">所有</option>
							<option value="bank" <c:if test="${gateway=='bank'}">selected="true"</c:if>>网银</option>
							<option value="kuaijie" <c:if test="${gateway=='kuaijie'}">selected="true"</c:if>>快捷</option>
							<option value="daifu" <c:if test="${gateway=='daifu'}">selected="true"</c:if>>代付</option>
						</select>
					</div>
					<div class="col-md-4 mt15">
						<span>交易金额:</span>
						<input type="text" name="txnAmt" id="txnAmt" value="${txnAmt}" class="form-control" placeholder="请输入交易金额">
					</div>
					
					<div class="col-md-4 mt15">
						<span>商户订单号:</span>
						<input type="text" name="merOrderId" id="merOrderId" value="${merOrderId}" class="form-control" placeholder="请输入商户订单号">
					</div>
					<div class="col-md-4 mt15">
						<span>交易状态:</span>
						<select class="form-control" id="select_status">
							<option value="">所有</option>
							<option value="1001" <c:if test="${status=='1001'}">selected="true"</c:if>>支付成功</option>
							<option value="1002" <c:if test="${status=='1002'}">selected="true"</c:if>>交付失败</option>
							<option value="1000" <c:if test="${status=='1000'}">selected="true"</c:if>>待支付</option>
						</select>
					</div>
					<div class="col-md-4 mt15">
						<span>银行:</span>
						<select class="form-control" id="select_bank" >
							<option value="">所有</option>
							<c:forEach items="${Banklist}" var="item" varStatus="status1">
									<option value="${item.bankName}" <c:if test="${bank==item.bankName}">selected="true"</c:if>>${item.bankName}</option>
							</c:forEach>
						</select>
					</div>
				</form>
					<div class="col-md-1 f-r mt15">
						<button type="button" onclick="search()" class="btn btn-primary">查询</button>
					</div>
					<!-- <div class="col-md-1 f-r mt15">
						<button class="btn btn-primary" id="export">导出所有商户</button>
					</div> -->
					<!-- 显示表格数据 -->
					<div class="row">
						<div class="col-md-12">
							<table class="table table-hover" cellspacing=1 cellpadding=5 width=100%>
								<tr>
					               <th>订单日期</th>
					               <th>平台订单号</th>
					               <th>商户号</th>
					               <th>商户名称</th>
					               <th width="3%">商户订单号</th>
					               <th width="6%">订单金额(元)</th>
					               <th>手续费额(元)</th>
					               <th>产品类型</th>
					               <th>银行</th>
					               <th>通道简称</th>
					               <th>通道名</th>
					               <th>通道状态码</th>
					               <th>状态详情</th>
					             </tr>
								<c:forEach items="${list}" var="l" varStatus="state">
					                <tr>
					                     <td align="center">${l.create_at}</td>
					                     <td align="center">${l.orderId}</td>
					                     <td align="center">${l.merchantId}</td>
					                     <td align="center">${l.mer_name}</td>
					                     <td align="center">${l.merOrderId}</td>
					                     <td align="center">${l.txnAmt}</td>
					                     <td align="center">${l.in_fee}</td>
					                     <c:if test="${l.gateway=='bank'}">
					                     	<td align="center">网银</td>
					                     </c:if>
					                     <c:if test="${l.gateway=='kuaijie'}">
					                     	<td align="center">快捷</td>
					                     </c:if>
					                     <c:if test="${l.gateway=='daifu'}">
					                     	<td align="center">代付</td>
					                     </c:if>
					                     <td align="center">${l.bank_name}</td>
					                     <td align="center">${l.channel_mer_abbr}</td>
					                     <td align="center">${l.name}</td>
					                     <td align="center">${l.notifytimes}</td>
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
			</div>
		</div>
		<script src="../static/admin/js/bootbox.js"></script>
		<script type="text/javascript">
			//查询
			function search(){
				var page = $("#pa").val();
	  			var abbr = $("#selec_abbr").val();
	  			var cplx = $("#select_cplx").val();
	  			var bank = $("#select_bank").val();
	  			var txnAmt = $("#txnAmt").val();
	  			var merchantId = $("#merchantId").val();
	  			var orderId = $("#orderId").val();
	  			var merOrderId = $("#merOrderId").val();
	  			var createStateAt = $("#input_merId").val();
	  			var createEndAt = $("#input_endmerId").val();
	  			var status = $("#select_status").val();
	  			location.href="orderQuery.do?merchantId="+merchantId+'&orderId='+orderId+'&merOrderId='+merOrderId
	  			+'&abbr='+abbr+'&bank='+bank+'&txnAmt='+txnAmt+'&createStateAt='+createStateAt+'&createEndAt='+createEndAt+'&page='+page
				+'&gateway='+cplx+'&status='+status
			}
		
			//分页函数
	  		function pageClick(page){
	  			$("#pa").val(page);
	  			search();
	  		}
		
	  		//导出
      		$("#export").click(function(){
      			 location.href="../channel/export.do";
      		});
			
			laydate.render({
			  elem: '#input_merId',
              type: 'datetime'
			});
			laydate.render({
			  elem: '#input_endmerId',
              type: 'datetime'
			});
		</script>
	
</body>
</html>
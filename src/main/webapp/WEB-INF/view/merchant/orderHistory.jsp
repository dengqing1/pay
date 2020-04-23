<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8">
	<title>欢迎 - 支付平台 - ACE 3</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link href="./merchant/css/normalize.css" rel="stylesheet">
	<link href="./merchant/css/header.css" rel="stylesheet">
	<link href="./merchant/css/page.css" rel="stylesheet">
	<link href="./merchant/css/liMarquee.css" rel="stylesheet">
	<link href="./merchant/css/tradeQuery.css?20190109" rel="stylesheet">
	<link href="./merchant/css/date.css" rel="stylesheet">
	<link href="./merchant/css/daterangepicker.min.css" rel="stylesheet">
	
	<script src="./merchant/js/jquery-1.8.3.min.js"></script>
	<script src="./merchant/js/ckeditor.js"></script>
	<script src="./merchant/js/currentAccount.js?20190109"></script>
	
	<link href="//cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.css" rel="stylesheet">
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/moment.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.js"></script>
	
	
	<script src="./merchant/js/bootstrap.min.js"></script>
	<script src="./merchant/js/bootstrap-datepicker.js"></script>
	<script src="./merchant/js/orderHistory.js"></script>
	<script src="./merchant/js/logout.js"></script>	
	</head>
	
<style>
    .daterangepicker {
        display: none;
    }
    .daterangepicker .input-mini {
        width: 200px;
    }
    .daterangepicker .calendar th, .daterangepicker .calendar td {
        min-width: auto;
    }
</style>
	
	
	
<body>
<div class="top-header">
	<div class="header-container flex flex-pack-justify">
		<div class="header-left">
			<label class="big-title">丰迈</label>
<!-- 			<label class="beta-label">BETA</label> -->
		</div>
		<div class="header-right flex flex-align-center">
			<p class="header-right-welcome">欢迎&nbsp;${user.name}</p>
			<a class="header-right-logout" href="javascript:void(0);">退出</a>
		</div>
	</div>
</div>
<div class="main">
    <div class="flex">
		<div class="main-logo">
			<label class="header-title">支付平台</label>
			<label class="header-english">PAYMENT PLATFORM</label>
		</div>
		<div class="main-logo">
		</div>
    </div>
	<div class="flex-v">
        <label class="separate-line"></label>
	</div>
	<div class="main-content flex"> 		<div class="main-left" style="height: 594px;">

<!--   		  <div class="first-header flex flex-align-center"> -->
<!--                <div class="first-header-dot"></div> -->
<!--                <a class="first-header-text" href="/index/channel?name=merchant">系统管理</a> -->
<!--            </div> -->
           
         	<div class="first-header flex flex-align-center">
	            <div class="first-header-dot"></div>
	            <a class="first-header-text" href="javascript:void(0);">欢迎</a>
        	</div>
        	
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./merchant.html">欢迎</a>
          </div>
          
         <div class="second-header flex flex-align-center ">
	          <div class="second-header-dot"></div>
	          <a class="second-header-text" href="./user.html">修改密码</a>
         </div>
              <div class="first-header flex flex-align-center">
         <div class="first-header-dot"></div>
       		<a class="first-header-text" href="javascript:void(0);">账务</a>
    	 </div>
    	 
    	 
         <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               	<a class="second-header-text" href="./moneyHistory.html">钱流水</a>
      	 </div>
      	 
      	 
          <div class="second-header flex flex-align-center second-header-select">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./orderHistory.html">交易查询</a>
          </div>
          
          
          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./withdrawHistory.html">账务查询</a>
          </div>

          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./balanceLogs.html">调账查询</a>
          </div>          
          
           <div class="second-header flex flex-align-center ">
               
          <div class="first-header-dot"></div>
            <a class="first-header-text" href="javascript:void(0);">代付</a>
       	  </div>
       	  
       	  
           <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifu.html">批量代付</a>
           </div>
           
			<div class="second-header flex flex-align-center  ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./daifuStatement.html">账目查询</a>
            </div> 
        </div>

<div class="main-right">
	<div class="right-container flex flex-align-center">
		<p class="right-header-title">交易查询</p>
		<p class="right-header-separate"></p>
		<p class="right-header-title">TRADING QUERY</p>
	</div>
	<div class="right-separate"></div>
	<div class="options-container">
		<div class="options-container-content">
			<form action="" method="get" id="search-form" novalidate="novalidate">
			<div class="options-th-container flex flex-align-center">
				<p class="options-title left-position">交易日期</p>
				<p class="options-title middle-position">产品类型</p>
				<p class="options-title right-position">交易金额</p>
			</div>
			<div class="options-th-container flex">
				<input type="text" name="date_range" id="daterangepicker"
				 class="options-trade-time  left-position" readonly="readonly" value="">
				 
				<select class="options-select-container middle-position" id="gateway">
                        <option value="0">所有</option>
                        <option value="bank" >网银</option>
                        <option value="daifu">代付</option>
                        <option value="qqpay">QQ支付</option>
                        <option value="jdpay">京东支付</option>
                        <option value="wxpay">微信支付</option>
                        <option value="alipay">支付宝支付</option>
				</select>
				<input type="text" class="right-position options-input options-input-trade-money" value="" name="amount" id="txnAmts" />
			</div>
				<div class="options-th-container flex">
					<p class="options-title left-position">商户订单号</p>
					<p class="options-title middle-position">平台订单号</p>
				</div>
				<div class="options-th-container flex">
					<input type="text" class="left-position options-input options-input-order" value="" name="mer_order_id" id="merOrderId" />
					<input type="text" class="left-position options-input options-input-order" value="" name="order_id" id="orderid" />
				</div>
				
			<div class="options-th-container flex">
				<p class="options-title middle-position">交易状态</p>
				<p class="options-title right-position">银行</p>
			</div>
			<div class="options-th-container flex">
				<select class="options-select-container middle-position" id="status">
					<option value="0">所有</option>
					<option value="1000" >初始态</option>
					<option value="1001" >交易成功</option>
					<option value="1002" >交易失败</option>
					<option value="1111" >进行中</option>
				</select>
				<select class="options-select-container middle-position" id="bankid">
                    <option value="0">所有</option>
                   	<c:forEach items="${banks}" var="obj"  >	
                     	  <option value="${obj.bankId}">${obj.bankName}</option>
					</c:forEach>
				</select>
				<input type="hidden" name="search" value="1">
				<input id="task_type" type="hidden" name="task" value="">
				<a class="search-btn trade-btn" href="javascript:void(0);" >搜索</a>
				<a class="export-btn trade-btn" href="javascript:void(0);" >导出Excel</a>
			</div>
			</form>
		</div>
		<p class="options-tip">注意导出数据最多支持50000条</p>
	</div>

	<div class="content-container">
		<div class="top-container"></div>
		<div class="ace-widget-content">
			<table id="user-grid" class="table table-striped ace-grid" style="min-width: 765px;">
				<thead>
					<tr>
						<th><span>订单日期</span></th>
						<th><span>平台订单号</span></th>
						<th><span>商户号</span></th>
						<th><span>商户名称</span></th>
						<th><span>商户订单号</span></th>
						<th><span>订单金额（元）</span></th>
						<th><span>手续费（元）</span></th>
						<th><span>产品类型</span></th>
						<th><span>银行</span></th>
						<th><span>状态</span></th>
						<th><span>状态详情</span></th>
					</tr>
				</thead>
				<tbody id="list">
					<c:forEach items="${list.rows}" var="obj" varStatus="index"  >	
						<tr>
							<td>${obj.createTime}</td>
							<td>${obj.orderid}</td>
							<td>${obj.merchantid}</td>
							<td>${obj.msName}</td>
							<td>${obj.merorderid}</td>
							<td>${obj.txnAmts}</td>
							<td>${obj.inFee}</td>
							<td>${obj.types}</td>
							<td>${obj.bankName}</td>
							<c:choose>
						      <c:when test="${obj.status == 1000 }">
						      	<td><span class="table-grid-status table-grid-gray-status">初始状态</span></td>
						      </c:when>
						      <c:when test="${obj.status == 1001 }">
								<td><span class="table-grid-status table-grid-light-status">交易成功</span></td>
						      </c:when>
						      <c:when test="${obj.status == 1002 }">
								<td><span class="table-grid-status table-grid-gray-status">交易失败</span></td>
						      </c:when>
						      <c:when test="${obj.status == 1111 }">
								<td><span class="table-grid-status table-grid-gray-status">进行中</span></td>
						      </c:when>
						      <c:otherwise>
								<td><span class="table-grid-status table-grid-gray-status">其他</span></td>
						      </c:otherwise>
							</c:choose> 
							<td>${obj.statusdesc}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
			<div class="page-container flex flex-align-center">
				<p class="page-desc" id="number">当前页 ${list.pageNum}/${list.pages} 总页数 </p>
				<a id="first-page"  class="first-page gray-icon-page" href="javascript:void(0);" onclick="firstPage()"></a>
				<a id="previous-page"  class="previous-page gray-icon-page"  href="javascript:void(0);" onclick="previousPage()"></a>
				<form id='form1' action='/pay/money-history?page=1' method='get'>
					<input class="page-num" id="search"  name="page" type="text" value='${list.pageNum}' />
				</form>
				
			<c:choose>
			      <c:when test="${list.pages == 1}">
			      	<a id="last-page" class="last-page gray-icon-page" href="javascript:void(0);" onclick="nextPage()"></a>
					<a id="next-page"  class="next-page gray-icon-page" href="javascript:void(0);" onclick="lastPage(${list.pages})"></a>
			      </c:when>
			      <c:otherwise>
						<a id="last-page" class="last-page" href="javascript:void(0);" onclick="nextPage()"></a>
						<a id="next-page"  class="next-page" href="javascript:void(0);" onclick="lastPage(${list.pages})"></a>
			      </c:otherwise>
			</c:choose> 
				
			</div>
			<input id="pageNum" type="hidden" value='${list.pageNum}' />
			<input id="pages" type="hidden" value='${list.pages}' />
			</div>

			<br/>
		<div class="content-container">
			<div class="top-container"></div>
			<div class="ace-widget-content">
				<table id="user-grid" class="table table-striped ace-grid" style="min-width: 765px;">
					<thead>
					<tr>
						<th><span></span></th>
						<th><span>交易金额(元)</span></th>
						<th><span>手续费额(元)</span></th>
						<th><span>笔数</span></th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<td>总计(全部)</td>
							<td>${all.txnAmts}</td>
							<td>${all.inFee}</td>
							<td>${all.count}</td>
						</tr>
						<tr>
							<td>总计(初始状态)</td>
							<td>${status.txnAmts}</td>
							<td>${status.inFee}</td>
							<td>${status.count}</td>
						</tr>
						<tr>
							<td>总计(成功)</td>
							<td>${success.txnAmts}</td>
							<td>${success.inFee}</td>
							<td>${success.count}</td>
						</tr>
						<tr>
							<td>总计(失败)</td>
							<td>${error.txnAmts}</td>
							<td>${error.inFee}</td>
							<td>${error.count}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

    </div>
</div>
</body>
</html>
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
<!-- 	<link href="./merchant/css/systemReconciliation.css" rel="stylesheet"> -->
	
	<script src="./merchant/js/jquery-1.8.3.min.js"></script>
	<script src="./merchant/js/ckeditor.js"></script>
	<script src="./merchant/js/currentAccount.js?20190109"></script>
	
	<link href="//cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.css" rel="stylesheet">
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/moment.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap-daterangepicker/2.1.18/daterangepicker.min.js"></script>
	
	
	<script src="./merchant/js/bootstrap.min.js"></script>
	<script src="./merchant/js/bootstrap-datepicker.js"></script>
	<script src="./merchant/js/balanceLog.js"></script>
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
    .left-position {
  	  width: 350px;
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
	<div class="main-content flex">

 		<div class="main-left" style="height: 594px;">

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
      	 
      	 
          <div class="second-header flex flex-align-center ">
               <div class="second-header-dot"></div>
               <a class="second-header-text" href="./orderHistory.html">交易查询</a>
          </div>
          
          
          <div class="second-header flex flex-align-center ">
                <div class="second-header-dot"></div>
                <a class="second-header-text" href="./withdrawHistory.html">账务查询</a>
          </div>
          
          <div class="second-header flex flex-align-center  second-header-select ">
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
<!--         <div class="right-container flex flex-align-center">
            <p class="right-header-title">调账记录查询</p>
            <p class="right-header-separate"></p>
        </div> -->
        <div class="right-separate"></div>
	<div class="options-container">
		<div class="options-container-content">
			<form action="" method="get" id="search-form" novalidate="novalidate">
				<div class="options-th-container flex flex-align-center">
					<p class="options-title left-position">交易日期</p>
				</div>
				<div class="options-th-container flex">
					<input type="text" name="date_range" id="daterangepicker"
					 class="options-trade-time  left-position" readonly="readonly" value="">
                    <a class="search-btn trade-btn" href="javascript:void(0);" >搜 索</a>
				</div>
			</form>
		</div>
	</div>
	<div class="content-container">
		<div class="top-container"></div>
		<div class="ace-widget-content">
			<table id="user-grid" class="table table-striped ace-grid" style="min-width: 765px;">
				<thead>
					<tr>
                        <th><span>创建时间</th>
                        <th><span>金额(元)</th>
                        <th><span>类型</th>
					</tr>
				</thead>
				<tbody id="list">
					<c:forEach items="${list.rows}" var="obj" varStatus="index"  >	
						<tr>
							<td>${obj.createTime}</td>
							<td>${obj.txnAmts}</td>
							<c:choose>
						      <c:when test="${obj.opType == 1 }">
						      	<td>冻结</td>
						      </c:when>
						      <c:when test="${obj.opType == 2 }">
								<td>解冻</td>
						      </c:when>
						      <c:when test="${obj.opType == 3 }">
								<td>加钱</td>
						      </c:when>
						      <c:when test="${obj.opType == 4 }">
								<td>减少</td>
						      </c:when>
						      <c:otherwise>
								<td>未知</td>
						      </c:otherwise>
							</c:choose> 
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


	</div>

    </div>
</div>
</body>
</html>